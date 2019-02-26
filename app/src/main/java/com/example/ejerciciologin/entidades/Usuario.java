package com.example.ejerciciologin.entidades;

public class Usuario {
    private Integer id;
    private String nombre;
    private String nacionalidad;
    private String contraseña;

    public Usuario(Integer id, String nombre,String contraseña,String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña=contraseña;
        this.nacionalidad = nacionalidad;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
