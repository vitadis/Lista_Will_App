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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import trata_excepciones.UtilFormatos;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * Clase encargada exclusivamente de la construccion y gestion de ventanas y
 * paneles visuales.
 */
public class GestionPanel2 {

	// ----------CONSTANTES----------

	// Paleta de colores
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String COLOR_SECONDARY = "#1976D2";
	private static final String COLOR_LIGHT = "#42A5F5";
	private static final String COLOR_DARK = "#0D47A1";
	private static final String FONDO_TRANSPARENTE = "rgba(135, 206, 235, 0.3)";

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
		panel.setStyle("-fx-background-color: linear-gradient(to bottom," + "#FFDEE9 0%," + "#B5FFFC 40%,"
				+ "#4FACFE 70%," + "#00C6FB 100%" + ");");

		panel.getColumnConstraints().addAll(new ColumnConstraints(WIDTH * 0.20), new ColumnConstraints(WIDTH * 0.80));

		RowConstraints row = new RowConstraints();
		row.setMinHeight(HEIGHT);
		panel.getRowConstraints().add(row);

		panel.add(parteIzqP2(panel), 0, 0);

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
		menu.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";");

		menu.setAlignment(Pos.CENTER);
		menu.setPadding(new Insets(15));

		Button will = crearBotonWill("ISLA DE WILL");

		Button b1 = crearBotonMenu("1. Empleados", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");
		Button b2 = crearBotonMenu("2. Invitados", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");
		Button b3 = crearBotonMenu("3. Gestionar asistencia", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");
		Button b4 = crearBotonMenu("4. Filtrado", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");
		Button b5 = crearBotonMenu("5. Salir", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		b1.setOnAction(e -> empleados1b(panel));
		b2.setOnAction(e -> invitados2b(panel));
		b3.setOnAction(e -> System.out.println("Asistencia"));
		b4.setOnAction(e -> System.out.println("Filtrado"));
		b5.setOnAction(e -> {
			if (App.confirmarSalida())
				Platform.exit();
		});

		menu.getChildren().addAll(will, new Separator(), b1, b2, b3, b4, new Region(), b5);

		VBox.setVgrow(menu.getChildren().get(menu.getChildren().size() - 2), Priority.ALWAYS);
		return menu;
	}

	// Tabla de empleados
	@SuppressWarnings("unchecked")
	private static TableView<Empleado> tablaEmpleados(ArrayList<Empleado> lista) {
		TableView<Empleado> table = new TableView<>();
		estiloTabla(table);

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
		estiloTabla(table);
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

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

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

		Button btnGuardar = crearBotonMenu("Guardar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

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
			if (!UtilFormatos.formatoNumero(telefono)) {
				etiquetaError(mensajeError, "AGREGA UN NUMERO VALIDO DE 9 DIG.");
				return;
			}
			if (!UtilFormatos.mayorDeEdad(fechaNac)) {
				etiquetaError(mensajeError, "TIENES QUE SER MAYOR DE EDAD");
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

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("ELIMINAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		Button btnEliminar = crearBotonMenu("Eliminar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

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
			if (!UtilFormatos.dniFormato(dni)) {
				etiquetaError(mensajeError, "El dni tiene que tener el formato NNNNNNNNL");
				return;
			}

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

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("MODIFICAR EMPLEADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		Button btnModificar = crearBotonMenu("Modificar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

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

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

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

		Button btnGuardar = crearBotonMenu("Guardar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		HBox botones = new HBox(10, btnGuardar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnGuardar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		//
		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

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
			if (nombre == "" || fechaNac == null || email == "" || telefono == "" || cargo == null) {
				etiquetaError(mensajeError, "TODOS LOS CAMPOS SON NECESARIOS");
				return;
			}

			if (!UtilFormatos.correoFormato(email)) {
				etiquetaError(mensajeError, "AGREGA UN CORREO VALIDO");
				return;
			}

			if (!UtilFormatos.formatoNumero(telefono)) {
				etiquetaError(mensajeError, "AGREGA UN NUMERO VALIDO DE 9 DIG.");
				return;
			}

			if (!UtilFormatos.mayorDeEdad(fechaNac)) {
				etiquetaError(mensajeError, "TIENES QUE SER MAYOR DE EDAD");
				return;
			}
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

		contenedor.getChildren().addAll(titulo, tfNombre, dpFechaNac, tfEmail, tfTelefono, cbCargo, chkActivo, botones,
				mensajeError);

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
		Button btnEliminar = crearBotonMenu("Eliminar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");
		Button btnAgregar = crearBotonMenu("Agregar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnModificar = crearBotonMenu("Modificar", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");

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
		Button btnAgregar = crearBotonMenu("Agregar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnModificar = crearBotonMenu("Modificar", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");

		// digo, que estos tienen prioridad al momento de ordenar
		HBox.setHgrow(btnAgregar, Priority.ALWAYS);
		HBox.setHgrow(btnModificar, Priority.ALWAYS);

		box.getChildren().addAll(btnAgregar, btnModificar);

		btnAgregar.setOnAction(e -> System.out.println("Agregar"));
		btnModificar.setOnAction(e -> System.out.println("Modificar"));

		return box;
	}

	private static Button crearBotonMenu(String texto, String color_n, String color_h) {
		Button btn = new Button(texto);
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setPrefHeight(45);

		String normal = "-fx-background-color:" + color_n + "; -fx-text-fill:white;";
		String hover = "-fx-background-color:" + color_h + "; -fx-text-fill:white;";

		btn.setCursor(Cursor.HAND);

		btn.setStyle(normal);
		btn.setOnMouseEntered(e -> btn.setStyle(hover));
		btn.setOnMouseExited(e -> btn.setStyle(normal));

		return btn;
	}

	// boton de will
	private static Button crearBotonWill(String texto) {
		Button b = new Button(texto);
		b.setMaxWidth(Double.MAX_VALUE);
		b.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: #5DADE2;" + "-fx-font-size: 24px;"
				+ "-fx-font-weight: 900;" + "-fx-alignment: center;");
		b.setOnAction(e -> {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText(null);
			alerta.setContentText("DIAGO DEJA DE ACOSAR MENORES");
			alerta.showAndWait();
		}

		);
		return b;
	}

	private static Label crearTitulo(String texto) {
		Label l = new Label(texto);
		l.setStyle("-fx-text-fill: #2C3E50;" + "-fx-font-size: 24px;" + "-fx-font-weight: 900;"
				+ "-fx-alignment: center;");
		return l;
	}

	// etiqueta de error del dni
	private static void etiquetaError(Label etiqueta, String mensaje) {
		etiqueta.setText("¡¡¡" + mensaje + "!!!");
		etiqueta.setStyle(
				"-fx-text-fill: #D32F2F;" + "-fx-font-weight: bold;" + "-fx-background-color: rgba(255, 255, 0, 0.3);"
						+ "-fx-background-radius: 10;" + "-fx-padding: 8 12 8 12;" + "-fx-alignment: center;");
	}

	private static <T> TableColumn<T, String> crearColumna(String titulo, String atri, double width) {
		// T es una variable generica de cualquier clase
		TableColumn<T, String> col = new TableColumn<>(titulo);
		col.setCellValueFactory(new PropertyValueFactory<>(atri));
		col.setPrefWidth(width);
		return col;
	}

	private static <T> void estiloTabla(TableView<T> tabla) {
		// Fondo y bordes del TableView
		tabla.setStyle(
				"-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-table-cell-border-color: transparent;");

		// filas trasparentes
		tabla.setRowFactory(tv -> {
			TableRow<T> row = new TableRow<>();
			row.setStyle("-fx-background-color: transparent;");

			row.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
				if (isNowSelected) {
					row.setStyle("-fx-background-color: rgba(254, 165, 0, 0.5);");
				} else {
					row.setStyle("-fx-background-color: transparent;");
				}
			});

			return row;
		});
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
