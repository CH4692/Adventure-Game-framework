package student;

/**
 * Factory class with functions to generate a game and terminal.
 */
public final class Factory {
    
    private Factory() { }
        
    /**
     * Creates a new game-instance with an empty board of the given size.
     * @param name The name of the game
     * @param boardWidth the width of the board
     * @param boardHeight the height of the board
     * @return a fresh game instance
     * @throws ias.TextAdventureException Think about the cases in which an exception is useful and implement it.
     */
    public static ias.TextAdventure getGame(String name, int boardWidth, int boardHeight)
            throws ias.TextAdventureException {
        return new TextAdventure(name, boardWidth, boardHeight);
    }

    /**
     * Creates a new terminal-instance to read user input and prompt messages.
     * @return a terminal instance
     */ 
    public static ias.Terminal getTerminal() {
        return new TextAdventureTerminal();
    }
}
