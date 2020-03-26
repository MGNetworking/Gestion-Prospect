package com.metier;

import com.exception.ExceptionPersonnaliser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class ClientTest {

    private Client client;

    @BeforeEach
    void init() {
        client = new Client();
    }


    @ParameterizedTest
    @CsvSource({"100,-1", "10,10", "1,0", "0,1"})
    @DisplayName("Teste calcule du Ratio ")
    void exceptionCalculRatio(int chiffreAffaire, int nEmployer) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> client.calculRatioClientEmployer(chiffreAffaire, nEmployer),
            "la moyen de chiffre d'affaire : " + chiffreAffaire + " / " + nEmployer);
    }


    @ParameterizedTest
    @CsvSource({
        "100, 10",
        "1000, 100"
    })
    @DisplayName("Teste calcule du Ratio ")
    void valideationCalculRatio(int chiffreAffaire, int nEmployer) {

        assertDoesNotThrow(() -> client.calculRatioClientEmployer(chiffreAffaire, nEmployer),
            "la moyen de chiffre d'affaire : " + chiffreAffaire + " / " + nEmployer);
    }

}
