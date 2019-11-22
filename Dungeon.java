//import statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.io.*;
import java.util.InputMismatchException;

//instance variables
class Dungeon{
	private static int numEnemiesDefeated = 0;
	private static int currentBoard = 1;
	private World world;
	private boolean alreadyExecuted = false;
	private static boolean doesFileExist = false;
	private boolean onItem = false;
	private boolean itemExistence = false;
	private static int doesEnemyMove = 2;
	ArrayList<Integer> value = new ArrayList<Integer>();
	ArrayList<Integer> value2 = new ArrayList<Integer>();
	ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	ArrayList<Player> player = new ArrayList<Player>();
	Random random = new Random();
	char playerSymbol;
	Integer[] placement = new Integer[]{0, 0, 0};
	Player dungeonPlayer;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Item> itemsList = new ArrayList<Item>();
	private boolean skip = true;
	File saveFile = new File("saveFile.txt");




	//constructor with a 2D char array and a new player 
	Dungeon(Player dungeonPlayer, char playerSymbol){
		this.dungeonPlayer = dungeonPlayer;
		this.playerSymbol = playerSymbol;
		world = new World();
		Enemy enemy0 = new Enemy("enemy 0", 15, 2, 0);
		Enemy enemy1 = new Enemy("enemy 1", 15, 17, 0);
		Enemy enemy2 = new Enemy("enemy 2", 15, 2, 1);
		Enemy enemy3 = new Enemy("enemy 3", 15, 17, 1);
		Enemy enemy4 = new Enemy("enemy 4", 15, 2, 2);
		Enemy enemy5 = new Enemy("enemy 5", 15, 17, 2);
		enemyList.add(enemy0);
		enemyList.add(enemy1);
		enemyList.add(enemy2);
		enemyList.add(enemy3);
		enemyList.add(enemy4);
		enemyList.add(enemy5);
		Item newItem;
		for (int zz = 0; zz < 3; zz++){
			for (int yy = 0; yy < 4; yy++){
				boolean validItemLocation = false;
				while(validItemLocation == false){
					newItem = ItemGenerator.generate();
					int itemRow = newItem.getItemRow();
					int itemColumn = newItem.getItemColumn();
					char[][] currentItemBoard = world.getCurrentBoard(zz);
					if (currentItemBoard[itemRow][itemColumn] == ' '){
						currentItemBoard[itemRow][itemColumn] = 'I';
						newItem.setItemBoard(zz);
						itemsList.add(newItem);
						world.setNewBoard(zz, currentItemBoard);
						validItemLocation = true;
					}
					else{
						validItemLocation = false;
					}
				}
			}
		}
	}
	public ArrayList<Item> getItemsList(){
		return this.itemsList;
	}
	public void setItemsList(ArrayList<Item> newItemsList){
		this.itemsList = newItemsList;
	}

	public void moveEnemies(){
		//System.out.println("The size of the enemy list is " + enemyList.size());
		if (skip == false){

			for (int z = 0; z < enemyList.size(); z++){
				int currentEnemyBoard = enemyList.get(z).getEnemyBoardNum();
				char[][] oldBoard = world.getCurrentBoard(currentEnemyBoard);
				char [][] newBoard = enemyList.get(z).moveEnemy(oldBoard);
				world.setNewBoard(currentEnemyBoard, newBoard);
				//System.out.println("Enemy " + z + " new row = " + enemyList.get(z).getRow() + ", enemy new column = " + enemyList.get(z).getColumn());
			}
			skip = true;
		}
		else if (skip == true){
			skip = false;
		}
	}



	//printing the gameboard showing the players location, enemies location, and items locations
	public void  printBoard(){

		//System.out.println("About to print the board!");



		//printing a star in the blank spaces
		world.getCurrentBoard(currentBoard)[dungeonPlayer.getRow()][dungeonPlayer.getColumn()] = playerSymbol;
		for (int i = 0; i < 20; i++){
			for (int j = 0; j < 20; j++){
				if (world.getCurrentBoard(currentBoard)[i][j] == ' '){
					System.out.print('*');
				} else {
					System.out.print(world.getCurrentBoard(currentBoard)[i][j]);
				}
			}
			System.out.println();
		}

		//System.out.println("Done printing board!");
	}

	//making the item dissapear after you pick it up, if you dont the item will reappear and a new item will be created
	public void makeFalse(){
		onItem = false;
		itemExistence = false;
	}
	public Player getDungeonPlayer(){
		return this.dungeonPlayer;
	}

