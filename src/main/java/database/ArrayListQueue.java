package database;

import java.util.ArrayList;
import java.util.List;

public class ArrayListQueue implements Queue {
    
    protected ArrayList cola;
    protected MongoDBConnection db;
    
    public ArrayListQueue(){
        cola = new ArrayList();
        this.db = new MongoDBConnection();
    }

    @Override
    public void enqueue(Object dato) {
        cola.add(dato);
    }

    @Override
    public Object dequeue() {
        if (cola.isEmpty()){
            System.out.println("Cola vacia");
            return null;
        } else {
            return cola.remove(0);
        }
    }

    @Override
    public int size() {
       return cola.size();
    }

    @Override
    public Object front() {
        if (cola.isEmpty()){
            System.out.println("Cola vacia");
            return null;
        } else {
            return cola.get(0);
        } 
    }

    @Override
    public boolean isEmpty() {
        return cola.isEmpty();
    }
    
    public void launcherURLS(List<String> elementos){
        for (String elemento : elementos) {
            this.enqueue(elemento);
        }
        
        if (this.size() > 30){
            this.vaciarDatos();
        }
    }
  
    
    public void vaciarDatos(){
        System.out.println("Liberando datos...");
        while (!this.isEmpty()){ 
            //db.InsertarURL("DatosSteamRadar","URLS", (String) this.front());
            System.out.println(this.front());
            this.dequeue(); 
        }
        
    }
    
    
}
