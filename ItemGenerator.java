import java.lang.*;
import java.util.*;

/**This class generates a new item at random and returns that item
 * @author Tyler Viacara
 */
public class ItemGenerator{

	//static random generator shared between the class
	private static Random randomInt = new Random();
	/**Method that creates a new item at random and returns the newly created random item
	 * @return The newly created random item
	 */
	public static Item generate(){


		//creating the possible weapon item types
		Item sword = new Item("Sword", 20, 10, 20, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		Item fineSword = new Item("Fine Sword", 30, 20, 30, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		Item fineSteelSword = new Item("Fine Steel Sword", 40, 55, 40, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		ArrayList<Item> weaponsList = new ArrayList<Item>(Arrays.asList(sword, fineSword, fineSteelSword));

		//creating the possible armor item types
		Item shield = new Item("Shield", 20, 10, 20,  (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		Item fineSteelShield = new Item("Fine Steel Shield", 30, 20, 30, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		Item bodyArmor = new Item("Body Armor", 40, 55, 40, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		ArrayList<Item> armorList = new ArrayList<Item>(Arrays.asList(shield, fineSteelShield, bodyArmor));

		//creating the potiential potions for the user
		Item potion = new Item("Potion", 10, 10, 10, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Potion);
		potion.setHealthPoints(20);
		Item superPotion = new Item("Super Potion", 15, 15, 15, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Potion);
		superPotion.setHealthPoints(30);
		Item hyperPotion = new Item("Hyper Potion", 20, 20, 20, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Potion);
		hyperPotion.setHealthPoints(40);
		ArrayList<Item> potionList = new ArrayList<Item>(Arrays.asList(potion, superPotion, hyperPotion));

		//creating the possible other item types
		Item key = new Item("Key", 10, 5, 0,   (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		Item vase = new Item("Vase", 7, 3, 0, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		Item pokeball = new Item("PokeBall", 2, 20, 0, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		ArrayList<Item> otherList = new ArrayList<Item>(Arrays.asList(key, vase, pokeball));

		//randomly deciding what item the user will get
		int listPicker = randomInt.nextInt(4);
		if (listPicker == 0){
			int weaponPicker = randomInt.nextInt(3);
			if (weaponPicker == 0){
				return weaponsList.get(0);
			}
			else if (weaponPicker == 1) {
				return weaponsList.get(1);
			}
			else{
				return weaponsList.get(2);
			}
		}
		else if (listPicker == 1){
			int armorPicker = randomInt.nextInt(3);
			if (armorPicker == 0){
				return armorList.get(0);
			}
			else if (armorPicker == 1) {
				return armorList.get(1);
			}
			else{
				return armorList.get(2);
			}
		}
		else if (listPicker == 2){
			int potionPicker = randomInt.nextInt(3);
			if (potionPicker == 0){
				return potionList.get(0);
			}
			else if (potionPicker == 1){
				return potionList.get(1);
			}
			else{
				return potionList.get(2);
			}
		}
		else{
			int otherPicker = randomInt.nextInt(3);
			if (otherPicker == 0){
				return otherList.get(0);
			}
			else if (otherPicker == 1){
				return otherList.get(1);
			}
			else{
				return otherList.get(2);
			}
		}

	}
}


