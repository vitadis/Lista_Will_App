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
import trata_excepciones.UtilFormatos;

/**
 * Clase encargada exclusivamente de la construccion y gestion de ventanas y
 * paneles visuales.
 */
public class GestionPanel2 {

	// ----------CONSTANTES----------

	// Paleta de colores
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String FONDO_TRANSPARENTE = "rgba(135, 206, 235, 0.3)";

	// Dimensiones de pantalla
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = SCREEN_SIZE.width;
	private static final int HEIGHT = SCREEN_SIZE.height - 70;

	// Archivos
	private static final File PERSONA = new File("persona.dat");
	private static final File PERSONA_AUX = new File("persona.tmp");

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
	private static void empleados1b(GridPane panel) {
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
	private static void invitados2b(GridPane panel) {
		ArrayList<Invitado> lista2 = Gestionar_Ficheros.listInvitado(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("TABLA INVITADOS");

		contenedor.getChildren().addAll(titulo, tablaInvitados(lista2), grudInvitados2b(panel));

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// boton 3, despues de validar el dni
	private static void gestionAsistencia3b(GridPane panel, Invitado invitado) {
		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("TABLA DE ASISTENCIA DE " + invitado.getDni());

		contenedor.getChildren().addAll(titulo, tablaAsistencia(invitado), grudAsistencia3b(panel, invitado));

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// apartado de filtrado
	private static void filtrado4b(GridPane panel) {

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("TIPOS DE FILTRADO");

		contenedor.getChildren().addAll(titulo, grudFiltrado4b(panel));
		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

	}

	private static void estadistica5b(GridPane panel) {

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("ESTADISTICA");

		contenedor.getChildren().addAll(titulo, grudRankingb5(panel));
		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

	}

	// panel vacio que se modificara en caso de que agregue la tabla y la
	// estadistica
	private static <O> VBox panelVoidb5(TableView<O> tabla, String titu) {

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo(titu);

		contenedor.getChildren().addAll(titulo, tabla);
		return contenedor;
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
		Button b5 = crearBotonMenu("5. Estadisticas", "rgba(0, 123, 255, 0.8)", "rgba(0, 153, 255, 1);");
		Button b6 = crearBotonMenu("6. Salir", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		b1.setOnAction(e -> empleados1b(panel));
		b2.setOnAction(e -> invitados2b(panel));
		b3.setOnAction(e -> formGestionAsistencia(panel));
		b4.setOnAction(e -> filtrado4b(panel));
		b5.setOnAction(e -> estadistica5b(panel));

		b6.setOnAction(e -> {
			if (App.confirmarSalida())
				Platform.exit();
		});

		menu.getChildren().addAll(will, new Separator(), b1, b2, b3, b4, b5, new Region(), b6);

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

	// tabla para mostrar las asistencias de la persona
	@SuppressWarnings({ "unchecked" })
	private static TableView<Asistencia> tablaAsistencia(Invitado invitado) {
		ArrayList<Asistencia> asistencia = invitado.getAsistencia();

		TableView<Asistencia> table = new TableView<>();
		estiloTabla(table);
		table.setItems(FXCollections.observableArrayList(asistencia));

		double w = (WIDTH * 0.8 - 40) / 5;

		table.getColumns().addAll(crearColumna("COD", "codigo", w), crearColumna("FechaInicio", "fechaIni", w),
				crearColumna("FechaFin", "fechaFin", w), crearColumna("Secciones", "codSecciones", w),
				crearColumna("Valoracion", "valoracion", w));
		return table;
	}

	// tablas de las estadisticas
	@SuppressWarnings({ "unchecked" })
	private static void crearTablaRanking1(GridPane panel) {
		ArrayList<Invitado> invitados = Gestionar_Ficheros.listInvitado(PERSONA);
		ArrayList<RankingAsistencia> ranking = GestionarEstadisticas.estadisticaInvitados(invitados);
		TableView<RankingAsistencia> tabla = new TableView<>();
		estiloTabla(tabla);
		double w = (WIDTH * 0.8 - 40) / 3;

		tabla.getColumns().addAll(crearColumna("DNI", "dni", w), crearColumna("Nombre", "nombre", w),
				crearColumna("Cant. Asistencias", "cantAsistencia", w));

		tabla.getItems().addAll(ranking);

		VBox contenedor = panelVoidb5(tabla, "CANTIDAD DE ASISTENCIA");
		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// Porcentaje de la seccion con mejor calificacion
	@SuppressWarnings("unchecked")
	private static void crearTablaSecciones2(GridPane panel) {
		ArrayList<Invitado> invitados = Gestionar_Ficheros.listInvitado(PERSONA);
		ArrayList<RankingFechas> ranking = GestionarEstadisticas.estadisticaFechasRango(invitados);

		TableView<RankingFechas> tabla = new TableView<>();
		estiloTabla(tabla);

		double w = (WIDTH * 0.8 - 40) / 2;

		tabla.getColumns().addAll(crearColumna("Fecha", "fecha", w),
				crearColumna("Cantidad", "cantidad", w));
		tabla.getItems().addAll(ranking);
		VBox contenedor = panelVoidb5(tabla, "Concurrencia por fechas");
		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// Porcentaje de asistencia a cada seccion
	@SuppressWarnings("unchecked")
	private static void crearTablaSecciones3(GridPane panel) {
		ArrayList<Invitado> invitados = Gestionar_Ficheros.listInvitado(PERSONA);
		ArrayList<RankingSeccion> ranking = GestionarEstadisticas.estadisticaSecciones(invitados);

		TableView<RankingSeccion> tabla = new TableView<>();
		estiloTabla(tabla);

		double w = (WIDTH * 0.8 - 40) / 2;

		tabla.getColumns().addAll(crearColumna("Seccion", "nombreSeccion", w),
				crearColumna("Porcentaje", "porcentaje", w));
		tabla.getItems().addAll(ranking);
		VBox contenedor = panelVoidb5(tabla, "RECURENCIA POR SECCION");
		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
	}

	// --------FORMULARIOS--------
	// formulario agregar
	private static void formAgregarEmpleados(GridPane panel) {

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
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA_AUX);
			String mensaje = Gestionar_Ficheros.actualizarPersonas();

			if (mensaje != null)
				mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

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
	private static void formEliminarEmpleados(GridPane panel) {

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
				Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA_AUX);
				String mensaje = Gestionar_Ficheros.actualizarPersonas();

				if (mensaje != null)
					mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

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

	private static void formModificarEmpleados(GridPane panel) {
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
	private static void formModiEmpTrue(GridPane panel, TextField tfDni) {
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
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA_AUX);
			String mensaje = Gestionar_Ficheros.actualizarPersonas();

			if (mensaje != null)
				mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

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
	// formulario de Invitados

	private static void formAgregarInvitados(GridPane panel) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("AGREGAR INVITADO");
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

		ComboBox<TipoInvitado> cbTipInv = new ComboBox<>();
		cbTipInv.getItems().addAll(TipoInvitado.values());
		cbTipInv.setPromptText("Tipo de invitado");

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
			TipoInvitado tipInvitado = cbTipInv.getValue();
			ArrayList<Asistencia> asistencia = new ArrayList<Asistencia>();

			int indice = Gestionar_Ficheros.indiceALPInvitado(personas, dni);

			// mensajes de error, para validar los campos
			if (dni == "" || nombre == "" || fechaNac == null || email == "" || telefono == "" || tipInvitado == null) {
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
				etiquetaError(mensajeError, "TIENES QUE SER MAYOR DE 16");
				return;
			}

			Invitado nuevoInvitado = new Invitado(dni, nombre, fechaNac, email, telefono, tipInvitado, asistencia);
			personas.add(nuevoInvitado);
			// sobreescribo sin mas a mi archivo
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA_AUX);
			String mensaje = Gestionar_Ficheros.actualizarPersonas();

			if (mensaje != null)
				mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

			invitados2b(panel);

		});

		btnCancelar.setOnAction(e -> {
			invitados2b(panel);
		});

		contenedor.getChildren().addAll(titulo, tfDni, tfNombre, dpFechaNac, tfEmail, tfTelefono, cbTipInv, botones,
				mensajeError);

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	private static void formModiInvitado(GridPane panel) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("MODIFICAR INVITADO");
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
			indice = Gestionar_Ficheros.indiceALPInvitado(personas, dni);

			if (indice != -1) {
				formAgregarInvTrue(panel, dni);
			} else {
				etiquetaError(mensajeError, "EL DNI NO EXISTE");
				return;
			}

		});

		btnCancelar.setOnAction(e -> invitados2b(panel));

		contenedor.getChildren().addAll(titulo, tfDni, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	private static void formAgregarInvTrue(GridPane panel, String dni) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("AGREGAR INVITADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfNombre = new TextField();
		tfNombre.setPromptText("Nombre");

		DatePicker dpFechaNac = new DatePicker();
		dpFechaNac.setPromptText("Fecha de nacimiento");

		TextField tfEmail = new TextField();
		tfEmail.setPromptText("Email");

		TextField tfTelefono = new TextField();
		tfTelefono.setPromptText("Teléfono");

		ComboBox<TipoInvitado> cbTipInv = new ComboBox<>();
		cbTipInv.getItems().addAll(TipoInvitado.values());
		cbTipInv.setPromptText("Tipo de invitado");

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
			String nombre = tfNombre.getText();
			LocalDate fechaNac = dpFechaNac.getValue();
			String email = tfEmail.getText();
			String telefono = tfTelefono.getText();
			TipoInvitado tipInvitado = cbTipInv.getValue();
			ArrayList<Asistencia> asistencia = new ArrayList<Asistencia>();

			int indice = Gestionar_Ficheros.indiceALPInvitado(personas, dni);

			// mensajes de error, para validar los campos
			if (nombre == "" || fechaNac == null || email == "" || telefono == "" || tipInvitado == null) {
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
				etiquetaError(mensajeError, "TIENES QUE SER MAYOR DE 16");
				return;
			}

			Invitado nuevoInvitado = new Invitado(dni, nombre, fechaNac, email, telefono, tipInvitado, asistencia);
			personas.set(indice, nuevoInvitado);
			// sobreescribo sin mas a mi archivo
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA);
			String mensaje = Gestionar_Ficheros.actualizarPersonas();

			if (mensaje != null)
				mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

			invitados2b(panel);

		});

		btnCancelar.setOnAction(e -> {
			invitados2b(panel);
		});

		contenedor.getChildren().addAll(titulo, tfNombre, dpFechaNac, tfEmail, tfTelefono, cbTipInv, botones,
				mensajeError);

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	// FORMULARIOS PARA GESTION DE ASISTENCIA
	// formulario principal
	// formulario principal para gestionar asistencia
	private static void formGestionAsistencia(GridPane panel) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 500);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("GESTIONAR ASISTENCIA DE INVITADO");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfDni = new TextField();
		tfDni.setPromptText("DNI");

		Button BtnBuscarAsistencia = crearBotonMenu("Buscar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");

		HBox botones = new HBox(10, BtnBuscarAsistencia);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(BtnBuscarAsistencia, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		// llamar si existe el dni sin mas
		BtnBuscarAsistencia.setOnAction(e -> {
			int indice;
			String dni = tfDni.getText().toUpperCase();
			indice = Gestionar_Ficheros.indiceALPInvitado(personas, dni);

			if (indice != -1) {
				gestionAsistencia3b(panel, (Invitado) personas.get(indice));
			} else {
				etiquetaError(mensajeError, "EL DNI NO EXISTE");
				return;
			}

		});

		contenedor.getChildren().addAll(titulo, tfDni, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	// formulario para agregar asistencia
	private static void formAgregarAsistencia(GridPane panel, Invitado invitado) {
		ArrayList<Persona> personas = Gestionar_Ficheros.leerFicheroPersona(PERSONA);

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 550);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;");

		Label titulo = new Label("AGREGAR ASISTENCIA");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		Label infoInvitado = new Label("Invitado: " + invitado.getNombre() + " (" + invitado.getDni() + ")");
		infoInvitado.setStyle("-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #2C3E50;");

		TextField tfCodigo = new TextField();
		tfCodigo.setPromptText("Código de asistencia (ej: AST005)");

		DatePicker dpFechaIni = new DatePicker();
		dpFechaIni.setPromptText("Fecha de inicio");

		DatePicker dpFechaFin = new DatePicker();
		dpFechaFin.setPromptText("Fecha de fin");

		// ComboBox para valoración
		ComboBox<Integer> cbValoracion = new ComboBox<>();
		cbValoracion.getItems().addAll(1, 2, 3, 4, 5);
		cbValoracion.setPromptText("Valoración (1-5)");

		// CheckBox para las secciones disponibles
		Label lblSecciones = new Label("Seleccione las secciones:");
		lblSecciones.setStyle("-fx-font-weight: bold;");

		CheckBox chkBuceo = new CheckBox("BUCEO");
		CheckBox chkBicicleta = new CheckBox("BICICLETA");
		CheckBox chkParacaidas = new CheckBox("PARACAIDAS");
		CheckBox chkFiestaJoven = new CheckBox("FIESTA-JOVEN");

		VBox seccionesBox = new VBox(5, lblSecciones, chkBuceo, chkBicicleta, chkParacaidas, chkFiestaJoven);
		seccionesBox.setStyle(
				"-fx-background-color: rgba(255, 255, 255, 0.3);" + "-fx-padding: 10;" + "-fx-background-radius: 5;");

		Button btnGuardar = crearBotonMenu("Guardar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		HBox botones = new HBox(10, btnGuardar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnGuardar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		btnGuardar.setOnAction(e -> {
			String codigo = tfCodigo.getText().trim();
			LocalDate fechaIni = dpFechaIni.getValue();
			LocalDate fechaFin = dpFechaFin.getValue();
			Integer valoracion = cbValoracion.getValue();

			// Validaciones
			if (codigo.isEmpty()) {
				etiquetaError(mensajeError, "DEBE INGRESAR UN CÓDIGO");
				return;
			}
			if (fechaIni == null) {
				etiquetaError(mensajeError, "DEBE SELECCIONAR FECHA DE INICIO");
				return;
			}
			if (fechaFin == null) {
				etiquetaError(mensajeError, "DEBE SELECCIONAR FECHA DE FIN");
				return;
			}
			if (fechaFin.isBefore(fechaIni)) {
				etiquetaError(mensajeError, "LA FECHA DE FIN DEBE SER POSTERIOR A LA FECHA DE INICIO");
				return;
			}

			// Obtener secciones seleccionadas
			ArrayList<String> seccionesSeleccionadas = new ArrayList<>();
			if (chkBuceo.isSelected())
				seccionesSeleccionadas.add("BUCEO");
			if (chkBicicleta.isSelected())
				seccionesSeleccionadas.add("BICICLETA");
			if (chkParacaidas.isSelected())
				seccionesSeleccionadas.add("PARACAIDAS");
			if (chkFiestaJoven.isSelected())
				seccionesSeleccionadas.add("FIESTA-JOVEN");

			if (seccionesSeleccionadas.isEmpty()) {
				etiquetaError(mensajeError, "DEBE SELECCIONAR AL MENOS UNA SECCIÓN");
				return;
			}

			// Verificar que el código no exista ya
			for (Asistencia asist : invitado.getAsistencia()) {
				if (asist.getCodigo().equalsIgnoreCase(codigo)) {
					etiquetaError(mensajeError, "EL CÓDIGO DE ASISTENCIA YA EXISTE");
					return;
				}
			}

			// Crear nueva asistencia
			Asistencia nuevaAsistencia = new Asistencia(codigo, fechaIni, fechaFin, valoracion, seccionesSeleccionadas);

			// Agregar la asistencia al invitado
			invitado.getAsistencia().add(nuevaAsistencia);

			// Actualizar en el archivo
			int indice = Gestionar_Ficheros.indiceALPInvitado(personas, invitado.getDni());
			personas.set(indice, invitado);
			Gestionar_Ficheros.sobreEscribirPersona(personas, PERSONA_AUX);
			String mensaje = Gestionar_Ficheros.actualizarPersonas();
			if (mensaje != null)
				mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);

			// Volver a la vista de gestión de asistencia
			gestionAsistencia3b(panel, invitado);
		});

		btnCancelar.setOnAction(e -> {
			gestionAsistencia3b(panel, invitado);
		});

		contenedor.getChildren().addAll(titulo, infoInvitado, tfCodigo, dpFechaIni, dpFechaFin, cbValoracion,
				seccionesBox, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);
	}

	// form filtrado boton 4
	private static void formFiltradoEntreDosFechas(GridPane panel) {

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(700, 250);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;" + "-fx-border-color: #cccccc;");

		Label titulo = new Label("Introduce las fechas");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		DatePicker dpFecha1 = new DatePicker();
		dpFecha1.setPromptText("Primera fecha");

		DatePicker dpFecha2 = new DatePicker();
		dpFecha2.setPromptText("Segunda fecha");

		Button btnAplicar = crearBotonMenu("Aplicar filtros", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		HBox botones = new HBox(10, btnAplicar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnAplicar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		// acciones de los botones
		btnAplicar.setOnAction(e -> {
			LocalDate fecha1 = dpFecha1.getValue();
			LocalDate fecha2 = dpFecha2.getValue();

			if (fecha1 == null || fecha2 == null) {
				etiquetaError(mensajeError, "DEBES SELECCIONAR AMBAS FECHAS");
				return;
			}

			if (fecha2.isBefore(fecha1)) {
				etiquetaError(mensajeError, "LA SEGUNDA FECHA NO PUEDE SER ANTERIOR A LA PRIMERA");
				return;
			}

			ArrayList<Invitado> listaInvitados = Gestionar_Ficheros.listInvitado(PERSONA);

			ArrayList<Invitado> filtrados = Gestionar_Ficheros.filtrarInvitadosEntreFechas(listaInvitados, fecha1,
					fecha2);

			if (filtrados.isEmpty()) {
				etiquetaError(mensajeError, "NO HAY INVITADOS ENTRE ESAS FECHAS");
				return;
			}

			invitadosFiltrados(panel, filtrados);

		});

		btnCancelar.setOnAction(e -> filtrado4b(panel));

		contenedor.getChildren().addAll(titulo, dpFecha1, dpFecha2, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);

	}

	private static void formFiltradoPorEdad(GridPane panel) {

		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setMaxSize(600, 210);

		contenedor.setStyle("-fx-background-color: " + FONDO_TRANSPARENTE + ";" + "-fx-background-radius: 10;"
				+ "-fx-border-radius: 10;" + "-fx-border-color: #cccccc;");

		Label titulo = new Label("Introduce la edad");
		titulo.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");

		TextField tfEdad = new TextField();
		tfEdad.setPromptText("Edad");

		Button btnAplicar = crearBotonMenu("Aplicar filtro", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnCancelar = crearBotonMenu("Cancelar", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		HBox botones = new HBox(10, btnAplicar, btnCancelar);
		botones.setAlignment(Pos.CENTER);
		HBox.setHgrow(btnAplicar, Priority.ALWAYS);
		HBox.setHgrow(btnCancelar, Priority.ALWAYS);

		Label mensajeError = new Label();
		mensajeError.setWrapText(true);

		btnAplicar.setOnAction(e -> {
			String edadTexto = tfEdad.getText().trim();

			if (edadTexto.isEmpty() || !edadTexto.matches("\\d+")) {
				etiquetaError(mensajeError, "INTRODUCE UNA EDAD VÁLIDA");
				return;
			}

			int edad = Integer.parseInt(edadTexto);

			ArrayList<Invitado> listaInvitados = Gestionar_Ficheros.listInvitado(PERSONA);

			ArrayList<Invitado> filtrados = Gestionar_Ficheros.filtrarInvitadosPorEdad(listaInvitados, edad);

			if (filtrados.isEmpty()) {
				etiquetaError(mensajeError, "NO HAY INVITADOS CON ESA EDAD");
				return;
			}

			invitadosFiltrados(panel, filtrados);

		});
		/*
		 * String edad = tfEdad.getText();
		 * 
		 * if (edad == "") { etiquetaError(mensajeError,
		 * "TODOS LOS CAMPOS SON NECESARIOS"); return; } else {
		 * 
		 * 
		 * filtrado4b(panel); }
		 * 
		 * });
		 */

		btnCancelar.setOnAction(e -> filtrado4b(panel));

		contenedor.getChildren().addAll(titulo, tfEdad, botones, mensajeError);

		eliminarElementoGrid(1, 0, panel);

		panel.add(contenedor, 1, 0);

		GridPane.setHalignment(contenedor, HPos.CENTER);
		GridPane.setValignment(contenedor, VPos.CENTER);

	}

	private static HBox grudFiltrado4b(GridPane panel) {
		HBox box = new HBox(15);
		box.setAlignment(Pos.CENTER);
		box.setFillHeight(true);

		// botones
		Button btnFiltradoIzquierda = crearBotonMenu("Entre dos fechas (ASISTENCIA)", "rgba(0, 128, 0, 0.7)",
				"rgba(0, 200, 0, 0.7)");
		Button btnFiltradoDerecha = crearBotonMenu("Mayor que... (INVITADOS)", "rgba(0, 128, 0, 0.7)",
				"rgba(0, 200, 0, 0.7)");

		// digo, que estos tienen prioridad al momento de ordenar
		HBox.setHgrow(btnFiltradoIzquierda, Priority.ALWAYS);
		HBox.setHgrow(btnFiltradoDerecha, Priority.ALWAYS);

		box.getChildren().addAll(btnFiltradoIzquierda, btnFiltradoDerecha);

		btnFiltradoIzquierda.setOnAction(e -> formFiltradoEntreDosFechas(panel));
		btnFiltradoDerecha.setOnAction(e -> formFiltradoPorEdad(panel));

		return box;
	}

	private static void invitadosFiltrados(GridPane panel, ArrayList<Invitado> listaFiltrada) {

		VBox contenedor = new VBox(15);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setPadding(new Insets(20));

		Label titulo = crearTitulo("INVITADOS FILTRADOS POR EDAD");

		contenedor.getChildren().addAll(titulo, tablaInvitados(listaFiltrada));

		eliminarElementoGrid(1, 0, panel);
		panel.add(contenedor, 1, 0);
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

		btnAgregar.setOnAction(e -> formAgregarInvitados(panel));
		btnModificar.setOnAction(e -> formModiInvitado(panel));

		return box;
	}

	private static HBox grudAsistencia3b(GridPane panel, Invitado invitado) {
		HBox box = new HBox(15);
		box.setAlignment(Pos.CENTER);
		box.setFillHeight(true);

		// botones
		Button btnAgregar = crearBotonMenu("Agregar", "rgba(0, 128, 0, 0.7)", "rgba(0, 200, 0, 0.7)");
		Button btnModificar = crearBotonMenu("Atras", "rgba(255, 0, 0, 0.5)", "rgba(255, 50, 50, 0.7)");

		// digo, que estos tienen prioridad al momento de ordenar
		HBox.setHgrow(btnAgregar, Priority.ALWAYS);
		HBox.setHgrow(btnModificar, Priority.ALWAYS);

		box.getChildren().addAll(btnAgregar, btnModificar);

		btnAgregar.setOnAction(e -> formAgregarAsistencia(panel, invitado));
		btnModificar.setOnAction(e -> formGestionAsistencia(panel));

		return box;
	}

	private static HBox grudRankingb5(GridPane panel) {

		HBox box = new HBox(15);
		box.setAlignment(Pos.CENTER);
		box.setFillHeight(true);

		// Botones de estadísticas
		Button btnEstadisticaAsistencias = crearBotonMenu("Ranking Asistencias", "rgba(0, 0, 180, 0.6)",
				"rgba(50, 50, 255, 0.8)");

		Button btnEstadisticaValoracion = crearBotonMenu("Valoraciones", "rgba(90, 0, 120, 0.6)",
				"rgba(150, 0, 200, 0.8)");

		Button btnEstadisticaSecciones = crearBotonMenu("Secciones más visitadas", "rgba(120, 120, 0, 0.6)",
				"rgba(200, 200, 0, 0.8)");

		HBox.setHgrow(btnEstadisticaAsistencias, Priority.ALWAYS);
		HBox.setHgrow(btnEstadisticaValoracion, Priority.ALWAYS);
		HBox.setHgrow(btnEstadisticaSecciones, Priority.ALWAYS);

		box.getChildren().addAll(btnEstadisticaAsistencias, btnEstadisticaValoracion, btnEstadisticaSecciones);

		// funciones para la estadistica
		btnEstadisticaAsistencias.setOnAction(e -> crearTablaRanking1(panel));
		btnEstadisticaValoracion.setOnAction(e -> crearTablaSecciones2(panel));
		btnEstadisticaSecciones.setOnAction(e -> crearTablaSecciones3(panel));

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

	// alerta error
	private static void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
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
