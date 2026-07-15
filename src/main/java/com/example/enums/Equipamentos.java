package com.example.enums;

public enum Equipamentos {
    ARCO("Arco", "1d6"), 
    CAJADO("Cajado", "1d4"), 
    ESPADA("Espada", "1d8"), 
    MACHADO("Machado", "1d12");

    private String nome;
    private String dano;

    Equipamentos(String nome, String dano) {
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public String getDano() {
        return dano;
    }
}
