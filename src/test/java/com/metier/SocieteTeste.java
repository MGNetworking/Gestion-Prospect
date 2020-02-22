package com.metier;

import com.exception.ExceptionPersonnaliser;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SocieteTest {

    // heritage locale pour le teste de la classe abstraite Societe
    class SocieteTeste extends Societe {
    }


    @Test
    @DisplayName("Societe Raison Sociale Null ")
    void excetionSetRaisonSocialeNullTest() {

        SocieteTeste st = new SocieteTeste();
        String stNull = null;
        assertThrows(NullPointerException.class,
            () -> st.setRaisonSociale(stNull), "Societe raison sociale null");
    }
}
