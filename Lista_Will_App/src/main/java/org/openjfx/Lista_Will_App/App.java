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
	// tamaño de la ventana
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final int WIDTH = screenSize.width;
	final int HEIGHT = screenSize.height - 70;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) {
		// agrego el panel 2
		var panel2 = GestionVentanas.panel2();
		var scene = new Scene(panel2, WIDTH, HEIGHT);

		// e.- Es el parametro del evento 
		stage.setOnCloseRequest(e -> {

			if (confirmarSalida()) {
				Platform.exit();
			}
		});

		stage.setScene(scene);
		stage.show();
	}

	// creo un Alert
	public static boolean confirmarSalida() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Cerrar aplicación");
		alert.setContentText("¿Quieres salir?");

		// creo un valor opcional, donde le pido el siguiente metodo, retorna el tipo
		// del boton
		Optional<ButtonType> result = alert.showAndWait();

		// retorn un boolean, donde si esta presente y pulso ok, retorna true, de lo
		// contrario false
		return result.isPresent() && result.get() == ButtonType.OK;
	}

	public static void main(String[] args) {
		launch();
	}

}