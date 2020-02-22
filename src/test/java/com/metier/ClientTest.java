import com.bean.exception.ExceptionPersonnaliser;
import com.bean.metier.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    Client cl ;
    @BeforeEach
    void init(){
        cl = new Client();
    }

    @Test
    @DisplayName("Division par Zero")
    void exceptionCalculDivisionTeste(){

        assertThrows(ArithmeticException.class,
                () -> cl.calculRatioClientEmployer(10, 0) , "divivsion par 0");

    }


    @Test
    @DisplayName("Moyen du chiffre d'affaire : 1")
    void ex(){

        assertThrows(ExceptionPersonnaliser.class,
            () -> cl.calculRatioClientEmployer(10, 10) , "la moyen de chiffre d'affaire");
    }

    @Test
    @DisplayName("Moyen du chiffre d'affaire : 2")
    void exception_2_CalculTest(){

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
