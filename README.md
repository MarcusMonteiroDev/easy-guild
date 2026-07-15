# Easy Guild

<img src="src/main/resources/com/example/img/easy-guild.png" height="100" alt="Easy Guild logo" />

Easy Guild é uma aplicação desktop Java que simula um sistema genérico de RPG para gerenciamento de personagens e party.

## O que faz
- Carrega e salva o estado da party
- Exibe interface gráfica de personagens e inventário
- Permite navegar entre telas de personagem, itens e criação de novo jogador

## Tecnologias utilizadas
- Java 21
- JavaFX 26 (controls + FXML)
- Maven
- Jackson Databind 2.22

## Como executar
1. `mvn clean javafx:run`

ou

2. Realize a instalação por meio do executável disponível para download

## Detalhes
- Aplicação GUI com FXML
- Persistência de estado em memória/arquivo via `PartyState`
- Interface localizada em `com.example` com controllers e modelos simples
