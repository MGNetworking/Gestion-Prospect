package com.metier;

import com.exception.ExceptionPersonnaliser;
import org.junit.jupiter.api.*;

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
}
