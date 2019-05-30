package com.example.taller_4.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.io.Serializable;

@Entity(tableName = "persona")

public class Persona implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    public int getId()
    {
        return id;
    }


    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setId(int id) {
        this.id = id;
    }
}
