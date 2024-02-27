package observerTEST;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManejadorEventos {
    
    Map<String, List<EscuchadorEventos>> listeners = new HashMap<>();

    public ManejadorEventos(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EscuchadorEventos listener) {
        List<EscuchadorEventos> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EscuchadorEventos listener) {
        List<EscuchadorEventos> users = listeners.get(eventType);
        users.remove(listener);
    }

    //notificar a los observadores para que se actualicen
    public void notify(String eventType, File file) {
        List<EscuchadorEventos> users = listeners.get(eventType);
        for (EscuchadorEventos listener : users) {
            listener.update(eventType, file);
        }
    }
}