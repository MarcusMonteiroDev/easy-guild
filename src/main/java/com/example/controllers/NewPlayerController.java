package com.example.controllers;

import com.example.enums.Classes;
import com.example.enums.Idiomas;
import com.example.enums.Racas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

public class NewPlayerController {
    @FXML
    private ComboBox<Racas> comboBoxRaca;

    @FXML
    private ComboBox<Classes> comboBoxClasse;

    @FXML
    private FlowPane flowPaneIdiomas;

    @FXML
    public void initialize() {
        comboBoxRaca.getItems().addAll(Racas.values());
        comboBoxRaca.setValue(Racas.ANAO);

        comboBoxClasse.getItems().addAll(Classes.values());
        comboBoxClasse.setValue(Classes.BARBARO);

    }
}
