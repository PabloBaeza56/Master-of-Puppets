package observerTEST;

public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new ActivadorEventoConcreto("admin@example.com"));
        editor.events.subscribe("save", new ActivadorEventoConcreto("xxxx@example.com"));

        try {
            editor.openFile("filepah");
            editor.saveFile();
        } catch (Exception e) {System.out.println(e);}
    }
}