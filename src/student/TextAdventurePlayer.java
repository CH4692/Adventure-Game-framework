package student;

import java.util.ArrayList;
import java.util.List;

import ias.Player;
import ias.TextAdventureException;

/**
 * TextAdventurePlayer.
 */
public class TextAdventurePlayer implements Player {

    private int x;
    private int y;
    private TextAdventure adventure;
    private List<String> inventory = new ArrayList<>();

    /**
     * Konstruktor.
     * @param adventure das aktuelle Spiel
     * @param x breite des Feldes
     * @param y höhe des Feldes
     */
    public TextAdventurePlayer(TextAdventure adventure, int x, int y) {
        this.adventure = adventure; // Spielinstanz festlegen
        this.x = x; // spielerposition festlegen
        this.y = y;
    }

    @Override
    public String go(String direction) {
        String result = "You go "; // start der Ergebnisausgabe
        try {
            switch (direction) { // überprüfung der richtung
                case "N":
                    tryExecuteGo(x, y - 1); // versuchen in die angegebene richtung zu gehen
                    result += "north"; // die richtung an die Ausgabe anhängen
                    break;
                case "NE":
                    tryExecuteGo(x + 1, y - 1);
                    result += "northeast";
                    break;
                case "E":
                    tryExecuteGo(x + 1, y);
                    result += "east";
                    break;
                case "SE":
                    tryExecuteGo(x + 1, y + 1);
                    result += "southeast";
                    break;
                case "S":
                    tryExecuteGo(x, y + 1);
                    result += "south";
                    break;
                case "SW":
                    tryExecuteGo(x - 1, y + 1);
                    result += "southwest";
                    break;
                case "W":
                    tryExecuteGo(x - 1, y);
                    result += "west";
                    break;
                case "NW":
                    tryExecuteGo(x - 1, y - 1);
                    result += "northwest";
                    break;
                default:
                    // keine gültige richtung
                    return "Sorry, only the directions N, NE, E, SE, S, SW, W and NW are valid.";
            }
        } catch (IndexOutOfBoundsException e) {
            return "Sorry, there is nothing in that direction."; // ziel liegt außerhalb des spielfelds
        } catch (NullPointerException e) {
            return "Sorry, you dont know how to do that."; // richtung war null
        }
        return result + ".";
    }

    /**
     * versucht auf die angegebenen koordinaten zu gehen.
     * @param x breite des Feldes
     * @param y höhe des Feldes
     * @throws IndexOutOfBoundsException
     */
    private void tryExecuteGo(int x, int y) throws IndexOutOfBoundsException {
        if (!adventure.isInBounds(x, y)) {
            throw new IndexOutOfBoundsException(); // schlägt fehl, wenn außerhalb des spielfelds
        }

        this.x = x; // wenn innerhalb des spielfelds, spielerposition setzen
        this.y = y;
    }

    @Override
    public String[] look() {
        List<String> objects = adventure.getObjectsAtPosition(x, y); // objekte an momentaner position abrufen
        String[] result = new String[objects.size()]; 
        return objects.toArray(result); // liste in ein array umwandeln
    }

    @Override
    public String[] inventory() {
        String[] result = new String[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) { // durch alle objekte im inventar gehen
            String item = inventory.get(i);
            // item name und beschreibung zum array hinzufügen
            result[i] = item + " - " + adventure.getItemDescription(item);
        }
        return result;
    }

    @Override
    public String take(String item) { // nimmt ein item an der Position auf
        if (adventure.isScenery(item, x, y)) { // überprüfen, ob das objekt scenery ist
            return "Sorry, " + item + " is scenery and can't be picked up.";
        }
        if (adventure.removeItem(item, x, y)) { // versucht das Item vom feld zu entfernen
            inventory.add(item); // wenn erfolgreich entfernt, wird es dem Inventar hinzugefügt
            return "You pick up the " + item + ".";
        } else {
            return "Sorry, there is no " + item + " here."; // andernfalls eine fehlermeldung ausgegeben
        }
    }

    @Override
    public String drop(String item) { // lässt ein Item fallen
        if (inventory.remove(item)) { // versucht das Item aus dem Inventar zu entfernen
            adventure.add(item, x, y); // wenn erfolgreich fügt es dem feld hinzu
            return "You drop the " + item;
        }

        return "Sorry, you don't carry any " + item + "."; // andernfalls wird eine Fehlermeldung ausgegeben
    }

    @Override
    public String convert(String item1, String item2) {
        boolean hasItems = false;
        if (inventory.remove(item1)) { // item 1 aus dem inventar entfernen
            // und überprüfen, ob das zweite item existiert
            if (inventory.contains(item2) || adventure.contains(item2, x, y)) {
                hasItems = true;
            }
            inventory.add(item1); // das erste item wieder zurückpacken um einen unveränderten status zu erhalten
        } else if (adventure.remove(item1, x, y)) { // das selbe für den fall, dass das erste item auf dem Feld liegt
            if (inventory.contains(item2) || adventure.contains(item2, x, y)) {
                hasItems = true;
            }
            adventure.add(item1, x, y);
        }

        if (!hasItems) { // fehlermeldung wenn nicht beide items vorhanden
            return "Sorry, you don't have " + item1 + " and " + item2 + ".";
        }

        try {
            // mutationsregel für die beiden Items finden
            TextAdventureMutation mutation = adventure.findMutation(item1, item2);
            if (mutation == null) {
                return "Sorry, there is no way to combine " + item1 + " and " + item2 + ".";
            }

            if (!inventory.remove(item1)) { // erste Item entfernen
                adventure.remove(item1, x, y);
            }

            if (!inventory.remove(item2)) { // zweite Item entfernen
                adventure.remove(item2, x, y);
            }

            return executeMutation(mutation); // mutation ausführen
        } catch (TextAdventureException e) {
            return "Sorry, you failed to find a way to combine " + item1 + " and " + item2 + ".";
        }
    }

    @Override
    public String decompose(String item) {
        // TODO Dekomposition von 'Lake' obwohl diese nicht in den Mutationsregeln enthalten sind
        if (!inventory.contains(item) && !adventure.contains(item, x, y)) { // überprüfen, ob item vorhanden
            return "Sorry, you don't have any " + item + ".";
        }

        try {
            TextAdventureMutation mutation = adventure.findMutation(item, null); // mutationsregel finden
            if (mutation == null) {
                return "Sorry, there is no way to decompose " + item + ".";
            }

            if (!inventory.remove(item)) { // item entfernen
                adventure.remove(item, x, y);
            }

            return executeMutation(mutation); // decomposition ausführen
        } catch (TextAdventureException e) {
            return "Sorry, you failed to find a way to decompose " + item + ".";
        }
    }

    /**
     * Methode fügt die Ergebnisse einer mutation zum inventar oder feld hinzu.
     * @param mutation
     * @return
     */
    private String executeMutation(TextAdventureMutation mutation) {
        for (String output : mutation.getOutputs()) { // schleife über Ergebnisse der Mutation
            if (adventure.isScenery(output)) { // wenn scenery zum Feld hinzufügen
                try {
                    adventure.placeItem(output, x, y);
                } catch (TextAdventureException e) {
                    e.printStackTrace();
                }
            } else { // andernfalls zum Inventar hinzufügen
                inventory.add(output);
            }
        }
        return mutation.getDescription(); // beschreibung der mutation zurückgeben
    }
}