package objetosConcretos;


public class Educacion {
    private String instituto;
    private String titulo;
    private String anioInicio;
    private String anioFin;

    public Educacion() {
        this.instituto = "";
        this.titulo = "";
        this.anioInicio = "";  
        this.anioFin = "";
    }

    public Educacion(String instituto, String titulo, String anioInicio, String anioFin) {
        this.instituto = instituto;
        this.titulo = titulo;
        this.anioInicio = anioInicio;  
        this.anioFin = anioFin;
    }

  
    public void setAnioInicio(String anioInicio) {
        this.anioInicio = anioInicio;
    }

   

    public void setAnioFin(String anioFin) {
        this.anioFin = anioFin;
    }
    
    

   
    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Educacion{" + "instituto=" + instituto + ", titulo=" + titulo + ", anioInicio=" + anioInicio + ", anioFin=" + anioFin + '}';
    }

    
}
