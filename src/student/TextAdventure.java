package student;

import ias.Player;
import ias.TextAdventureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TextAdventure.
 */
public class TextAdventure implements ias.TextAdventure {

    private HashMap<String, String> items = new HashMap<>();
    private HashMap<String, String> scenery = new HashMap<>();
    private List<TextAdventureMutation> mutations = new ArrayList<>();
    private TextAdventureTile[][] map = null;
    private String name;

    // Konstruktor für ein Spiel
    protected TextAdventure(String name, int width, int height) throws TextAdventureException {
        if (width <= 0 || height <= 0) { // wenn höhe oder breite kleiner oder gleich 0 sind wird ein fehler geworfen
            throw new TextAdventureException("Width and Height must be positive.");
        }

        if (name == null || name.length() == 0) { // wenn der Name nicht gegeben ist wird ein fehler geworfen
            throw new TextAdventureException("Text adventure name must be set.");
        }

        this.name = name;
        map = new TextAdventureTile[width][height]; // array für das Spielfeld erstellen
        for (int x = 0; x < width; x++) { // schleife über die breite
            for (int y = 0; y < height; y++) { // innere schleife über die höhe
                map[x][y] = new TextAdventureTile(); // jedes Feld initialisieren
            }
        }
    }
    // Methode registriert einen Item typ
    @Override
    public void addItemType(String id, String description) throws TextAdventureException {
        if (id == null || id.isBlank()) { // wenn id nicht gegeben, wird ein fehler geworfen
            throw new TextAdventureException("Item type id can not be null or empty.");
        }
        if (!id.matches("[A-Za-z]+")) { // wenn id nicht nur aus Buchstaben besteht, wird ein Fehler geworfen
            throw new TextAdventureException("Item type id may only contain letters.");
        }
        // wenn id bereits verwendet wird, wird ein fehler geworfen
        if (items.containsKey(id) || scenery.containsKey(id)) {
            throw new TextAdventureException("Item and Scenery id need to be unique.");
        }

        items.put(id, description);
    }

    // Methode registriert einen Scenery typ
    @Override
    public void addSceneryType(String id, String description) throws TextAdventureException {
        if (id == null || id.isBlank()) { // wenn id nicht gegeben, wird ein fehler geworfen
            throw new TextAdventureException("Scenery type id can not be null or empty.");
        }
        if (!id.matches("[A-z]+")) { // wenn id nicht nur aus Buchstaben besteht, wird ein Fehler geworfen
            throw new TextAdventureException("Scenery type id may only contain letters.");
        }
        // wenn id bereits verwendet wird, wird ein fehler geworfen
        if (items.containsKey(id) || scenery.containsKey(id)) {
            throw new TextAdventureException("Item and Scenery id need to be unique.");
        }

        scenery.put(id, description);
    }
    // Methode platziert ein Item auf dem Spielfeld
    @Override
    public void placeItem(String type, int x, int y) throws TextAdventureException {
        if (!isInBounds(x, y)) { // wenn koordinaten nicht im Spielfeld wird ein fehler geworfen
            throw new TextAdventureException(
                    "Coordinates need to be between 0 and x:" + map.length + " y:" + map[0].length);
        }

        if (items.containsKey(type)) { // wenn typ ein item
            map[x][y].getItems().add(type);
        } else if (scenery.containsKey(type)) { // wenn typ ein scenery objekt
            map[x][y].getScenery().add(type);
        } else { // andernfalls ein unbekannter typ
            throw new TextAdventureException("Unknown object type " + type);
        }
    }
    // Methode erstellt eine neue Kompositionsregel
    @Override
    public void addComposition(String in1, String in2, String out, String description) throws TextAdventureException {
        if (description == null) { // wenn beschreibung nicht gegeben
            throw new TextAdventureException("Composition description needs to be set.");
        }
        if (!objectNamesValid(in1, in2, out)) { // wenn ein objekt typ nicht registriert
            throw new TextAdventureException("Composition contains invalid objects. " + in1 + ", " + in2 + ", " + out);
        }
        if (isMutationDuplicate(in1, in2)) { // wenn bereits eine regel existiert
            throw new TextAdventureException("A mutation with inputs " + in1 + " and " + in2 + " already exists.");
        }

        TextAdventureMutation mutation = new TextAdventureMutation(description); // erstell eine neue Regel
        mutation.getInputs()[0] = in1; // hinzufügen von inputs und outputs
        mutation.getInputs()[1] = in2;
        mutation.getOutputs()[0] = out;
        mutations.add(mutation);
    }
    @Override
    public void addDecomposition(String in, String out1, String out2, String description)
            throws TextAdventureException { // Methode erstellt eine neue Dekompositionsregel
        if (description == null) { // wenn beschreibung nicht gegeben
            throw new TextAdventureException("Decomposition description needs to be set.");
        }
        if (!objectNamesValid(in, out1, out2)) { // wenn ein objekt typ nicht registriert
            throw new TextAdventureException("Decomposition contains invalid objects. "
                    + in + ", " + out1 + ", " + out2);
        }
        if (isMutationDuplicate(in, null)) { // wenn bereits eine regel existiert
            throw new TextAdventureException("A decomposition with input " + in + " already exists.");
        }

        TextAdventureMutation mutation = new TextAdventureMutation(description); // erstell eine neue Regel
        mutation.getInputs()[0] = in; // hinzufügen von inputs und outputs
        mutation.getOutputs()[0] = out1;
        mutation.getOutputs()[1] = out2;
        mutations.add(mutation);
    }

