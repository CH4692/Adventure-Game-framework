package student;

import ias.Terminal;
import java.util.Scanner;

/**
 * Klasse für den Terminal Input und Output des Spielers.
 */
public class TextAdventureTerminal implements Terminal {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void promptInput(String input) {
        System.out.print(input); // Textausgabe
    }

    @Override
    public String[] readInput() {
        return scanner.nextLine().split(" "); // Lesen mithilfe eines Scanners
    }
    
}