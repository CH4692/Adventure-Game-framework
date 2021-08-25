package student;

import ias.TextAdventureException;

/**
 * Klasse für mutationsregeln
 */
public class TextAdventureMutation {

    private String[] inputs = new String[2]; // Array mit ausgangsstoffen
    private String[] outputs = new String[2]; // Array mit ergebnissen
    private String description; // Beschreibung der Mutation

    /**
     * Setter für die Beschreibung.
     * @param description
     */
    public TextAdventureMutation(String description) { // konstruktor
        this.description = description;
    }

    /**
     * Getter für die Beschreibung.
     * @return beschreibung
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter für inputs.
     * @return inputs
     */
    public String[] getInputs() {
        return inputs;
    }

    /**
     * Getter für outputs.
     * @return outputs
     */
    public String[] getOutputs() {
        return outputs;
    }

    /**
     * Methode zur überprüfung, ob die Mutationsregel auf zwei Items anwendbar ist.
     * @param input1
     * @param input2
     * @return true, wenn die Mutationsregel anwenbar ist.
     * @throws TextAdventureException
     */
    public boolean matches(String input1, String input2) throws TextAdventureException {
        if (input1 == null && input2 == null) { // wenn kein Item gegeben ist
            throw new TextAdventureException("Both inputs cant be empty");
        }

        if (input1 == null) { // Wenn ein wert nicht gegeben ist immer auf wert 2
            input1 = input2;
            input2 = null;
        }
        if (input1.equals(inputs[0])) { // wenn das erste item mit dem ersten input übereinstimmt
            if (input2 == null) {
                return inputs[1] == null;
            }
            return input2.equals(inputs[1]);
            // wenn das zweite item mit dem ersten input übereinstimmt
        } else if (input2 == null || input2.equals(inputs[0])) {
            if (input2 == null && inputs[0] == null) {
                return false;
            }
            return input1.equals(inputs[1]);
        }

        return false; // keine übereinstimmung
    }
}