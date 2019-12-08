/**The Item class handles the properties of the specific item, as well as their position on the current board it is on
 * @author Tyler Viacara, Chris Papp
 */
public class Item{
	//instance variables
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;
	private int itemBoard;
	private int itemRow;
	private int itemColumn;
	private int healthPoints;

	/**Constructor that takes in the items name, weight, value, strength, the type of item, and their location on a specific board
	 * @param name The name of the item as a String
	 * @param weight The weight of the item as an integer
	 * @param value The value of the item as an integer
	 * @param strength The strength of the item as an integer
	 * @param itemRow The row the item is in as an integer
	 * @param itemColumn The column the item is in as an integer
	 * @param type The type of item as defined in ItemType as an ENUM
	 */
	Item (String name, int weight, int value, int strength, int itemRow, int itemColumn, ItemType type){
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.itemRow = itemRow;
		this.itemColumn = itemColumn;
	}
	/**Constructor that takes in the items name, weight, value, strength, and the type of item
         * @param name The name of the item as a String
         * @param weight The weight of the item as an integer
         * @param value The value of the item as an integer
         * @param strength The strength of the item as an integeR
	 * @param type The type of item as defined in ItemType as an ENUM
	 */
	Item(String name, int weight, int value, int strength, ItemType type){
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.type = type;
	}
	/**Constructor that takes in the location of the item and the board they're on
	 *@param itemRow The row the item is in as an integer
	 *@param itemColumn The column the item is in as an integer
	 *@param itemBoard The board the item is on as an integer
	 */
	Item(int itemBoard, int itemRow, int itemColumn){
		this.itemBoard = itemBoard;
		this.itemRow = itemRow;
		this.itemColumn = itemColumn;
	}
	/**Method that sets the health points of an item
	 * @param healthPoints The health points the item is being assigned as an integer
	 */
	public void setHealthPoints(int healthPoints){
		this.healthPoints = healthPoints;
	}
	/**Method that returns the item health points as an integer
	 * @return The health points associated with the item as an integer
	 */
	public int getHealthPoints(){
		return this.healthPoints;
	}
	/**Method that sets the type of the item
	 * @param type The type of the item as defined in ItemType as an ENUM
	 */
	public void setItemType(ItemType type){
		this.type = type;
	}
	/**Method that sets the name of the item based on what is passed in
	 * @param name The name of the item that is being assigned
	 */
	public void setName(String name){
		this.name = name;
	}
	/**Method that sets the weight of the item
	 * @param weight Sets the weight of the item based on the integer that is passed in
	 */
	public void setWeight(int weight){
		this.weight = weight;
	}
	/**Method that sets the value of the item
	 * @param value Sets the value of the item based on the integer that is passed in
	 */
	public void setValue(int value){
		this.value = value;
	}
	/**Method that sets the strength of the item
	 * @param strength Sets the strength of the item based on the integer that is passed in
	 */
	public void setStrength(int strength){
		this.strength = strength;
	}
	/**Method that returns the weight of the item as an integer
	 * @return The weight of the item as an integer
	 */
	public int getWeight(){
		return this.weight;
	}
	/**Method that returns the value of the item as an integer
	 * @return The value of the item as an integer
	 */
	public int getValue(){
		return this.value;
	}
	/**Method that returns the name of the item as a String
	 * @return The name of the item as a string
	 */
	public String getName(){
		return this.name;
	}
	/**Method that returns the type of the item as defined in ItemType as an ENUM
	 * @return The type of the item as an ENUM defined in ItemType
	 */
	public ItemType getType(){
		return this.type;
	}
	/**Method that returns the type of the item as a String
	 * @return The type of the item as a String
	 */
	public String getTypeString(){
		if (type == ItemType.Weapon){
			return "Weapon";
		}
		else if (type == ItemType.Armor){
			return "Armor";
		}
		else if (type == ItemType.Shovel){
			return "Shovel";
		}
		else if (type == ItemType.Potion){
			return "Potion";
		}
		else{
			return "Other";
		}
	}
	/**Method that returns the strength of the item
	 * @return The strength of the item as an integer
	 */
	public int getStrength(){
		return this.strength;
	}
	/**Prints out all of the items statistics in a readable format for the user
	 * @return Prints out all of the items properties in a readable format for the user
	 */
	public String toString(){
		return ("The " + this.getName() + " is a " + this.getType() + ", it has a weight of: " + this.getWeight() + ". a value of: " + this.getValue() + ", and a strength of: " + this.getStrength() + ".");
	}
	/**Method that sets the board the item is on based on the number that is passed in
	 * @param itemBoard The board the item is on as an integer. Either 0, 1, or 2
	 */
	public void setItemBoard(int itemBoard){
		this.itemBoard = itemBoard;
	}
	/**Method that retuns the board the item is on
	 * @return The number of the board the item is on as an integer
	 */
	public int getItemBoard(){
		return this.itemBoard;
	}
	/**Method that sets the item's row based on the number passed in
	 * @param itemRow Sets the item's row based on the integer passed in
	 */
	public void setItemRow(int itemRow){
		this.itemRow = itemRow;
	}
	/**Method that returns the row the item is in
	 * @return The row the item is in as an integer
	 */
	public int getItemRow(){
		return this.itemRow;
	}
	/**Method that returns the column the item is in
	 * @return The column the item is in as an integer
	 */
	public int getItemColumn(){
		return this.itemColumn;
	}
	/**Method that sets the column the item is in based on the number that is passed in
	 * @param itemColumn The column location of the item based on the integer passed in
	 */
	public void setItemColum(int itemColumn){
		this.itemColumn = itemColumn;
	}
}
