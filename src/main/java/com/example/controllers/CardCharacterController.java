package com.example.controllers;

import java.io.IOException;

import com.example.enums.Idiomas;
import com.example.models.Jogador;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CardCharacterController {

    @FXML
    private Label nomePersonagem;

    @FXML
    private Label nivelPersonagem;

    @FXML
    private Label classeRaca;

    @FXML
    private Label hpPoints;

    @FXML
    private Label hpMax;

    @FXML
    private Label xpPoints;

    @FXML
    private Label xpNextLvl;

    @FXML
    private Label valorAtk;

    @FXML
    private Label valorDef;

    @FXML
    private Label valorOuro;

    @FXML
    private Button btnDano;

    @FXML
    private Button btnCura;

    @FXML
    private Button btnXp;

    @FXML
    private Button btnOuro;

    @FXML
    private Button btnMenosOuro;

    @FXML
    private FlowPane listaIdiomas;

    private Jogador jogador;

    @FXML
    public void initialize() {

        this.jogador = new Jogador();

        nomePersonagem.textProperty().bind(jogador.nomProperty());
        classeRaca.textProperty().bind(Bindings.format("%s - %s", jogador.racaProperty(), jogador.classeProperty()));
        nivelPersonagem.textProperty().bind(Bindings.concat("Lvl ", jogador.nivelProperty()));
        hpPoints.textProperty().bind(jogador.vidaAtualProperty().asString());
        hpMax.textProperty().bind(jogador.vidaMaxProperty().asString());
        xpPoints.textProperty().bind(jogador.xpAtualProperty().asString());
        xpNextLvl.textProperty().bind(jogador.xpProxNivelProperty().asString());
        valorAtk.textProperty().bind(jogador.ataqueProperty().asString());
        valorDef.textProperty().bind(jogador.defesaProperty().asString());
        valorOuro.textProperty().bind(jogador.ouroProperty().asString());
        
        jogador.getIdiomas().addListener((ListChangeListener<Idiomas>) mudanca -> {
            while(mudanca.next()) {
                if(mudanca.wasAdded()) {
                    for (Idiomas idioma : mudanca.getAddedSubList()) {
                        Label label = new Label(idioma.toString());
                        listaIdiomas.getChildren().add(label);
                    }
                }
            }

            if(mudanca.wasRemoved())
                recarregarIdiomas();
        });

        System.out.println("Card de personagem criado");

    }

    private void recarregarIdiomas() {
        listaIdiomas.getChildren().clear();
        for(Idiomas idioma : jogador.getIdiomas())
            listaIdiomas.getChildren().add(new Label(idioma.toString()));
    }

    public void criarPersonagem(Jogador jogador) {
        this.jogador.setNome(jogador.getNome());
        this.jogador.setNivel(jogador.getNivel());
        this.jogador.setClasse(jogador.getClasse());
        this.jogador.setRaca(jogador.getRaca());
        this.jogador.setVidaMax(jogador.getVidaMax());
        this.jogador.setVidaAtual(jogador.getVidaAtual());
        this.jogador.setxpProxNivel(jogador.getxpProxNivel());
        this.jogador.setxpAtual(jogador.getxpAtual());
        this.jogador.setOuro(jogador.getOuro());
        this.jogador.setAtaque(jogador.getAtaque());
        this.jogador.setDefesa(jogador.getDefesa());
    }



    @FXML
    private void darOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        jogador.setOuro(jogador.getOuro() + popUpController.getValor());
    }

    @FXML
    private void tirarOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        jogador.setOuro(jogador.getOuro() - popUpController.getValor());
    }

    @FXML
    private void darXp() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        jogador.setxpAtual(jogador.getxpAtual() + popUpController.getValor());
    }

    @FXML
    private void aplicarDano() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

    }

    @FXML
    private void aplicarCura() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

    }

    private QuantidadePopUpController abrirPopUp() throws IOException {
        // Cria a classe responsável por carregar os arquivos fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/QuantidadePopUp.fxml"));
        // Carrega o fxml
        Parent root = loader.load();
        // Carrega o controller associado ao fxml carregado
        QuantidadePopUpController quantidadePopUpController = loader.getController();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);

        popup.setScene(new Scene(root));

        popup.showAndWait();

        return quantidadePopUpController;
    }

}
