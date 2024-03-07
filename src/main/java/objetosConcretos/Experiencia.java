package objetosConcretos;

public class Experiencia {
    private String nombreEmpresa;

    
    private String puesto;
    private Fechas fecha;
    private String ubicacion;


    
    
    public Experiencia() {
        this.nombreEmpresa = "";
        this.puesto = "";
        this.fecha = null;
        this.ubicacion = "";

    }

    public Experiencia(String nombreEmpresa, String puesto,Fechas fecha ,String ubicacion) {
        this.nombreEmpresa = nombreEmpresa;
        this.puesto = puesto;
        this.fecha = fecha;
        this.ubicacion = ubicacion;

        
    }
    

  
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

   

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

 
    public void setFecha(Fechas fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
          return """
                 Experiencia{
                 \tnombreEmpresa=""" + nombreEmpresa + 
            "\n\tpuesto=" + puesto + 
            "\n\tfecha=" + fecha + 
            "\n\tubicacion=" + ubicacion + 
            "\n}";
    }

    
    
}
