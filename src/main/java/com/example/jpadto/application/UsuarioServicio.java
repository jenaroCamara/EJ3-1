package com.example.jpadto.application;

import com.example.jpadto.profesor.domain.Profesor;
import com.example.jpadto.student.domain.Student;
import com.example.jpadto.exceptions.BeanNotFoundException;
import com.example.jpadto.exceptions.UnprocesableException;
import com.example.jpadto.profesor.infraestructure.dto.input.InputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.persona.domain.Persona;
import com.example.jpadto.profesor.infraestructure.dto.output.OutputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import com.example.jpadto.student.infraestructure.repository.EstudianteRepositorio;
import com.example.jpadto.profesor.infraestructure.repository.ProfesorRepositorio;
import com.example.jpadto.persona.infraestructure.repository.PersonaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio implements UsuarioServicioInterface {

    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private ProfesorRepositorio profesorRepositorio;
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    public OutputDTOPersona guardar(InputDTOPersona persona) throws Exception {
        String auxName = persona.getUsuario();
        if (auxName.length() < 6 || auxName.length() > 16) {
            throw new BeanNotFoundException("Error al guardar el usuario");
        }
        return modelMapper.map(personaRepositorio.save(modelMapper.map(persona, Persona.class)), OutputDTOPersona.class);
    }

    public List<OutputDTOPersona> getUsuarios() {
        Iterable<Persona> iterableUsuario = personaRepositorio.findAll();
        List<OutputDTOPersona> ListaDTOs = new ArrayList<>();
        iterableUsuario.forEach((Usuario) -> {
            ListaDTOs.add(modelMapper.map(Usuario, OutputDTOPersona.class));
        });
        return ListaDTOs;
    }

    public List<OutputDTOPersona> getUsuariosByName(@PathVariable String nombre) {
        List<Persona> lista = personaRepositorio.findByName(nombre);
        return lista.stream()
                .map(Usuario -> modelMapper.map(Usuario, OutputDTOPersona.class))
                .collect(Collectors.toList());
    }

    public InputDTOPersona getUserById(String id) throws Exception {
        Persona persona = personaRepositorio.findById(Integer.parseInt(id)).orElseThrow(() -> new BeanNotFoundException("Usuario no encontrado"));
        return modelMapper.map(persona, InputDTOPersona.class);
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
        personaRepositorio.deleteById(Integer.parseInt(id));
    }

    //--------------------------Profesor----------------------

    public OutputDTOProfesor guardarProfesor(InputDTOProfesor profesor) throws Exception {
        Persona persona = personaRepositorio.findById(Integer.parseInt(profesor.getPersona())).orElseThrow(
                () -> new UnprocesableException("Error, el usuario con id: " + profesor.getId_profesor() + ", no se encuentra"));
        Profesor profesor1 = modelMapper.map(profesor, Profesor.class);
        profesor1.setPersona(persona);
        profesor1 = profesorRepositorio.save(profesor1);
        return modelMapper.map(profesor1, OutputDTOProfesor.class);
    }

    //---------------------------Estudiante--------------------
    public OutputDTOStudent guardarEstudiante(InputDTOStudent dtoStudent) {
        Persona persona = personaRepositorio.findById(dtoStudent.getPersona()).orElseThrow(
                () -> new UnprocesableException("Error, la persona con id: " + dtoStudent.getPersona() + ", no se encuentra"));

        Profesor profesor = profesorRepositorio.findById(dtoStudent.getProfesor()).orElseThrow(
                () -> new UnprocesableException("Error, el profesor con id: " + dtoStudent.getProfesor() + ", no se encuentra"));

        Student estudiante = modelMapper.map(dtoStudent, Student.class);
        estudiante.setPersona(persona);
        estudiante.setProfesor(profesor);
        estudiante.setAsignaturas(new ArrayList<>());
        estudianteRepositorio.save(estudiante);
        return modelMapper.map(estudiante, OutputDTOStudent.class);
    }

    public OutputDTOStudentFull getEstudiante(String id) {
        Student estudent = estudianteRepositorio.findById(id).orElseThrow(() -> new UnprocesableException("Usuario no encontrado"));
        OutputDTOStudentFull h = modelMapper.map(estudent, OutputDTOStudentFull.class);
        h.setPersona(estudent.getPersona());
        h.setProfesor(estudent.getProfesor());
        return h;
    }
}
