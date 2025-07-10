package com.example.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.DAO.UserDAO;

public class RegisterController {
    @FXML private TextField codeField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;


    @FXML
    private void handleRegister() {
        String code = codeField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (!code.equals("1234")) {
            showAlert("Code incorrect.");
            return;
        }

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }

        boolean success = UserDAO.register(username, password);
        if (success) {
            showAlert("Compte créé !");
        } else {
            showAlert("Erreur lors de la création.");
        }
    }



    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}
