package com.metier;

import com.exception.ExceptionPersonnaliser;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SocieteTest {

    // heritage locale pour le teste de la classe abstraite Societe
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
    void exceptionSetRaisonSocialeVideYTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> st.setRaisonSociale(""), "Societe raison sociale vide");
    }

    @Test
    @DisplayName("Societe domain null")
    void exceptionDomainStNullTest(){
        assertThrows(NullPointerException.class, ()-> st.setDomainSociete(null),
            "Le domain Societe n'est pas null");

    }
    @Test
    @DisplayName("Societe domain Public et Prive")
    void exceptionDomainStPriveTest(){
        assertThrows(ExceptionPersonnaliser.class, ()-> st.setDomainSociete("prive"),
            "Le champs domain Societe n'est pas respecté : prive");

    }
}
