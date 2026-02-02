package org.openjfx.Lista_Will_App;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	// Archivos de secciones

	// tamaño de la ventana
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final int WIDTH = screenSize.width;
	final int HEIGHT = screenSize.height - 70;

	@Override

	public void start(@SuppressWarnings("exports") Stage stage) {

		// Iniciar con la pantalla de login

		/*var loginScene = new Scene(Panel1Login.getLoginView(() -> {

			stage.setScene(new Scene(GestionPanel2.panel2(), WIDTH, HEIGHT));

		}), WIDTH, HEIGHT);*/
		
		var loginScene = new Scene(GestionPanel2.panel2(), WIDTH, HEIGHT);

		// e.- Es el parametro del evento
		stage.setOnCloseRequest(e -> {
			e.consume(); // detengo el proceso de apagar
			if (confirmarSalida()) {
				Platform.exit();
			}
		});
		stage.setTitle("Isla de Will - Sistema de Gestión");
		stage.setScene(loginScene);
		stage.show();
	}

	// creo un Alert
	public static boolean confirmarSalida() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Cerrar aplicación");
		alert.setContentText("¿Quieres salir de la isla de Willgo?");

		// creo un valor opcional, donde le pido el siguiente metodo, retorna el tipo
		// del boton
		Optional<ButtonType> result = alert.showAndWait();

		// retorn un boolean, donde si esta presente y pulso ok, retorna true, de lo
		// contrario false
		return result.isPresent() && result.get() == ButtonType.OK;
	}

	public static void main(String[] args) {

		Gestionar_Ficheros.sobreEscribirSec();
		Gestionar_Ficheros.inicializarDatos();
		launch();
	}

}