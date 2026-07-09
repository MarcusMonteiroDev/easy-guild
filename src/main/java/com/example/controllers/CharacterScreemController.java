package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import com.example.PartyState;
import com.example.models.Jogador;

public class CharacterScreemController {

    @FXML
    private FlowPane galeriaAventureiros;

    @FXML
    public void initialize() {
        for (Jogador jogador : PartyState.getParty()) {
            VBox novoCard = criarCard(jogador);
            galeriaAventureiros.getChildren().add(novoCard);
        }

    }

    private VBox criarCard(Jogador jogador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/CharacterCard.fxml"));
            VBox card = loader.load();

            CardCharacterController controller = loader.getController();
            controller.criarPersonagem(jogador);

            return card;
        } catch (IOException e) {
            e.printStackTrace();
            return new VBox(); // Retorna um VBox vazio em caso de erro
        }
    }

}
