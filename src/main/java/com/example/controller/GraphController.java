package com.example.controller;

import com.example.DAO.StudentDAO;
import com.example.model.Student;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public void initialize() {
        List<Student> students = StudentDAO.getAllStudents();
        Map<String, Integer> classCount = new HashMap<>();

        for (Student s : students) {
            classCount.put(s.getClasse(), classCount.getOrDefault(s.getClasse(), 0) + 1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Étudiants par classe");

        for (Map.Entry<String, Integer> entry : classCount.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);
    }

    @FXML
    private void handleReturn() {
        Stage stage = (Stage) barChart.getScene().getWindow();
        stage.close();
    }
}
