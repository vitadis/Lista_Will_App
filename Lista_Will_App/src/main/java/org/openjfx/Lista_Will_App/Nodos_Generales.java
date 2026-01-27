package org.openjfx.Lista_Will_App;

import javafx.scene.control.Button;

public class Nodos_Generales {

	// PALETA DE COLORES
	// private static final String COLOR_PRIMARY = "#1565C0";
	private static final String COLOR_SECONDARY = "#1976D2";
	private static final String COLOR_LIGHT = "#42A5F5";
	// private static final String COLOR_DARK = "#0D47A1";
	// private static final String COLOR_ACCENT = "#2196F3";
	// private static final String COLOR_BG = "#E3F2FD";

	
	// Métodos los cuales facilitarán la construcción de objetos

	/**
	 * <h1>-------MÉTODO GENERAL PARA CREAR EL BOTÓN-------</h1> Se usara para
	 * gestionar los eventos, por ejemplo para el control del menu o funciones
	 * especificas que queramos visualizar
	 */
	@SuppressWarnings("exports")
	public static Button crearBotonMenu(String texto_del_boton) {
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
	
	// CREEN OBJETOS GENERALES, LOS CUALES USAREMOS
}
