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

    private static ArrayList<Jogador> party = new ArrayList<>();

    public static ArrayList<Jogador> getParty() {
        return party;
    }

    public static void setParty(ArrayList<Jogador> party) {
        PartyState.party = party;
    }

    public static void addPlayser(Jogador jogador) {
        party.add(jogador);

    }

    public static void deletePlayer(String ID) throws IOException {

        party.removeIf(jogador -> jogador.getID().equals(ID));

        salvarParty();
    }

    public static void carregaParty() throws IOException {
        inicializarParty();
        party = MAPPER.readValue(DATA_FILE.toFile(), new TypeReference<ArrayList<Jogador>>() {
        });
    }

    public static void salvarParty() throws IOException {
        inicializarParty();
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(DATA_FILE.toFile(), party);
        System.out.println("Party salva");
    }

    private static void inicializarParty() throws IOException {
        if (Files.exists(DATA_FILE))
            return;

        Files.createDirectories(DATA_DIR);

        MAPPER.writerWithDefaultPrettyPrinter().writeValue(DATA_FILE.toFile(), new ArrayList<Jogador>());
    }

    public static String mostrarParty() {
        return party.isEmpty()
                ? "A party não possui jogadores."
                : party.stream()
                        .map(Jogador::toString)
                        .reduce("=== PARTY ===\n\n",
                                (a, b) -> a + b + "\n--------------------\n");
    }

    public static void dividirXpParty(int valor) throws IOException {
        int xpRecebido = valor / party.size();
        int somaXp;
        for (Jogador jogador : party) {
            somaXp = jogador.getxpAtual() + xpRecebido;

            if (somaXp == 100) {
                jogador.aumentarAtributos(jogador.getNivel(), jogador.getNivel() + 1);
                jogador.setxpAtual(0);
                jogador.setNivel(jogador.getNivel() + 1);
            } else if (somaXp > 100) {
                jogador.aumentarAtributos(jogador.getNivel(), jogador.getNivel() + somaXp / 100);
                jogador.setNivel(jogador.getNivel() + somaXp / 100);
                jogador.setxpAtual(somaXp % 100);
            } else
                jogador.setxpAtual(somaXp);
        }
    }

    public static void dividirOuroParty(int valor) throws IOException {
        int ouroRecebido = valor / party.size();

        for (Jogador jogador : party)
            jogador.setOuro(jogador.getOuro() + ouroRecebido);
    }
}
