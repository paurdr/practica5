/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 */

 package edu.comillas.icai.gitt.pat.spring.p5.entity;

 import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
 import jakarta.persistence.*;
 
 @Entity
 @Table(name = "APP_USER")
 public class AppUser {
 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
 
     @Column(nullable = false, unique = true)
     private String email;
 
     @Column(nullable = false)
     private String password;
 
     @Column(nullable = false)
     private String name;
 
     @Enumerated(EnumType.STRING)
     @Column(nullable = false)
     private Role role;
 
     // Constructor vacío para JPA
     public AppUser() { }
 
     // Getters y setters
     public Long getId() {
         return id;
     }
     public void setId(Long id) {
         this.id = id;
     }
 
     public String getEmail() {
         return email;
     }
     public void setEmail(String email) {
         this.email = email;
     }
 
     public String getPassword() {
         return password;
     }
     public void setPassword(String password) {
         this.password = password;
     }
 
     public String getName() {
         return name;
     }
     public void setName(String name) {
         this.name = name;
     }
 
     public Role getRole() {
         return role;
     }
     public void setRole(Role role) {
         this.role = role;
     }
 }