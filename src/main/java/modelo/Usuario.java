package modelo;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Setter private ArrayList<Experiencia> experienciaLaboral;
    @Setter private ArrayList<Educacion> educacion;
    @Setter private datosBasicos informacionPersonal;

    @Override
    public String toString() {
        return """
               Usuario{
               \texperiencia=""" + experienciaLaboral + 
                "\n\teducacion=" + educacion + 
                "\n\tdatosBasicos=" + informacionPersonal + 
                "\n}";
    }
    
    public static class UsuarioBuilder {
        @Setter private ArrayList<Experiencia> experienciaLaboral;
        @Setter private ArrayList<Educacion> educacion;
        @Setter private datosBasicos informacionPersonal;

        public UsuarioBuilder() {
            experienciaLaboral = new ArrayList<>();
            educacion = new ArrayList<>();
        }

        public UsuarioBuilder experienciaLaboral(ArrayList<Experiencia> experienciaLaboral) {
            this.experienciaLaboral = experienciaLaboral;
            return this;
        }

        public UsuarioBuilder educacion(ArrayList<Educacion> educacion) {
            this.educacion = educacion;
            return this;
        }

        public UsuarioBuilder informacionPersonal(datosBasicos informacionPersonal) {
            this.informacionPersonal = informacionPersonal;
            return this;
        }

        public Usuario build() {
            return new Usuario(experienciaLaboral, educacion, informacionPersonal);
        }
    }
 
}
