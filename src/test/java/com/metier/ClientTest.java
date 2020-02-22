package com.metier;

import com.exception.ExceptionPersonnaliser;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client cl ;
    @BeforeEach
    void init(){
        cl = new Client();
    }

    @Test
    @DisplayName("Client Division par Zero")
    void exceptioncalculRatioClientEmployerDivisionParZeroTeste(){
        assertThrows(ArithmeticException.class,
                () -> cl.calculRatioClientEmployer(10, 0) , "divivsion par 0");

    }

    @Test
    @DisplayName("Client Moyen du chiffre d'affaire = 1")
    void exceptioncalculRatioClientEmployerRationEgale1(){

        assertThrows(ExceptionPersonnaliser.class,
            () -> cl.calculRatioClientEmployer(10, 10) , "la moyen de chiffre d'affaire =1 ");
    }

    @Test
    @DisplayName("Client Moyen du chiffre d'affaire = 10")
    void exceptioncalculRatioClientEmployerRationEgale10(){

        assertThrows(ExceptionPersonnaliser.class,
            () -> cl.calculRatioClientEmployer(100, 11) , "la moyen de chiffre d'affaire doit etre > 10");
    }

    @Test
    @DisplayName("Client Moyen du chiffre d'affaire : AutreTechnique")
    void exceptioncalculRatioClientEmployer_AutreTechnique_CalculTest(){

        Client client = new Client();

        boolean exceptionArith = false;

        try{
            client.calculRatioClientEmployer(100,100);

        }catch (ExceptionPersonnaliser ar){
            exceptionArith = true;
        }

        assertTrue(exceptionArith);
    }

}
