package org.openjfx.Lista_Will_App;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.scene.Scene;
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

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}