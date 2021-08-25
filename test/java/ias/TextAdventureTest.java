package ias;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import student.Factory;

public class TextAdventureTest extends AbstractTest {

    private static TextAdventure textAdventure;

    @BeforeEach
    public void createGame() throws TextAdventureException {
        textAdventure = Factory.getGame("Test", 5, 5);
    }

    @Test
    public void constructorTest() throws TextAdventureException {
        TextAdventure textAdventure = Factory.getGame("Test", 42, 42);
        assertNotNull(textAdventure);
    }

    @Test
    public void startTest() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertNotNull(p);
    }

    @Test
    public void widthNotNegativeOrNull() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            Factory.getGame("Test", 0, 5);
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void heightNotNegativeOrNull() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            Factory.getGame("Test", 5, 0);
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void addPlaceableItems() throws TextAdventureException {
        textAdventure.addItemType("a", "description");
    }

    @Test
    public void invalidIdItem() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            textAdventure.addItemType("a_", "description");
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void placeAndTakeItemTest() throws TextAdventureException {
        textAdventure.addItemType("mouse", "A mouse");
        textAdventure.placeItem("mouse", 0, 0);

        Player player = textAdventure.startGame(0, 0);
        player.take("mouse");

        String[] expected = new String[]{"mouse - A mouse"};
        String[] result = player.inventory();
        assertArrayEquals(expected, result);
    }

    @Test
    public void addSceneryItems() throws TextAdventureException {
        textAdventure.addSceneryType("a", "description");
    }

    @Test
    public void placeItemTest() throws TextAdventureException {
        textAdventure.addItemType("mouse", "A mouse");
        textAdventure.placeItem("mouse", 0, 0);

        String[] expected = new String[]{"mouse - A mouse"};
        String[] result = textAdventure.startGame(0, 0).look();
        assertArrayEquals(expected, result);
    }

    @Test
    public void placeSceneryTest() throws TextAdventureException {
        textAdventure.addSceneryType("house", "A house");
        textAdventure.placeItem("house", 0, 0);

        String[] expected = new String[]{"house - A house"};
        String[] result = textAdventure.startGame(0, 0).look();
        assertArrayEquals(expected, result);
    }

    @Test
    public void placeSceneryItemAndTakeTest() throws TextAdventureException {
        textAdventure.addItemType("mouse", "A mouse");
        textAdventure.placeItem("mouse", 0, 0);
        Player player = textAdventure.startGame(0, 0);
        player.take("mouse");


        String[] expected = new String[]{"mouse - A mouse"};
        String[] result = player.inventory();
        assertArrayEquals(expected, result);
    }

    @Test
    public void duplicateItemsThrowException() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            textAdventure.addItemType("id", "bla");
            textAdventure.addSceneryType("id", "bla");
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void addComposition() throws TextAdventureException {
        textAdventure.addItemType("a", "");
        textAdventure.addSceneryType("b", "");
        textAdventure.addItemType("out", "");

        textAdventure.addComposition("a", "b", "out", "muh");
    }

    @Test
    public void addCompositionWithNonExistingItems() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            textAdventure.addComposition("a", "b", "out", "muh");
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void addDecomposition() throws TextAdventureException {
        textAdventure.addItemType("a", "");
        textAdventure.addSceneryType("outOne", "");
        textAdventure.addItemType("outTwo", "");

        textAdventure.addDecomposition("a", "outOne", "outTwo", "muh");
    }

    @Test
    public void addTransformation() throws TextAdventureException {
        textAdventure.addItemType("a", "");
        textAdventure.addSceneryType("b", "");
        textAdventure.addItemType("outOne", "");
        textAdventure.addItemType("outTwo", "");

        textAdventure.addTransformation("a", "b", "outOne", "outTwo", "muh");
    }

    @Test
    public void nullTransformation() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
            TextAdventure textAdventure = Factory.getGame("Test", 42, 42);

            textAdventure.addSceneryType("BurningTree", "A blazing fire on an oak tree.");
            textAdventure.addSceneryType("HalfBurningTree", "A little fire on an oak tree.");
            textAdventure.addItemType("Bucket", "An empty bucket.");
            textAdventure.addItemType("Water", "A bucket full of water.");
            textAdventure.addTransformation("BurningTree", "Water", "HalfBurningTree", "Bucket",
                    null);

            Player p = textAdventure.startGame(0, 0);
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
}
