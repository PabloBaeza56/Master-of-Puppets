package objetosConcretos;

import java.util.ArrayList;

public class Usuario {

 
    private ArrayList<Experiencia> experiencia;
    private ArrayList<Educacion> educacion;
    private Object datosBasicos;

    public Usuario() {
        this.datosBasicos = null;
        this.experiencia = null;
        this.educacion = null;
       // this.fechaEncuentro = "";
        //this.fechaExtraccion = "";
        //this.extraido = false;
        
    }

    public Usuario(Object datosBasicos, ArrayList<Experiencia> experiencia, ArrayList<Educacion> educacion) {
        this.datosBasicos = datosBasicos;
        this.experiencia = experiencia;
        this.educacion = educacion;
       
        
    }

   

    /*
    public static void main(String[] args) {
        // Crear instancia de Experiencia
        Experiencia experiencia = new Experiencia("Empresa XYZ", "Puesto XYZ", "01/01/2020", "01/01/2022", 24, "Ciudad XYZ", "Descripción de la experiencia XYZ");

        // Crear instancia de Educacion
        Educacion educacion = new Educacion("Instituto ABC", "Título ABC", "01/01/2018", "01/01/2020", 24);

        // Crear instancia de Usuario con Experiencia y Educacion
        Usuario usuario = new Usuario("Nombre Usuario", "Leyenda Usuario", "Ubicación Usuario", experiencia, educacion);

        // Mostrar información del usuario
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Leyenda: " + usuario.getLeyenda());
        System.out.println("Ubicación: " + usuario.getUbicacion());
        System.out.println("Experiencia: " + usuario.getExperiencia().getDescripcion());
        System.out.println("Educación: " + usuario.getEducacion());
    }
*/

    public ArrayList<Experiencia> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ArrayList<Experiencia> experiencia) {
        this.experiencia = experiencia;
    }

    public ArrayList<Educacion> getEducacion() {
        return educacion;
    }

    public void setEducacion(ArrayList<Educacion> educacion) {
        this.educacion = educacion;
    }

    public Object getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(Object datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    @Override
    public String toString() {
        return """
               Usuario{
               \texperiencia=""" + experiencia + 
                "\n\teducacion=" + educacion + 
                "\n\tdatosBasicos=" + datosBasicos + 
                "\n}";
    }


   
}
