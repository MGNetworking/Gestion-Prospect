package com.metier;

import static org.junit.jupiter.api.Assertions.*;

import com.exception.ExceptionPersonnaliser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.exception.ExceptionPersonnaliser.*;

import java.util.stream.Stream;

public class AdresseTest {

    private Adresse ad;

    @BeforeEach
    void init() {
        ad = new Adresse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "tes", "txt4", "123456", "nom de la rue beaucoup trop long"})
    @DisplayName("Teste le nom de la rue")
    void exceptionSetNomRueTest(String nomTest) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> ad.setNomRue(nomTest), "Adresse le nom de la rue format non respecter : " + nomTest);

        assertThrows(NullPointerException.class,
            () -> ad.setNomRue(null), "Adresse le nom de la rue = null");

    }

    static Stream<String> validationRue() {
        return Stream.of("rue des boulangers", "rue du teste", "avenue du general de gaulle");

    }

    @ParameterizedTest
    @MethodSource("validationRue")
    @DisplayName("certifie ok nom de rue")
    void validationNomRue(String nomRue) {

        assertDoesNotThrow(() -> ad.setNomRue(nomRue),
            "Certification du nom de la rue : " + nomRue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "12348", "nom de la ville beaucoup trop long", "cdd"})
    @DisplayName("Teste le nom de la ville")
    void exceptionSetVilleTest(String nomVille) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> ad.setVille(nomVille), "Format du nom de la ville : " + nomVille);

        assertThrows(NullPointerException.class,
            () -> ad.setVille(null), "nom de la ville est null");
    }

    static Stream<String> validationVille() {
        return Stream.of("Amiens", "Paris", "lille", "marseille");
    }

    @ParameterizedTest
    @MethodSource("validationVille")
    @DisplayName("certifie ok nom de la ville")
    void validationNomVille(String nomVille) {

        assertDoesNotThrow(() -> ad.setVille(nomVille),
            "Certification du nom de la ville : " + nomVille);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1234", "123456", "abcdef"})
    @DisplayName("Teste le code postale")
    void exceptionSetCodePostTest(String codePostale) {
        assertThrows(ExceptionPersonnaliser.class,
            () -> ad.setCodePost(codePostale), "Format code postale : " + codePostale);

        assertThrows(NullPointerException.class,
            () -> ad.setCodePost(null), "code postale null : " + codePostale);
    }

    @ParameterizedTest
    @ValueSource(strings = {"80000", "75000", "68130"})
    @DisplayName("certifie ok le code postale")
    void validationCodePostale(String codePostale) {
        assertDoesNotThrow(() -> ad.setCodePost(codePostale),
            "Certification du code postale: " + codePostale);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 201})
    @DisplayName("Teste valeur de numero de rue")
    void exceptionSetNumeroRue(int numeroRue) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> ad.setNumeroDeRueSt(numeroRue), "Format numéro : " + numeroRue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "1", "200"})
    @DisplayName("certifie ok le numeroi de la rue")
    void validationNumeroRue(int nuemroRue) {

        assertDoesNotThrow(() -> ad.setNumeroDeRueSt(nuemroRue),
            "Certification du numro de la rue : " + nuemroRue);
    }
}
