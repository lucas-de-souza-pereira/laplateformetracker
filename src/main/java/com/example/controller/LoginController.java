package com.example.controller;

import com.example.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        // String user = usernameField.getText();
        // String pass = passwordField.getText();

        String user = "admin";
        String pass = "admin";

        if (user.equals("admin") && pass.equals("admin")) {
            SceneManager.switchScene("dashboard");
        } else {
            System.out.println("Identifiants incorrects !");
        }
    }
}
