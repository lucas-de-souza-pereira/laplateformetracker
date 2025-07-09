package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



import com.example.model.Student;
import com.example.DAO.EditStudentDAO;



public class EditStudentController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField classField;
    @FXML private TextField ageField;
    @FXML private TextField averageField;

    private Student studentToEdit;
    private Runnable onUpdate;

    public void setStudentData(Student student) {
        this.studentToEdit = student;
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        classField.setText(student.getClasse());
        ageField.setText(String.valueOf(student.getAge()));
        averageField.setText(String.valueOf(student.getAverage()));
    }

    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    @FXML
    private void handleValidateUpdate() {
        try {
            studentToEdit.setFirstName(firstNameField.getText().trim());
            studentToEdit.setLastName(lastNameField.getText().trim());
            studentToEdit.setClasse(classField.getText().trim());
            studentToEdit.setAge(Integer.parseInt(ageField.getText().trim()));
            studentToEdit.setAverage(Double.parseDouble(averageField.getText().trim()));

            boolean success = new EditStudentDAO().updateStudent(studentToEdit);

            if (success && onUpdate != null) {
                onUpdate.run();
            }

            Stage stage = (Stage) firstNameField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}