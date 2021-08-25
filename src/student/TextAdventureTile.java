package student;

import java.util.List;
import java.util.ArrayList;

/**
 * Klasse für ein Feld auf dem Spielfeld.
 */
public class TextAdventureTile {

    private List<String> items = new ArrayList<>(); // items auf dem Feld
    private List<String> scenery = new ArrayList<>(); // scenery objekte auf dem Feld

    /**
     * Getter für Items.
     * @return items
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Getter für Scenery.
     * @return scenery
     */
    public List<String> getScenery() {
        return scenery;
    }

}