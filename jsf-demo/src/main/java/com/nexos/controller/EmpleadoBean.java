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
import com.nexos.modelo.entity.Departamento;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.nexos.modelo.service.EmpleadoDAO;
import com.nexos.modelo.entity.Empleado;
import com.nexos.modelo.service.DepartamentoDAO;
import java.util.ArrayList;

@ManagedBean(name = "empleadoBean")
@RequestScoped
public class EmpleadoBean {

    private Integer option;

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }
    
    private String tipoDocumento ;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
            
    
    public String nuevo() {
        Empleado c = new Empleado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("empleado", c);
        return "nuevoEmpleado.xhtml";
    }

    public String guardar(Empleado empleado) {
        // guarda la fecha de registro

        String codigo = empleado.getDocumentoNumero();

        if (codigo != null && codigo.trim().length() > 0) {

            Empleado existe = new Empleado();
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            existe = empleadoDAO.buscar(codigo);
            if (existe.getDocumentoNumero() == null) {
              //---------------------------------------------------------------
                Departamento departamento = new Departamento() ;
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                departamento = departamentoDAO.buscar(option);
                empleado.setIdDepartamento(departamento);
              //---------------------------------------------------------------
                Date fechaActual = new Date();
                empleado.setFregistro(new java.sql.Date(fechaActual.getTime()));
                empleado.setFechaHoraModifica(new java.sql.Date(0));
                empleadoDAO.guardar(empleado);
            }
        }
        return "consultaEmpleados.xhtml";
    }

    public List<Empleado> obtenerEmpleados() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        return empleadoDAO.obtenerEmpleados();
    }

    public List<MenuDepartamento> obtenerDeptos() {
        List<Departamento> departamentos = obtenerDepartamentos();
        List<MenuDepartamento> lista = new ArrayList<>();
        for (Departamento d : departamentos) {
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
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado c = new Empleado();
        c = empleadoDAO.buscar(id);
        System.out.println("******************************************");
        System.out.println(c);

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("empleado", c);
        return "editarEmpleado.xhtml";
    }

    public String muestraEliminar(Integer id) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado c = new Empleado();
        c = empleadoDAO.buscar(id);
        System.out.println("******************************************");
        System.out.println(c);

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("empleado", c);
        return "eliminarEmpleado.xhtml";
    }
    
    public String actualizar(Empleado empleado) {
        // guarda la fecha de actualizacion
        try {
            //---------------------------------------------------------------
              Departamento departamento = new Departamento() ;
              DepartamentoDAO departamentoDAO = new DepartamentoDAO();
              departamento = departamentoDAO.buscar(option);
              empleado.setIdDepartamento(departamento);
            //----------------------------------------------------------------------------
              Date fechaActual = new Date();
              empleado.setFechaHoraModifica(new java.sql.Date(fechaActual.getTime()));
	    //---------------------------------------------------------------------------  
              EmpleadoDAO empleadoDAO = new EmpleadoDAO();
              empleadoDAO.editar(empleado);
        } catch (Exception e) {
                 System.out.println("ERROR EN actualizar(): "+e) ;
        }
        return "consultaEmpleados.xhtml";
    }

    // eliminar un empleado
    public String eliminar(Integer id) {
        try {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            empleadoDAO.eliminar(id);
            System.out.println("Empleado eliminado..");
        } catch (Exception e) {
        }
        return "consultaEmpleados.xhtml";
    }
    
    public String menu() { 
       return "menucrudjsf.xhtml";
    }

}
