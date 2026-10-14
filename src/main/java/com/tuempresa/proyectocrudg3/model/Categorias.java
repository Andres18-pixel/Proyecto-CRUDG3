package com.tuempresa.proyectocrudg3.model;

public class Categorias {
    private int idCategoria;
    private String nombre;

    public Categorias() {}

    // Constructor requerido por el Controlador
    public Categorias(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre; // Permite mostrar el nombre en los ComboBox de JavaFX
    }
}