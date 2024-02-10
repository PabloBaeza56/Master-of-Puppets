package utilidades;

public class Generales {
    public static void esperarSegundos(int tiempo){
        try {
            Thread.sleep(tiempo * 1000);
        } catch (InterruptedException e) {}  
    }
    
}
