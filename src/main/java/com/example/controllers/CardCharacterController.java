package com.example.controllers;

import java.io.IOException;

import com.example.PartyState;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CardCharacterController {

    @FXML
    private VBox mainVBox;

    @FXML
    private Label nomePersonagem;

    @FXML
    private Label nivelPersonagem;

    @FXML
    private Label classeRaca;

    @FXML
    private Label labelMorto;

    @FXML
    private Label hpPoints;

    @FXML
    private Label hpMax;

    @FXML
    private ProgressBar barraHp;

    @FXML
    private Label xpPoints;

    @FXML
    private Label xpNextLvl;

    @FXML
    private ProgressBar barraXp;

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
    private FlowPane flowPaneIdiomas;

    @FXML
    private VBox listaEquipamentos;

    private Jogador jogador;

    @FXML
    public void initialize() {

    }

    private void atualizarIdiomas() {
        flowPaneIdiomas.getChildren().clear();

        for (Idiomas idioma : jogador.getIdiomas()) {
            Label label = new Label(idioma.name());
            label.getStyleClass().add("labelIdioma");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/ItemTemplate.fxml"));
            HBox card = loader.load();

            ItemTemplateController itemController = loader.getController();
            itemController.criarEquipamento(equipamento);

            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return new HBox();
        }
    }

    public void criarPersonagem(Jogador jogador) {
        this.jogador = jogador;

        if (jogador.getJogadorVivo()) {
            labelMorto.setVisible(false);
            labelMorto.setManaged(false);
        } else {
            labelMorto.setVisible(true);
            labelMorto.setManaged(true);
        }

        jogador.jogadorVivoProperty().addListener(
                (observable, vivoAntes, vivoAgora) -> {
                    if (!vivoAgora) {
                        labelMorto.setVisible(true);
                        labelMorto.setManaged(true);
                    } else {
                        labelMorto.setVisible(false);
                        labelMorto.setManaged(false);
                    }
                });

        nomePersonagem.textProperty().bind(jogador.nomProperty());
        classeRaca.textProperty().bind(Bindings.format("%s - %s", jogador.racaProperty(), jogador.classeProperty()));
        nivelPersonagem.textProperty().bind(Bindings.concat("Lvl ", jogador.nivelProperty()));
        hpPoints.textProperty().bind(jogador.vidaAtualProperty().asString());
        hpMax.textProperty().bind(jogador.vidaMaxProperty().asString());
        barraHp.progressProperty().bind(
                Bindings.when(jogador.vidaMaxProperty().isEqualTo(0))
                        .then(0.0)
                        .otherwise(jogador.vidaAtualProperty().multiply(1.0).divide(jogador.vidaMaxProperty())));
        xpPoints.textProperty().bind(jogador.xpAtualProperty().asString());
        xpNextLvl.textProperty().bind(jogador.xpProxNivelProperty().asString());
        barraXp.progressProperty().bind(jogador.xpAtualProperty().divide(100.0));
        valorAtk.textProperty().bind(jogador.ataqueProperty().asString());
        valorDef.textProperty().bind(jogador.defesaProperty().asString());
        valorOuro.textProperty().bind(jogador.ouroProperty().asString());
        jogador.getIdiomas().addListener((ListChangeListener<Idiomas>) change -> {
            atualizarIdiomas();
        });
        jogador.getEquipamentos().addListener((ListChangeListener<Equipamentos>) change -> {
            atualizarEquipamentos();
        });

        atualizarIdiomas();
        atualizarEquipamentos();

    }

    @FXML
    private void excluirJogador() throws IOException {
        PartyState.deletePlayer(jogador.getID());
        FlowPane pai = (FlowPane) mainVBox.getParent();
        pai.getChildren().remove(mainVBox);
    }

    @FXML
    private void darOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        jogador.setOuro(jogador.getOuro() + popUpController.getValor());
        PartyState.salvarParty();
    }

    @FXML
    private void tirarOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        jogador.setOuro(jogador.getOuro() - popUpController.getValor());
        PartyState.salvarParty();
    }

    @FXML
    private void darXp() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        int somaXp = jogador.getxpAtual() + popUpController.getValor();

        if (somaXp == 100) {
            jogador.aumentarAtributos(jogador.getNivel(), jogador.getNivel() + 1);
            jogador.setxpAtual(0);
            jogador.setNivel(jogador.getNivel() + 1);
        } else if (somaXp > 100) {
            jogador.aumentarAtributos(jogador.getNivel(), jogador.getNivel() + somaXp / 100);
            jogador.setNivel(jogador.getNivel() + somaXp / 100);
            jogador.setxpAtual(somaXp % 100);
        } else
            jogador.setxpAtual(somaXp);

        PartyState.salvarParty();
    }

    @FXML
    private void aplicarDano() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        int dano = popUpController.getValor();

        if (jogador.getVidaAtual() - dano <= 0) {
            jogador.setVidaAtual(0);
            jogador.setJogadorVivo(false);
        } else {
            jogador.setVidaAtual(jogador.getVidaAtual() - dano);
        }

        PartyState.salvarParty();
    }

    @FXML
    private void aplicarCura() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();
        int cura = popUpController.getValor();

        if (cura + jogador.getVidaAtual() >= jogador.getVidaMax())
            jogador.setVidaAtual(jogador.getVidaMax());
        else
            jogador.setVidaAtual(jogador.getVidaAtual() + cura);

        jogador.setJogadorVivo(true);

        PartyState.salvarParty();
    }

    public static QuantidadePopUpController abrirPopUp() throws IOException {
        // Cria a classe responsável por carregar os arquivos fxml
        FXMLLoader loader = new FXMLLoader(
                CardCharacterController.class.getResource("/com/example/fxml/QuantidadePopUp.fxml"));
        // Carrega o fxml
        Parent root = loader.load();
        // Carrega o controller associado ao fxml carregado
        QuantidadePopUpController quantidadePopUpController = loader.getController();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);

        popup.setScene(new Scene(root));

        popup.setOnHidden(event -> {
            if (quantidadePopUpController.getValor() == null) {
                System.out.println("Nenhum valor inserido");
            }
        });

        popup.showAndWait();

        return quantidadePopUpController;
    }

}