	public int getCurrentBoardNum(){
		return this.currentBoard;

	}

	public void setCurrentBoardNum(int newBoard){
		this.currentBoard = newBoard;
	}

	public World getWorld(){
		return this.world;
	}
	public Player getPlayer(){
		return this.dungeonPlayer;
	}
	//saving the game
	public void save()throws FileNotFoundException{
		try{
			//saving the current player information
			PrintWriter output = new PrintWriter(saveFile);
			output.println(dungeonPlayer.getName());
			output.println(dungeonPlayer.getHealth());
			output.println(dungeonPlayer.getRow());
			output.println(dungeonPlayer.getColumn());
			output.println(dungeonPlayer.getCurrentPlayerBoard());
			output.println(dungeonPlayer.getNumEnemiesDefeated());

			//saving the players current equipped weapon
			output.println(dungeonPlayer.getInventory().getEquippedWeapon().getName());
			output.println(dungeonPlayer.getInventory().getEquippedWeapon().getWeight());
			output.println(dungeonPlayer.getInventory().getEquippedWeapon().getValue());
			output.println(dungeonPlayer.getInventory().getEquippedWeapon().getStrength());
			output.println("Weapon");

			//saving the players current equipped armor
			output.println(dungeonPlayer.getInventory().getEquippedArmor().getName());
			output.println(dungeonPlayer.getInventory().getEquippedArmor().getWeight());
			output.println(dungeonPlayer.getInventory().getEquippedArmor().getValue());
			output.println(dungeonPlayer.getInventory().getEquippedArmor().getStrength());
			output.println("Armor");



			//saving the players current inventory
			for (int z = 0; z < dungeonPlayer.getInventory().getSize(); z++){
				output.println(dungeonPlayer.getInventory().getItem(z).getName());
				output.println(dungeonPlayer.getInventory().getItem(z).getWeight());
				output.println(dungeonPlayer.getInventory().getItem(z).getValue());
				output.println(dungeonPlayer.getInventory().getItem(z).getStrength());
				if(dungeonPlayer.getInventory().getItem(z).getTypeString().equals("Weapon")){
					output.println("Weapon");
				}
				else if(dungeonPlayer.getInventory().getItem(z).getTypeString().equals("Armor")){
					output.println("Armor");
				}
				else{
					output.println("Other");
				}

			}
			output.println(".");

			//saving the current enemies
			for (int y = 0; y < enemyList.size(); y++){
				int currentEnemyBoard = enemyList.get(y).getEnemyBoardNum();
				//making sure the enemies still correlate with an alive enemy or E on the board
				if (world.getCurrentBoard(currentEnemyBoard)[enemyList.get(y).getRow()][enemyList.get(y).getColumn()] == 'E'){
					output.println(enemyList.get(y).getPlaceName());
					output.println(enemyList.get(y).getRow());
					output.println(enemyList.get(y).getColumn());
					output.println(enemyList.get(y).getEnemyBoardNum());
				}

			}
			output.println(".");
			
			//saving the current items left on the board
			for (int xx = 0; xx < itemsList.size(); xx++){
				output.println(itemsList.get(xx).getName());
				output.println(itemsList.get(xx).getWeight());
				output.println(itemsList.get(xx).getValue());
				output.println(itemsList.get(xx).getStrength());
				output.println(itemsList.get(xx).getTypeString());
				output.println(itemsList.get(xx).getItemRow());
				output.println(itemsList.get(xx).getItemColumn());
				output.println(itemsList.get(xx).getItemBoard());
			}

	


			output.println(".");

			output.close();

		}
		catch (IOException ioe){
			System.out.println("Error");
		}
	}
	//overriding the default enemy list
	public void setEnemyList(ArrayList<Enemy> enemyList){
		this.enemyList = enemyList;
	}

