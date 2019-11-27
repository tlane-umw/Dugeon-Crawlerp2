import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.io.*;
import java.util.InputMismatchException;

class SaveRestore{
	private static boolean doesFileExist = false;
	Dungeon dungeon;
	Random random = new Random();
	File saveFile = new File("saveFile.txt");

	SaveRestore(Dungeon dungeon){
		this.dungeon = dungeon;
	}

	//saving the game
	public void save(Dungeon dungeon)throws FileNotFoundException{
		try{
			//saving the current player information
			PrintWriter output = new PrintWriter(saveFile);
			output.println(dungeon.getPlayer().getName());
			output.println(dungeon.getPlayer().getHealth());
			output.println(dungeon.getPlayer().getRow());
			output.println(dungeon.getPlayer().getColumn());
			output.println(dungeon.getPlayer().getCurrentPlayerBoard());
			output.println(dungeon.getPlayer().getNumEnemiesDefeated());

			//saving the players current equipped weapon
			output.println(dungeon.getPlayer().getInventory().getEquippedWeapon().getName());
			output.println(dungeon.getPlayer().getInventory().getEquippedWeapon().getWeight());
			output.println(dungeon.getPlayer().getInventory().getEquippedWeapon().getValue());
			output.println(dungeon.getPlayer().getInventory().getEquippedWeapon().getStrength());
			output.println("Weapon");

			//saving the players current equipped armor
			output.println(dungeon.getPlayer().getInventory().getEquippedArmor().getName());
			output.println(dungeon.getPlayer().getInventory().getEquippedArmor().getWeight());
			output.println(dungeon.getPlayer().getInventory().getEquippedArmor().getValue());
			output.println(dungeon.getPlayer().getInventory().getEquippedArmor().getStrength());
			output.println("Armor");

			//saving the players current inventory
			for (int z = 0; z < dungeon.getPlayer().getInventory().getSize(); z++){
				output.println(dungeon.getPlayer().getInventory().getItem(z).getName());
				output.println(dungeon.getPlayer().getInventory().getItem(z).getWeight());
				output.println(dungeon.getPlayer().getInventory().getItem(z).getValue());
				output.println(dungeon.getPlayer().getInventory().getItem(z).getStrength());
				if(dungeon.getPlayer().getInventory().getItem(z).getTypeString().equals("Weapon")){
					output.println("Weapon");
				}
				else if(dungeon.getPlayer().getInventory().getItem(z).getTypeString().equals("Armor")){
					output.println("Armor");
				}
				else{
					output.println("Other");
				}

			}
			output.println(".");

			//saving the current enemies
			for (int yyy = 0; yyy < dungeon.getEnemyList().size(); yyy++){
				int currentEnemyBoard = dungeon.getEnemyList().get(yyy).getBoard();
				//making sure the enemies still correlate with an alive enemy or E on the board
				if (dungeon.getWorld().getCurrentBoard(currentEnemyBoard)[dungeon.getEnemyList().get(yyy).getRow()][dungeon.getEnemyList().get(yyy).getColumn()] == 'E'){
					output.println(dungeon.getEnemyList().get(yyy).getPlaceName());
					output.println(dungeon.getEnemyList().get(yyy).getRow());
					output.println(dungeon.getEnemyList().get(yyy).getColumn());
					output.println(dungeon.getEnemyList().get(yyy).getBoard());
				}

			}
			output.println(".");

			//saving the current items left on the board
			for (int xx = 0; xx < dungeon.getItemsList().size(); xx++){
				output.println(dungeon.getItemsList().get(xx).getName());
				output.println(dungeon.getItemsList().get(xx).getWeight());
				output.println(dungeon.getItemsList().get(xx).getValue());
				output.println(dungeon.getItemsList().get(xx).getStrength());
				output.println(dungeon.getItemsList().get(xx).getTypeString());
				output.println(dungeon.getItemsList().get(xx).getItemRow());
				output.println(dungeon.getItemsList().get(xx).getItemColumn());
				output.println(dungeon.getItemsList().get(xx).getItemBoard());
			}

			output.println(".");

			output.close();

		}
		catch (IOException ioe){
			System.out.println("Error");
		}
	}


