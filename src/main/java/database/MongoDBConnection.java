package database;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import objetosConcretos.Usuario;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.out;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Aggregates.unwind;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Indexes.ascending;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import objetosConcretos.LinkUsuario;
import objetosConcretos.UsuarioPivote;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.types.ObjectId;


public class MongoDBConnection {

    @Getter private MongoDatabase database;
   
    private MongoDBConnection() {
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileReader("config.properties"));
            MongoClient mongoClient = MongoClients.create(propiedades.getProperty("mongoURI"));
            this.database = mongoClient.getDatabase("STEAM-RADAR");
        } catch (IOException e) {}
    }
    
    public static MongoDBConnection getInstance() {
        return MongoDBConnectionHolder.INSTANCE;
    }

    private static class MongoDBConnectionHolder {
        private static final MongoDBConnection INSTANCE = new MongoDBConnection();
    }

    public void InsertarURL(LinkUsuario usuario) {

        
       MongoCollection<Document> collection = this.getDatabase().getCollection("Links");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
    public void InsertarPivote(UsuarioPivote usuario) {

        
       MongoCollection<Document> collection = this.getDatabase().getCollection("Pivotes");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
    public void InsertarURL(ArrayList<LinkUsuario> usuarios) {
        for (LinkUsuario usuario : usuarios) {
            InsertarURL(usuario);
        }
    }

    public void SubirUsuario(Usuario usuario) {

        
        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }

    public double obtenerPromedioDuracionEmpleo(Map<String, String> datos) {

     
        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

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
   
        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

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

        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

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

    public long obtenerNumeroUsuariosQueCumplenFiltro(Map<String, String> datos) {

 
        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

        Bson filterStage = filtroGenerico(datos);

        long count = collection.countDocuments(filterStage);
        return count;
    }

    public long obtenerNumeroDocumentosEnColeccion() {

        MongoCollection<Document> collection = this.getDatabase().getCollection("2024");

        long count = collection.countDocuments();
        return count;
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
    
    
   public List<LinkUsuario> obtenerDocumentos(int cantidad) {
        List<LinkUsuario> listaLinks = new ArrayList<>();

        MongoCollection<Document> collection = this.getDatabase().getCollection("Links");

        long totalDocumentos = collection.countDocuments();

        if (totalDocumentos == 0) {
            System.out.println("La colección está vacía.");
            return listaLinks;
        }

        int cantidadReal = (int) Math.min(totalDocumentos, cantidad);

        Document filtro = new Document("visitado", false);

        try (MongoCursor<Document> cursor = collection.find(filtro).limit(cantidadReal).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                LinkUsuario linkUsuario = documentoALinkUsuario(doc);
                listaLinks.add(linkUsuario);
            }
        }

        return listaLinks;
    }

    
    private LinkUsuario documentoALinkUsuario(Document doc) {
        
        String urlUsuario = doc.getString("UrlUsuario");
        Boolean visitado = doc.getBoolean("visitado");
        return new LinkUsuario( doc.getObjectId("_id") , urlUsuario, visitado);
    }
    
     public void marcarDocumentoComoVisitado(ObjectId idDocumento) {

        MongoCollection<Document> collection = this.getDatabase().getCollection("Links");

        Document filtroId = new Document("_id", idDocumento);

        Document update = new Document("$set", new Document("visitado", true));

        collection.updateOne(filtroId, update);
    }
     
      public void eliminarDuplicadosPorUrlUsuario() {

        MongoCollection<Document> collection = this.getDatabase().getCollection("Links");

        // Utilizamos un conjunto para almacenar los URLUsuario únicos
        Set<String> urlsUnicos = new HashSet<>();

        // Creamos un cursor para recorrer todos los documentos en la colección
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String urlUsuario = doc.getString("UrlUsuario");
                // Verificamos si ya hemos visto este URLUsuario antes
                if (urlsUnicos.contains(urlUsuario)) {
                    // Si ya existe, eliminamos el documento duplicado
                    collection.deleteOne(new Document("UrlUsuario", urlUsuario));
                } else {
                    // Si es único, lo agregamos al conjunto para futuras comprobaciones
                    urlsUnicos.add(urlUsuario);
                }
            }
        }
    }
      
      
    public  List<String> obtenerNombresUsuariosDesdeMongoDB() {
        List<String> nombresUsuarios = new ArrayList<>();
        
        MongoCollection<Document> collection = this.getDatabase().getCollection("Pivotes");

            // Utiliza el método distinct() para obtener los nombres de usuario distintos
            List<Object> resultados = collection.distinct("nombre", String.class).into(new ArrayList<>());

            // Convierte los resultados a una lista de String
            for (Object resultado : resultados) {
                nombresUsuarios.add((String) resultado);
            }
       

        return nombresUsuarios;
    }  
    
    
    public String buscarURLasociadoConNombrePivote(String nombreABuscar){
        
        MongoCollection<Document> collection = this.getDatabase().getCollection("Pivotes");
            
        Document filtro = new Document("nombre", nombreABuscar);
            
        Document resultado = collection.find(filtro).first();
            
        
        String url = resultado.getString("UrlUsuario");
        System.out.println("URL encontrada para " + nombreABuscar + ": " + url);
        return url;
    }        
          

}
