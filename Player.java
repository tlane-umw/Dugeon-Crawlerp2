import java.util.Scanner;
import java.util.Hashtable;
class Player extends Creature{
	//instance variables
	Scanner s = new Scanner(System.in);
	private String answer;
	private static int currentPlayerBoard = 1;
	private static int numEnemiesDefeated = 0;
	private boolean itemExistence, onItem;
	private String name;
	private Inventory userInventory = new Inventory (250);
	private int value, health, count, row, column, newColumn, newRow;
	private char playerSymbol;

	//default constructor
	public Player(String name, char PlayerSymbol){
		super(name);
		this.playerSymbol = playerSymbol;
		this.health = 100;
		this.row = 1;
		this.column = 8;

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
	public char[][] move(char userMove, char[][] playerBoard){

		char[][] newBoard = playerBoard;
		itemExistence = false;
		if (userMove == 'W'){
			newBoard = displacement(-1, false, newBoard);
		}
		else if (userMove == 'A'){
			newBoard = displacement(-1, true, newBoard);
		} 
		else if (userMove == 'S'){
			newBoard = displacement(1, false, newBoard);
		}
		else {
			newBoard = displacement(1, true, newBoard);
		}	
		return newBoard;
	}
	//keeping track of where the user is
	public boolean fight(){
		Enemy dungeonEnemy = EnemyGenerator.generate();
		boolean playerAlive = true;
		boolean enemyAlive = true;
		boolean didUserWin = false;
		int roundNumber = 1;

		//running through the fight until the player or enemy dies
		while ((playerAlive == true) && (enemyAlive == true)){
			System.out.println("Round " + roundNumber + " is about to begin!");
			System.out.println();

			//player attacking the enemy
			int enemyDamage = getInventory().getEquippedWeapon().getStrength();
			dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
			System.out.println("Enemy took " + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
			System.out.println();

			//checking

		}
		return didUserWin;
	}

	public int getColumn(){
		return this.column;
	}
	public int getRow(){
		return this.row;
	}
	public boolean getItemExistence(){
		return itemExistence;
	}
	public void makeFalse(){
		onItem = false;
		itemExistence = false;
	}
	public Integer[] playerLocation(){
		Integer[] position = new Integer[]{currentPlayerBoard, row, column};
		return position;
	}

	public int getCurrentPlayerBoard(){
		return this.currentPlayerBoard;
	}
	public void setCurrentPlayerBoard(int newPlayerBoard){
		this.currentPlayerBoard = newPlayerBoard;
	}
	public char[][] displacement(int change, boolean choice, char[][] newBoard){
		if(choice == true){
			newColumn = column + change;
			newRow = row;
		} else {
			newColumn = column;
			newRow = row + change;
		}
		if ((newBoard[newRow][newColumn] == '|') || (newBoard[newRow][newColumn] == '_')){
			System.out.println("Invalid move, player would hit the wall!");
			return newBoard;
		}
		else if (newBoard[newRow][(newColumn)] == 'D'){

			System.out.println("You have found a door! Do you want to go into the new room?");
			System.out.println("Enter 'Y' for yes and 'N' for no.");
			String userDoor = s.nextLine();
			while ((!(userDoor.equals("Y"))) && (!(userDoor.equals("N")))){
				System.out.println("You did not enter a 'Y' or 'N'.");
				System.out.println("Please enter 'Y' to go through the door or 'N' to stay where you are.");
				userDoor = s.nextLine();
			}
			if (userDoor.equals("N")){
				return newBoard;
			}
			else{
				System.out.println("You entered a door into a new room!");
				if (currentPlayerBoard == 1){
					if ((column < 10)){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 0;
						newRow = 10;
						newColumn = 17;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 2;
						newRow = 10;
						newColumn = 2;
					}
				}
				else if (currentPlayerBoard == 2){
					newBoard[row][column] = ' ';
					currentPlayerBoard = 1;
					newRow = 10;
					newColumn = 17;
				}
				else{
					newBoard[row][column] = ' ';
					currentPlayerBoard = 1;
					newRow = 10;
					newColumn = 2;

				}
			}
		}
		else if (newBoard[newRow][newColumn] == 'I') {
			newBoard[row][column] = ' ';
			newBoard[newRow][newColumn] = playerSymbol;
			itemExistence = true;
			onItem = true;
		} 
		else if (newBoard[newRow][newColumn] == 'E') {
			boolean didPlayerWin = false;
			didPlayerWin = fight();
			if(didPlayerWin == true){	
				if (numEnemiesDefeated == 2){
					System.out.println("Congratulations!! You beat the game!!!");
					System.out.println();
					System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
					System.out.println();
					System.exit(0);
				}
				else if (numEnemiesDefeated >= 0){
					System.out.println("You defeated the enemy! Keep going!!");
					numEnemiesDefeated = numEnemiesDefeated + 1;
					System.out.println();
				}
			}


			else{
				System.out.println("Better luck next time!");
				System.exit(0);
			}
		}
		else if (onItem == true) {
			newBoard[row][column] = 'I';
			newBoard[newRow][newColumn] = playerSymbol;
			onItem = false;
		} 
		else {
			newBoard[row][column] = ' ';
			newBoard[newRow][newColumn] = playerSymbol;
		}
		row = newRow;
		column = newColumn;	
		//calling the move enemy after every user turn
		return newBoard;
	}
}
