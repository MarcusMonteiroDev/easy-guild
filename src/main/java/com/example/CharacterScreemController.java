package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOException;

import com.example.models.Jogador;

public class CharacterScreemController {

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

        // Cria o objeto do Jogador
        Jogador jogador = new Jogador();

        jogador.setNome("Arthas");
        jogador.setNivel(15);
        jogador.setClasse("GUERREIRO");
        jogador.setRaca("HUMANO");

        jogador.setVidaMax(100);
        jogador.setVidaAtual(85);

        jogador.setxpAtual(250);
        jogador.setxpProxNivel(300);
        jogador.setOuro(150);

        jogador.setAtaque(20);
        jogador.setDefesa(15);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CharacterCard.fxml"));
            VBox card = loader.load();

            CardCharacterController controller = loader.getController();
            controller.criarPersonagem(jogador.getNome(), jogador.getNivel(), jogador.getRaca(), jogador.getClasse(),
                    jogador.getVidaMax(), jogador.getVidaAtual(), jogador.getxpAtual(), jogador.getxpProxNivel(), jogador.getOuro(),
                    jogador.getAtaque(), jogador.getDefesa());

            return card;
        } catch (IOException e) {
            e.printStackTrace();
            return new VBox(); // Retorna um VBox vazio em caso de erro
        }
    }

}
