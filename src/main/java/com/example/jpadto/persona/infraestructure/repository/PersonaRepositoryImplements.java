/*package com.example.jpadto.persona.infraestructure.repository;

import com.example.jpadto.persona.domain.Persona;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonaRepositoryImplements implements PersonaRepositorio{
    @PersistenceContext
    private EntityManager em;

    List<Persona> findByName(String nombre){
        List<Persona> lista = em.createQuery("Select h from Persona where name = '" + nombre + "'",
                 Persona.class).getResultList();
        return lista;
    }

}*/
