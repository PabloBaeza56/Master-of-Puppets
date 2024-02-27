package observerTEST;

import java.io.File;

public interface EscuchadorEventos {
    void update(String eventType, File file);
}