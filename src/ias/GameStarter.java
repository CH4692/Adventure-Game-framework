package ias;

import student.Factory;

/**
 * Class integrates the implementation of the student package and allows to play the text adventure scenarios.
 */
public class GameStarter {

    private static final String PROMPT = "play>";
    private static Player player;
    private Terminal terminal;
    private TextAdventure[] games;

    /**
     * Constructor to create a GameStarter instance.
     * @param games array with text adventures games to play
     */
    public GameStarter(TextAdventure[] games) {
        terminal = Factory.getTerminal();
        this.games = games;
    }

    /**
     * Starts one of the given games.
     */
    public void startGame() {
        String[] input;
        String prompt = "Play (";
        for (TextAdventure game : this.games) {
            prompt += game.getName();
            prompt += "|";
        }
        prompt = prompt.substring(0, prompt.length() - 1) + ")";
        do {
            terminal.promptInput(prompt);
            input = terminal.readInput();
        } while (input[0].equals("") || input[0].equals(" "));


        String gameName = input[0];
        for (TextAdventure game : this.games) {
            if (game.getName().equals(gameName)) {
                startGame(game, 0, 0);
                return;
            }
        }
        System.out.println("Unknown game scenario");
        System.exit(1);


    }

    /**
     * Starts a given game and runs a simple input-loop that accepts game-commands.
     * @param textAdventure to play
     * @param x coordinate of the player
     * @param y coordinate of the player
     */
    public void startGame(TextAdventure textAdventure, int x, int y) {
        try {
            player = textAdventure.startGame(x, y);
        } catch (TextAdventureException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean run = true;
        while (run) {
            try {
                terminal.promptInput(PROMPT);
                String[] input = terminal.readInput();
                run = processInput(input);
            } catch (TextAdventureException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Process the given command-line, translate it into player-actions if possible.
     * @param input the splitted user input
     * @return false if the user wants to quit, true otherwise
     * @throws TextAdventureException If an invalid command is entered
     */
    private static boolean processInput(String[] input) throws TextAdventureException {
        switch (input[0]) {
            case "go": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify a direction.");
                } else {
                    System.out.println(player.go(input[1]));
                }
                return true;
            }
            case "look": {
                String[] list = player.look();
                for (String line : list) {
                    System.out.println(line);
                }
                return true;
            }
            case "inventory": {
                String[] list = player.inventory();
                for (String line : list) {
                    System.out.println(line);
                }
                return true;
            }
            case "take": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.take(input[1]));
                }
                return true;
            }
            case "drop": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.drop(input[1]));
                }
                return true;
            }
            case "convert": {
                if (input.length < 3) {
                    System.out.println("Sorry, please specify the objects to compose.");
                } else {
                    System.out.println(player.convert(input[1], input[2]));
                }
                return true;
            }
            case "decompose": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.decompose(input[1]));
                }
                return true;
            }
            case "help": {
                System.out.println("Valid Commands are: go, look, inventory, take, drop, compose, decompose");
                return true;
            }
            case "exit": {
                System.out.println("Bye!");
                return false;
            }
            default: {
                throw new TextAdventureException("Unknown Command: " + input[0]);
            }
        }
    }
}
