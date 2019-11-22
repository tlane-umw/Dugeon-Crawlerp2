import java.util.*;

//inventory class that holds the users items information, their max weight, and the equipped armor/weapon they have
class Inventory{
	Scanner input = new Scanner(System.in);

	//instance variable
	private ArrayList<Item> items = new ArrayList<Item>();
	private int maxWeight = 250;
	private Item equippedWeapon;
	private Item equippedArmor;
	private int totalWeight;

	//default constructor
	Inventory (int maxWeight){
		this.maxWeight = 250;
		Item starterSword = new Item("Starter Sword", 5, 5, 5, ItemType.Weapon);
		items.add(starterSword);

		Item starterShield = new Item("Starter Shield", 5, 5, 5, ItemType.Armor);
		items.add(starterShield);

		Item dragonball = new Item("Dragonball", 0, 100, 0, ItemType.Other);
		items.add(dragonball);	

		equippedWeapon = starterSword;
		equippedArmor = starterShield;


	}
	public void setItems(ArrayList<Item> newItems){
		this.items = newItems;
	}
	//add method to see if the newly generated Item object can be added to the users inventory
	//checks to make sure the newly created item will not push the users weight past their max limit
	//if true the Item is added to the users inventory and the total weight will reflect the newly added item
	//if false the newly created iten will not be added and the total weight will stay the same
	public boolean add (Item item){
		int potentialNewWeight = this.totalWeight() + item.getWeight();
		if (potentialNewWeight  >= maxWeight){

			System.out.println("This was not possible. This item would cause you to go over the max. Weight would be : " + potentialNewWeight);
			try{
				Thread.sleep(3000);
			}
			catch(InterruptedException noAdd){
				System.out.println("Interrupted!");
			}
			potentialNewWeight = 0;
			return false;
		}
		else{
			items.add(item);
			System.out.println("Your new weight in your inventory is: " + this.totalWeight());
			System.out.println("The max weight for your inventory is: " + this.getMaxWeight());
			try{
				Thread.sleep(3000);
			}
			catch (InterruptedException yesAdd){
				System.out.println("Interrupted!");
			}
			return true;
		}
	}
	public void remove(int i){
		items.remove(i);
	}
	public int getMaxWeight(){
		return this.maxWeight;
	}
	public int getSize(){
		return items.size();
	}
	public Item getItem(int z){
		return items.get(z);
	}
	//method to return the weapon the user currently has equipped
	public Item getEquippedWeapon(){
		return this.equippedWeapon;
	}

	//method to return the armor the user currently has equipped
	public Item getEquippedArmor(){
		return this.equippedArmor;
	}


