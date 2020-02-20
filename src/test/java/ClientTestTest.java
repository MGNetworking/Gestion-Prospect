import com.bean.metier.Client;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTestTest {



    public void exceptionCalculTeste(){

        Client cl = new Client();

        assertThrows(ArithmeticException.class,
                () -> cl.calculRatioClientEmployer(10, 0) , "divivsion par 0");


    }

}