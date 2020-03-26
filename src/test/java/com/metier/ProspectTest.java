package com.metier;

import com.exception.ExceptionPersonnaliser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProspectTest {

    Prospect prospect;

    @BeforeEach
    void init() {
        prospect = new Prospect();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "2000/10/05", "01/9/1982", "20-10-2020", "20/10/20"})
    @DisplayName("Teset sur date les Prospections")
    void exceptionSetDatePropectTest(String date) {

        assertThrows(ExceptionPersonnaliser.class,
            () -> prospect.setDatePropect(date), "Date prospection est : " + date);

        assertThrows(NullPointerException.class,
            () -> prospect.setDatePropect(null), "Date prospection est null");
    }

    static Stream<String> validDates() {
        return Stream.of(
            "20/10/2005",
            "20/10/2010",
            "20/10/2010"
        );
    }

    @ParameterizedTest
    @MethodSource("validDates")
    @DisplayName("Prospect date Format regex ")
    void validSetDatePropectFormatTest(String value) {
        assertDoesNotThrow(
            () -> prospect.setDatePropect(value),
            "Format ne doit pas renvoyer exce^ption : " + value);
    }

    @Test
    @DisplayName("Prospect Interet null")
    void exceptionSetInteretNullTest() {

        assertThrows(ExceptionPersonnaliser.class,
            () -> prospect.setInteresse(null), "=> prospect interet null ");
    }


    // parti teste perso
    public int add(int a, int b) {
        return a + b;
    }

    @ParameterizedTest
    @CsvSource({
        "10, 10, 20",
        "2, -4, -2"
    })
    void testAdd(int a, int b, int result) {
        assertEquals(result, add(a, b),
            "Certifie que : " + a + " + " + b + " = " + result);
    }


}
