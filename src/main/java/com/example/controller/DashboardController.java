package com.example.controller;

import com.example.model.Student;
import com.example.utils.SceneManager;

import com.example.DAO.StudentDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Button logoutButton;
    @FXML private TextField searchField;

    @FXML private Button searchButton, editButton, chartButton, importButton, exportButton, nextPageButton , searchAdvancedButton;
    @FXML private TableView<Student> studentTableView;

    @FXML private ComboBox<String> searchTypeComboBox;
    @FXML private VBox advancedSearchBox;
    @FXML private TextField minAgeField, maxAgeField, classeField, minAverageField, maxAverageField;


    @FXML
    public void initialize() {

        searchTypeComboBox.setItems(FXCollections.observableArrayList(
            "ID", "Prénom","Nom","Age", "Classe", "Recherche avancée"
        ));

        searchTypeComboBox.setOnAction(event -> {
            String selected = searchTypeComboBox.getValue();
            boolean isAdvanced = "Recherche avancée".equals(selected);

            searchField.clear();
            advancedSearchBox.setVisible(isAdvanced);
            advancedSearchBox.setManaged(isAdvanced);

            if (!isAdvanced) {
                minAgeField.clear();
                maxAgeField.clear();
                classeField.clear();
                minAverageField.clear();
                maxAverageField.clear();
    }
        });

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> fristNameCol = new TableColumn<>("Prénom");
        fristNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Nom");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Âge");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> classCol = new TableColumn<>("Classe");
        classCol.setCellValueFactory(new PropertyValueFactory<>("classe"));

        TableColumn<Student, Double> averageCol = new TableColumn<>("Moyenne");
        averageCol.setCellValueFactory(new PropertyValueFactory<>("average"));

        studentTableView.getColumns().addAll(idCol, fristNameCol, lastNameCol, ageCol, classCol, averageCol);

        ObservableList<Student> data = FXCollections.observableArrayList(StudentDAO.getAllStudents());

        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        studentTableView.setItems(data);
    }

    public void refreshTable() {
    studentTableView.getItems().clear();
    studentTableView.getItems().addAll(StudentDAO.getAllStudents());
}
    

    @FXML
    private void openEditStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_student.fxml"));
            Parent root = loader.load();
            //  mettre ici le truc pour récup l'info de l'étudiant selectionner
            Stage stage = new Stage();
            stage.setTitle("Modifier un étudiant");
            stage.setScene(new Scene(root, 400, 300));
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
}


    @FXML
    private void openAddStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_student.fxml"));
            Parent root = loader.load();

        AddStudentController controller = loader.getController();

        controller.setOnStudentAdded(this::refreshTable);

        Stage stage = new Stage();
        stage.setTitle("Ajouter un étudiant");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
