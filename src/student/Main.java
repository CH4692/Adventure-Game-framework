package student;

import ias.Adventures;
import ias.TextAdventureException;
import ias.GameStarter;
import ias.TextAdventure;

/**
 * Main class, serves as an entry point to the program.
 */
public class Main {

    /**
     * Main method which serves as an entry to the program.
     * @param args terminal parameters
     */
    public static void main(String[] args) {
        try {
            // Add additional games to the Array
            TextAdventure[] games = {
                    Adventures.getFiremanGame(), Adventures.getLumberjackGame(), Adventures.getPokemonGame()
            };
            GameStarter starter = new GameStarter(games);
            starter.startGame();
        } catch (TextAdventureException e) {
            e.printStackTrace();
        }

    }
}
