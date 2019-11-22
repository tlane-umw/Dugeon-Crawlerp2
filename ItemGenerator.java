import java.lang.*;
import java.util.*;

//class to generate a random Item
public class ItemGenerator{

	//static random generator shared between the class
	private static Random randomInt = new Random();
	public static Item generate(){


		//creating the possible weapon item types
		Item sword = new Item("Sword", 50, 25, 75, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		Item fineSword = new Item("Fine Sword", 60, 35, 85, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		Item fineSteelSword = new Item("Fine Steel Sword", 80, 55, 105, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Weapon);
		ArrayList<Item> weaponsList = new ArrayList<Item>(Arrays.asList(sword, fineSword, fineSteelSword));

		//creating the possible armor item types
		Item shield = new Item("Shield", 40, 30, 60,  (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		Item fineSteelShield = new Item("Fine Steel Shield", 65, 55, 85, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		Item bodyArmor = new Item("Body Armor", 85, 75, 105, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Armor);
		ArrayList<Item> armorList = new ArrayList<Item>(Arrays.asList(shield, fineSteelShield, bodyArmor));

		//creating the possible other item types
		Item key = new Item("Key", 10, 5, 0,   (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		Item vase = new Item("Vase", 7, 3, 0, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		Item pokeball = new Item("PokeBall", 2, 20, 0, (randomInt.nextInt(15) + 1), (randomInt.nextInt(17) + 1), ItemType.Other);
		ArrayList<Item> otherList = new ArrayList<Item>(Arrays.asList(key, vase, pokeball));

		//randomly deciding what item the user will get
		int listPicker = randomInt.nextInt(3);
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


