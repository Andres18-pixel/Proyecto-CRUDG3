package com.tuempresa.proyectocrudg3.model;

import java.time.LocalDate;

public class Productos {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private LocalDate fechaCreacion;
    private String categoria;
    private String marca;
    private int idCategoria;
    private int idMarca;
    private int estado;

    // Constructor vacío por defecto
    public Productos() {}

    // Constructor principal utilizado por el Controlador
    public Productos(int idProducto, String codigo, String nombre, String descripcion,
                     double precio, int stock, LocalDate fechaCreacion,
                     String categoria, String marca) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.fechaCreacion = fechaCreacion;
        this.categoria = categoria;
        this.marca = marca;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}