package objetosConcretos;


public class Educacion {
    private String instituto;
    private String titulo;
    private Fechas fecha;

    public Educacion() {
        this.instituto = "";
        this.titulo = "";
        this.fecha = null;
    }

    public Educacion(String instituto, String titulo, Fechas fecha) {
        this.instituto = instituto;
        this.titulo = titulo;
        this.fecha = fecha;  
    }
    
    public Fechas getFecha() {
        return fecha;
    }

    public void setFecha(Fechas fecha) {
        this.fecha = fecha;
    }


    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
