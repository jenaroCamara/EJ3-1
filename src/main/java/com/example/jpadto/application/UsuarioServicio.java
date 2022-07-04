package com.example.jpadto.application;

import com.example.jpadto.persona.infraestructure.dto.output.outputDTOpersonafull;
import com.example.jpadto.exceptions.BeanNotFoundException;
import com.example.jpadto.exceptions.UnprocesableException;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.persona.domain.Persona;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.persona.infraestructure.repository.PersonaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio implements UsuarioServicioInterface {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager em;

    private void comprobaciones(InputDTOPersona persona) {
        String auxName = persona.getUsuario();
        if (auxName.length() < 6 || auxName.length() > 16) {
            throw new BeanNotFoundException("Error al guardar el usuario");
        }
        if (persona.getUsuario() != null && persona.getProfesor() != null) {
            throw new BeanNotFoundException("Error, el usuario no puede ser 2 cosas a la vez");
        }
        if (persona.getUsuario() == null && persona.getProfesor() == null) {
            throw new BeanNotFoundException("Error, el usuario puede ser solo una cosa");
        }
    }

    public OutputDTOPersona guardar(InputDTOPersona persona) throws Exception {
        comprobaciones(persona);
        return modelMapper.map(personaRepositorio.save(modelMapper.map(persona, Persona.class)), OutputDTOPersona.class);
    }

    public List<Object> getUsuarios(String queryParam) {
        Iterable<Persona> iterableUsuario = personaRepositorio.findAll();
        List<Object> ListaDTOs = new ArrayList<>();
        iterableUsuario.forEach((Usuario) -> {
            ListaDTOs.add(modelMapper.map(Usuario, outputDTOpersonafull.class));
        });
        if (queryParam.equals("full")) {
            List<Object> secondList = ListaDTOs.stream()
                    .map(Usuario -> modelMapper.map(Usuario, OutputDTOPersona.class))
                    .collect(Collectors.toList());
            return (secondList);
        }
        return ListaDTOs;
    }

    public List<OutputDTOPersona> getUsuariosByNameCriteria(String nombre, String fecha) {
        HashMap<String, Object> data=new HashMap<>();
        //en el hasmap comprobaré solo el nombre, pero podría añadir mas condiciones
        if(nombre!=null){
            data.put("name",nombre);
            data.put("created_date",fecha);
        }
        //obtenemos la lista del repositorio, y la sacamos como outputdtoPersona
        List<Persona> lista = getData(data);
        List<OutputDTOPersona> resultado = lista.stream()
                .map(Usuario -> modelMapper.map(Usuario, outputDTOpersonafull.class))
                .collect(Collectors.toList());
        return resultado;
    }

    public List<Object> getUsuariosByName(String nombre, String queryParam) {
        List<Persona> lista = personaRepositorio.findByName(nombre);
        List<Object> secondList = new ArrayList<>();
        if (queryParam.equals("full")) {
            secondList = lista.stream()
                    .map(Usuario -> modelMapper.map(Usuario, outputDTOpersonafull.class))
                    .collect(Collectors.toList());
            return (secondList);
        } else {
            secondList = lista.stream()
                    .map(Usuario -> modelMapper.map(Usuario, OutputDTOPersona.class))
                    .collect(Collectors.toList());
        }
        return secondList;
    }

    public outputDTOpersonafull getUserById(String id) throws Exception {
        Persona persona = personaRepositorio.findById(Integer.parseInt(id)).orElseThrow(() -> new BeanNotFoundException("Usuario no encontrado"));
        outputDTOpersonafull prueba = modelMapper.map(persona, outputDTOpersonafull.class);
        return prueba;
    }

    public InputDTOPersona actualiza(@RequestBody InputDTOPersona usuario) throws Exception {
        Persona persona = personaRepositorio.findById(usuario.getId()).orElseThrow(() -> new Exception("Usuario no encontrado"));
        personaRepositorio.save(modelMapper.map(persona, Persona.class));
        return usuario;
    }

    public void deleteById(String id) throws Exception {
        if (!personaRepositorio.findById(Integer.parseInt(id)).isPresent()) {
            throw new UnprocesableException("Usuario no encontrado");
        }
        if(personaRepositorio.findById(Integer.parseInt(id)).get().getProfesor()!=null || personaRepositorio.findById(Integer.parseInt(id)).get().getEstudiante()!=null){
            throw new UnprocesableException("No se puede eliminar porque tiene un profesor o estudiante asignado.");
        }
        personaRepositorio.deleteById(Integer.parseInt(id));
    }

    /*---------------------------CRITERIA-------------------*/
    public List<Persona> getData(HashMap<String, Object> data){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();
        data.forEach((field,value) ->
        {
            switch (field)
            {
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "created_date":
                    //case GREATER_THAN:
                    String hola = value.toString();
                    LocalDate date = LocalDate.parse(value.toString());
                    Date fecha = java.sql.Date.valueOf(date);
                    predicates.add(cb.greaterThan(root.<Date>get(field),fecha));
                    break;
//                    case LESS_THAN:
//                        predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
//                        break;
//                    case EQUAL:
//                        predicates.add(cb.equal(root.<Date>get(field),(Date)value));
//                        break;
            }

        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(query).getResultList();
    }
}
