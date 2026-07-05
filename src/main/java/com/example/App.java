package com.example;

import java.io.IOException;
import java.io.InputStream;
import com.example.models.Party;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            // Carrega a party
            ObjectMapper mapper = new ObjectMapper();

            InputStream path = getClass().getResourceAsStream("/com/example/save/dados.json");

            Party party = mapper.readValue(path, Party.class);
            
            PartyState.setParty(party);
            
            System.out.println("Party carregada com sucesso");
        } catch (StreamReadException e) {
            System.out.println("JSON mal formatado.");
        } catch (DatabindException e) {
            System.out.println("JSON não corresponde à estrutura da classe.");
        } catch (IOException e) {
            System.out.println("Erro de leitura do arquivo.");
        } catch(Exception e) {
            System.out.println("Erro inesperado ao carregar a party");
            e.printStackTrace();
        }
        
        scene = new Scene(loadFXML("CharacterScreem"));

        //stage.setMinWidth(100);
        //stage.setMinHeight(100);

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