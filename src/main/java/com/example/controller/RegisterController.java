package com.example.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML private TextField codeField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        String code = codeField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!"1234".equals(code)) {
            showAlert("Code invalide !");
            return;
        }

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Remplis tous les champs !");
            return;
        }

        String hash = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        try (Connection conn = com.example.DAO.Database.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hash);
                stmt.executeUpdate();
            }

            showAlert("Compte créé !");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur lors de la création !");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}
