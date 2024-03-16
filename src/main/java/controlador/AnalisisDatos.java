package controlador;

import database.BusquedaDatos;
import database.MongoDBConnection;
import database.QuerysMongoDB;
import java.util.Map;
import lombok.Getter;

public class AnalisisDatos {
    @Getter private final QuerysMongoDB conector;
    @Getter private final BusquedaDatos busqueda;
    protected AnalisisDatos(){
        this.conector = new QuerysMongoDB();
        this.busqueda = new BusquedaDatos();
    }
    
    protected void ObtenerPuestosExperienciaPorUsuario(Map<String, String> datos){
        System.out.println(getConector().obtenerPuestosExperienciaPorUsuario(datos));
    }
    
    protected void obtenerEmpresasTrabajadas(Map<String, String> datos){
        System.out.println(getConector().obtenerEmpresasTrabajadas(datos));
    }
    
    protected void obtenerNumeroUsuariosQueCumplenFiltro(Map<String, String> datos){
        System.out.println(getConector().obtenerNumeroUsuariosQueCumplenFiltro(datos));
    }
    
    protected void obtenerNumeroDocumentosEnColeccion(){
        System.out.println(getBusqueda().obtenerNumeroDocumentosEnColeccion());
    }
    
    protected void obtenerPromedioDuracionEmpleo(Map<String, String> datos){
        System.out.println(getConector().obtenerPromedioDuracionEmpleo(datos));
    }
    
    
    
    
    
    
}
