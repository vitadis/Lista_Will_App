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

		stage.setOnCloseRequest(e -> {
			e.consume();

			if (confirmarSalida()) {
				Platform.exit();
			}
		});

		stage.setScene(scene);
		stage.show();
	}

	// creo un Alert
	private boolean confirmarSalida() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Cerrar aplicación");
		alert.setContentText("¿Quieres salir?");

		Optional<ButtonType> result = alert.showAndWait();

		return result.isPresent() && result.get() == ButtonType.OK;
	}

	public static void main(String[] args) {
		launch();
	}

}