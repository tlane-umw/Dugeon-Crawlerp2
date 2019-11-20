//for chris number 2
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
	private int value, health, count, row, column, board, newColumn, newRow;
	private char playerSymbol;

	//default constructor
	public Player(String name, int health, char PlayerSymbol){
		super(name, health);
		this.playerSymbol = playerSymbol;
		this.row = 1;
		this.column = 8;
		this.board = 1;

	}
	public Inventory getInventory(){
		return userInventory;
	}
	public int getNumEnemiesDefeated(){
		return this.numEnemiesDefeated;
	}
	public static void setNewNumEnemiesDefeated(int newNumEnemiesDefeated){
		numEnemiesDefeated = newNumEnemiesDefeated;
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
	/* Our move method takes the users input and the 2d array that represents the old board. Based on whether the users move was W, A, S or D, this method calls the displacement method, giving it the displacement (-1 or 1), the direction(true is the column, or x axis and false is the row, or y axis) and the old 2d array that represents the board.So, for example -1 and false would be in the negative y direction, while 1 and false would be in the positive y direction. It then returns the newBoard with the new player location.*/
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
			try{

				Thread.sleep(2500);
			}
			catch(InterruptedException g){
				System.out.println("Interrupted");
			}
			//player attacking the enemy
			//
			System.out.println("The " + dungeonEnemy.getName() + " currently has a health of " + dungeonEnemy.getHealth());
			try{
				Thread.sleep(2500);
			}
			catch (InterruptedException z){
				System.out.println("Interrupted!");
			}
			//getting the amount of damage the enemy will take
			int enemyDamage = getInventory().getEquippedWeapon().getStrength();
			dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
			System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
			System.out.println();
			try{
				Thread.sleep(2500);
			}
			catch (InterruptedException f){
				System.out.println("Interrupted!");
			}

			//checking if the enemy won
			if (dungeonEnemy.getHealth() <= 0){
				didUserWin = true;
				enemyAlive = false;
				return true;
			}

			//printing the enemies health if it is still alive
			else{
				System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
				System.out.println();
				try{
					Thread.sleep(2500);
				}
				catch (InterruptedException f){
					System.out.println("Interruputed!");
				}
			}



			//getting players current health
			System.out.println("The player currently has a health of " + getHealth());
			System.out.println();
			try{
				Thread.sleep(2500);
			}
			catch (InterruptedException eee){
				System.out.println("Interrupted!");
			}

			//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
			int playerDamage = ((dungeonEnemy.getDamage()) - (getInventory().getEquippedArmor().getStrength()));

			//checking if the players current armor is stronger than the enemy's attack
			if (playerDamage <= 0){
				try{
					Thread.sleep(2500);
				}
				catch (InterruptedException y){
					System.out.println("Interrupted");
				}
				System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
				System.out.println();
				try{
					Thread.sleep(2500);
				}
				catch (InterruptedException d){
					System.out.println("Interrupted!");
				}
			}
			//setting the players new health based on the previously calculated enemy damage
			else{
				int currentPlayerHealth = getHealth();
				int newPlayerHealth = currentPlayerHealth - playerDamage;
				setHealth(newPlayerHealth);

				System.out.println("You took " + playerDamage + " damage from the enemy!");
				System.out.println();
				try{
					Thread.sleep(2500);
				}
				catch (InterruptedException c){
					System.out.println("Interrupted!");
				}

				//cheching if the player is still alive
				if (getHealth() <= 0){
					didUserWin = false;
					System.out.println("The enemy brutally murdered you!");
					System.out.println("Better luck next time!");
					try{
						Thread.sleep(2500);
					}
					catch (InterruptedException b){
						System.out.println("Interrupted!");
					}
					System.exit(0);
					System.out.println();
					playerAlive = false;
				}
				else{
					System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
					System.out.println();
					try{
						Thread.sleep(2500);
					}
					catch(InterruptedException a){
						System.out.println("Interrupted!");
					}
				}
			}

			roundNumber = roundNumber + 1;
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
	public void setRow(int row){
		this.row = row;
	}
	public void setColumn(int column){
		this.column = column;
	}

	//This method is called when the user steps on an item and chooses to pick it up. Since the item is no longer there, the booleans should be false so that the I symbol does not reappear once the player leaves that space.
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
	//The displacement method takes an integer for the displacement of the player and a boolean for the direction (moving in the x direction or y direction) and the old board. It returns a newBoard based on the players new location. The purpose of this method was to save ourselves having to write this code 4 times for move method based on if the player pressed W, A, S, or D.
	public char[][] displacement(int change, boolean choice, char[][] newBoard){
		if(choice == true){
			newColumn = column + change;
			newRow = row;
		} else {
			newColumn = column;
			newRow = row + change;
		}
		//Prevents the player from running into the wall.
		if ((newBoard[newRow][newColumn] == '|') || (newBoard[newRow][newColumn] == '-')){
			System.out.println("Invalid move, player would hit the wall!");
			return newBoard;
		}
		//What happens when a player lands on the symbol D for door.
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
				//If the player chooses to enter the room, the new players location in the new room is determined by which room the player is currently in and which side of the room that player is currently on. The player will now appear right next to the door in the new room.
				System.out.println("You entered a door into a new room!");
				if (currentPlayerBoard == 1){
					if ((newColumn < 10)){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 0;
						newRow = 10;
						newColumn = 18;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 2;
						newRow = 10;
						newColumn = 1;
					}
				}
				else if (currentPlayerBoard == 2){
					newBoard[row][column] = ' ';
					currentPlayerBoard = 1;
					newRow = 10;
					newColumn = 18;
				}
				else{
					newBoard[row][column] = ' ';
					currentPlayerBoard = 1;
					newRow = 10;
					newColumn = 1;

				}
			}
		}
		//What happens when the player steps on an item.
		else if (newBoard[newRow][newColumn] == 'I') {
			newBoard[row][column] = ' ';
			newBoard[newRow][newColumn] = playerSymbol;
			itemExistence = true;
			onItem = true;
		}
		//Whata happens when the player bumps into an enemy.
		else if (newBoard[newRow][newColumn] == 'E') {
			boolean didPlayerWin = false;
			didPlayerWin = fight();
			if(didPlayerWin == true){
				newBoard[row][column] = ' ';
				newBoard[newRow][newColumn] = playerSymbol;
				numEnemiesDefeated = numEnemiesDefeated + 1;
				System.out.println("You have defeated " + numEnemiesDefeated);	
				if (numEnemiesDefeated == 6){
					try{
						Thread.sleep(4000);
					}
					catch(InterruptedException w){
						System.out.println("Interrupted!");
					}
					System.out.println("Congratulations!! You beat the game!!!");
					System.out.println();
					try{
						Thread.sleep(4000);
					}
					catch (InterruptedException x){
						System.out.println("Interrupted!");
					}
					System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
					System.out.println();
					System.exit(0);
				}
				else{
					try{
						Thread.sleep(2500);
					}
					catch(InterruptedException zz){
						System.out.println("Interrupted!");
					}
					System.out.println("You defeated the enemy! Keep going!!");
					System.out.println();
					try{
						Thread.sleep(2500);
					}
					catch(InterruptedException fff){
						System.out.println("Interrupted!");
					}
				}
			}


			else{
				try{
					Thread.sleep(4000);
				}
				catch(InterruptedException www){
					System.out.println("Interrupted!");
				}
				System.out.println("Better luck next time!");
				System.exit(0);
			}
		}
		//What happens when a player is no longer standing on an item that they chose to not pick up.
		else if (onItem == true) {
			newBoard[row][column] = 'I';
			newBoard[newRow][newColumn] = playerSymbol;
			onItem = false;
		} 
		//Nothing is in the players way, so the player can freely move forward.
		else {
			newBoard[row][column] = ' ';
			newBoard[newRow][newColumn] = playerSymbol;
		}	
		row = newRow;
		column = newColumn;
		return newBoard;
	}

}

