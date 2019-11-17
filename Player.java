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
	private int value, health, count, row, column;
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
			//if (this.dungeonGameBoard(row - 1)
			if ((row - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			}

			else if (newBoard[(row - 1)][column] == 'D'){

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
							row = 10;
							column = 17;
						}
						else{
							newBoard[row][column] = ' ';
							currentPlayerBoard = 2;
							row = 10;
							column = 2;
						}
					}
					else if (currentPlayerBoard == 2){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 17;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 2;

					}
				}
			}

			//seeing if the user landed on an item
			else if (newBoard[row - 1][column] == 'I') {
				newBoard[row][column] = ' ';
				row--;
				newBoard[row][column] = playerSymbol;
				itemExistence = true;
				onItem = true;
			}
			//seeing if the user landed on an enemy
			else if (newBoard[row - 1][column] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					newBoard[row][column] = ' ';
					row--;
					newBoard[row][column] = playerSymbol;
					numEnemiesDefeated = numEnemiesDefeated + 1;

					//checking if the player has beat both enemies
					if (numEnemiesDefeated == 6){
						System.out.println("Congratulations!! You beat the game!!!");
						System.out.println();
						System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
						System.out.println();
						System.exit(0);
					}
					else{
						System.out.println("You defeated the enemy! Keep going!!");
						System.out.println();
					}
				}



			}


			//seeing if the user landed on an enemy
			else if (onItem == true) {
				newBoard[row][column] = 'I';
				row--;
				newBoard[row][column] = playerSymbol;
				onItem = false;
			} else {
				newBoard[row][column] = ' ';
				row--;
				newBoard[row][column] = playerSymbol;
			}
		}

		else if (userMove == 'A'){
			if ((column - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			}	
			else if (newBoard[row][(column - 1)] == 'D'){

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
							row = 10;
							column = 17;
						}
						else{
							newBoard[row][column] = ' ';
							currentPlayerBoard = 2;
							row = 10;
							column = 2;
						}
					}
					else if (currentPlayerBoard == 2){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 17;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 2;

					}
				}
			} 

			else if (newBoard[row][column - 1] == 'I') {
				newBoard[row][column] = ' ';
				column--;
				newBoard[row][column] = playerSymbol;
				itemExistence = true;
				onItem = true;
			}
			else if (newBoard[row][column - 1] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					newBoard[row][column] = ' ';
					column--;
					newBoard[row][column] = playerSymbol;
					numEnemiesDefeated = numEnemiesDefeated + 1;
					if (numEnemiesDefeated == 2){
						System.out.println("Congratulations!! You beat the game!!!");
						System.out.println();
						System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
						System.out.println();
						System.exit(0);
					}
					else if (numEnemiesDefeated == 1){
						System.out.println("You defeated the enemy! Keep going!!");
						System.out.println();
					}

				}
			}
			else if (onItem == true) {
				newBoard[row][column] = 'I';
				column--;
				newBoard[row][column] = playerSymbol;
				onItem = false;
			} 
			else {
				newBoard[row][column] = ' ';
				column--;
				newBoard[row][column] = playerSymbol;
			}

		} 
		else if (userMove == 'S'){
			if ((row + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			}

			else if (newBoard[(row + 1)][column] == 'D'){

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
							row = 10;
							column = 17;
						}
						else{
							newBoard[row][column] = ' ';
							currentPlayerBoard = 2;
							row = 10;
							column = 2;
						}
					}
					else if (currentPlayerBoard == 2){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 17;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 2;

					}
				}
			}	
			else if (newBoard[(row + 1)][column] == ' '){
				newBoard[row][column] = ' ';
				row++;
				newBoard[row][column] = playerSymbol;
			}



			else if (newBoard[row + 1][column] == 'I') {
				newBoard[row][column] = ' ';
				this.row++;
				newBoard[row][column] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} 

			else if (newBoard[row + 1][column] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					newBoard[row][column] = ' ';
					this.row++;
					newBoard[row][column] = playerSymbol;
					numEnemiesDefeated = numEnemiesDefeated + 1;
					if (numEnemiesDefeated == 2){
						System.out.println("Congratulations!! You beat the game!!!");
						System.out.println();
						System.out.println("All the best from the creators - Chris, Toby, & Tyler!");
						System.out.println();
						System.exit(0);
					}
					else if (numEnemiesDefeated == 1){
						System.out.println("You defeated the enemy! Keep going!!");
						System.out.println();
					}



					else{
						System.out.println("Better luck next time");
						System.exit(0);
					}

				} 
				else if (onItem == true) {
					newBoard[row][column] = 'I';
					this.row++;
					newBoard[row][column] = playerSymbol;
					onItem = false;
				}
				else {
					newBoard[row][column] = ' ';
					this.row++;
					newBoard[row][column] = playerSymbol;
					//System.out.println("Success! You have moved your player down a space!");
				}
			}
		}
		else {
			if ((column + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			}
			else if (newBoard[row][(column + 1)] == 'D'){

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
							row = 10;
							column = 17;
						}
						else{
							newBoard[row][column] = ' ';
							currentPlayerBoard = 2;
							row = 10;
							column = 2;
						}
					}
					else if (currentPlayerBoard == 2){
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 17;
					}
					else{
						newBoard[row][column] = ' ';
						currentPlayerBoard = 1;
						row = 10;
						column = 2;

					}
				}
			}
			else if (newBoard[row][column + 1] == 'I') {
				newBoard[row][column] = ' ';
				this.column++;
				newBoard[row][column] = playerSymbol;
				numEnemiesDefeated = numEnemiesDefeated + 1;
			} 
			else if (newBoard[row][column + 1] == 'E') {
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
					else if (numEnemiesDefeated == 1){
						System.out.println("You defeated the enemy! Keep going!!");
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
				this.column++;
				newBoard[row][column] = playerSymbol;
				onItem = false;
			} 
			else {
				newBoard[row][column] = ' ';
				this.column++;
				newBoard[row][column] = playerSymbol;
			}	
			//calling the move enemy after every user turn
		}
		/*for (int i = 0; i < 20; i++){
		  for (int j = 0; j < 20; j++){
		  System.out.print(newBoard[i][j]);
		  }
		  System.out.println();
		}
		*/
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
			try{
				Thread.sleep(5000);
			}
			catch(InterruptedException g){
				System.out.println("Interrupted");

				//player attacking the enemy
				int enemyDamage = getInventory().getEquippedWeapon().getStrength();
				dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
				System.out.println("Enemy took " + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
				System.out.println();
				try{
					Thread.sleep(5000);
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
						Thread.sleep(5000);
					}
					catch (InterruptedException f){
						System.out.println("Interruputed!");
					}
				}

				//enemy attacking the player


				//getting players current health
				System.out.println("The player currently has a health of " + getHealth());
				System.out.println();
				try{
					Thread.sleep(5000);
				}
				catch (InterruptedException e){
					System.out.println("Interrupted!");
				}

				//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
				int playerDamage = ((dungeonEnemy.getDamage()) - (getInventory().getEquippedArmor().getStrength()));

				//checking if the players current armor is stronger than the enemy's attack
				if (playerDamage <= 0){
					System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
					System.out.println();
					try{
						Thread.sleep(5000);
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
						Thread.sleep(5000);
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
							Thread.sleep(5000);
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
							Thread.sleep(5000);
						}
						catch(InterruptedException a){
							System.out.println("Interrupted!");
						}
					}
				}

				roundNumber = roundNumber + 1;
			}

		}

		return didUserWin;
	}

	public int getColumn(){
		return this.column;
	}
	public int getRow(){
		return this.row;
	}

	public int getCurrentPlayerBoard(){
		return this.currentPlayerBoard;
	}
	public void setCurrentPlayerBoard(int newPlayerBoard){
		this.currentPlayerBoard = newPlayerBoard;
	}
}
