package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class QuantidadePopUpController {

    @FXML
    private Label labelErro;
    @FXML
    private TextField input;

    private Integer valor = 0;

    @FXML
    public void initialize() {
        labelErro.setVisible(false);
        labelErro.setManaged(false);
    }

    @FXML
    private void confirmar() {
        try {
            valor = Integer.parseInt(input.getText());
            Stage stage = (Stage) input.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            labelErro.setVisible(true);
            labelErro.setManaged(true);
        }

    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
