package com.metier;

import com.exception.ExceptionPersonnaliser;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SocieteTest {

    // héritage locale pour les testes de la classe abstraite Societe
    class StTeste extends Societe {
    }

    Societe st;

    @BeforeEach
    void init() {
        st = new StTeste();
    }

    @Test
    @DisplayName("Societe Raison Sociale Null ")
    void excetionSetRaisonSocialeNullTest() {
        assertThrows(NullPointerException.class,
            () -> st.setRaisonSociale(null), "Societe raison sociale null");
    }

    @Test
    @DisplayName("Societe Raison Sociale vide ")
    void exceptionSetRaisonSocialeVideYTest() {
        assertThrows(ExceptionPersonnaliser.class,
            () -> st.setRaisonSociale(""), "Societe raison sociale vide");
    }

    @Test
    @DisplayName("Societe domain null")
    void exceptionDomainStNullTest() {
        assertThrows(NullPointerException.class, () -> st.setDomainSociete(null),
            "Le domain Societe n'est pas null");

    }

}
