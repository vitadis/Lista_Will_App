package org.openjfx.Lista_Will_App;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import clases.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

// dependencias para eliminar del grid
import javafx.scene.Node;
import java.util.Iterator;

public class GestionVentanas {
	// ------PALETA DE COLORES--------
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String COLOR_SECONDARY = "#1976D2";
	private static final String COLOR_LIGHT = "#42A5F5";
	private static final String COLOR_DARK = "#0D47A1";
	// private static final String COLOR_ACCENT = "#2196F3";
	// private static final String COLOR_BG = "#E3F2FD";
	
	// ------TAMAÑO TOTAL DE LA VENTANA.------------
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int WIDTH = screenSize.width;
	final static int HEIGHT = screenSize.height - 70;

	// -----ARCHIVOS---------
	private static final File PERSONA = new File("Persona.dat");
	// private static final File SECCION = new File("Seccion.dat");

	@SuppressWarnings("exports")
	public static GridPane panel2() {
		GridPane panel = new GridPane();

		// creo las columnas
		ColumnConstraints col1 = new ColumnConstraints(WIDTH * 0.20);
		ColumnConstraints col2 = new ColumnConstraints(WIDTH * 0.80);

		// creo la fila y defino su altura
		RowConstraints row = new RowConstraints();
		row.setMinHeight(HEIGHT);

		panel.getRowConstraints().add(row);
		panel.getColumnConstraints().addAll(col1, col2);

		// creamos un nuevoVBox
		VBox menIzq = parteIzqP2(panel);

		panel.add(menIzq, 0, 0);
		panel.setGridLinesVisible(true);

		return panel;

	}

	private static VBox parteIzqP2(GridPane panel) {

		VBox menuPanel = new VBox(20);
		menuPanel.setAlignment(Pos.CENTER);
		menuPanel.setPadding(new Insets(15));

		// creo los botones
		Button menu1 = crearBotonMenu("1. Empleados");
		Button menu2 = crearBotonMenu("2. Invitados");
		Button menu3 = crearBotonMenu("3. Gestionar asistencia de invitados");
		Button menu4 = crearBotonMenu("4. Filtrado de Invitados");
		Button menu5 = crearBotonMenu("5. Salir");

		// agrego la accion de los botones
		menu1.setOnAction(e -> empleados1b(panel));
		menu2.setOnAction(e -> empleados2b(panel));
		menu3.setOnAction(e -> System.out.println("Gestionar Asistencia de invitados"));
		menu4.setOnAction(e -> System.out.println("Filtrado de invitados"));
		menu5.setOnAction(e -> System.out.println("Mensaje de la salida del boton"));

		Label titulo = new Label("ISLA DE WILL"); // creo el label del nombre del menu
		titulo.setStyle("-fx-text-fill: " + COLOR_SECONDARY + ";" + "-fx-font-size: 20px;" + "-fx-font-weight: 900;"
				+ "-fx-font-family: 'Arial Black';");

		menuPanel.getChildren().addAll(titulo, new Separator(), menu1, menu2, menu3, menu4, new Region(), menu5);

		// lo que hacemos aqui es establecer el 2do elemento del array de children
		VBox.setVgrow(menuPanel.getChildren().get(menuPanel.getChildren().size() - 2), Priority.ALWAYS);
		// con Priority.AlWAYS (le digo la que se rellene verticalmente tomando en
		// cuenta el penultimo hijo)

		return menuPanel;
	}

	// V2 1er button Empleados
	@SuppressWarnings("exports")
	public static void empleados1b(GridPane panel) {
		ArrayList<Empleado> listaPer = Gestionar_Ficheros.listEmpleado(PERSONA);
		
		VBox empBox = new VBox(15);
		empBox.setAlignment(Pos.CENTER);
		
		empBox.setPadding(new Insets(20));
		// titulo 
		Label titulo = new Label("TABLA EMPLEADOS");
		titulo.setStyle("-fx-text-fill: " + COLOR_DARK + ";" + "-fx-font-size: 20px;" + "-fx-font-weight: 900;"
				+ "-fx-font-family: 'Arial Black';");
		
		empBox.getChildren().addAll(titulo,tablaEmpleados(listaPer),new Region(),groudEmpleados2b());
		VBox.setVgrow(empBox.getChildren().get(empBox.getChildren().size() - 2), Priority.ALWAYS);

		eliminarElementoEn(1,0,panel);
		panel.add(empBox, 1, 0);
		
	}

	// tabla de empleados
	@SuppressWarnings({ "unchecked" })
	private static TableView<Empleado> tablaEmpleados(ArrayList<Empleado> lista) {
		// tamaño de cada columna
		double tamColumn = (WIDTH*0.8-40)/8;
		
		TableView<Empleado> table = new TableView<>();
		table.setStyle("-fx-background-color: transparent;");

		TableColumn<Empleado, String> colDni = new TableColumn<>("DNI");
		colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		colDni.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colNombre = new TableColumn<>("Nombre");
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colNombre.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colFechaNac = new TableColumn<>("Fecha_Nacimiento");
		colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
		colFechaNac.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colMail = new TableColumn<>("Email");
		colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colMail.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colTelf = new TableColumn<>("Telefono");
		colTelf.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		colTelf.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colCod = new TableColumn<>("Codigo");
		colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colCod.setPrefWidth(tamColumn);
		TableColumn<Empleado, String> colCargo = new TableColumn<>("Cargo");
		colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		colCargo.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colActivo = new TableColumn<>("Activo");
		colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
		colActivo.setPrefWidth(tamColumn);

		// agrego las columnas
		table.getColumns().addAll(colDni, colNombre, colFechaNac, colMail, colTelf, colCod, colCargo, colActivo);

		// agrego las filas
		ObservableList<Empleado> data = FXCollections.observableArrayList(lista);
		table.setItems(data);
		
		return table;
	}
	
