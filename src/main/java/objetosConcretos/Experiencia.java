package objetosConcretos;

public class Experiencia {
    private String nombreEmpresa;

    
    private String puesto;
    private Fechas fecha;
    private String ubicacion;
    private String descripcion;
    
    
    public Experiencia() {
        this.nombreEmpresa = "";
        this.puesto = "";
        this.fecha = null;
        this.ubicacion = "";
        this.descripcion = "";
    }

    public Experiencia(String nombreEmpresa, String puesto, Fechas fecha, String ubicacion, String descripcion) {
        this.nombreEmpresa = nombreEmpresa;
        this.puesto = puesto;
        this.fecha = fecha;  
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }
    

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Fechas getFecha() {
        return fecha;
    }

    public void setFecha(Fechas fecha) {
        this.fecha = fecha;
    }
}
