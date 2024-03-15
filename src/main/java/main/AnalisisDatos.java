package main;

import database.MongoDBConnection;
import java.util.Map;
import lombok.Getter;

public class AnalisisDatos {
    @Getter private final MongoDBConnection conector;
    
    protected AnalisisDatos(){
        this.conector = MongoDBConnection.getInstance();
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
        System.out.println(getConector().obtenerNumeroDocumentosEnColeccion());
    }
    
    protected void obtenerPromedioDuracionEmpleo(Map<String, String> datos){
        System.out.println(getConector().obtenerPromedioDuracionEmpleo(datos));
    }
    
    
    
    
    
    
}
