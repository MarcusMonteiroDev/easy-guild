package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaPrincipalController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoLogin;

    @FXML
    private Button botaoLimpar;

    @FXML
    public void initialize() {
        System.out.println("Campos funcionando");
        campoUsuario.getText();
    }

    @FXML
    public void fazerLogin() {
        if (!campoUsuario.getText().isBlank() && !campoSenha.getText().isBlank()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("teste_2.fxml"));

                Parent root = loader.load();

                Scene novaCena = new Scene(root);
                Stage janelaAtual = (Stage) botaoLogin.getScene().getWindow();
                janelaAtual.setScene(novaCena);
                janelaAtual.setTitle("Sistema - Lista de Usuários");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Preencha todos os campos");
        }
    }

    @FXML
    public void limparCampos() {
        campoUsuario.clear();
        campoSenha.clear();
    }

}
