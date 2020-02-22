package com.metier;

import static org.junit.jupiter.api.Assertions.*;

import com.exception.ExceptionPersonnaliser;
import org.junit.jupiter.api.*;

public class AdresseTest {

    Adresse ad ;

    @BeforeEach
    void init(){
        ad = new Adresse();
    }

    @Test
    @DisplayName("L'objet qui porte nom de la rue Null")
    void exceptionSetNomRueNullTest(){
        assertThrows(NullPointerException.class,
            ()-> ad.setNomRue(null) , "Adresse le nom de la rue = null");

    }

    @Test
    @DisplayName("Le nom de la rue vide")
    void exceptionSetNomRueVideTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setNomRue("") , "Adresse le nom de la rue est vide");

    }

    @Test
    @DisplayName("Le nom de la rue vide")
    void exceptionSetNomRueCharTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setNomRue("123456") , "Adresse le nom de la rue doit vaoir des caractéres");

    }

    @Test
    @DisplayName("L'objet qui porte le code societe = null")
    void exceptionSetCodePostNullTest(){
        assertThrows(NullPointerException.class,
            () -> ad.setNomRue(null), "Adresse null");
    }

    @Test
    @DisplayName("Le code societe est vide ")
    void exceptionSetCodePostVideTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setNomRue(""), "Adresse vide ");
    }

    @Test
    @DisplayName("Le format code societe")
    void exceptionSetCodePostFormatTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setNomRue("12356"), "Adresse doit étre composer de caratére");
    }


    @Test
    @DisplayName("L'objet qui porte le nom de ville est null")
    void exceptionSetVilleNullTest(){
        assertThrows(NullPointerException.class,
            ()-> ad.setVille(null), "l'oBjet ville est null");
    }

    @Test
    @DisplayName("L'objet qui porte le nom de ville est vide")
    void exceptionSetVilleVideTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setVille(""), "l'oBjet ville est vide");
    }

    @Test
    @DisplayName("Le format de la ville n'est pas respectée")
    void exceptionSetVilleCharTest(){
        assertThrows(ExceptionPersonnaliser.class,
            ()-> ad.setVille("123456"), "L'objet ville de type string doit étre composer de caratére");
    }
}
