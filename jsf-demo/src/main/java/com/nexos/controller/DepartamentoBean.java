/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.controller;

/**
 *
 * @author Developer
 */
 
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import com.nexos.modelo.service.DepartamentoDAO;
import com.nexos.modelo.entity.Departamento;
import java.util.ArrayList;
 
@ManagedBean(name = "departamentoBean")
@RequestScoped
public class DepartamentoBean {
 
 private String option ;   

 public String getOption() {
    return option;
}

public void setOption(String option) {
   this.option = option;
}
    
 public String nuevo() {
  Departamento c = new Departamento();
  Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
  sessionMap.put("departamento", c);
   return "nuevoDepartamento.xhtml";
 }
 
 public String guardar(Departamento departamento) {
  // guarda la fecha de registro
  
  String codigo = departamento.getDepartamentoCodigo() ;
  
  if (codigo != null && codigo.trim().length()>0) {
      
      Departamento existe = new Departamento() ;
      DepartamentoDAO departamentoDAO = new DepartamentoDAO();
      existe = departamentoDAO.buscar(codigo) ;
      if (existe.getDepartamentoCodigo() == null ) {
           Date fechaActual = new Date();
           departamento.setFregistro(new java.sql.Date(fechaActual.getTime()));
           departamentoDAO.guardar(departamento);
      }    
  }    
  return "consultaDepartamento.xhtml";
 }
 
 public List<MenuDepartamento> obtenerDeptos(){
     List<Departamento> departamentos =obtenerDepartamentos();
     List<MenuDepartamento> lista = new ArrayList<>();
     for(Departamento d : departamentos) {
         MenuDepartamento c = new MenuDepartamento();
         c.setId(d.getIdDepartamento());
         c.setElnombre(d.getDepartamentoNombre());
         lista.add(c);
     }
     return lista;
 }
 
 public List<Departamento> obtenerDepartamentos() {
  DepartamentoDAO departamentoDAO = new DepartamentoDAO();
 
  /*
   * List<departamento> listaDepartamentos = new ArrayList<>(); Departamento c1 = new Departamento();
   * c1.setId(1L); c1.setNombres("Elivar"); c1.setApellidos("Largo");
   * c1.setDireccion("Loja");
   * 
   * Departamento c2 = new Departamento(); c2.setId(1L); c2.setNombres("Elivar1");
   * c2.setApellidos("Largo1"); c2.setDireccion("Loja1"); listaDepartamentos.add(c1);
   * listaDepartamentos.add(c2);
   * 
   * return listaDepartamentos;
   */
  return departamentoDAO.obtenerDepartamentos();
 }
 
 public String editar(Integer id) {
  DepartamentoDAO departamentoDAO = new DepartamentoDAO();
  Departamento c = new Departamento();
  c = departamentoDAO.buscar(id);
  System.out.println("******************************************");
  System.out.println(c);
 
  Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
  sessionMap.put("departamento", c);
  return "editarDepartamento.xhtml"; }
 
 public String actualizar(Departamento departamento) {
  // guarda la fecha de actualizacion
  try {
         Date fechaActual = new Date();
         departamento.setFactualizar(new java.sql.Date(fechaActual.getTime()));
 
         DepartamentoDAO departamentoDAO = new DepartamentoDAO();  
         departamentoDAO.editar(departamento);
  }catch(Exception e) {
  }       
  return "consultaDepartamento.xhtml";
 }
 
 // eliminar un departamento
 public String eliminar(Integer id) {
      try {
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            departamentoDAO.eliminar(id);
            System.out.println("Departamento eliminado..");
      }catch(Exception e) {
      }        
      return "consultaDepartamento.xhtml";
 }
 
}