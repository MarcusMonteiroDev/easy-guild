package com.example.controllers;

import com.example.enums.Equipamentos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemTemplateController {

    @FXML
    private Label nomeItem;

    @FXML
    private Label danoItem;

    public void criarEquipamento(Equipamentos equipamento) {
        nomeItem.setText(equipamento.getNome());
        danoItem.setText(equipamento.getDano());
    }
}
