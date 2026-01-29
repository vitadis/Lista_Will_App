package org.openjfx.Lista_Will_App;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import clases.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import trata_excepciones.UtilFormatos;

/**
 * Clase encargada exclusivamente de la construccion y gestion de ventanas y
 * paneles visuales.
 */
public class GestionVentanas {

	// ----------CONSTANTES----------

	// Paleta de colores
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String COLOR_SECONDARY = "#1976D2";
	private static final String COLOR_LIGHT = "#42A5F5";
	private static final String COLOR_DARK = "#0D47A1";

	// Dimensiones de pantalla
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = SCREEN_SIZE.width;
	private static final int HEIGHT = SCREEN_SIZE.height - 70;

	// Archivos
	private static final File PERSONA = new File("Persona.dat");

	// -------VISTAS PRINCIPALES-------
	@SuppressWarnings("exports")
	public static GridPane panel2() {
		GridPane panel = new GridPane();

		panel.getColumnConstraints().addAll(new ColumnConstraints(WIDTH * 0.20), new ColumnConstraints(WIDTH * 0.80));

		RowConstraints row = new RowConstraints();
		row.setMinHeight(HEIGHT);
		panel.getRowConstraints().add(row);

		panel.add(parteIzqP2(panel), 0, 0);
		panel.setGridLinesVisible(true);

		return panel;
	}