	//restoring the game
	public void restore()throws InputMismatchException {
		try{
			//restoring player information
			Scanner input = new Scanner(saveFile);
			this.dungeonPlayer.setName(input.nextLine());
			this.dungeonPlayer.setHealth(input.nextInt());
			this.dungeonPlayer.setRow(input.nextInt());
			this.dungeonPlayer.setColumn(input.nextInt());
			this.dungeonPlayer.setCurrentPlayerBoard(input.nextInt());
			dungeonPlayer.setNewNumEnemiesDefeated(input.nextInt());

			//removing any possible items in player inventory
			for (int y = 0; y < dungeonPlayer.getInventory().getSize(); y++){
				dungeonPlayer.getInventory().remove(0);
			}

			//restoring players equipped weapon
			String thisTrash = input.nextLine();
			String restoreEWName = input.nextLine();
			int restoreEWWeight = input.nextInt();
			int restoreEWValue = input.nextInt();
			int restoreEWStrength = input.nextInt();
			String restoreEWType = input.nextLine();
			//String trash2 = input.nextLine();
			Item restoreEquippedWeapon = new Item(restoreEWName, restoreEWWeight, restoreEWValue, restoreEWStrength, ItemType.Weapon);
			dungeonPlayer.getInventory().restoreEquippedWeapon(restoreEquippedWeapon);
			this.dungeonPlayer.getInventory().add(restoreEquippedWeapon);
			System.out.println("Success! You restored your last equipped weapon!");

			//restoring the players equipped armor
			String newTrash = input.nextLine();
			String restoreEAName = input.nextLine();
			int restoreEAWeight = input.nextInt();
			int restoreEAValue = input.nextInt();
			int restoreEAStrength = input.nextInt();
			String restoreEAType = input.nextLine();
			//String trash3 = input.nextLine();
			Item restoreEquippedArmor = new Item(restoreEAName, restoreEAWeight, restoreEAValue, restoreEAStrength, ItemType.Armor);
			dungeonPlayer.getInventory().restoreEquippedArmor(restoreEquippedArmor);
			this.dungeonPlayer.getInventory().add(restoreEquippedArmor);
			System.out.println("Success! You restored your last equipped armor!");

			//restoring the rest of the items in the inventory
			boolean inItems = true;
			boolean inEnemies = true;
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
					String trash = input.nextLine();
					String itemTypeName = input.nextLine();
					Item newItem;
					if ((itemName.equals(restoreEWName)) || (itemName.equals(restoreEAName))){

					}
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
					System.out.println("Input Mismatch Exception");
				}
			}
			newInventoryItems.add(restoreEquippedWeapon);
			newInventoryItems.add(restoreEquippedArmor);
			dungeonPlayer.getInventory().setItems(newInventoryItems);

			//removing any possible enemies in the enemy list
			for (int abc = 0; abc < this.enemyList.size(); abc++){
				this.enemyList.remove(0);
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
					System.out.println("Input Mismatch Exception");
				}

			}
			setEnemyList(newEnemyList);

			//removing any possible items on the board
			for (int cba = 0; cba < this.itemsList.size(); cba++){
				this.itemsList.remove(0);
			}
			ArrayList<Item> newItemsList = new ArrayList<Item>();
			boolean inItemsList = true;
			Item newItem;
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
			setItemsList(newItemsList);

			//blanking out the boarda if theyre not a wall or a door
			for (int a = 0; a < 3; a++){
				char[][] blankBoard = world.getCurrentBoard(a);
				for (int i = 0; i < 20; i++){
					for (int j = 0; j < 20; j++){
						if ((!(blankBoard[i][j] == '-')) && ((!(blankBoard[i][j] == '|')))){
							if (!(blankBoard[i][j] == 'D')){
								blankBoard[i][j] = ' ';
							}
						}
					}
				}
				this.world.setNewBoard(a, blankBoard);
			}


			//putting E's on the board based on the restored enemy list
			for (int h = 0; h < this.enemyList.size(); h++){
				int restoreEnemyBoardNum = enemyList.get(h).getEnemyBoardNum();
				char [][] restoreEnemyBoard = world.getCurrentBoard(restoreEnemyBoardNum);
				restoreEnemyBoard[enemyList.get(h).getRow()][enemyList.get(h).getColumn()] = 'E';
				this.world.setNewBoard(restoreEnemyBoardNum, restoreEnemyBoard);
			}
			for (int hh = 0; hh < this.itemsList.size(); hh++){
				int restoreItemBoardNum = itemsList.get(hh).getItemBoard();
				char[][] restoreItemBoard = world.getCurrentBoard(restoreItemBoardNum);
				restoreItemBoard[itemsList.get(hh).getItemRow()][itemsList.get(hh).getItemColumn()] = 'I';
				this.world.setNewBoard(restoreItemBoardNum, restoreItemBoard);
			}
			for (int tylersays = 0; tylersays < newItemsList.size(); tylersays++){
				System.out.println(newItemsList.get(tylersays).toString());
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
	}
	public  boolean doesSaveFileExist(){
		return this.doesFileExist;
	}
}



