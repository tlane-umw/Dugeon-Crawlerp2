import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
/**The enemy class handles the enemies type and the damage they can do, The enemies movement is also handled in this class.
 * @author Tyler Viacara, Toby Lane
 */
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

	/**This constructor is for the enemies that are created in the fight class, They are assigned a random health and damage
	 * @param type The type of enemy that is being created, which is defined in EnemyType
	 * @param name The name of the enemy, which  is the same as its type, as a String
	 * @param health The inital health of the enemy as an integer
	 * @param damage The damage the enemy does when it attacks as an integer
	 * @param drop An item that the enemy drops after they are defeated. Was not implemented up to this point
	 */
	Enemy(EnemyType type, String name, int health, int damage, Item drop){
		super(name, health);
		this.type = type;
		this.damage = damage;
		this.drop = drop;
	}

	/**This constructor is for the Enemy ArrayList in the Dungeon class, Assigning the enemy a board number and a specific location on that board on a specific row and column
	 * @param placeName Just a name assigned in Java to help keep track of what enemies are still alive in the Dungeon
	 * @param row The row position of the enemy as an integer
	 * @param column The column position of the enemy as an integer
	 * @param board The board number the enemy is on as an integer, either 0, 1, or 2
	 */
	Enemy (String placeName, int row, int column, int board){
		super(placeName, row, column, board);

	}

	/**Method that returns the place holder name for the Dungeon's enemy ArrayList
	 * @return The place holder name for the Dungeon's Enemy ArrayList as a String
	 */
	public String getPlaceName(){
		return this.placeName;
	}

	/**Method that returns the type of the Enemy, as defined in EnemyType
	 * @return The ENUM enemy type as defined in EnemyType
	 */
	public EnemyType getType(){
		return type;
	}

	/**Method that returns the damage the enemy does when it attacks as an integer
	 * @return The amount of damage the Enemy does when it attacks as an integer
	 */
	public int getDamage(){
		return this.damage;
	}

	/**Method that takes in a 2d char array and tests until the enemy moves into a blank space on the board then it returns the new board after updating the enemies getRow() and getColumn(), 
	 * and where their E is on the board if the enemy has been defeated and their E is replaced with a space, 
	 * it returns the same board that was passed in
	 * @param enemyBoard the current gameboard (2d array) the enemy is on
	 * @return The new updated board after the enemy has succesfully moved
	 */
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