	// boton 1 de empleados
	@SuppressWarnings("exports")
	public static void empleados1b(GridPane panel) {
		ArrayList<Empleado> lista = Gestionar_Ficheros.listEmpleado(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("TABLA EMPLEADOS");

		contenedor.getChildren().addAll(titulo, tablaEmpleados(lista), grudEmpleados2b(panel));

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// boton 2 invitados
	@SuppressWarnings("exports")
	public static void invitados2b(GridPane panel) {
		ArrayList<Invitado> lista2 = Gestionar_Ficheros.listInvitado(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("TABLA INVITADOS");

		contenedor.getChildren().addAll(titulo, tablaInvitados(lista2), grudInvitados2b(panel));

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// Panel izquierdo (El menu de opciones)
	private static VBox parteIzqP2(GridPane panel) {
		VBox menu = new VBox(20);
		menu.setAlignment(Pos.CENTER);
		menu.setPadding(new Insets(15));

		Label titulo = new Label("ISLA DE WILL");
		titulo.setStyle("-fx-text-fill: " + COLOR_SECONDARY + ";" + "-fx-font-size: 20px;" + "-fx-font-weight: 900;"
				+ "-fx-font-family: 'Arial Black';");

		Button b1 = crearBotonMenu("1. Empleados");
		Button b2 = crearBotonMenu("2. Invitados");
		Button b3 = crearBotonMenu("3. Gestionar asistencia");
		Button b4 = crearBotonMenu("4. Filtrado");
		Button b5 = crearBotonMenu("5. Salir");

		b1.setOnAction(e -> empleados1b(panel));
		b2.setOnAction(e -> invitados2b(panel));
		b3.setOnAction(e -> System.out.println("Asistencia"));
		b4.setOnAction(e -> System.out.println("Filtrado"));
		b5.setOnAction(e -> {
			if (App.confirmarSalida())
				Platform.exit();
		});

		menu.getChildren().addAll(titulo, new Separator(), b1, b2, b3, b4, new Region(), b5);

		VBox.setVgrow(menu.getChildren().get(menu.getChildren().size() - 2), Priority.ALWAYS);
		return menu;
	}

	// Tabla de empleados
	@SuppressWarnings("unchecked")
	private static TableView<Empleado> tablaEmpleados(ArrayList<Empleado> lista) {
		TableView<Empleado> table = new TableView<>();
		table.setItems(FXCollections.observableArrayList(lista));

		double w = (WIDTH * 0.8 - 40) / 7;

		table.getColumns().addAll(crearColumna("DNI", "dni", w), crearColumna("Nombre", "nombre", w),
				crearColumna("Fecha", "fechaNac", w), crearColumna("Email", "email", w),
				crearColumna("Telefono", "telefono", w), crearColumna("Cargo", "cargo", w),
				crearColumna("Activo", "activo", w));

		return table;
	}

	@SuppressWarnings("unchecked")
	private static TableView<Invitado> tablaInvitados(ArrayList<Invitado> lista) {
		TableView<Invitado> table = new TableView<>();
		table.setItems(FXCollections.observableArrayList(lista));

		double w = (WIDTH * 0.8 - 40) / 6;

		table.getColumns().addAll(crearColumna("DNI", "dni", w), crearColumna("Nombre", "nombre", w),
				crearColumna("Fecha", "fechaNac", w), crearColumna("Email", "email", w),
				crearColumna("Telefono", "telefono", w), crearColumna("Tipo de invitado", "tipo", w));

		return table;
	}

	// --------FORMULARIOS--------
	// formulario agregar
	@SuppressWarnings("exports")
	public static void formAgregarEmpleados(GridPane panel) {

		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;"
				+ "-fx-border-color: #cccccc;");

		Label titulo = new Label("AGREGAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		TextField tfNombre = new TextField();
		tfNombre.setPromptText("Nombre");

		DatePicker dpFechaNac = new DatePicker();
		dpFechaNac.setPromptText("Fecha de nacimiento");

		TextField tfEmail = new TextField();
		tfEmail.setPromptText("Email");

		TextField tfTelefono = new TextField();
		tfTelefono.setPromptText("Teléfono");

		ComboBox<Cargo> cbCargo = new ComboBox<>();
		cbCargo.getItems().addAll(Cargo.values());
		cbCargo.setPromptText("Cargo");

		CheckBox chkActivo = new CheckBox("Empleado activo");
		chkActivo.setSelected(true);

		Button btnGuardar = crearBotonMenu("Guardar");
		Button btnCancelar = crearBotonMenu("Cancelar");

		HBox botones = new HBox(10, btnGuardar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnGuardar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		// acciones de los botones
		btnGuardar.setOnAction(e -> {
			// obtengo el valor de los formularios y lo guardo
			String dni = tfDni.getText().toUpperCase();
			String nombre = tfNombre.getText();
			LocalDate fechaNac = dpFechaNac.getValue();
			String email = tfEmail.getText();
			String telefono = tfTelefono.getText();
			Cargo cargo = cbCargo.getValue();
			boolean activo = chkActivo.isSelected();

			int indice = Gestionar_Ficheros.indiceALPEEmpleado(personas, dni);

			// mensajes de error, para validar los campos
			if (dni == "" || nombre == "" || fechaNac == null || email == "" || telefono == "" || cargo == null) {
				etiquetaError(mensajeError, "TODOS LOS CAMPOS SON NECESARIOS");
				return;
			}
			if (indice > -1) {
				etiquetaError(mensajeError, "AGREGA UN DNI DIFERENTE");
				return;
			}
			if (!UtilFormatos.dniFormato(dni)) {
				etiquetaError(mensajeError, "El dni tiene que tener el formato NNNNNNNNL");
				return;
			}

			if (!UtilFormatos.correoFormato(email)) {
				etiquetaError(mensajeError, "AGREGA UN CORREO VALIDO");
				return;
			}

			Empleado nuevoEmpleado = new Empleado(dni, nombre, fechaNac, email, telefono, cargo, activo);
			personas.add(nuevoEmpleado);
			// sobreescribo sin mas a mi archivo
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA);
			empleados1b(panel);

		});

		btnCancelar.setOnAction(e -> {
			empleados1b(panel);
		});

		contenedor.getChildren().addAll(titulo, tfDni, tfNombre, dpFechaNac, tfEmail, tfTelefono, cbCargo, chkActivo,
				botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	// formulario eliminar un trabajador segun su dni
	@SuppressWarnings("exports")
	public static void formEliminarEmpleados(GridPane panel) {

		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setMaxSize(600, 500);

		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);

		contenedor.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;"
				+ "-fx-border-color: #cccccc;");

		Label titulo = new Label("AGREGAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		Button btnEliminar = crearBotonMenu("Eliminar");
		Button btnCancelar = crearBotonMenu("Cancelar");

		HBox botones = new HBox(10, btnEliminar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnEliminar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		btnEliminar.setOnAction(e -> {
			int indice;
			String dni = tfDni.getText().toUpperCase();
			indice = Gestionar_Ficheros.indiceALPEEmpleado(personas, dni);

			if (indice != -1) {
				personas.remove(indice);
				Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA);
				empleados1b(panel);

			} else {
				etiquetaError(mensajeError, "EL DNI NO EXISTE");
				return;
			}

		});

		btnCancelar.setOnAction(e -> {
			empleados1b(panel);
		});

		contenedor.getChildren().addAll(titulo, tfDni, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);

	}
	// formulario de modificar, enseñara los campos que se puedan modificar

	@SuppressWarnings("exports")
	public static void formModificarEmpleados(GridPane panel) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;"
				+ "-fx-border-color: #cccccc;");

		Label titulo = new Label("MODIFICAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		Button btnModificar = crearBotonMenu("Modificar");
		Button btnCancelar = crearBotonMenu("Cancelar");

		HBox botones = new HBox(10, btnModificar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnModificar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		btnModificar.setOnAction(e -> {
			int indice;
			String dni = tfDni.getText().toUpperCase();
			indice = Gestionar_Ficheros.indiceALPEEmpleado(personas, dni);

			if (indice != -1) {
				formModiEmpTrue(panel, tfDni);
			} else {
				etiquetaError(mensajeError, "EL DNI NO EXISTE");
				return;
			}

		});

		btnCancelar.setOnAction(e -> empleados1b(panel));

		contenedor.getChildren().addAll(titulo, tfDni, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);

	}

	// subformulario
	public static void formModiEmpTrue(GridPane panel, TextField tfDni) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);
		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;"
				+ "-fx-border-color: #cccccc;");

		Label titulo = new Label("MODIFICAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfNombre = new TextField();
		tfNombre.setPromptText("Nombre");

		DatePicker dpFechaNac = new DatePicker();
		dpFechaNac.setPromptText("Fecha de nacimiento");

		TextField tfEmail = new TextField();
		tfEmail.setPromptText("Email");

		TextField tfTelefono = new TextField();
		tfTelefono.setPromptText("Teléfono");

		ComboBox<Cargo> cbCargo = new ComboBox<>();
		cbCargo.getItems().addAll(Cargo.values());
		cbCargo.setPromptText("Cargo");

		CheckBox chkActivo = new CheckBox("Empleado activo");
		chkActivo.setSelected(true);

		Button btnGuardar = crearBotonMenu("Guardar");
		Button btnCancelar = crearBotonMenu("Cancelar");

		HBox botones = new HBox(10, btnGuardar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnGuardar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		btnGuardar.setOnAction(e -> {
			// obtengo el valor de los formularios y lo guardo
			String dni = tfDni.getText();
			String nombre = tfNombre.getText();
			LocalDate fechaNac = dpFechaNac.getValue();
			String email = tfEmail.getText();
			String telefono = tfTelefono.getText();
			Cargo cargo = cbCargo.getValue();
			boolean activo = chkActivo.isSelected();
			// indice
			int indice = Gestionar_Ficheros.indiceALPEEmpleado(personas, dni);

			Empleado cambiarEmpleado = new Empleado(dni, nombre, fechaNac, email, telefono, cargo, activo);

			personas.set(indice, cambiarEmpleado);
			// sobreescribo sin mas a mi archivo
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA);
			empleados1b(panel);
		});

		btnCancelar.setOnAction(e -> {
			empleados1b(panel);
		});

		contenedor.getChildren().addAll(titulo, tfNombre, dpFechaNac, tfEmail, tfTelefono, cbCargo, chkActivo, botones);

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);

	}

	// Controles, basicamente los botones
	private static HBox grudEmpleados2b(GridPane panel) {
		HBox box = new HBox(15);
		box.setAlignment(Pos.CENTER);
		box.setFillHeight(true);

		// botones
		Button btnEliminar = crearBotonMenu("Eliminar");
		Button btnAgregar = crearBotonMenu("Agregar");
		Button btnModificar = crearBotonMenu("Modificar");

		// digo, que estos tienen prioridad al momento de ordenar
		HBox.setHgrow(btnEliminar, Priority.ALWAYS);
		HBox.setHgrow(btnAgregar, Priority.ALWAYS);
		HBox.setHgrow(btnModificar, Priority.ALWAYS);

		box.getChildren().addAll(btnEliminar, btnAgregar, btnModificar);

		btnEliminar.setOnAction(e -> formEliminarEmpleados(panel));
		btnAgregar.setOnAction(e -> formAgregarEmpleados(panel));
		btnModificar.setOnAction(e -> formModificarEmpleados(panel));

		return box;
	}

	private static HBox grudInvitados2b(GridPane panel) {
		HBox box = new HBox(15);
		box.setAlignment(Pos.CENTER);
		box.setFillHeight(true);

		// botones
		Button btnAgregar = crearBotonMenu("Agregar");
		Button btnModificar = crearBotonMenu("Modificar");

		// digo, que estos tienen prioridad al momento de ordenar
		HBox.setHgrow(btnAgregar, Priority.ALWAYS);
		HBox.setHgrow(btnModificar, Priority.ALWAYS);

		box.getChildren().addAll(btnAgregar, btnModificar);

		btnAgregar.setOnAction(e -> System.out.println("Agregar"));
		btnModificar.setOnAction(e -> System.out.println("Modificar"));

		return box;
	}

	private static Button crearBotonMenu(String texto) {
		Button btn = new Button(texto);
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setPrefHeight(45);

		String normal = "-fx-background-color:" + COLOR_SECONDARY + "; -fx-text-fill:white;";
		String hover = "-fx-background-color:" + COLOR_LIGHT + "; -fx-text-fill:white;";

		btn.setStyle(normal);
		btn.setOnMouseEntered(e -> btn.setStyle(hover));
		btn.setOnMouseExited(e -> btn.setStyle(normal));

		return btn;
	}

	private static Label crearTitulo(String texto) {
		Label l = new Label(texto);
		l.setStyle("-fx-text-fill:" + COLOR_DARK + ";" + "-fx-font-size:20px;" + "-fx-font-weight:900;");
		return l;
	}

	// etiqueta de error del dni
	private static void etiquetaError(Label etiqueta, String mensaje) {
		etiqueta.setText("¡¡¡"+mensaje+"!!!");
		etiqueta.setStyle("-fx-text-fill: #D32F2F;"+ "-fx-font-weight: bold;");
		etiqueta.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private static <T> TableColumn<T, String> crearColumna(String titulo, String atri, double width) {
		// T es una variable generica de cualquier clase
		TableColumn<T, String> col = new TableColumn<>(titulo);
		col.setCellValueFactory(new PropertyValueFactory<>(atri));
		col.setPrefWidth(width);
		return col;
	}

	// Eliminar elementos del grid
	private static void eliminarElementoGrid(int col, int row, GridPane grid) {
		for (Iterator<Node> it = grid.getChildren().iterator(); it.hasNext();) {
			Node n = it.next();

			int nodeCol = GridPane.getColumnIndex(n) == null ? 0 : GridPane.getColumnIndex(n);
			int nodeRow = GridPane.getRowIndex(n) == null ? 0 : GridPane.getRowIndex(n);

			if (nodeCol == col && nodeRow == row) {
				it.remove();
				break;
			}
		}
	}

}
