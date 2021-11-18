/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.modelo.service;

/**
 *
 * @author Developer
 */
import com.nexos.modelo.entity.Empleado;
import com.nexos.modelo.entity.Empleado;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmpleadoDAO {

    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

    // guardar empleado
    public void guardar(Empleado empleado) {
        entity.getTransaction().begin();
        entity.persist(empleado);
        entity.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    // editar empleado
    public void editar(Empleado empleado) {
        entity.getTransaction().begin();
        entity.merge(empleado);
        entity.getTransaction().commit();
        /// JPAUtil.shutdown();
    }

    // buscar empleado
    public Empleado buscar(Integer id) {
        Empleado c = new Empleado();
        c = entity.find(Empleado.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    public Empleado buscar(String codigo) {
        Empleado c = new Empleado();
        Query q = entity.createQuery("SELECT e FROM Empleado e WHERE e.documentoNumero = :documentoNumero");
        q.setParameter("documentoNumero", codigo);
        try {
            c = (Empleado) q.getSingleResult();
        } catch (Exception e) {
        }
        // JPAUtil.shutdown();
        return c;
    }

    /// eliminar empleado
    public void eliminar(Integer id) {
        Empleado c = new Empleado();
        c = entity.find(Empleado.class, id);
        entity.getTransaction().begin();
        entity.remove(c);
        entity.getTransaction().commit();
    }

    // obtener todos los empleado
    public List<Empleado> obtenerEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Query q = entity.createQuery("SELECT c FROM Empleado c");
        listaEmpleados = q.getResultList();
        return listaEmpleados;
    }

}
