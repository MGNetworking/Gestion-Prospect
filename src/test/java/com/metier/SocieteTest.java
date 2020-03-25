package com.metier;

import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionSociete;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Type;


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

    @ParameterizedTest
    @ValueSource(strings = {"nom", "", "noms4", "m@xime", "c", "D"})
    @DisplayName("Listes de testes ")
    void exceptionSetRaisonSociale(String raisonTeste) {
        assertThrows(ExceptionPersonnaliser.class, () -> st.setRaisonSociale(raisonTeste));

    }

    @Test
    @DisplayName("Societe domain null")
    void exceptionDomainStNullTest() {
        assertThrows(NullPointerException.class, () -> st.setDomainSociete(null),
            "Le domain Societe n'est pas null");

    }

    @Test
    @DisplayName("Test sur enuméartion Domain : PRIVE / PUBLIC ")
    void exceptionDomainTeste() {
        assertThat(Societe.DomainSociete.PRIVE.getDomainst() , is("PRIVE"));
        assertThat(Societe.DomainSociete.PUBLIC.getDomainst() , is("PUBLIC"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"user#domain.com", "@yahoo.com"})
    void exceptionEmail(String emaiTeste) {
        assertThrows(ExceptionPersonnaliser.class, () -> st.setEmail(emaiTeste), "Erreur avec email");
    }

    @ParameterizedTest
    @ValueSource(strings = {"06 50 44 20 65 26", "+330102030405", "+49-01-02-03-04-05", "+33 01 02 03 04 05"})
    void exceptionTelephone(String teleTeste) {
        assertThrows(ExceptionSociete.class, () -> st.setTelephone(teleTeste), "Erreur telephone");
    }
}
