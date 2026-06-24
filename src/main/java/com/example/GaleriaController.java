package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class GaleriaController {

    @FXML
    private FlowPane galeriaAventureiros;

    @FXML
    public void initialize() {
        for (int i = 1; i <= 1; i++) {
            VBox novoCard = criarCard();
            galeriaAventureiros.getChildren().add(novoCard);
        }
    }

    /**
     * Método auxiliar para criar o visual do Card carregando o FXML
     * CharacterCard.fxml.
     * Configura o título e preço nos Labels do card.
     */
    private VBox criarCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CharacterCard.fxml"));
            VBox card = loader.load();

            // Define tamanho fixo do card
            card.setPrefWidth(500);
            card.setPrefHeight(600);
            card.setMinWidth(500);
            card.setMinHeight(600);
            card.setMaxWidth(500);
            card.setMaxHeight(600);
            return card;
        } catch (IOException e) {
            e.printStackTrace();
            return new VBox(); // Retorna um VBox vazio em caso de erro
        }
    }

}
