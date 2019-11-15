import java.util.Scanner;
import java.util.Hashtable;
class Player extends Creature{
	//instance variables
	Scanner s = new Scanner(System.in);
	private String answer;
	private static int numEnemiesDefeated = 0;
	private boolean itemExistence, onItem;
	private String name;
	private Inventory userInventory = new Inventory (250);
	private int value, health, count, playerRow, playerColumn;
	private char playerSymbol;

	//default constructor
	public Player(String name, int health, char PlayerSymbol){
		super(name, health);
		this.playerSymbol = playerSymbol;
		
	}
	public Player(){
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
		itemExistence = false;
		if (userMove == 'W'){
			//if (this.dungeonGameBoard(playerRow - 1)
			if ((playerRow - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			}

			//seeing if the user landed on an item
			else if (playerBoard[playerRow - 1][playerColumn] == 'I') {
				playerBoard[playerRow][playerColumn] = ' ';
				playerRow--;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			}
			//seeing if the user landed on an enemy
			else if (playerBoard[playerRow - 1][playerColumn] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					playerBoard[playerRow][playerColumn] = ' ';
					playerRow--;
					playerBoard[playerRow][playerColumn] = playerSymbol;
					numEnemiesDefeated = numEnemiesDefeated + 1;

					//checking if the player has beat both enemies
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


			//seeing if the user landed on an enemy
			else if (onItem == true) {
				playerBoard[playerRow][playerColumn] = 'I';
				playerRow--;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				playerBoard[playerRow][playerColumn] = ' ';
				playerRow--;
				playerBoard[playerRow][playerColumn] = playerSymbol;
			}
		}

		else if (userMove == 'A'){
			if ((playerColumn - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (playerBoard[playerRow][playerColumn - 1] == 'I') {
				playerBoard[playerRow][playerColumn] = ' ';
				playerColumn--;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} else if (playerBoard[playerRow][playerColumn - 1] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					playerBoard[playerRow][playerColumn] = ' ';
					playerColumn--;
					playerBoard[playerRow][playerColumn] = playerSymbol;
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
				else if (onItem == true) {
					playerBoard[playerRow][playerColumn] = 'I';
					playerColumn--;
					playerBoard[playerRow][playerColumn] = playerSymbol;
					onItem = false;
				} else {
					playerBoard[playerRow][playerColumn] = ' ';
					playerColumn--;
					playerBoard[playerRow][playerColumn] = playerSymbol;
				}
			}
		} else if (userMove == 'S'){
			if ((playerRow + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (playerBoard[playerRow + 1][playerColumn] == 'I') {
				playerBoard[playerRow][playerColumn] = ' ';
				playerRow++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} else if (playerBoard[playerRow + 1][playerColumn] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					playerBoard[playerRow][playerColumn] = ' ';
					playerRow++;
					playerBoard[playerRow][playerColumn] = playerSymbol;
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
				else{
					System.out.println("Better luck next time");
					System.exit(0);
				}

			} else if (onItem == true) {
				playerBoard[playerRow][playerColumn] = 'I';
				playerRow++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				playerBoard[playerRow][playerColumn] = ' ';
				playerRow++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				//System.out.println("Success! You have moved your player down a space!");
			}
		}else{
			if ((playerColumn + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (playerBoard[playerRow][playerColumn + 1] == 'I') {
				playerBoard[playerRow][playerColumn] = ' ';
				playerColumn++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				numEnemiesDefeated = numEnemiesDefeated + 1;
			} else if (playerBoard[playerRow][playerColumn + 1] == 'E') {
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
				playerBoard[playerRow][playerColumn] = 'I';
				playerColumn++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} 
			else {
				playerBoard[playerRow][playerColumn] = ' ';
				playerColumn++;
				playerBoard[playerRow][playerColumn] = playerSymbol;
			}
		}
		//calling the move enemy after every user turn
		
		return playerBoard;
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
