package com.example;

import com.example.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        SceneManager.switchScene("login");
        stage.setTitle("LaPlateforme Tracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
