package com.example.controller;

import com.example.DAO.StudentDAO;
import com.example.model.Student;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField classField;
    @FXML private TextField ageField;
    @FXML private TextField averageField;

    private Runnable onStudentAdded;

    public void setOnStudentAdded(Runnable callback) {
        this.onStudentAdded = callback;
    }

    
        @FXML
    private void handleAddStudent() {   
        try {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            int age = Integer.parseInt(ageField.getText());
            String classe = classField.getText().trim();
            double average = Double.parseDouble(averageField.getText());

            Student newStudent = new Student(firstName, lastName, age, classe, average);
            StudentDAO dao = new StudentDAO();

            boolean success = dao.addStudent(newStudent);

            if (success && onStudentAdded != null){
            onStudentAdded.run();
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            stage.close();
            } else {
            System.out.println("Erreur lors de l'ajout.");
            }

            }
        catch (Exception e) {
            e.printStackTrace();
            }
        }
        }


