package com.example.jpadto.persona.infraestructure.repository;

import com.example.jpadto.persona.domain.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImplements {
    @PersistenceContext
    private EntityManager em;

    List<Persona> findByName(String nombre){
        List<Persona> lista = em.createQuery("Select h from Persona where name = '" + nombre + "'",
                 Persona.class).getResultList();
        return lista;
    }

    public List<Persona> getData(HashMap<String, Object> data){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();
        data.forEach((field,value) ->
        {
            switch (field)
            {
                /*case "id":
                    predicates.add(cb.equal(root.get(field), (Integer)value));
                    break;*/
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                /*case "address":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;*/
            }

        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(query).getResultList();
    }

}
