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
		super(placeName, row, column, board);

	}
	//place holder name for E's on the board
	public String getPlaceName(){
		return this.placeName;
	}

	//This returns the type of enemy.
	public EnemyType getType(){
		return type;
	}
	//get damage of enemy
	public int getDamage(){
		return this.damage;
	}

	//method that takes in a 2d char array and tests until the enemy moves into a blank space on the board
	//then it returns the new board after updating the enemies getRow() & getColumn(), and where their E is on the board
	//if the enemy has been defeated and their E is replaced with a space, it returns the same board that was passed in
	public char[][] moveEnemy(char[][] enemyBoard){
		char[][] newEnemyBoard = enemyBoard;
		boolean validMove = false;
		if (newEnemyBoard[getRow()][getColumn()] == 'E'){
			while(validMove == false) {
				int number = random.nextInt(4);
				if((number == 0) && (newEnemyBoard[(getRow() - 1)][getColumn()] == ' ')){
					newEnemyBoard[getRow()][getColumn()] = ' ';
					int currentRow = this.getRow() - 1;
					setRow(currentRow);
					newEnemyBoard[getRow()][getColumn()] = 'E';
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 1) && (newEnemyBoard[getRow()][(getColumn() - 1)] == ' ')) {
					newEnemyBoard[getRow()][getColumn()] = ' ';
					int currentColumn = this.getColumn() - 1;
					setColumn(currentColumn);
					newEnemyBoard[getRow()][getColumn()] = 'E';
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 2) && (newEnemyBoard[(getRow() + 1)][getColumn()] == ' ')) {
					newEnemyBoard[getRow()][getColumn()] = ' ';
					int currentRow = this.getRow() + 1;
					setRow(currentRow);
					newEnemyBoard[getRow()][getColumn()] = 'E';
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else if ((number == 3) && (newEnemyBoard[getRow()][(getColumn() + 1)] == ' ')) {
					newEnemyBoard[getRow()][getColumn()] = ' ';
					int currentColumn = this.getColumn() + 1;
					setColumn(currentColumn);
					newEnemyBoard[getRow()][getColumn()] = 'E';
					numEnemiesMoved++;
					validMove = true;
					return newEnemyBoard;
				} 

				else {
					validMove = false;
				}
			}
		}
		else{
			newEnemyBoard[getRow()][getColumn()] = ' ';
		}

		return newEnemyBoard;
	}

}