	//grudEmpleados2b
	private static HBox groudEmpleados2b() {
		HBox contenedor = new HBox(15);
		contenedor.setAlignment(Pos.CENTER);
		
		// botones para el grud
		Button btnEliminar = crearBotonMenu("Eliminar usuario");
		Button btnAgregar = crearBotonMenu("Agregar usuario");
		Button btnModificar = crearBotonMenu("Modificar usuario");
		
		// agrego las funciones del button
		btnEliminar.setOnAction(e -> System.out.println("Eliminar"));
		btnAgregar.setOnAction(e -> System.out.println("Agregar"));
		btnModificar.setOnAction(e -> System.out.println("modificar"));

		contenedor.getChildren().addAll(btnEliminar,btnAgregar,btnModificar);

		
		return contenedor;
	}
	
	
	// V2 2do button Empleados
	// cambia el arraylist de invitados
	@SuppressWarnings("exports")
	public static void empleados2b(GridPane panel) {
		ArrayList<Empleado> listaPer = Gestionar_Ficheros.listEmpleado(PERSONA);
		
		VBox empBox = new VBox(15);
		empBox.setAlignment(Pos.CENTER);
		
		empBox.setPadding(new Insets(20));
		// titulo 
		Label titulo = new Label("TABLA INVITADOS");
		titulo.setStyle("-fx-text-fill: " + COLOR_DARK + ";" + "-fx-font-size: 20px;" + "-fx-font-weight: 900;"
				+ "-fx-font-family: 'Arial Black';");
		
		empBox.getChildren().addAll(titulo,tablaInvitados(listaPer),new Region(),new Label("Hola"));
		VBox.setVgrow(empBox.getChildren().get(empBox.getChildren().size() - 2), Priority.ALWAYS);

		eliminarElementoEn(1,0,panel);
		
		panel.add(empBox, 1, 0);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private static TableView<Empleado> tablaInvitados(ArrayList<Empleado> lista) {
		// tamaño de cada columna
		double tamColumn = (WIDTH*0.8-40)/8;
		
		TableView<Empleado> table = new TableView<>();
		table.setStyle("-fx-background-color: transparent;");

		TableColumn<Empleado, String> colDni = new TableColumn<>("DNI");
		colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		colDni.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colNombre = new TableColumn<>("Nombre");
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colNombre.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colFechaNac = new TableColumn<>("Fecha_Nacimiento");
		colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
		colFechaNac.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colMail = new TableColumn<>("Email");
		colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colMail.setPrefWidth(tamColumn);

		TableColumn<Empleado, String> colTelf = new TableColumn<>("Telefono");
		colTelf.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		colTelf.setPrefWidth(tamColumn);

		// agrega los campos del invitado, cambia la clase a invitado

		// agrego las columnas
		table.getColumns().addAll(colDni, colNombre, colFechaNac, colMail, colTelf);

		// agrego las filas
		ObservableList<Empleado> data = FXCollections.observableArrayList(lista);
		table.setItems(data);
		
		return table;
	}
	
	
	
	
	
	
	// persona a trabajador

	// -------------Plantilla de nodos repetitivos---------------

	/**
	 * <h1>-------MÉTODO GENERAL PARA CREAR EL BOTÓN-------</h1> Se usara para
	 * gestionar los eventos, por ejemplo para el control del menu o funciones
	 * especificas que queramos visualizar
	 */
	private static Button crearBotonMenu(String texto_del_boton) {
		Button btn = new Button(texto_del_boton);

		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setPrefHeight(45);

		String mainStyle = "-fx-background-color: " + COLOR_SECONDARY + "; " + "-fx-text-fill: white; "
				+ "-fx-font-size: 14px; " + "-fx-cursor: hand; " + "-fx-background-radius: 5;";

		String secStyle = "-fx-background-color: " + COLOR_LIGHT + "; " + "-fx-text-fill: white; "
				+ "-fx-font-size: 14px; " + "-fx-cursor: hand; " + "-fx-background-radius: 5;";

		btn.setStyle(mainStyle);
		// hover del boton
		btn.setOnMouseEntered(e -> btn.setStyle(secStyle));
		btn.setOnMouseExited(e -> btn.setStyle(mainStyle));

		return btn;
	}
	
	// ELIMINAR ELEMENTOS DE UN GRIDPANE
	private static void eliminarElementoEn(final int col, final int row, GridPane gridPane) {
	    ObservableList<Node> children = gridPane.getChildren();
	    // Usamos un iterador para eliminar elementos de la lista mientras iteramos
	    for (Iterator<Node> iterator = children.iterator(); iterator.hasNext();) {
	        Node node = iterator.next();
	        Integer nodeCol = GridPane.getColumnIndex(node);
	        Integer nodeRow = GridPane.getRowIndex(node);

	        // Verificamos si la columna y fila coinciden. Manejamos nulos si es necesario.
	        boolean colMatches = (nodeCol != null && nodeCol == col);
	        boolean rowMatches = (nodeRow != null && nodeRow == row);

	        if (colMatches && rowMatches) {
	            iterator.remove(); // Elimina el nodo del GridPane de forma segura
	            break; // Si solo hay uno por celda, salimos
	        }
	    }
	}
	

	// MIS NOTAS
	// PUEDO PEDIR COMO PARAMETRO, EL PROPIO GRIDPANE, PARA REALIZAR LOS CAMBIOS
	// DENTRO DEL MISMO MEDIANTE STATIC VOID
}
