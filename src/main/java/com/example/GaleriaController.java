package com.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GaleriaController {

    // Injetando o FlowPane que criamos no Scene Builder
    @FXML
    private FlowPane galeriaAventureiros;

    @FXML
    public void initialize() {
        // Quando a tela carregar, vamos adicionar 10 cards de exemplo
        for (int i = 1; i <= 100; i++) {
            VBox novoCard = criarCard("Produto " + i, "R$ " + (10 * i) + ",00");
            
            // Adiciona o card gerado dentro do FlowPane
            galeriaAventureiros.getChildren().add(novoCard);
        }
    }

    /**
     * Método auxiliar para criar o visual do Card.
     * Você pode adicionar imagens, botões, e mudar as cores aqui.
     */
    private VBox criarCard(String tituloProduto, String precoProduto) {
        VBox card = new VBox();
        
        // Estilizando o card (cor de fundo, borda arredondada, tamanho, sombra, etc.)
        card.setStyle("-fx-background-color: white; " +
                      "-fx-background-radius: 10; " +
                      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);");
        
        // Tamanho fixo do card
        card.setPrefSize(150, 200); 
        card.setPadding(new Insets(10));
        card.setSpacing(10); // Espaço interno entre o título e o preço

        // Criando os textos do card
        Label lblTitulo = new Label(tituloProduto);
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label lblPreco = new Label(precoProduto);
        lblPreco.setStyle("-fx-text-fill: green;");

        // Adicionando os textos dentro do card
        // Se tivesse uma imagem (ImageView), você adicionaria aqui também
        card.getChildren().addAll(lblTitulo, lblPreco);

        return card;
    }
}