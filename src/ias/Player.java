package ias;

/**
 * Models the interface of a text adventure player.
 */
public interface Player {

	/**
	 * Move the player's token into direction.
	 * @param direction One of eight valid directions "N", "NE", "E", "SE", "S", "SW", "W", "NW"
	 * @return A ui-message for the user
	 */
	String go(String direction);

	/**
	 * Returns an array of objects on the current field.
	 * @return An array of object-type and descriptions for the ui
	 */
	String[] look();
	
	/**
	 * Returns an array of objects in the inventory.
	 * @return An array of object-type and descriptions for the ui
	 */
	String[] inventory();

	/**
	 * Removes an object of the given type from the current field and adds it to the inventory.
	 * @param item An object-type
	 * @return A ui-message for the user
	 */
	String take(String item);
	
	/**
	 * Removes an object of the given type from the inventory and adds it to the current field.
	 * @param item An object-type
	 * @return A ui-message for the user
	 */
	String drop(String item);

	/**
	 * Applies a transformation- or composition-rule to item1 and item2. 
	 * If possible, removes item1 and item2 from the current field or inventory 
	 * and adds the mutation-result to the current field or inventory,
	 * depending on the object's base-type (scenery or item). 
	 * @param item1 An Object-Type
	 * @param item2 An Object-Type
	 * @return A ui-message for the user
	 */
	String convert(String item1, String item2);
	
	/**
	 * Applies a decomposition-rule to item. 
	 * If possible, removes item from the current field or inventory 
	 * and adds the mutation-result to the current field or inventory,
	 * depending on the object's base-type (scenery or item). 
	 * @param item An Object-Type
	 * @return A ui-message for the user
	 */
	String decompose(String item);
}
