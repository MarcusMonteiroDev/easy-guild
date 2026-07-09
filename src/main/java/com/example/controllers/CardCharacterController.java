package com.example.controllers;

import java.io.IOException;

import com.example.enums.Equipamentos;
import com.example.enums.Idiomas;
import com.example.models.Jogador;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Button addItem;

    @FXML
    private FlowPane flowPaneIdiomas;

    @FXML
    private VBox listaEquipamentos;

    private Jogador jogador = new Jogador();

    @FXML
    public void initialize() {
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
        jogador.getIdiomas().addListener((ListChangeListener<Idiomas>) change -> {
            atualizarIdiomas();
        });
        jogador.getEquipamentos().addListener((ListChangeListener<Equipamentos>) change -> {
            atualizarEquipamentos();
        });        

        System.out.println("Card de personagem criado");

    }

    private void atualizarIdiomas() {
        flowPaneIdiomas.getChildren().clear();

        for (Idiomas idioma : jogador.getIdiomas()) {
            Label label = new Label(idioma.name());
            flowPaneIdiomas.getChildren().add(label);
        }
    }

    private void atualizarEquipamentos() {
        listaEquipamentos.getChildren().clear();

        for (Equipamentos equipamento : jogador.getEquipamentos()) {
            HBox novoItem = criarItem(equipamento);
            listaEquipamentos.getChildren().add(novoItem);
        }
    }

    private HBox criarItem(Equipamentos equipamento) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/ItemTemplate.fxml"));
            //HBox card = loader.load();

            //ItemTemplateController itemController = loader.getController();
            //itemController.criarEquipamento(equipamento);

            //return card;
            return new HBox();
        } catch (Exception e) {
            e.printStackTrace();
            return new HBox();
        }
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
        this.jogador.setIdiomas(jogador.getIdiomas());
        this.jogador.setEquipamentos(jogador.getEquipamentos());
    }
    @FXML
    private void excluirJogador() {

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
