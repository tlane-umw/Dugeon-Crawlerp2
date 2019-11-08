import java.util.Scanner;
import java.util.Hashtable;
class Player extends Creature{
	//instance variables
	Scanner s = new Scanner(System.in);
	String answer;
	private String name;
	private Inventory userInventory = new Inventory (250);
	private int value;
	private int health;
	private int count;

	//default constructor
	public Player(String name, int health){
		super(name, health);
	}
	public Inventory getInventory(){
		return userInventory;
	}

	//method asking the user if they want to pick up the item they stepped on
	public boolean itemQuestion(Item item){
		boolean response;
		System.out.println("You have found the " + item.toString() + ", would you like to pick it up?");
		System.out.println("Enter 'Y' for yes and 'N' for no.");
		answer = s.nextLine();
		while(!(answer.equals("N") || answer.equals("n"))){
			if(answer.equals("Y") || answer.equals("y")){
				userInventory.add(item);
				response = true;
				return response;
			} else { 
				System.out.println("Not a valid input.");
				System.out.println("You have found an item, would you like to pick it up?");
				System.out.println("Enter 'Y' for yes and 'N' for no.");
				answer = s.nextLine();

			}

		}
		response = false;
		return response;
	}
	//generating new items on the board for the player
	public Hashtable<Integer, Item> itemSpawn(int size){
		count = 1;
		Hashtable<Integer, Item> groundItems = new Hashtable<Integer, Item>();
		for(int i = 1; i < size + 1; i++) {
			groundItems.put(count, ItemGenerator.generate());
			count++;
		}
		return groundItems;
	}
}
