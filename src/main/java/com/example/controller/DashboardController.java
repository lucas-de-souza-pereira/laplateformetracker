package com.example.controller;

import com.example.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    @FXML private Button logoutButton;

    @FXML
    private void initialize() {
        logoutButton.setOnAction(e -> SceneManager.switchScene("login"));
    }
}
