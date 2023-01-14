// Entidad Usuario para acceder a la Database según modelo multicapas.
// Tendremos que relacionar la TABLA que genera este código con la TABLA
// generada por la Entidad Rol, por eso DTO.
package com.portfolio.sesarego.Security.Entity;

import com.portfolio.sesarego.Security.Enums.RolNombre;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)            
    private int id;
    // declaramos que en este campo solo se permiten strings
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;
    
    // Constructor

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }    
}
