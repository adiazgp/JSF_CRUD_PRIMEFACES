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

import com.nexos.modelo.entity.Departamento;
import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
 
public class DepartamentoDAO {    
 EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
 
 // guardar departamento
 public void guardar(Departamento departamento) {
  entity.getTransaction().begin();
  entity.persist(departamento);
  entity.getTransaction().commit();
  // JPAUtil.shutdown();
 }
 
 // editar departamento
 public void editar(Departamento departamento) {
  entity.getTransaction().begin();
  entity.merge(departamento);
  entity.getTransaction().commit();
  /// JPAUtil.shutdown();
 }
 
 // buscar departamento
 public Departamento buscar(Integer id) {
  Departamento c = new Departamento();
  c = entity.find(Departamento.class, id);
  // JPAUtil.shutdown();
  return c;
 }
 
 public Departamento buscar(String codigo) {
  Departamento c = new Departamento();
  Query q = entity.createQuery("SELECT d FROM Departamento d WHERE d.departamentoCodigo = :departamentoCodigo");
  q.setParameter("departamentoCodigo",codigo) ;
  try {
       c = (Departamento)q.getSingleResult() ;
  }catch(Exception e) {
  }      
  // JPAUtil.shutdown();
  return c;
 }
 
 /// eliminar departamento
 public void eliminar(Integer id) {
  Departamento c = new Departamento();
  c = entity.find(Departamento.class, id);
  entity.getTransaction().begin();
  entity.remove(c);
  entity.getTransaction().commit();
 }
 
 // obtener todos los departamento
 public List<Departamento> obtenerDepartamentos() {
  List<Departamento> listaDepartamentos = new ArrayList<>();
  Query q = entity.createQuery("SELECT c FROM Departamento c");
  listaDepartamentos = q.getResultList();
  return listaDepartamentos;
 }
 
}