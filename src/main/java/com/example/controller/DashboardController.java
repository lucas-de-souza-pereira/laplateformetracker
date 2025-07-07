package com.example.controller;

import com.example.model.Student;
import com.example.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.example.DAO.StudentDAO;

public class DashboardController {

    @FXML private Button logoutButton;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeComboBox;
    @FXML private Button profileButton, chartButton, importButton, exportButton, nextPageButton;
    @FXML private TableView<Student> studentTableView;


    @FXML
    public void initialize() {
        // Définir les colonnes
        TableColumn<Student, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Student, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Âge");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> classeCol = new TableColumn<>("Classe");
        classeCol.setCellValueFactory(new PropertyValueFactory<>("classe"));

        TableColumn<Student, Double> moyenneCol = new TableColumn<>("Moyenne");
        moyenneCol.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        studentTableView.getColumns().addAll(prenomCol, nomCol, ageCol, classeCol, moyenneCol);
        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        

        // Données fictives (mock)
        // ObservableList<Student> data = FXCollections.observableArrayList(
        //     new Student("Lucas", "Dupont", 20, "L3", 14.5),
        //     new Student("Marie", "Durand", 22, "M1", 15.8),
        //     new Student("Sami", "Ben Ali", 19, "L2", 13.2)
        // );

        ObservableList<Student> data = FXCollections.observableArrayList(StudentDAO.getAllStudents());
        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        studentTableView.setItems(data);
    }
}
