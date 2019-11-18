//import statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.io.*;

//instance variables
class Dungeon{
	private static int numEnemiesDefeated = 0;
	int playerColumn;
	int playerRow;
	int enemyColumn;
	int enemyRow;
	int enemyColumn2;
	int enemyRow2;
	private static int currentBoard = 1;
	private World world;
	private boolean alreadyExecuted = false;
	private boolean alreadyExecuted2 = false;
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
	Hashtable<Integer, Integer[]> location = new Hashtable<Integer, Integer[]>();
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
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
	}

	public void moveEnemies(){
		System.out.println("The size of the enemy list is " + enemyList.size());
		if (skip == false){

			for (int z = 0; z < enemyList.size(); z++){
				int currentEnemyBoard = enemyList.get(z).getEnemyBoardNum();
				char[][] oldBoard = world.getCurrentBoard(currentEnemyBoard);
				char [][] newBoard = enemyList.get(z).moveEnemy(oldBoard);
				world.setNewBoard(currentEnemyBoard, newBoard);
				System.out.println("Enemy " + z + " new row = " + enemyList.get(z).getRow() + ", enemy new column = " + enemyList.get(z).getColumn());
			}
			skip = true;
		}
		else if (skip == true){
			skip = false;
		}
	}



	//printing the gameboard showing the players location, enemies location, and items locations
	public Hashtable<Integer, Integer[]>  printBoard(){

		//System.out.println("About to print the board!");
		int number = random.nextInt(4) + 3;
		int count = 1;
		if(!alreadyExecuted) {
			for(int k = 0; k < 3; k++){
				for(int i = 0; i < number; i++){
					value.add(random.nextInt(17) + 2);				
					value2.add(random.nextInt(17) + 2);
					if(world.getCurrentBoard(k)[value.get(i+(number*k))][value2.get(i+(number*k))] == ' '){
						world.getCurrentBoard(k)[value.get(i+(number*k))][value2.get(i+(number*k))] = 'I';
						placement = new Integer[]{k, value.get(i+(number*k)), value2.get(i+(number*k))};
						location.put(count, placement);
						count++;
					}
				}
			}	
			alreadyExecuted = true;
			return location;

		}
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
		return location;
		//System.out.println("Done printing board!");
	}

	//keeping track of where the user is
	public Integer[] playerLocation(){
		Integer[] position = new Integer[]{playerRow, playerColumn};
		return position;
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
	public void save()throws FileNotFoundException{
		try{
			PrintWriter output = new PrintWriter(saveFile);
			output.println(dungeonPlayer.getName());
			output.println(dungeonPlayer.getHealth());
			output.println(dungeonPlayer.getRow());
			output.println(dungeonPlayer.getColumn());
			output.println(dungeonPlayer.getCurrentPlayerBoard());
			for (int z = 0; z < dungeonPlayer.getInventory().getSize(); z++){
				output.println(dungeonPlayer.getInventory().getItem(z).getName());
				output.println(dungeonPlayer.getInventory().getItem(z).getWeight());
				output.println(dungeonPlayer.getInventory().getItem(z).getValue());
				output.println(dungeonPlayer.getInventory().getItem(z).getStrength());
				output.println("Items Beginning");
				if((dungeonPlayer.getInventory().getItem(z).getType()) == ItemType.Weapon){
					output.println("Weapon");
				}
				else if((dungeonPlayer.getInventory().getItem(z).getType()) == ItemType.Armor){
					output.println("Armor");
				}
				else{
					output.println("Other");
				}

			}
			output.println(".");

			output.close();
		}
		catch (IOException ioe){
			System.out.println("Error");
		}
	}
	public void restore(){
		try{
			Scanner input = new Scanner(saveFile);
			this.dungeonPlayer.setName(input.nextLine());
			this.dungeonPlayer.setHealth(input.nextInt());
			this.dungeonPlayer.setRow(input.nextInt());
			this.dungeonPlayer.setColumn(input.nextInt());
			this.dungeonPlayer.setCurrentPlayerBoard(input.nextInt());

			for (int y = 0; y < dungeonPlayer.getInventory().getSize(); y++){
				dungeonPlayer.getInventory().remove(0);
			}
			boolean inItems = true;
			while (inItems == true){
				String itemName = input.nextLine();
				if (itemName.equals(".")){
					inItems = false;
					break;
				}
				int weight = input.nextInt();
				int value = input.nextInt();
				int strength = input.nextInt();
				String itemTypeName = input.nextLine();
				ItemType type;
				if (itemTypeName.equals("Weapon")){
					type = ItemType.Weapon;
				}
				else if (itemTypeName.equals("Armor")){
					type = ItemType.Armor;
				}
				else{
					type = ItemType.Weapon;
				}
				Item newItem = new Item(itemName, weight, value, strength, type);
				this.dungeonPlayer.getInventory().add(newItem);	

			}
		}
		catch (FileNotFoundException f){
			System.out.println("File was not found");
		}






	}
}

