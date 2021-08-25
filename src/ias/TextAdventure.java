package ias;

/**
 * Models the interface of a text adventure game-instance.
 */
public interface TextAdventure {

	/**
	 * Declares a new portable object-type with given id and description.
	 * @param id The id object-type, used in the ui to search for objects
	 * @param description The description of the object-type
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void addItemType(String id, String description) throws TextAdventureException;
	
	/**
	 * Declares a new non-portable object-type with given id and description.
	 * @param id The id object-type, used in the ui to search for objects
	 * @param description The description of the object-type
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void addSceneryType(String id, String description) throws TextAdventureException;

	/**
	 * Adds a new object of the given type to the field at the specified position.
	 * @param type The id of an object-type
	 * @param x A field coordinate on the board
	 * @param y A field coordinate on the board
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void placeItem(String type, int x, int y) throws TextAdventureException;

	/**
	 * Adds a new composition-rule to the set of mutation-rules.
	 * @param in1 Object-type id of the input-object
	 * @param in2 Object-type id of the input-object
	 * @param out Object-type id of the output-object
	 * @param description Mutation-description for the ui
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void addComposition(String in1, String in2, String out, String description) throws TextAdventureException;
	
	/**
	 * Adds a new decomposition-rule to the set of mutation-rules.
	 * @param in Object-type id of the input-object
	 * @param out1 Object-type id of the output-object
	 * @param out2 Object-type id of the output-object
	 * @param description Mutation-description for the ui
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void addDecomposition(String in, String out1, String out2, String description) throws TextAdventureException;
	
	/**
	 * Adds a new transformation-rule to the set of mutation-rules.
	 * @param in1 in1 Object-type id of the input-object
	 * @param in2 in1 Object-type id of the input-object
	 * @param out1 Object-type id of the output-object
	 * @param out2 Object-type id of the output-object
	 * @param description Mutation-description for the ui
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	void addTransformation(String in1, String in2, String out1, String out2, String description)
			throws TextAdventureException;

	/**
	 * Initializes a new game instance and returns a player-instance for game-control. 
	 * The player's initial position is at the given coordinates
	 * @param x A field coordinate on the board
	 * @param y A field coordinate on the board
	 * @return A player-instance for game-control
	 * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
	 */
	Player startGame(int x, int y) throws TextAdventureException;

	/**
	 * Returns the name of the game instance.
	 * @return the name of the game instance
	 */
	String getName();
}
