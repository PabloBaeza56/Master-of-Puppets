package database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.regex;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.bson.conversions.Bson;


public class QuerysMongoDB {
    private MongoDBConnection db;
    
    public QuerysMongoDB(){
        this.db = MongoDBConnection.getInstance();
    }
    
    public double obtenerPromedioDuracionEmpleo(Map<String, String> datos) {

     
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        Bson unwindStage = unwind("$experienciaLaboral");
        Bson projectStage = project(new Document("_id", 0)
                .append("duracionMeses", "$experienciaLaboral.permanenciaEmpleado.duracionMeses"));

        Bson filterStage = match(
                and(
                        or(
                                regex("educacion.centroEducativo", quitarAcentos(datos.get("acronimoUniversidad")), "i"),
                                regex("educacion.centroEducativo", quitarAcentos(datos.get("nombreUniversidadEspaniol")), "i"),
                                regex("educacion.centroEducativo", quitarAcentos(datos.get("nombreUniversidadIngles")), "i"),
                                regex("educacion.centroEducativo", datos.get("acronimoUniversidad"), "i"),
                                regex("educacion.centroEducativo", datos.get("nombreUniversidadEspaniol"), "i"),
                                regex("educacion.centroEducativo", datos.get("nombreUniversidadIngles"), "i")
                        ),
                        or(
                                regex("educacion.gradoAcademico", quitarAcentos(datos.get("nombreCarreraEspaniol")), "i"),
                                regex("educacion.gradoAcademico", quitarAcentos(datos.get("nombreCarreraIngles")), "i"),
                                regex("educacion.gradoAcademico", datos.get("nombreCarreraEspaniol"), "i"),
                                regex("educacion.gradoAcademico", datos.get("nombreCarreraIngles"), "i")
                        )
                )
        );

        MongoCursor<Document> cursor = collection.aggregate(List.of(unwindStage, filterStage, projectStage)).iterator();

        int totalDuracionMeses = 0;
        int count = 0;

        while (cursor.hasNext()) {

            Document doc = cursor.next();
            if (doc.containsKey("duracionMeses")) {
                totalDuracionMeses += Integer.parseInt(doc.getString("duracionMeses"));
                count++;
            }
        }

        double promedio = count > 0 ? (double) totalDuracionMeses / count : 0;
        return promedio;
    }

    public Map<String, Integer> obtenerEmpresasTrabajadas(Map<String, String> datos) {
   
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        // Crear un diccionario para almacenar las empresas y el recuento de personas que trabajaron en ellas
        Map<String, Integer> recuentoEmpresas = new HashMap<>();

        // Crear el filtro para la consulta
        Bson filterStage = filtroGenerico(datos);

        // Iterar sobre cada documento en la colección
        MongoCursor<Document> cursor = collection.find(filterStage).iterator();

        while (cursor.hasNext()) {
            Document documento = cursor.next();
            List<Document> experienciaLaboral = (List<Document>) documento.get("experienciaLaboral");

            // Crear un conjunto para registrar las empresas únicas para esta persona
            Set<String> empresasUnicasPorPersona = new HashSet<>();

            // Actualizar el conjunto de empresas únicas para esta persona
            for (Document experiencia : experienciaLaboral) {
                String nombreEmpresa = experiencia.getString("nombreEmpresa");
                empresasUnicasPorPersona.add(nombreEmpresa);
            }

            // Actualizar el recuento de personas que trabajaron en cada empresa
            for (String empresa : empresasUnicasPorPersona) {
                recuentoEmpresas.put(empresa, recuentoEmpresas.getOrDefault(empresa, 0) + 1);
            }
        }

        return recuentoEmpresas;
    }

    public double obtenerPuestosExperienciaPorUsuario(Map<String, String> datos) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        int totalPuestos = 0;
        int totalUsuarios = 0;

        Bson filterStage = filtroGenerico(datos);

        MongoCursor<Document> cursor = collection.find(filterStage).iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            List<Document> experienciaLaboral = (List<Document>) doc.get("experienciaLaboral");
            totalPuestos += experienciaLaboral.size();
            totalUsuarios++;
        }

        if (totalUsuarios == 0) {
            return 0;
        } else {
            return (double) totalPuestos / totalUsuarios;
        }
    }

    public static String quitarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{ASCII}]", "");
    }
    
    public Bson filtroGenerico(Map<String, String> datos){
        Bson filterStage = Filters.and(
                Filters.or(
                        Filters.regex("educacion.centroEducativo", quitarAcentos(datos.get("acronimoUniversidad")), "i"),
                        Filters.regex("educacion.centroEducativo", quitarAcentos(datos.get("nombreUniversidadEspaniol")), "i"),
                        Filters.regex("educacion.centroEducativo", quitarAcentos(datos.get("nombreUniversidadIngles")), "i"),
                        Filters.regex("educacion.centroEducativo", datos.get("acronimoUniversidad"), "i"),
                        Filters.regex("educacion.centroEducativo", datos.get("nombreUniversidadEspaniol"), "i"),
                        Filters.regex("educacion.centroEducativo", datos.get("nombreUniversidadIngles"), "i")
                ),
                Filters.or(
                        Filters.regex("educacion.gradoAcademico", quitarAcentos(datos.get("nombreCarreraEspaniol")), "i"),
                        Filters.regex("educacion.gradoAcademico", quitarAcentos(datos.get("nombreCarreraIngles")), "i"),
                        Filters.regex("educacion.gradoAcademico", datos.get("nombreCarreraEspaniol"), "i"),
                        Filters.regex("educacion.gradoAcademico", datos.get("nombreCarreraIngles"), "i")
                )
        );
        return filterStage;
    }
    
    public long obtenerNumeroUsuariosQueCumplenFiltro(Map<String, String> datos) {

 
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        Bson filterStage = filtroGenerico(datos);

        long count = collection.countDocuments(filterStage);
        return count;
    }
    
}
