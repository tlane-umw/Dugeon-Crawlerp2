//import statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.io.*;
import java.util.InputMismatchException;

/**
 * the Dungeon class handles the world, which contains all 3 gameboards, the current amount of enemies you have defeated, and where all the enemies and items are on the board in respective ArrayLists. the Dungeon also automatically moves the enemies every other time the user moves
 * @author Chris Papp Tyler Viacara
 */
class Dungeon{
	private static int numEnemiesDefeated = 0;
	private static int currentBoard = 1;
	private World world;
	private boolean onItem = false;
	private boolean itemExistence = false;
	ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	Random random = new Random();
	char playerSymbol;
	Player dungeonPlayer;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Item> itemsList = new ArrayList<Item>();
	private boolean skip = true;

	/**
	 * constructor that takes in a new player and their symbol. It then creates a new world object containg all three gameboards, sets the enemies in its ArrayList, and then randomly places 4 items on each board.
	 * @param dungeonPlayer A new player object containg the players health, inventory, current equipped weapon and armor, and their position on the board.
	 * @param playerSymbol The player symbol for the player.
	 */
	Dungeon(Player dungeonPlayer, char playerSymbol){
		this.dungeonPlayer = dungeonPlayer;
		this.playerSymbol = playerSymbol;
		world = new World();

		//adding creating two enemeies for each board
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

		//adding 4 items to all 3 boards
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

	/**
	 * method that moves the enemies every other turn. if skip is false, then the enemies move and skip is assigned true. if skip is true, nothing happens besides skip being assigned false.
	 */
	public void moveEnemies(){
		if (skip == false){
			for (int z = 0; z < enemyList.size(); z++){
				int currentEnemyBoard = enemyList.get(z).getBoard();
				char[][] oldBoard = world.getCurrentBoard(currentEnemyBoard);
				char [][] newBoard = enemyList.get(z).moveEnemy(oldBoard);
				world.setNewBoard(currentEnemyBoard, newBoard);
			}
			skip = true;
		}
		else if (skip == true){
			skip = false;
		}
	}



	/**
	 * method that prints the current gameboard depending on what board the user is on. if a space on the board is empty, a '*' is placed there just for printing purposes.
	 */
	public void printBoard(){
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
	}

	/**
	 * this method is called when the player picks up an item they have found. not only does the onItem get assigned false, but so does itemExistence. this tells the program that the user is no longer on an item because it is in their inventory. and it lets the Dungeon know the item no longer exists because the user has picked it up.
	 */
	public void makeFalse(){
		onItem = false;
		itemExistence = false;
	}
	/**
	 * method that returns the current player object in the dungeon.
	 * @return The current player object that is in the Dungeon.
	 */
	public Player getDungeonPlayer(){
		return this.dungeonPlayer;
	}
	/**
	 * method that returns the current board number the player is on.
	 * @return the current board the player is on as an integer, either 0, 1, or 2.
	 */
	public int getCurrentBoardNum(){
		return this.currentBoard;
	}
	/**
	 * method that sets the new board number the player is on.
	 * @param newBoard the new board number the player is on as an integer, either 0, 1, or 3.
	 */
	public void setCurrentBoardNum(int newBoard){
		this.currentBoard = newBoard;
	}
	/**
	 * method that returns the current player object in the dungeon.
         * @return the current player object that is in the Dungeon.
	 */
	public Player getPlayer(){
		return this.dungeonPlayer;
	}
	/**
	 * method that sets the new items arrayList. Is only called after restoring a previously saved game.
	 * @param newItemsList the new items list that is returned from the save restore file.
	 */
	public void setItemsList(ArrayList<Item> newItemsList){
		this.itemsList = newItemsList;
	}
	/**
	 * method that returns the current Items List the Dungeon has. 
	 * @return an ArrayList of the current Items that are still in the Dungeon that the user hasn't picked up.
	 */
	public ArrayList<Item> getItemsList(){
		return this.itemsList;
	}
	/**
	 * method that returns the current World with all three gameboards and the postion of the player, enemies, and items in all three rooms.
	 * @return the latest world object the Dungeon has with the players, enemies, and items locations in all three rooms.
	 */
	public World getWorld(){
		return this.world;
	}
	/**
	 * method that sets the new World object. is only called from the saverestore file based on the saved world.
	 * @param newWorld the new World object called from the Save Restore file with the players, enemies, and all the items location.
	 */
	public void setWorld(World newWorld){
		this.world = newWorld;
	}
	/**
	 * method that sets the new Enemy ArrayList from the SaveRestore file.
	 * @param enemyList sets the new Enemy ArrayList in the Dungeon from the SaveRestore file.
	 */
	public void setEnemyList(ArrayList<Enemy> enemyList){
		this.enemyList = enemyList;
	}
	/**
	 * method that returns the current Enemy ArrayList the Dungeon has.
	 * @return the current ArrayList the Dungeon has with all the enemies locations.
	 */
	public ArrayList<Enemy> getEnemyList(){
		return this.enemyList;
	}
}
