package com.example.controller;

import com.example.model.Student;
import com.example.utils.SceneManager;
import com.example.DAO.DeleteStudentDAO;
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

import java.io.IOException;
import java.util.List;

public class DashboardController {

    @FXML private Button logoutButton;
    @FXML private TextField searchField;

    @FXML private Button searchButton, editButton, chartButton, importButton, exportButton, nextPageButton , prevPageButton, searchAdvancedButton, delButton;
    @FXML private TableView<Student> studentTableView;

    @FXML private ComboBox<String> searchTypeComboBox;
    @FXML private VBox advancedSearchBox;
    @FXML private TextField minAgeField, maxAgeField, classeField, minAverageField, maxAverageField;


    private List<Student> allStudents;
    private final int rowsPerPage = 15;
    private int currentPage = 0;

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
                }  });

        searchButton.setOnAction(e -> handleSearch());


        allStudents = StudentDAO.getAllStudents();

        setupTableColumns();
        updateTable();

        nextPageButton.setOnAction(e -> {
            if ((currentPage + 1) * rowsPerPage < allStudents.size()) {
                currentPage++;
                updateTable();
            }
        });

        prevPageButton.setOnAction(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateTable();
            }
        });
    }


    private void setupTableColumns() {
        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> firstNameCol = new TableColumn<>("Prénom");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Nom");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Âge");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> classCol = new TableColumn<>("Classe");
        classCol.setCellValueFactory(new PropertyValueFactory<>("classe"));

        TableColumn<Student, Double> averageCol = new TableColumn<>("Moyenne");
        averageCol.setCellValueFactory(new PropertyValueFactory<>("average"));

        studentTableView.getColumns().setAll(idCol, firstNameCol, lastNameCol, ageCol, classCol, averageCol);

        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    }

    private void updateTable() {
    int fromIndex = currentPage * rowsPerPage;
    int toIndex = Math.min(fromIndex + rowsPerPage, allStudents.size());

    if (fromIndex <= toIndex) {
        studentTableView.getItems().setAll(allStudents.subList(fromIndex, toIndex));
    }
}

    private void updateTableAllStudent() {
    allStudents = StudentDAO.getAllStudents(); 
    int fromIndex = currentPage * rowsPerPage;
    int toIndex = Math.min(fromIndex + rowsPerPage, allStudents.size());

    if (fromIndex <= toIndex) {
        studentTableView.getItems().setAll(allStudents.subList(fromIndex, toIndex));
    }
}



@FXML
private void openEditStudentWindow() {
    Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();

    if (selectedStudent == null) {
        System.out.println("Aucun étudiant sélectionné.");
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_student.fxml"));
        Parent root = loader.load();

        EditStudentController controller = loader.getController();
        controller.setStudentData(selectedStudent);
        controller.setOnUpdate(this::updateTable);

        Stage stage = new Stage();
        stage.setTitle("Modifier un étudiant");
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void openAddStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_student.fxml"));
            Parent root = loader.load();

        AddStudentController controller = loader.getController();

        controller.setOnStudentAdded(this::updateTableAllStudent);

        Stage stage = new Stage();
        stage.setTitle("Ajouter un étudiant");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
}

    @FXML
    private void deleteStudent(){

    Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();


    if (selectedStudent == null) {
        System.out.println("Aucun étudiant sélectionné.");
        return;
    }

    DeleteStudentDAO dao = new DeleteStudentDAO();
    boolean success = dao.deleteStudent(selectedStudent);

    if (success) {
        System.out.println("Étudiant supprimé avec succès.");
        updateTable(); 
    } else {
        System.out.println("Erreur lors de la suppression.");
    }

    }


private void handleSearch() {
    String selectedField = searchTypeComboBox.getValue();
    String keyword = searchField.getText().trim();

    if (keyword.isEmpty() || selectedField == null || selectedField.equals("Recherche avancée")) {
        allStudents = StudentDAO.getAllStudents();
        currentPage = 0;
        updateTable();
        return;
    }

    String dbField = switch (selectedField) {
        case "ID" -> "id";
        case "Prénom" -> "first_name";
        case "Nom" -> "last_name";
        case "Age" -> "age";
        case "Classe" -> "class";
        default -> null;
    };

    if (dbField != null) {
        allStudents = StudentDAO.searchStudents(dbField, keyword);
        currentPage = 0;
        updateTable();
    }
}



}
