package ias;

/**
 * Models the interface of terminal to interact with a player.
 */
public interface Terminal {

    /**
     * Prompts the user to enter a string as input. The string is printed to the standard output (terminal).
     *
     * @param input the message to display the player
     */
    public void promptInput(String input);

    /**
     * Reads the user input and returns splitted at spaces as string array.
     * @return splitted user input string
     */
    public String[] readInput();

}
