//Setting up the Item class, properties each item will have

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

	//default constructor
	Item (String name, int weight, int value, int strength, int itemRow, int itemColumn, ItemType type){
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.itemRow = itemRow;
		this.itemColumn = itemColumn;
	}
	Item(String name, int weight, int value, int strength, ItemType type){
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.type = type;
	}
	Item(int itemBoard, int itemRow, int itemColumn){
		this.itemBoard = itemBoard;
		this.itemRow = itemRow;
		this.itemColumn = itemColumn;
	}
	//standard get &et methods along with to string
	public void setHealthPoints(int healthPoints){
		this.healthPoints = healthPoints;
	}
	public int getHealthPoints(){
		return this.healthPoints;
	}
	public void setItemType(ItemType type){
		this.type = type;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public void setValue(int value){
		this.value = value;
	}
	public void setStrength(int strength){
		this.strength = strength;
	}
	public int getWeight(){
		return this.weight;
	}
	public int getValue(){
		return this.value;
	}
	public String getName(){
		return this.name;
	}
	public ItemType getType(){
		return this.type;
	}
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
	public int getStrength(){
		return this.strength;
	}
	public String toString(){
		return ("The " + this.getName() + " is a " + this.getType() + ", it has a weight of: " + this.getWeight() + ". a value of: " + this.getValue() + ", and a strength of: " + this.getStrength() + ".");
	}
	public void setItemBoard(int itemBoard){
		this.itemBoard = itemBoard;
	}
	public int getItemBoard(){
		return this.itemBoard;
	}
	public void setItemRow(int itemRow){
		this.itemRow = itemRow;
	}
	public int getItemRow(){
		return this.itemRow;
	}
	public int getItemColumn(){
		return this.itemColumn;
	}

	public void setItemColum(int itemColumn){
		this.itemColumn = itemColumn;
	}

}
