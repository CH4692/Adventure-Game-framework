package ias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.Factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest extends AbstractTest {

    private static TextAdventure textAdventure;

    @BeforeEach
    public void createGame() throws TextAdventureException {
        textAdventure = Factory.getGame("Test", 5, 5);
    }

    @Test
    public void invalidMove() throws TextAdventureException {
        Player player = textAdventure.startGame(0, 0);
        assertTrue(player.go("N").startsWith("Sorry,"));
    }

    @Test
    public void goNorthTest() throws TextAdventureException {
        Player player = textAdventure.startGame(2, 2);
        assertMoveSuccess(player.go("N"));
    }

    @Test
    public void goNorthEastTest() throws TextAdventureException {
        Player player = textAdventure.startGame(0, 2);
        assertMoveSuccess(player.go("NE"));
    }

    @Test
    public void goEastTest() throws TextAdventureException {
        Player player = textAdventure.startGame(2, 2);
        assertMoveSuccess(player.go("E"));
    }

    @Test
    public void goSouthEastTest() throws TextAdventureException {
        Player player = textAdventure.startGame(4, 1);
        assertTrue(player.go("SE").startsWith("Sorry,"));
    }

    @Test
    public void goSouthTest() throws TextAdventureException {
        Player player = textAdventure.startGame(0, 0);
        assertMoveSuccess(player.go("S"));
    }

    @Test
    public void goSouthWestTest() throws TextAdventureException {
        Player player = textAdventure.startGame(4, 1);
        assertMoveSuccess(player.go("SW"));
    }

    @Test
    public void moveInCircle() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertMoveSuccess(p.go("E"));
        assertMoveSuccess(p.go("S"));
        assertMoveSuccess(p.go("W"));
        assertMoveSuccess(p.go("N"));
    }

    @Test
    public void moveInBiggerCircle() throws TextAdventureException {
        Player p = textAdventure.startGame(2, 0);
        assertMoveSuccess(p.go("W"));
        assertMoveSuccess(p.go("SW"));
        assertMoveSuccess(p.go("S"));
        assertMoveSuccess(p.go("SE"));
        assertMoveSuccess(p.go("E"));
        assertMoveSuccess(p.go("NE"));
        assertMoveSuccess(p.go("N"));
        assertMoveSuccess(p.go("NW"));
    }

    @Test
    public void nullGo() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.go(null));
    }

    @Test
    public void takeMessageTest() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);

        Player p = textAdventure.startGame(0, 0);
        assertStartsWith("You pick", p.take("Torch"));
    }

    @Test
    public void takeTest() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);

        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        assertContains("Torch - Shining bright as hell.", p.inventory());
    }

    @Test
    public void takeNotAvailable() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        Player p = textAdventure.startGame(0, 0);
        assertError(p.take("Torch"));
    }

    @Test
    public void dropMessageTest() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);

        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        assertStartsWith("You drop", p.drop("Torch"));
    }

    @Test
    public void dropTest() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);

        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        assertContains("Torch - Shining bright as hell.", p.inventory());
        p.drop("Torch");
        assertContainsNot("Torch - Shining bright as hell.", p.inventory());
    }

    @Test
    public void dropNotAvailable() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addDecomposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.drop("Torch"));
    }

    @Test
    public void sameItemCompositionInventoryInventoryToSceneryOutput() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        p.take("Torch");
        p.convert("Torch", "Torch");
        assertContains("Sun - The lovely sun.", p.look());
    }

    @Test
    public void sameItemCompositionInventoryInventoryToSceneryInput() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        p.take("Torch");
        p.convert("Torch", "Torch");
        assertContainsNot("Torch", p.look());
    }

    @Test
    public void sameItemCompositionFieldFieldToSceneryOutput() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.convert("Torch", "Torch");
        assertContains("Sun - The lovely sun.", p.look());
    }

    @Test
    public void sameItemCompositionFieldFieldToSceneryInput() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.convert("Torch", "Torch");
        assertContainsNot("Torch", p.look());
    }
}
