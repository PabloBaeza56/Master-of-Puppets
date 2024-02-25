package objetosConcretos;

public class Usuario {

    private String nombre;
    private String leyenda;
    private String ubicacion;
    private Experiencia experiencia;
    private Educacion educacion;
    private String fechaEncuentro;
    private String fechaExtraccion;
    private Boolean extraido;

    public Usuario() {
        this.nombre = "";
        this.leyenda = "";
        this.ubicacion = "";
        this.experiencia = null;
        this.educacion = null;
        this.fechaEncuentro = "";
        this.fechaExtraccion = "";
        this.extraido = false;
        
    }

    public String getFechaEncuentro() {
        return fechaEncuentro;
    }

    public void setFechaEncuentro(String fechaEncuentro) {
        this.fechaEncuentro = fechaEncuentro;
    }

    public String getFechaExtraccion() {
        return fechaExtraccion;
    }

    public void setFechaExtraccion(String fechaExtraccion) {
        this.fechaExtraccion = fechaExtraccion;
    }

    public Boolean getExtraido() {
        return extraido;
    }

    public void setExtraido(Boolean extraido) {
        this.extraido = extraido;
    }

    public Usuario(String nombre, String leyenda, String ubicacion, Experiencia experiencia, Educacion educacion, String fechaEncuentro, String fechaExtraccion, Boolean extraido) {
        this.nombre = nombre;
        this.leyenda = leyenda;
        this.ubicacion = ubicacion;
        this.experiencia = experiencia;
        this.educacion = educacion;
        this.fechaEncuentro = fechaEncuentro;
        this.fechaExtraccion = fechaExtraccion;
        this.extraido = extraido;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public Educacion getEducacion() {
        return educacion;
    }

    public void setEducacion(Educacion educacion) {
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
}
