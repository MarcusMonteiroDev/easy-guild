package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.models.Jogador;
import com.example.models.Party;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Monitora o estado da Party
// Essa será a única instância da party durante a execução do programa
public final class PartyState {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Path DATA_DIR = Paths.get(System.getProperty("user.home"), ".easyguild");
    private static final Path DATA_FILE = DATA_DIR.resolve("dados.json");

    private static Party party;

    public static Party getParty() {
        return party;
    }

    public static void setParty(Party party) {
        PartyState.party = party;
    }

    public static void addPlayser(Jogador jogador) {
        party.setJogador(jogador);
        System.out.println("Jogador Adicionado:\n" + jogador.toString());
    }

    public static void showParty() {
        for (Jogador jogador : party.getJogadores())
            System.out.println(jogador.getNome());
    }

    public static void carregaParty() throws IOException {
        inicializarParty();
        party = MAPPER.readValue(DATA_FILE.toFile(), Party.class);
    }

    public static void salvarParty() throws IOException {
        inicializarParty();
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(DATA_FILE.toFile(), party);
        System.out.println("Party salva");
        showParty();
    }

    private static void inicializarParty() throws IOException {
        if (Files.exists(DATA_FILE))
            return;

        Files.createDirectories(DATA_DIR);

        // TRECHO TEMPORÁRIO
        // Abre o arquivo padrão armazenado nos resources.
        try (InputStream is =
                     PartyState.class.getResourceAsStream(
                             "/com/example/save/dados.json")) {

            // Se o arquivo não for encontrado nos resources,
            // lança uma exceção.
            if (is == null) {
                throw new IOException(
                        "Arquivo padrão dados.json não encontrado."
                );
            }

            // Copia o arquivo padrão dos resources
            // para a pasta de dados do usuário.
            Files.copy(is, DATA_FILE);
        }
    }
}
