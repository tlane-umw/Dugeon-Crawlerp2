//Setting up the Item class, properties each item will have

public class Item{
	//instance variables
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;

	//default constructor
	Item (String name, int weight, int value, int strength, ItemType type){
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
	}
	Item(String name, int weight, int value, int strength){
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
	}
	public void setItemType(ItemType type){
		this.type = type;
	}

	//standard get methods
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
		else{
			return "Other";
		}
	}
	public int getStrength(){
		return this.strength;
	}
	//stand toString method to print Object in a readable manner
	public String toString(){
		return ("This item is a " + this.getName() + " with a weight of " + this.getWeight() + " and a value = " +  this.getValue() + " with a of strength " + this.getStrength() +" and is a type  " + this.getType() +  ".");
	}
}