	//restoring the game
	public Dungeon restore()throws InputMismatchException {
		try{

			//restoring player information
			Scanner input = new Scanner(saveFile);
			dungeon.dungeonPlayer.setName(input.nextLine());
			dungeon.dungeonPlayer.setHealth(input.nextInt());
			dungeon.dungeonPlayer.setRow(input.nextInt());
			dungeon.dungeonPlayer.setColumn(input.nextInt());
			dungeon.dungeonPlayer.setCurrentPlayerBoard(input.nextInt());
			dungeon.getPlayer().setNewNumEnemiesDefeated(input.nextInt());

			//removing any possible items in player inventory
			for (int y = 0; y < dungeon.getPlayer().getInventory().getSize(); y++){
				dungeon.getPlayer().getInventory().remove(0);
			}

			//restoring players equipped weapon
			String trash = input.nextLine();
			String restoreEWName = input.nextLine();
			int restoreEWWeight = input.nextInt();
			int restoreEWValue = input.nextInt();
			int restoreEWStrength = input.nextInt();
			String restoreEWType = input.nextLine();
			//String trash2 = input.nextLine();
			Item restoreEquippedWeapon = new Item(restoreEWName, restoreEWWeight, restoreEWValue, restoreEWStrength, ItemType.Weapon);
			dungeon.getPlayer().getInventory().restoreEquippedWeapon(restoreEquippedWeapon);
			dungeon.dungeonPlayer.getInventory().add(restoreEquippedWeapon);

			//restoring the players equipped armor
			String newTrash = input.nextLine();
			String restoreEAName = input.nextLine();
			int restoreEAWeight = input.nextInt();
			int restoreEAValue = input.nextInt();
			int restoreEAStrength = input.nextInt();
			String restoreEAType = input.nextLine();
			//String trash3 = input.nextLine();
			Item restoreEquippedArmor = new Item(restoreEAName, restoreEAWeight, restoreEAValue, restoreEAStrength, ItemType.Armor);
			dungeon.getPlayer().getInventory().restoreEquippedArmor(restoreEquippedArmor);
			dungeon.getPlayer().getInventory().add(restoreEquippedArmor);

			//restoring the rest of the items in the inventory
			boolean inItems = true;
			boolean inEnemies = true;
			boolean addedEW = false;
			boolean addedEA = false;
			ArrayList<Item> newInventoryItems = new ArrayList<Item>();
			while (inItems == true){
				try{
					String itemName = input.nextLine();
					if (itemName.equals(".")){
						inItems = false;
						break;
					}
					int weight = input.nextInt();
					int value = input.nextInt();
					int strength = input.nextInt();
					String trashTylersays = input.nextLine();
					String itemTypeName = input.nextLine();
					Item newItem;

					//making sure you dont add the equipped weapon to your inventory twice
					if ((itemName.equals(restoreEWName)) && (addedEW == false)){
						addedEW = true;
					}

					//making sure you dont add the equipped armor to your inventory twice
					else if ((itemName.equals(restoreEAName)) && (addedEA == false)){
						addedEA = true;
					}

					//adding the rest of your items to your inventory
					else{
						if (itemTypeName.equals("Weapon")){
							newItem = new Item(itemName, weight, value, strength, ItemType.Weapon);
						}
						else if (itemTypeName.equals("Armor")){
							newItem = new Item(itemName, weight, value, strength, ItemType.Armor);
						}
						else{
							newItem = new Item(itemName, weight, value, strength, ItemType.Other);
						}
						newInventoryItems.add(newItem);
					}
				}
				catch (InputMismatchException i){

				}
			}

			//adding the equipped weapon and 
			newInventoryItems.add(restoreEquippedWeapon);
			newInventoryItems.add(restoreEquippedArmor);
			dungeon.getPlayer().getInventory().setItems(newInventoryItems);

			//removing any possible enemies in the enemy list
			for (int abc = 0; abc < dungeon.getEnemyList().size(); abc++){
				dungeon.getEnemyList().remove(0);
			}

			//restoring the enemies and adding them to the new list
			ArrayList<Enemy> newEnemyList = new ArrayList<Enemy>();
			while (inEnemies == true){
				try{
					String enemyPlaceName = input.nextLine();
					if (enemyPlaceName.equals(".")){
						inEnemies = false;
						break;
					}
					int row = input.nextInt();
					int column = input.nextInt();
					int enemyBoard = input.nextInt();
					Enemy newEnemy = new Enemy(enemyPlaceName, row, column, enemyBoard);
					newEnemyList.add(newEnemy);
				}


				catch(InputMismatchException e){

				}

			}
			//setting new enemy list based on what was loaded from file
			dungeon.setEnemyList(newEnemyList);

			//removing any possible items from our item list
			for (int cba = 0; cba < dungeon.itemsList.size(); cba++){
				dungeon.itemsList.remove(0);
			}
			ArrayList<Item> newItemsList = new ArrayList<Item>();
			boolean inItemsList = true;
			Item newItem;
			//adding the saved items back to our item list
			while(inItemsList == true){
				try{
					String itemName = input.nextLine();
					if (itemName.equals(".")){
						inItemsList = false;
						break;
					}
					int itemWeight = input.nextInt();
					int itemValue = input.nextInt();
					int itemStrength = input.nextInt();
					String tylertrash = input.nextLine();
					String itemType = input.nextLine();
					int itemRow = input.nextInt();
					int itemColumn = input.nextInt();
					int itemBoard = input.nextInt();
					if (itemType.equals("Weapon")){
						newItem = new Item(itemName, itemWeight, itemValue, itemStrength, itemRow, itemColumn, ItemType.Weapon);
					}
					else if (itemType.equals("Armor")){
						newItem = new Item(itemName, itemWeight, itemValue, itemStrength, itemRow, itemColumn, ItemType.Armor);
					}
					else{
						newItem = new Item(itemName, itemWeight, itemValue, itemStrength, itemRow, itemColumn, ItemType.Other);
					}
					newItem.setItemBoard(itemBoard);
					newItemsList.add(newItem);
				}

				catch(InputMismatchException noMoreItems){

				}
			}
			//setting the new items list
			dungeon.setItemsList(newItemsList);

			//blanking out the boarda if theyre not a wall or a door
			for (int a = 0; a < 3; a++){
				char[][] blankBoard = dungeon.getWorld().getCurrentBoard(a);
				for (int i = 0; i < 20; i++){
					for (int j = 0; j < 20; j++){
						if ((!(blankBoard[i][j] == '-')) && ((!(blankBoard[i][j] == '|')))){
							if (!(blankBoard[i][j] == 'D')){
								blankBoard[i][j] = ' ';
							}
						}
					}
				}
				dungeon.getWorld().setNewBoard(a, blankBoard);
			}


			//putting E's on the board based on the restored enemy list
			for (int h = 0; h < dungeon.getEnemyList().size(); h++){
				int restoreEnemyBoardNum = dungeon.getEnemyList().get(h).getBoard();
				char [][] restoreEnemyBoard = dungeon.getWorld().getCurrentBoard(restoreEnemyBoardNum);
				restoreEnemyBoard[dungeon.getEnemyList().get(h).getRow()][dungeon.getEnemyList().get(h).getColumn()] = 'E';
				dungeon.getWorld().setNewBoard(restoreEnemyBoardNum, restoreEnemyBoard);
			}
			//adding I's to the board from the new items list
			for (int hh = 0; hh < dungeon.getItemsList().size(); hh++){
				int restoreItemBoardNum = dungeon.getItemsList().get(hh).getItemBoard();
				char[][] restoreItemBoard = dungeon.getWorld().getCurrentBoard(restoreItemBoardNum);
				restoreItemBoard[dungeon.getItemsList().get(hh).getItemRow()][dungeon.getItemsList().get(hh).getItemColumn()] = 'I';
				dungeon.getWorld().setNewBoard(restoreItemBoardNum, restoreItemBoard);
			}

			input.close();
			doesFileExist = true;

		}
		catch (FileNotFoundException f){
			System.out.println("Error.... there was no previous save file found.");
			System.out.println("You must save your game before you can restore it!");
			doesFileExist = false;
			try{
				Thread.sleep(3000);
			}
			catch (InterruptedException noSave){
				System.out.println("Interrupted!");
			}

		}
		return this.dungeon;
	}
	public  boolean doesSaveFileExist(){
		return this.doesFileExist;
	}
}
