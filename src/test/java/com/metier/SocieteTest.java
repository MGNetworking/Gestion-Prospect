package com.metier;

import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionSociete;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Type;
import java.util.stream.Stream;


class SocieteTest {

    // héritage locale pour les testes de la classe abstraite Societe
    private final class StTeste extends Societe {
    }

    Societe st;

    @BeforeEach
    void init() {
        st = new StTeste();
    }

    @ParameterizedTest
    @ValueSource(strings = {"nom", "", "noms4", "m@xime", "c", "D", "mcdonald's"})
    @DisplayName("Listes de testes ")
    void exceptionSetRaisonSociale(String raisonTeste) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> st.setRaisonSociale(raisonTeste), "Format raisonSociale : " + raisonTeste);

        assertThrows(NullPointerException.class,
            () -> st.setRaisonSociale(null), "Societe raison sociale null");

    }

    static Stream<String> validationRaisonSocialeSt() {
        return Stream.of("Clemessy", "Carfoure", "mcdonald");
    }

    @ParameterizedTest
    @MethodSource("validationRaisonSocialeSt")
    @DisplayName("Certification de la raison sociale")
    void validationRaisonSocial(String raisonSociale) {

        assertDoesNotThrow(() -> st.setRaisonSociale(raisonSociale),
            "Certification raison sociale : " + raisonSociale);

    }

    @Test
    @DisplayName("Test DomainSociete la methode getDomainst() est l'exception null")
    void exceptionDomainTeste() {

        assertThat(Societe.DomainSociete.PRIVE.getDomainst(), is("PRIVE"));
        assertThat(Societe.DomainSociete.PUBLIC.getDomainst(), is("PUBLIC"));

        assertThrows(NullPointerException.class, () -> st.setDomainSociete(null),
            "Le domain Societe est null");


    }

    @ParameterizedTest
    @ValueSource(strings = {"user#domain.com", "@yahoo.com"})
    void exceptionEmail(String emaiTeste) {
        assertThrows(ExceptionPersonnaliser.class,
            () -> st.setEmail(emaiTeste), "Erreur avec email" + emaiTeste);
    }

    static Stream<String> validationEmailSt() {
        return Stream.of("adresse.email@gmail.com", "adresse@gmail.com", "etudiant@maformationenligne.bzh", "etudiant@outlook.fr");
    }

    @ParameterizedTest
    @MethodSource("validationEmailSt")
    @DisplayName("Certification de l'email")
    void validationEmail(String email) {

        assertDoesNotThrow(() -> st.setEmail(email),
            "Certification de l'email : " + email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"06 50 44 20 65 26", "+330102030405", "+49-01-02-03-04-05", "+33 01 02 03 04 05"})
    void exceptionTelephone(String teleTeste) {
        assertThrows(ExceptionSociete.class,
            () -> st.setTelephone(teleTeste), "Erreur telephone" + teleTeste);
    }

    static Stream<String> validationNumeroTelephone() {
        return Stream.of("06 07 98 27 26", "03 22 55 10 58", "02 54 12 45 17");
    }

    @ParameterizedTest
    @MethodSource("validationNumeroTelephone")
    @DisplayName("Certification du numero de téléphone")
    void validationNbPhone(String numero) {

        assertDoesNotThrow(() -> st.setTelephone(numero),
            "Certiciation du numero : " + numero);
    }
}
