package org.openjfx.Lista_Will_App;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class GestionVentanas {
	// ------PALETA DE COLORES--------
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String COLOR_SECONDARY = "#1976D2";
	private static final String COLOR_LIGHT = "#42A5F5";
	// private static final String COLOR_DARK = "#0D47A1";
	// private static final String COLOR_ACCENT = "#2196F3";
	// private static final String COLOR_BG = "#E3F2FD";

	@SuppressWarnings("exports")
	public static GridPane panel2(final int WIDTH, final int HEIGHT) {
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
		VBox menIzq = parteIzqP2();

		panel.add(menIzq, 0, 0);
		panel.setGridLinesVisible(true);

		return panel;

	}

	private static VBox parteIzqP2() {

		VBox menuPanel = new VBox(20);
		menuPanel.setAlignment(Pos.CENTER);
		menuPanel.setPadding(new Insets(15));

		// creo los botones
		Button menu1 = crearBotonMenu("1. Empleados");
		Button menu2 = crearBotonMenu("2. Invitados");
		Button menu3 = crearBotonMenu("3. Gestionar asistencia de invitados");
		Button menu4 = crearBotonMenu("4. Filtrado de Invitados");
		Button menu5 = crearBotonMenu("7. Salir");

		// agrego la accion de los botones
		menu1.setOnAction(e -> System.out.println("Empleados"));
		menu2.setOnAction(e -> System.out.println("Invitados"));
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
	
	
	// tabla de empleados
	
	
	
	
	
	
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

	// MIS NOTAS
	// PUEDO PEDIR COMO PARAMETRO, EL PROPIO GRIDPANE, PARA REALIZAR LOS CAMBIOS
	// DENTRO DEL MISMO MEDIANTE STATIC VOID
}
