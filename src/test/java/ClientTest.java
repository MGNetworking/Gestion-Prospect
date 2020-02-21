import com.bean.exception.ExceptionPersonnaliser;
import com.bean.metier.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {


    @Test
    @DisplayName("1er teste")
    void exception_1_CalculTeste(){

        Client cl = new Client();

        assertThrows(ArithmeticException.class,
                () -> cl.calculRatioClientEmployer(10, 0) , "divivsion par 0");

        assertThrows(ExceptionPersonnaliser.class,
                () -> cl.calculRatioClientEmployer(10, 10) , "la moyen de chiffre d'affaire");


    }

    @Test
    @DisplayName("2eme teste")
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