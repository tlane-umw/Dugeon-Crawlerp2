//import statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;

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
	Integer[] placement = new Integer[]{0, 0};
	Player dungeonPlayer;
	Hashtable<Integer, Integer[]> location = new Hashtable<Integer, Integer[]>();

	//constructor with a 2D char array and a new player 
	Dungeon(Player dungeonPlayer, char playerSymbol){
		this.dungeonPlayer = dungeonPlayer;
		this.playerSymbol = playerSymbol;
		world = new World();
		playerColumn = 8;
		playerRow = 1;
		enemyColumn = 2;
		enemyRow = 15;
		enemyColumn2 = 17;
		enemyRow2 = 15;
	}

	//printing the gameboard showing the players location, enemies location, and items locations
	public Hashtable<Integer, Integer[]>  printBoard(){
		//System.out.println("About to print the board!");
		int number = random.nextInt(4) + 3;
		int count = 1;
		if(!alreadyExecuted) {
			for(int i = 0; i < number; i++){
				value.add(random.nextInt(17) + 2);				
				value2.add(random.nextInt(17) + 2);
				if(world.getCurrentBoard(currentBoard)[value.get(i)][value2.get(i)] == ' ');
					world.getCurrentBoard(currentBoard)[value.get(i)][value2.get(i)] = 'I';
					placement = new Integer[]{value.get(i), value2.get(i)};
					location.put(count, placement);
					count++;
				
			}
			alreadyExecuted = true;
			return location;	
		}
		
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

	//method to move enemy in a random direction, only moves once per every 2 player moves
	public void moveEnemy(){

		int number2 = random.nextInt(4);
		boolean validMove = false;
		boolean validMove2 = false;
		if (((doesEnemyMove % 2) == 0) && (world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] == 'E')) {
			while(validMove == false) {
				int number = random.nextInt(4);
				if((number == 0) && (world.getCurrentBoard(currentBoard)[enemyRow - 1][enemyColumn] == ' ')){
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = ' ';
					enemyRow--;
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = 'E';
					//System.out.println("Enemy Row = " + enemyRow);
					//System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 1) && (world.getCurrentBoard(currentBoard)[enemyRow][(enemyColumn - 1)] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = ' ';
					enemyColumn--;
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = 'E';
					//System.out.println("Enemy Row = " + enemyRow);
					//System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 2) && (world.getCurrentBoard(currentBoard)[(enemyRow + 1)][enemyColumn] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = ' ';
					enemyRow++;
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = 'E';
					//System.out.println("Enemy Row = " + enemyRow);
					//System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 3) && (world.getCurrentBoard(currentBoard)[enemyRow][(enemyColumn + 1)] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = ' ';
					enemyColumn++;
					world.getCurrentBoard(currentBoard)[enemyRow][enemyColumn] = 'E';
					//System.out.println("Enemy Row = " + enemyRow);
					//System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else {
					//System.out.println("Enemy Row = " + enemyRow);
					//System.out.println("Enemy column = " + enemyColumn);
					//System.out.println("Enemy wasn't moved. It was trying to go in " + number + " Trying again now.");
					validMove = false;
				}
			}

		}
		if (((doesEnemyMove % 2) == 0) && (world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] == 'E')){
			while(validMove2 == false) {
				number2 = random.nextInt(4);
				if((number2 == 0) && (world.getCurrentBoard(currentBoard)[(enemyRow2 - 1)][enemyColumn2] == ' ')){
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = ' ';
					enemyRow2--;
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = 'E';
					//System.out.println("Enemy Row = " + enemyRow2);
					//System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 1) && (world.getCurrentBoard(currentBoard)[enemyRow2][(enemyColumn2 - 1)] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = ' ';
					enemyColumn2--;
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = 'E';
					//System.out.println("Enemy Row = " + enemyRow2);
					//System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 2) && (world.getCurrentBoard(currentBoard)[(enemyRow2 + 1)][enemyColumn2] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = ' ';
					enemyRow2++;
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = 'E';
					//System.out.println("Enemy Row = " + enemyRow2);
					//System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 3) && (world.getCurrentBoard(currentBoard)[enemyRow2][(enemyColumn2 + 1)] == ' ')) {
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = ' ';
					enemyColumn2++;
					world.getCurrentBoard(currentBoard)[enemyRow2][enemyColumn2] = 'E';
					//System.out.println("Enemy Row = " + enemyRow2);
					//System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else {
					//System.out.println("Enemy Row = " + enemyRow2);
					//System.out.println("Enemy column = " + enemyColumn2);
					//System.out.println("Enemy wasn't moved. It was trying to go in " + number2 + " Trying again now.");
					validMove2 = false;
				}
			}

		}

		doesEnemyMove = doesEnemyMove + 1;
	}

	//moves the user depending on the character the user chooses
	public boolean movePlayer(char userMove){
		itemExistence = false;
		if (userMove == 'W'){
			//if (this.dungeonGameBoard(playerRow - 1)
			if ((playerRow - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			}

			//seeing if the user landed on an item
			else if (world.getCurrentBoard(currentBoard)[playerRow - 1][playerColumn] == 'I') {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerRow--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			}
			//seeing if the user landed on an enemy
			else if (world.getCurrentBoard(currentBoard)[playerRow - 1][playerColumn] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
					playerRow--;
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
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
				//exiting the program if the user lost the fight to the enemy
				else{
					System.out.println("Better luck next time!");
					System.exit(0);

				}
			}



			else if (onItem == true) {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = 'I';
				playerRow--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerRow--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
			}
		}

		else if (userMove == 'A'){
			if ((playerColumn - 1) <= 0){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (world.getCurrentBoard(currentBoard)[playerRow][playerColumn - 1] == 'I') {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerColumn--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} else if (world.getCurrentBoard(currentBoard)[playerRow][playerColumn - 1] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
					playerColumn--;
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
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
					System.out.println("Better luck next time!");
					System.exit(0);
				}

			}
			else if (onItem == true) {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = 'I';
				playerColumn--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerColumn--;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
			}
		}
		else if (userMove == 'S'){
			if ((playerRow + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (world.getCurrentBoard(currentBoard)[playerRow + 1][playerColumn] == 'I') {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerRow++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} else if (world.getCurrentBoard(currentBoard)[playerRow + 1][playerColumn] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
					playerRow++;
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
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
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = 'I';
				playerRow++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerRow++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				//System.out.println("Success! You have moved your player down a space!");
			}
		}
		else{
			if ((playerColumn + 1) >= 19){
				System.out.println("Invalid move, player would hit the wall!");
			} else if (world.getCurrentBoard(currentBoard)[playerRow][playerColumn + 1] == 'I') {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerColumn++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				itemExistence = true;
				onItem = true;
			} else if (world.getCurrentBoard(currentBoard)[playerRow][playerColumn + 1] == 'E') {
				boolean didPlayerWin = false;
				didPlayerWin = fight();
				if (didPlayerWin == true){
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
					playerColumn++;
					world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
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
					System.out.println("Better luck next time!");
					System.exit(0);
				}
			} else if (onItem == true) {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = 'I';
				playerColumn++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
				onItem = false;
			} else {
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = ' ';
				playerColumn++;
				world.getCurrentBoard(currentBoard)[playerRow][playerColumn] = playerSymbol;
			}
		}
		//calling the move enemy after every user turn
		this.moveEnemy();
		return itemExistence;
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

	//fight method between the users player and a newly generated enemy
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
			int enemyDamage = dungeonPlayer.getInventory().getEquippedWeapon().getStrength();
			dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
			System.out.println("Enemy took " + enemyDamage + " damage from the players " + dungeonPlayer.getInventory().getEquippedWeapon().getName());
			System.out.println();
			
			//checking if the players attack killed the enemy
			if (dungeonEnemy.getHealth() <= 0){
				didUserWin = true;
				enemyAlive = false;
				return true;
			}

			//printing the new enemy health if they are still alive
			else{
				System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
				System.out.println();
			}
			//enemy attacking the player

			//getting players current health
			System.out.println("The player currently has a health of " + dungeonPlayer.getHealth());
			System.out.println();

			//getting the amount of damage the enemy caused based off the players current equipped armor
			int playerDamage = ((dungeonEnemy.getDamage()) - (dungeonPlayer.getInventory().getEquippedArmor().getStrength()));

			//checking if the players current weapon is stronger than the enemies attack
			if (playerDamage <= 0){
				System.out.println("The players " + dungeonPlayer.getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
				System.out.println();
			}
			//setting the players new health based on the enemy damage
			else{
				int currentPlayerHealth = dungeonPlayer.getHealth();
				int newPlayerHealth = currentPlayerHealth - playerDamage;
				dungeonPlayer.setHealth(newPlayerHealth);

				System.out.println("Player took " + playerDamage + " damage from the enemy!");
				System.out.println();
				if (dungeonPlayer.getHealth() <=0){
					didUserWin = false;
					System.out.println("The enemy brutally murdered you!");
					System.out.println();
					playerAlive = false;
					return false;

				}
				else{
					System.out.println("After the enemy's latest attack, the player's health is  " + dungeonPlayer.getHealth());
					System.out.println();
				}
			}

			//updating the round number
			roundNumber = roundNumber + 1;
		}
		return didUserWin;
	}


}

