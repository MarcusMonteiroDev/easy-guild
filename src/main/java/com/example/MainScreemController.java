package com.example;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainScreemController {

    @FXML
    private AnchorPane telaAventureiros;

    @FXML
    private AnchorPane telaDados;

    @FXML
    public void initialize() {
        telaAventureiros.setVisible(true);
        telaDados.setVisible(false);
    }

    @FXML
    private void mostrarPrincipal() {
        telaAventureiros.setVisible(true);
        telaDados.setVisible(false);
    }

    @FXML
    private void mostrarSecundaria() {
        telaAventureiros.setVisible(false);
        telaDados.setVisible(true);
    }

}
