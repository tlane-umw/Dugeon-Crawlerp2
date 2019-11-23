import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;
class Player extends Creature{

	//instance variables
	Scanner s = new Scanner(System.in);
	private String answer;
	private static int currentPlayerBoard = 1;
	private static int numEnemiesDefeated = 0;
	private boolean itemExistence;
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
	public boolean itemQuestion(Dungeon itemQuestionDungeon, int tempRow, int tempColumn){
		boolean doesItemRemain;
		ArrayList<Item> currentDungeonItems = itemQuestionDungeon.getItemsList();
		for (int yy = 0; yy < currentDungeonItems.size(); yy++){
			int currentItemRow = currentDungeonItems.get(yy).getItemRow();
			int currentItemColumn = currentDungeonItems.get(yy).getItemColumn();
			int currentItemBoard = currentDungeonItems.get(yy).getItemBoard();
			if ((currentItemRow == tempRow) && (currentItemColumn == tempColumn)){
				if (currentItemBoard == this.currentPlayerBoard){
					System.out.println("You have stumbled onto a " + currentDungeonItems.get(yy).getName() + ".");
					System.out.println("Here is some information about the item: ");
					System.out.println(currentDungeonItems.get(yy).toString());
					System.out.println("Would you like to pick this item up?");
					System.out.println("Enter 'Y' for yes and 'N' for no.");
					answer = s.nextLine();
					while(!(answer.equals("N") || answer.equals("n"))){
						if(answer.equals("Y") || answer.equals("y")){
							boolean couldUserAdd = userInventory.add(currentDungeonItems.get(yy));

							if(couldUserAdd == true){
								currentDungeonItems.remove(yy);
								itemQuestionDungeon.setItemsList(currentDungeonItems);
								doesItemRemain = false;
								return doesItemRemain;
							}
							else{
								doesItemRemain = true;
								return doesItemRemain;
							}
						}
						else { 
							System.out.println("Not a valid input.");
							System.out.println("You have found an item, would you like to pick it up?");
							System.out.println("Enter 'Y' for yes and 'N' for no.");
							answer = s.nextLine();
						}
					}
				}

			}

		}
		doesItemRemain = true;
		return doesItemRemain;
	}	
	/* Our move method takes the users input and the 2d array that represents the old board. Based on whether the users move was W, A, S or D, this method calls the displacement method, giving it the displacement (-1 or 1), the direction(true is the column, or x axis and false is the row, or y axis) and the old 2d array that represents the board.So, for example -1 and false would be in the negative y direction, while 1 and false would be in the positive y direction. It then returns the newBoard with the new player location.*/
	public char[][] move(char userMove, char[][] playerBoard, Dungeon playerDungeon){

		char[][] newBoard = playerBoard;
		if (userMove == 'W'){
			newBoard = displacement(-1, false, newBoard, playerDungeon);
		}
		else if (userMove == 'A'){
			newBoard = displacement(-1, true, newBoard, playerDungeon);
		} 
		else if (userMove == 'S'){
			newBoard = displacement(1, false, newBoard, playerDungeon);
		}
		else {
			newBoard = displacement(1, true, newBoard, playerDungeon);
		}	
		return newBoard;
	}		

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

				Thread.sleep(1000);
			}
			catch(InterruptedException g){
				System.out.println("Interrupted");
			}
			//player attacking the enemy
			System.out.println("The " + dungeonEnemy.getName() + " currently has a health of " + dungeonEnemy.getHealth());
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException z){
				System.out.println("Interrupted!");
			}
			//getting the amount of damage the enemy will take and subtracting that from their current health
			int enemyDamage = getInventory().getEquippedWeapon().getStrength();
			dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
			System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
			System.out.println();
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException f){
				System.out.println("Interrupted!");
			}

			//checking if the enemy is still alive
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
					Thread.sleep(1000);
				}
				catch (InterruptedException f){
					System.out.println("Interruputed!");
				}
			}



			//getting players current health before the enemy attacks
			System.out.println("The player currently has a health of " + getHealth());
			System.out.println();
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException eee){
				System.out.println("Interrupted!");
			}

			//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
			int playerDamage = ((dungeonEnemy.getDamage()) - (getInventory().getEquippedArmor().getStrength()));

			//checking if the players current armor is stronger than the enemy's attack
			if (playerDamage <= 0){
				try{
					Thread.sleep(1000);
				}
				catch (InterruptedException y){
					System.out.println("Interrupted");
				}
				System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
				System.out.println();
				try{
					Thread.sleep(1000);
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
					Thread.sleep(1000);
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
						Thread.sleep(1000);
					}
					catch (InterruptedException b){
						System.out.println("Interrupted!");
					}
					System.exit(0);
					System.out.println();
					playerAlive = false;
				}
				else{
					//printing out players health after enemy attack
					System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
					System.out.println();
					try{
						Thread.sleep(1000);
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

	public void setRow(int row){
		this.row = row;
	}

	public void setColumn(int column){
		this.column = column;
	}

	public int getCurrentPlayerBoard(){
		return this.currentPlayerBoard;
	}

	public void setCurrentPlayerBoard(int newPlayerBoard){
		this.currentPlayerBoard = newPlayerBoard;
	}

	//The displacement method takes an integer for the displacement of the player and a boolean for the direction (moving in the x direction or y direction) and the old board. It returns a newBoard based on the players new location. The purpose of this method was to save ourselves having to write this code 4 times for move method based on if the player pressed W, A, S, or D.
	public char[][] displacement(int change, boolean choice, char[][] newBoard, Dungeon displacementDungeon){
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
			itemExistence = itemQuestion(displacementDungeon, newRow, newColumn);
		}
		//Whata happens when the player bumps into an enemy.
		else if (newBoard[newRow][newColumn] == 'E') {
			boolean didPlayerWin = false;
			didPlayerWin = fight();
			if(didPlayerWin == true){
				newBoard[row][column] = ' ';
				newBoard[newRow][newColumn] = playerSymbol;
				//updating the amount of enemies defeated
				numEnemiesDefeated = numEnemiesDefeated + 1;

				//checking to see if the user defeated the game
				if (numEnemiesDefeated == 6){
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException w){
						System.out.println("Interrupted!");
					}
					System.out.println("Congratulations!! You beat the game!!!");
					System.out.println();
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException x){
						System.out.println("Interrupted!");
					}
					System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
					System.out.println();
					System.exit(0);
				}
				
				//telling the user how many enemies they have beaten so far
				else{
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException zz){
						System.out.println("Interrupted!");
					}
					if (numEnemiesDefeated == 1){
						System.out.println("You've defreated 1 enemy so far!");
					}
					else{ 
						System.out.println("You have defeated " + numEnemiesDefeated + " enemies so far!");
					}
					System.out.println();
					System.out.println("Defeat all 6 to defeat the game!");
					System.out.println();
					System.out.println("Keeip going!!");
					System.out.println();
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException fff){
						System.out.println("Interrupted!");
					}
				}
			}


			else{
				try{
					Thread.sleep(1000);
				}
				catch(InterruptedException www){
					System.out.println("Interrupted!");
				}
				//exiting out if the user loses
				System.out.println("Better luck next time!");
				System.exit(0);
			}

			//Nothing is in the players way, so the player can freely move forward.
		}
		else if (itemExistence == true){
			newBoard[row][column] = 'I';
			newBoard[newRow][newColumn] = playerSymbol;
			itemExistence = false;
		}
		else {
			newBoard[row][column] = ' ';
			newBoard[newRow][newColumn] = playerSymbol;
		}	
		row = newRow;
		column = newColumn;

		return newBoard;
	}

}

