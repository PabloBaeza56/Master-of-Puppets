package objetosConcretos;


public class datosBasicos {

    private String nombre;
    private String leyenda;
    private String ubicacion;


    public datosBasicos() {
        this.nombre = "";
        this.leyenda = "";
        this.ubicacion = "";  
    }

    public datosBasicos(String nombre, String leyenda, String ubicacion) {
        this.nombre = nombre;
        this.leyenda = leyenda;
        this.ubicacion = ubicacion;   
    }

 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return """
               datosBasicos{
               \tnombre=""" + nombre + 
                "\n\tleyenda=" + leyenda + 
                "\n\tubicacion=" + ubicacion + 
                "\n}";
    }
}
