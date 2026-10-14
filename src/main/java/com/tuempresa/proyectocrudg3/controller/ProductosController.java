package com.tuempresa.proyectocrudg3.controller;

import com.tuempresa.proyectocrudg3.database.ConexionBD;
import com.tuempresa.proyectocrudg3.model.Categorias;
import com.tuempresa.proyectocrudg3.model.Marcas;
import com.tuempresa.proyectocrudg3.model.Productos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProductosController {

    // Campos del formulario
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtDescripcion;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<Categorias> cmbCategoria;
    @FXML private ComboBox<Marcas> cmbMarca;

    // Botones de acción
    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;

    // Campos de búsqueda y filtros
    @FXML private TextField txtFiltroCodigoNombre;
    @FXML private ComboBox<Categorias> cmbFiltroCategoria;
    @FXML private DatePicker dpFiltroDesde;
    @FXML private DatePicker dpFiltroHasta;
    @FXML private TextField txtFiltroPrecioMin;
    @FXML private TextField txtFiltroPrecioMax;
    @FXML private CheckBox chkSoloExistencias;
    @FXML private ComboBox<String> cmbFiltroOrdenar;
    @FXML private Button btnBuscar;
    @FXML private Button btnLimpiarFiltros;

    // Componentes de la Tabla
    @FXML private TableView<Productos> tblProductos;
    @FXML private TableColumn<Productos, String> colCodigo;
    @FXML private TableColumn<Productos, String> colNombre;
    @FXML private TableColumn<Productos, String> colCategoria;
    @FXML private TableColumn<Productos, String> colMarca;
    @FXML private TableColumn<Productos, Double> colPrecio;
    @FXML private TableColumn<Productos, Integer> colStock;
    @FXML private TableColumn<Productos, LocalDate> colFechaCreacion;

    // Listas observables para JavaFX
    private ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
    private ObservableList<Categorias> listaCategorias = FXCollections.observableArrayList();
    private ObservableList<Marcas> listaMarcas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println(">>> Inicializando ProductosController...");
        configurarTabla();
        cargarCategorias();
        cargarMarcas();
        cargarProductos();
    }

    private void configurarTabla() {
        if (tblProductos != null) {
            colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
            colFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));

            tblProductos.setItems(listaProductos);
        }
    }

    public void cargarProductos() {
        listaProductos.clear();
        String sql = "SELECT p.id_producto, p.codigo, p.nombre, p.descripcion, p.precio, p.stock, " +
                "p.fecha_creacion, c.nombre AS categoria, m.nombre AS marca " +
                "FROM catalogos.productos p " +
                "INNER JOIN catalogos.categorias c ON p.id_categoria = c.id_categoria " +
                "INNER JOIN catalogos.marcas m ON p.id_marca = m.id_marca " +
                "WHERE p.estado = 1 ORDER BY p.id_producto";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productos p = new Productos(
                        rs.getInt("id_producto"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getDate("fecha_creacion").toLocalDate(),
                        rs.getString("categoria"),
                        rs.getString("marca")
                );
                listaProductos.add(p);
            }
            System.out.println(">>> Se cargaron exitosamente " + listaProductos.size() + " productos.");

        } catch (SQLException e) {
            System.err.println(">>> Error al cargar los productos desde la base de datos:");
            e.printStackTrace();
        }
    }

    public void cargarCategorias() {
        listaCategorias.clear();
        String sql = "SELECT id_categoria, nombre FROM catalogos.categorias WHERE estado = 1";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaCategorias.add(new Categorias(rs.getInt("id_categoria"), rs.getString("nombre")));
            }
            if (cmbCategoria != null) cmbCategoria.setItems(listaCategorias);
            if (cmbFiltroCategoria != null) cmbFiltroCategoria.setItems(listaCategorias);

        } catch (SQLException e) {
            System.err.println(">>> Error al cargar las categorías:");
            e.printStackTrace();
        }
    }

    public void cargarMarcas() {
        listaMarcas.clear();
        String sql = "SELECT id_marca, nombre FROM catalogos.marcas WHERE estado = 1";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaMarcas.add(new Marcas(rs.getInt("id_marca"), rs.getString("nombre")));
            }
            if (cmbMarca != null) cmbMarca.setItems(listaMarcas);

        } catch (SQLException e) {
            System.err.println(">>> Error al cargar las marcas:");
            e.printStackTrace();
        }
    }
}