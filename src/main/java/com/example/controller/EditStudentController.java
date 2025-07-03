package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditStudentController {
    @FXML private TextField prenomField;
    @FXML private TextField nomField;
    @FXML private TextField classeField;
    @FXML private TextField ageField;
    @FXML private TextField moyenneField;

    @FXML
    private void handleValidate() {
        System.out.println("Modifications enregistrées !");
        
    }
}
