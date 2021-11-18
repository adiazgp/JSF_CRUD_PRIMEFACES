/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.modelo.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class JPAUtil {
 // Declaramos una variable con el nombre de la unidad de persistencia
 private static final String PERSISTENCE_UNIT_NAME = "JSF_NEXOS" ;
 
 // Creamos una variable de tipo EntityManagerFactory
 private static EntityManagerFactory factory;
 
 public static EntityManagerFactory getEntityManagerFactory() {
  if (factory == null) {
   // Referenciamos a nuestra unidad de persistencia para gestionar nuestras entidades
   factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }
  return factory;
 }
 
 // Definimos un método close para liberar el objeto Entity Manager.
 public static void shutdown() {
  if (factory != null) {
   factory.close();
  }
 }
}
