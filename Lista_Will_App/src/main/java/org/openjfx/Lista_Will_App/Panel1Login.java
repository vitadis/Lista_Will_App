package org.openjfx.Lista_Will_App;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Panel1Login {
	
	private static final File PERSONAS_FILE = new File("Persona.dat");
	
	@SuppressWarnings("exports")
	public static VBox getLoginView(Runnable onLoginSuccess) {
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(50));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");
		
		// Título
		Label titulo = new Label("Lista Will - Sistema de Gestión");
		titulo.setFont(Font.font("Arial", FontWeight.BOLD, 32));
		titulo.setStyle("-fx-text-fill: white;");
		
		Label subtitulo = new Label("Inicio de Sesión");
		subtitulo.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
		subtitulo.setStyle("-fx-text-fill: white;");
		
		// Panel de login
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(15);
		gridPane.setPadding(new Insets(30));
		gridPane.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
		gridPane.setMaxWidth(400);
		
		// Usuario
		Label lblUsuario = new Label("Usuario:");
		lblUsuario.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		TextField txtUsuario = new TextField();
		txtUsuario.setPromptText("Ingrese su nombre de usuario");
		txtUsuario.setPrefWidth(250);
		txtUsuario.setStyle("-fx-font-size: 14px;");
		
		// Contraseña
		Label lblPassword = new Label("Contraseña:");
		lblPassword.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Ingrese su contraseña");
		txtPassword.setPrefWidth(250);
		txtPassword.setStyle("-fx-font-size: 14px;");
		
		// Botón de login
		Button btnLogin = new Button("Iniciar Sesión");
		btnLogin.setPrefWidth(250);
		btnLogin.setPrefHeight(40);
		btnLogin.setStyle("-fx-background-color: #667eea; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5;");
		btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("-fx-background-color: #5568d3; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-cursor: hand;"));
		btnLogin.setOnMouseExited(e -> btnLogin.setStyle("-fx-background-color: #667eea; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5;"));
		
		// Acción del botón
		btnLogin.setOnAction(e -> {
			String usuario = txtUsuario.getText().trim();
			String password = txtPassword.getText();
			
			if (usuario.isEmpty() || password.isEmpty()) {
				mostrarAlerta("Error", "Por favor, complete todos los campos", Alert.AlertType.ERROR);
				return;
			}
			
			if (Gestionar_Ficheros.existeAdmin(PERSONAS_FILE, usuario, password)) {
				mostrarAlerta("Éxito", "Bienvenido, " + usuario + "!", Alert.AlertType.INFORMATION);
				onLoginSuccess.run();
			} else {
				mostrarAlerta("Error", "Usuario o contraseña incorrectos o no tiene permisos de administrador", Alert.AlertType.ERROR);
				txtPassword.clear();
			}
		});
		
		// Permitir login con Enter
		txtPassword.setOnAction(e -> btnLogin.fire());
		txtUsuario.setOnAction(e -> txtPassword.requestFocus());
		
		// Agregar componentes al grid
		gridPane.add(lblUsuario, 0, 0);
		gridPane.add(txtUsuario, 0, 1);
		gridPane.add(lblPassword, 0, 2);
		gridPane.add(txtPassword, 0, 3);
		gridPane.add(btnLogin, 0, 4);
		
		vbox.getChildren().addAll(titulo, subtitulo, gridPane);
		
		return vbox;
	}
	
	private static void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
