package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditStudentController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField classField;
    @FXML private TextField ageField;
    @FXML private TextField averageField;

    @FXML
    private void handleValidate() {
        System.out.println("Modifications enregistrées !");
        
    }
}
