package com.metier;

import com.exception.ExceptionPersonnaliser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProspectTest {

    Prospect prospect;

    @BeforeEach
    void init() {
        prospect = new Prospect();
    }

    @Test
    @DisplayName("Prospect date null ")
    void exceptionSetDatePropectNullTest() {
        assertThrows(NullPointerException.class,
            () -> prospect.setDatePropect(null), "=> prospect date null");
    }

    @Test
    @DisplayName("Prospect date vide ")
    void exceptionSetDatePropectVideTest() {
        assertThrows(ExceptionPersonnaliser.class,
            () -> prospect.setDatePropect(""), "=> propest date vide");
    }

    @Test
    @DisplayName("Prospect date Format regex ")
    void exceptionSetDatePropectFormatTest() {
        assertThrows(ExceptionPersonnaliser.class,
            () -> prospect.setDatePropect("2000/10/05"), " => propest format date regex");
    }

    @Test
    @DisplayName("Prospect Interet null")
    void exceptionSetInteretNullTest() {
        assertThrows(NullPointerException.class,
            () -> prospect.setInteresse(null), "=> prospect interet null ");
    }

/*

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
            () -> prospect.setDatePropect("20/10/2005"),
            " => propest format date regex");
    }

    public int add(int a, int b) {
        return a + b;
    }

    @ParameterizedTest
    @CsvSource({
        "10, 10, 20",
        "2, -4, -2"
    })
    void testAdd(int a, int b, int result) {
        assertEquals(result, add(a, b));
    }*/


}
