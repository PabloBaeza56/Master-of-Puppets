package observerTEST;

import java.io.File;

public class ActivadorEventoConcreto implements EscuchadorEventos {
    private final String email;

    public ActivadorEventoConcreto(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}