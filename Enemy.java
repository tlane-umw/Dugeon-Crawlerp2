import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
//This class sets up the enemy object in order for use in the Dungeon class.
public class Enemy extends Creature{
	//instance variables
	Random random = new Random();
	private String name;
	private int health;
	private int damage;
	private EnemyType type;
	private int row;
	private int column;
	private int board;
	private Item drop;
	private String placeName;
	private static int numEnemiesMoved = 0;
	private static int doesEnemyMoveThisTurn = 0;

	//constructor for the fight method
	Enemy(EnemyType type, String name, int health, int damage, Item drop){
		super(name, health);
		this.type = type;
		this.damage = damage;
		this.drop = drop;
	}
	//constructor for placing enemies on the board
	Enemy (String placeName, int row, int column, int board){
		this.placeName = placeName;
		this.row = row;
		this.column = column;
		this.board = board;
	}
	//place holder name for E's on the board
	public String getPlaceName(){
		return this.placeName;
	}

	//This returns the type of enemy.
	public EnemyType getType(){
		return type;
	}
	public int getDamage(){
		return this.damage;
	}

	//method that takes in a 2d char array and tests until the enemy moves into a blank space on the board
	//then it returns the new board after updating the enemies row & column, and where their E is on the board
	//if the enemy has been defeated and their E is replaced with a space, it returns the same board that was passed in
	public char[][] moveEnemy(char[][] enemyBoard){
		char[][] newEnemyBoard = enemyBoard;
		boolean validMove = false;
		if (newEnemyBoard[row][column] == 'E'){
			while(validMove == false) {
				int number = random.nextInt(4);
				if((number == 0) && (newEnemyBoard[(row - 1)][column] == ' ')){
					newEnemyBoard[row][column] = ' ';
					row--;
					newEnemyBoard[row][column] = 'E';
					//System.out.println("Enemy Row = " + row);
					//System.out.println("Enemy column = " + column);
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 1) && (newEnemyBoard[row][(column - 1)] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					column--;
					newEnemyBoard[row][column] = 'E';
					//System.out.println("Enemy went west!!!!!!!!!!!!!!");
					//System.out.println("Enemy Row = " + row);
					//System.out.println("Enemy column = " + column);
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 2) && (newEnemyBoard[(row + 1)][column] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					row++;
					newEnemyBoard[row][column] = 'E';
					//System.out.println("Enemy Row = " + row);
					//System.out.println("Enemy column = " + column);
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 3) && (newEnemyBoard[row][(column + 1)] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					column++;
					newEnemyBoard[row][column] = 'E';
					//System.out.println("Enemy Row = " + row);
					//System.out.println("Enemy column = " + column);
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else {
					//System.out.println("Enemy Row = " + row);
					//System.out.println("Enemy column = " + column);
					//System.out.println("Enemy wasn't moved. It was trying to go in " + number + " Trying again now.");
					validMove = false;
				}
			}
		}
		else{
			newEnemyBoard[row][column] = ' ';
		}

		return newEnemyBoard;
	}
	public int getEnemyBoardNum(){
		return this.board;
	}

}