	//goes through each item and adds each weight to a total and returns that integer
	public int totalWeight(){
		int combinedWeight = 0;
		for (int i = 0; i < items.size(); i++){
			combinedWeight += items.get(i).getWeight();
		}
		return combinedWeight;
	}
	//prints the items the user currently has
	public void print(){
		System.out.println();
		if (items.size() == 0){
			System.out.println("You currently have 0 items.");
			System.out.println("You must add an item before you can print the items.");
		}
		else{
			System.out.println("You currently have " + items.size() + " items.");//mostly for me, couldn't get the add method to work properly
			System.out.println("You currently have the following items: ");
			for (int j = 0; j < items.size(); j++){
				System.out.println(items.get(j).getName() + " " + items.get(j).getWeight() + " " + items.get(j).getValue() + " " + items.get(j).getValue());
			}
			System.out.println();
		}
	}
	//lists all the users items and removes which ever item they choose
	public void drop(){
		int numToDrop = -1;
		boolean validNum = false;
		while (validNum == false){
			if (items.size() == 0){
				System.out.println("No items in your inventory to drop.");
				try{
					Thread.sleep(3000);
				}
				catch(InterruptedException noDrop){
					System.out.println("Interrupted!");
				}
				validNum = true;
				break;
			}
			else{
				for (int k = 0; k < items.size(); k++){
					System.out.println("Item #" + (k+1) + " = " + items.get(k).getName());
				}
				System.out.println("Please enter the number of the item you'd like to drop or a 0 to cancel: ");
				System.out.println();
				try{
					numToDrop = input.nextInt();
				}
				catch(InputMismatchException e){
					String trash = input.nextLine();
					validNum = false;
				}
				if ((numToDrop > 0) && (numToDrop <= items.size())) {
					System.out.println("Success! You have dropped " + items.get(numToDrop - 1).getName() + " from your inventory.");
					items.remove(numToDrop - 1);
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException yesAdd){
						System.out.println("Interrupted!");

					}
					validNum = true;
					break;
				}

				else if (numToDrop == 0){
					System.out.println("Okay. Nothing will be dropped from your inventory!");
					System.out.println();
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException nothingDropped){
						System.out.println("Interrupted!");
					}
					validNum = false;
					break;
				}
				else {
					System.out.println("Please enter a number you see on the screen or 0 to cancel: ");
					validNum = false;
				}
			}
		}
	}
	//equips the weapon the user chooses
	public void equipWeapon(){

		int userNum = -1;
		boolean validNum = false;

		ArrayList<Item> currentWeapons = new ArrayList<Item>();
		for (int t = 0; t < items.size(); t++){
			if (items.get(t).getType() == ItemType.Weapon){
				currentWeapons.add(items.get(t));
			}
		}
		//making sure there is an item they can equip
		if (currentWeapons.size() == 0){
			System.out.println("No current weapons that you can equip.");
			try{
				Thread.sleep(2000);
			}
			catch (InterruptedException noWeapon){
				System.out.println("Interrupted!");
			}
			validNum = true;
		}

		else{
			while(validNum == false){


				for (int a = 0; a < currentWeapons.size(); a++){
					System.out.println((a+1) + " " + currentWeapons.get(a).getName() + " " + currentWeapons.get(a).getWeight() + " " + currentWeapons.get(a).getValue() + " " + currentWeapons.get(a).getStrength());
				}

				System.out.println((currentWeapons.size() + 1) + " Cancel");
				System.out.println("Please enter the number of the weapon: ");
				try {
					userNum = input.nextInt();

				}
				catch(InputMismatchException e){
					String trash = input.nextLine();
					validNum = false;
				}

				if (userNum == currentWeapons.size() + 1){
					System.out.println("No weapon will be equipped.");
					validNum = true;
					break;
				}
				else if ((userNum > 0) && (userNum <= (currentWeapons.size()))){
					System.out.println("Success! You have equipped " + currentWeapons.get(userNum-1).getName() + " as your weapon.");
					this.equippedWeapon = currentWeapons.get(userNum - 1);
					try{
						Thread.sleep(3000);
					}
					catch (InterruptedException yesWeapon){
						System.out.println("Interrupted");
					}
					validNum = true;
					break;
				}
				//making sure they pick a number on the screen
				else{
					System.out.println("Please enter a number you see on the screen.");
					validNum = false;
				}
			}
		}


	}
	//methods that set the restored equipped weapon & armor
	public void restoreEquippedWeapon(Item restoreEquippedWeapon){
		this.equippedWeapon = restoreEquippedWeapon;
	}
	public void restoreEquippedArmor(Item restoreEquippedArmor){
		this.equippedArmor = restoreEquippedArmor;
	}

	//equips the armor the user chooses
	public void equipArmor(){

		int userNum = -1;
		boolean validNum = false;

		ArrayList<Item> currentArmors = new ArrayList<Item>();
		for (int t = 0; t < items.size(); t++){
			if (items.get(t).getType() == ItemType.Armor){
				currentArmors.add(items.get(t));
			}
		}
		//making sure there is a armor the player can equip
		if (currentArmors.size() == 0){
			System.out.println("No current armors that you can equip.");
			try{
				Thread.sleep(3000);
			}
			catch (InterruptedException noArmor){
				System.out.println("Interrupted");
			}
			validNum = true;
		}

		else{
			while(validNum == false){

				//printing out all their armors
				for (int a = 0; a < currentArmors.size(); a++){
					System.out.println((a+1) + " " + currentArmors.get(a).getName() + " " + currentArmors.get(a).getWeight() + " " + currentArmors.get(a).getValue() + " " + currentArmors.get(a).getStrength());
				}

				System.out.println((currentArmors.size() + 1) + " Cancel");
				System.out.println("Please enter the number of the armor: ");
				try {
					userNum = input.nextInt();

				}
				catch(InputMismatchException e){
					String trash = input.nextLine();
					validNum = false;
				}

				if (userNum == currentArmors.size() + 1){
					System.out.println("No armor will be equipped.");
					validNum = true;
					break;
				}
				else if ((userNum > 0) && (userNum <= (currentArmors.size()))){
					System.out.println("Success! You have equipped " + currentArmors.get(userNum-1).getName() + " as your armor.");
					this.equippedArmor = currentArmors.get(userNum - 1);
					try{
						Thread.sleep(3000);
					}
					catch (InterruptedException yesArmor){
						System.out.println("Interrupted");
					}
					validNum = true;
					break;
				}
				else{
					System.out.println("Please enter a number you see on the screen.");
					validNum = false;
				}
			}
		}


	}

}