    @Override
    public void addTransformation(String in1, String in2, String out1, String out2, String description)
            throws TextAdventureException { // Methode erstellt eine neue Transformationsregel
        if (description == null) { // wenn beschreibung nicht gegeben
            throw new TextAdventureException("Transformation description needs to be set.");
        }
        if (!objectNamesValid(in1, in2, out1, out2)) { // wenn ein objekt typ nicht registriert
            throw new TextAdventureException("Transformation contains invalid objects. " + in1
                    + ", " + in2 + ", " + out1 + ", " + out2);
        }
        if (isMutationDuplicate(in1, in2)) { // wenn bereits eine regel existiert
            throw new TextAdventureException("A mutation with inputs " + in1 + " and " + in2 + " already exists.");
        }

        TextAdventureMutation mutation = new TextAdventureMutation(description); // erstell eine neue Regel
        mutation.getInputs()[0] = in1; // hinzufügen von inputs und outputs
        mutation.getInputs()[1] = in2;
        mutation.getOutputs()[0] = out1;
        mutation.getOutputs()[1] = out2;
        mutations.add(mutation);
    }
    @Override
    public Player startGame(int x, int y) throws TextAdventureException { // Methode startet das Spiel
        if (!isInBounds(x, y)) { // wenn startposition nicht im Feld
            throw new TextAdventureException(
                    "Coordinates need to be between 0 and x:" + map.length + " y:" + map[0].length);
        }
        Player player = new TextAdventurePlayer(this, x, y); // erstellt ein neues Spielerobjekt
        return player;
    }
    @Override
    public String getName() {
        return name;
    }

    /**
     * Methode zur überprüfung, ob ein typ ein scenery objekt ist.
     * @param item Name des Objekts
     * @return true, wenn der Typ ein scenery objekt ist
     */
    public boolean isScenery(String item) {
        return scenery.containsKey(item);
    }

    /**
     * Methode um ein Item auf der Karte zu platzieren.
     * @param item Name des objekts
     * @param x breite des Feldes
     * @param y höhe des Feldes
     */
    public void add(String item, int x, int y) {
        map[x][y].getItems().add(item);
    }

    /**
     * Methode prüft, ob koordinaten in der Map sind.
     * @param x breite des Feldes
     * @param y höhe des Feldes
     * @return true, wenn die koordinaten in der Map sind
     */
    protected boolean isInBounds(int x, int y) { //
        return x >= 0 && x < map.length && y >= 0 && y < map[x].length;
    }

    protected List<String> getObjectsAtPosition(int x, int y) {
        List<String> result = new ArrayList<>();
        for (String item : map[x][y].getItems()) {
            result.add(item + " - " + items.get(item));
        }
        for (String scene : map[x][y].getScenery()) {
            result.add(scene + " - " + scenery.get(scene));
        }
        return result;
    }

    /**
     * Methode um zu überprüfen, ob ein item oder scenery objekt an der Position existiert.
     * @param item Typ
     * @param x breite des Feldes
     * @param y höhe des Feldes
     * @return true, wenn ein objekt von einer von 2 typen existiert
     */
    public boolean contains(String item, int x, int y) {
        return map[x][y].getItems().contains(item) || map[x][y].getScenery().contains(item);
    }
    
    protected String getItemDescription(String item) { // Methode um die Beschreibung eines Item typs abzurufen
        return items.get(item);
    }

    // Methode zur überprüfung ob an den Koordinaten ein Scenery objekt von dem angegebenen typ ist
    protected boolean isScenery(String item, int x, int y) {
        return map[x][y].getScenery().contains(item);
    }

    protected boolean removeItem(String item, int x, int y) { // Methode um ein Item von einem Feld zu entfernen
        return map[x][y].getItems().remove(item);
    }

    // Methode um ein item oder scenery objekt von einem Feld zu entfernen
    protected boolean remove(String item, int x, int y) {
        return removeItem(item, x, y) || map[x][y].getScenery().remove(item);
    }

    // Methode gibt eine Mutation zu den eingabeobjekten zurück
    protected TextAdventureMutation findMutation(String in1, String in2) throws TextAdventureException {
        for (TextAdventureMutation mutation : mutations) { // für jede Mutationsregel
            if (mutation.matches(in1, in2)) { // wenn die mutationsregel passt
                return mutation; // Mutation wurde gefunden und schleife wird abgebrochen
            }
        }

        return null; // keine passende Regel gefunden
    }

    // Methode überprüft, ob es bereits eine Mutationsregel mit den gleichen eingabeobjekten gibt
    private boolean isMutationDuplicate(String in1, String in2) throws TextAdventureException {
        if (findMutation(in1, in2) != null) { // wenn eine Regel gefunden wurde
            return true;
        }
        return false;
    }

    // Methode prüft mehrere objekt typen, ob diese registriert wurden
    private boolean objectNamesValid(String... objectIds) {
        for (String object : objectIds) { // für alle gegebenen objekt typen
            // wenn ein typ weder item noch scenery ist
            if (!items.containsKey(object) && !scenery.containsKey(object)) {
                return false; // wird die Schleife abgebrochen und false zurückgegeben
            }
        }

        return true;
    }
}