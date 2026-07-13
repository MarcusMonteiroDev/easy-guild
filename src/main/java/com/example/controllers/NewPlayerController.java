package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.PartyState;
import com.example.enums.Classes;
import com.example.enums.Equipamentos;
import com.example.enums.Idiomas;
import com.example.enums.Racas;
import com.example.models.Jogador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class NewPlayerController {
    @FXML
    private TextField nome;

    @FXML
    private ComboBox<Racas> raca;

    @FXML
    private ComboBox<Classes> classe;

    @FXML
    private TextField hp;

    @FXML
    private TextField ataque;

    @FXML
    private TextField defesa;

    @FXML
    private TextField ouro;

    @FXML
    private ToggleButton idmAnao;

    @FXML
    private ToggleButton idmComum;

    @FXML
    private ToggleButton idmDraconico;

    @FXML
    private ToggleButton idmElfico;

    @FXML
    private ToggleButton idmHalfling;

    @FXML
    private ToggleButton idmOrc;

    private List<ToggleButton> idiomas;

    @FXML
    private ToggleButton equipArco;

    @FXML
    private ToggleButton equipCajado;

    @FXML
    private ToggleButton equipEspada;

    @FXML
    private ToggleButton equipMachado;

    private List<ToggleButton> equipamentos;

    @FXML
    public void initialize() {
        raca.getItems().addAll(Racas.values());
        raca.setValue(Racas.ANAO);

        classe.getItems().addAll(Classes.values());
        classe.setValue(Classes.GUERREIRO);

        this.idiomas = List.of(idmAnao, idmComum, idmDraconico, idmElfico, idmHalfling, idmOrc);
        this.equipamentos = List.of(equipArco, equipCajado, equipEspada, equipMachado);

    }

    @FXML
    public void addToParty() {
        try {
            Jogador jogador = new Jogador();

            jogador.setNome(nome.getText());
            jogador.setNivel(1);
            jogador.setClasse(classe.getValue().toString());
            jogador.setRaca(raca.getValue().toString());
            jogador.setVidaMax(Integer.parseInt(hp.getText()));
            jogador.setVidaAtual(Integer.parseInt(hp.getText()));
            jogador.setxpProxNivel(100);
            jogador.setxpAtual(0);
            jogador.setOuro(Integer.parseInt(ouro.getText()));
            jogador.setAtaque(Integer.parseInt(ataque.getText()));
            jogador.setDefesa(Integer.parseInt(defesa.getText()));

            // Verifica os idiomas e equipamentos selecionados
            for (ToggleButton idiomaSelecionado : this.idiomas) {
                if (idiomaSelecionado.isSelected()) {
                    jogador.getIdiomas().add(Idiomas.valueOf(idiomaSelecionado.getText().toUpperCase()));
                }
            }

            for (ToggleButton equipamentoSelecionado : this.equipamentos) {
                if (equipamentoSelecionado.isSelected()) {
                    jogador.getEquipamentos().add(Equipamentos.valueOf(equipamentoSelecionado.getText().toUpperCase()));
                }
            }

            try {
                PartyState.addPlayser(jogador);
                PartyState.salvarParty();

                CharacterScreemController.getInstance().adicionarJogador(jogador);

                Stage stage = (Stage) nome.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Preencha os dados corretamente!");
            alertaErro();
        }
    }

    public void alertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Não foi possível cadastrar o personagem");
        alert.setContentText("Preencha todos os campos corretamente");

        alert.showAndWait();
    }

}
