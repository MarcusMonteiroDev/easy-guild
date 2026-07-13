package com.example;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        PartyState.carregaParty();

        //scene = new Scene(loadFXML("NewPlayerScreem"));
        scene = new Scene(loadFXML("CharacterScreem"));

        // stage.setMinWidth(600);
        // stage.setMinHeight(800);

        stage.setTitle("Easy Guild");
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Janela fechada");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}