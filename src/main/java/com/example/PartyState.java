package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.example.models.Jogador;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Monitora o estado da Party
// Essa será a única instância da party durante a execução do programa
public final class PartyState {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Path DATA_DIR = Paths.get(System.getProperty("user.home"), ".easyguild");
    private static final Path DATA_FILE = DATA_DIR.resolve("dados.json");

    // private static Party party;
    private static ArrayList<Jogador> party = new ArrayList<>();

    public static ArrayList<Jogador> getParty() {
        return party;
    }

    public static void setParty(ArrayList<Jogador> party) {
        PartyState.party = party;
    }

    public static void addPlayser(Jogador jogador) {
        party.add(jogador);
        System.out.println("Jogador Adicionado:\n" + jogador.toString());
        
    }

    public static void deletePlayer(String ID) throws IOException {
        System.out.println("ID procurado -> " + ID);
        System.out.println("LISTA ANTES:");
        System.out.println(mostrarParty());

        party.removeIf(jogador -> jogador.getID().equals(ID));

        System.out.println("LISTA DEPOIS:");
        System.out.println(mostrarParty());

        salvarParty();
    }

    public static void carregaParty() throws IOException {
        inicializarParty();
        party = MAPPER.readValue(DATA_FILE.toFile(), new TypeReference<ArrayList<Jogador>>() {
        });
        //System.out.println(mostrarParty());
    }

    public static void salvarParty() throws IOException {
        inicializarParty();
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(DATA_FILE.toFile(), party);
        System.out.println("Party salva");
        System.out.println(mostrarParty());
    }

    private static void inicializarParty() throws IOException {
        if (Files.exists(DATA_FILE))
            return;

        Files.createDirectories(DATA_DIR);

        // TRECHO TEMPORÁRIO
        // Abre o arquivo padrão armazenado nos resources.
        try (InputStream is = PartyState.class.getResourceAsStream(
                "/com/example/save/dados.json")) {

            // Se o arquivo não for encontrado nos resources,
            // lança uma exceção.
            if (is == null) {
                throw new IOException(
                        "Arquivo padrão dados.json não encontrado.");
            }

            // Copia o arquivo padrão dos resources
            // para a pasta de dados do usuário.
            Files.copy(is, DATA_FILE);
        }
    }

    public static String mostrarParty() {
        return party.isEmpty()
                ? "A party não possui jogadores."
                : party.stream()
                        .map(Jogador::toString)
                        .reduce("=== PARTY ===\n\n",
                                (a, b) -> a + b + "\n--------------------\n");
    }
}
