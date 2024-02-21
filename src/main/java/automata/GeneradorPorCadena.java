package automata;

public class GeneradorPorCadena {
    
    public static String metodoURL(String cadena) {
        String[] lineas = cadena.split(" ");
        String cadenaSalida;

        String[] nuevoArreglo = new String[lineas.length];
        int index = 0;
        for (String linea : lineas) {
            String lineaLimpia = linea.trim();
            if (!lineaLimpia.isEmpty()) {
                nuevoArreglo[index] = lineaLimpia;
                index++;
            }
        }

        StringBuilder preCadena = new StringBuilder();
        for (int i = 0; i < index - 1; i++) {
            String subCadena = nuevoArreglo[i];
            preCadena.append(subCadena).append("%20");
        }

        preCadena.append(nuevoArreglo[index - 1]);

        cadenaSalida = "https://www.linkedin.com/search/results/people/?keywords=" + preCadena.toString() + "&origin=GLOBAL_SEARCH_HEADER&page=XXXXX&sid=";
        
        //Si llega a existir este caso se ejecutara
        cadenaSalida = cadenaSalida.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        return cadenaSalida;
    }
    
    public static String metodoBotones(String cadena) {
        return "";
    }
  
}
