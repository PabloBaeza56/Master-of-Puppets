package scrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class MiClaseTest {
    
    public MiClaseTest() {}
    
    @Test
    public void testMetodo1() {
        System.out.println("metodo1");
        MiClase instance = new MiClase();
        int expResult = 0;
        int result = instance.metodo1();
        assertEquals(expResult, result);
    }

    @Test
    public void testMetodo2() {
        System.out.println("metodo2");
        MiClase instance = new MiClase();
        String expResult = "Hola Mundo";
        String result = instance.metodo2();
        assertEquals(expResult, result);
    }
    
}
