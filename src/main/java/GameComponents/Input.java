package GameComponents;

/**
 *
 * @author Moresi Gianmarco
 */
public interface Input {
    /*
        Questa e' una interfaccia perche' tutte le classi che compongono il gioco
        effettuano un'acquisizone da file, ma la struttura delle classi e' diversa
        quindi, utilizzo questa interfaccia per implementare metodi differenti
        in base alla loro struttura.
    */
    void acquisizoneInputFile(String lineaInput);
}
