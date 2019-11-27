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
	private boolean onItem = false;
	private boolean itemExistence = false;
	ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	Random random = new Random();
	char playerSymbol;
	Player dungeonPlayer;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Item> itemsList = new ArrayList<Item>();
	private boolean skip = true;

	//constructor with a 2D char array and a new player 
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

	//moving all remaining enemies every other turn
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



	//printing the gameboard showing the players location, enemies location, and items locations depending on what board the player is on
	public void  printBoard(){
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

	//making the item dissapear after you pick it up, if you dont the item will reappear and a new item will be created
	public void makeFalse(){
		onItem = false;
		itemExistence = false;
	}
	//standard getters and setter
	public Player getDungeonPlayer(){
		return this.dungeonPlayer;
	}

	public int getCurrentBoardNum(){
		return this.currentBoard;
	}
	public void setCurrentBoardNum(int newBoard){
		this.currentBoard = newBoard;
	}
	public Player getPlayer(){
		return this.dungeonPlayer;
	}
	public void setItemsList(ArrayList<Item> newItemsList){
		this.itemsList = newItemsList;
	}
	public ArrayList<Item> getItemsList(){
		return this.itemsList;
	}
	public World getWorld(){
		return this.world;
	}
	public void setWorld(World newWorld){
		this.world = newWorld;
	}
	public void setEnemyList(ArrayList<Enemy> enemyList){
		this.enemyList = enemyList;
	}
	public ArrayList<Enemy> getEnemyList(){
		return this.enemyList;
	}

}
