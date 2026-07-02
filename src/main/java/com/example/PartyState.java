package com.example;

import com.example.models.Party;

// Monitora o estado da Party
// Essa será a única instância da party durante a execução do programa
public final class PartyState {
    private static Party party;

    public static Party getParty() {
        return party;
    }

    public static void setParty(Party party) {
        PartyState.party = party;
    }
}
