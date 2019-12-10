import java.util.Scanner;
import java.util.Hashtable;

/**
 * The creature class is the parent class for enemy and player, it also handles location, which includes row, column and board, along with their health and name.
 * @author Chris Papp, Tyler Viacara
 */
public class Creature{
	//instance variables
	private String name;
	private int row;
	private int column;
	private int board;
	private int health;

	/**Constructor that takes in the name and health of the creature
	 * @param name The name of the creature as a string.
	 * @param health The integer of the beginning health of the creature
	 */
	Creature (String name, int health){
		this.name = name;
		this.health = health;
	}

	/**Constructor that takes in the position of the creature, what board they are on, their health, and their string
	 * @param row The int position of the creature in the row
	 * @param column The int position of the creature in the column
	 * @param board The int position of the creature on which board they are on
	 * @param health The integer of the beginning health of the creature
	 * @param name The name of the creature as a string.
	 */
	Creature(int row, int column, int board, int health, String name){
		this.row = row;
		this.column = column;
		this.board = board;
		this.health = health;
		this.name = name;
	}

	/** Constructor that takes in the name of the creature as a string, and their position on a specific board as an int
	 * @param name The name of the creature as a string.
	 * @param row The int position of the creature in the row
	 * @param column The int position of the creature in the column
	 * @param board The int position of the creature on which board they are on
	 */
	Creature(String name, int row, int column, int board){
		this.name = name;
		this.row = row;
		this.column = column;
		this.board = board;
	}

	/**Method that returns the name of the creature as a string
	 * @return the name of the creature as a string.
	 */
	public String getName(){
		return name;
	}

	/**Method that returns the current health of the creature
	 * @return the latest health of the creature as an integer
	 */
	public int getHealth(){
		return health;
	}

	/**Method that sets the new health of the creature
	 * @param health the new health of the creature after they have taken damage in a fight
	 */
	public void setHealth(int health){
		this.health = health;
	}

	/**Method that sets the name of the creature
	 * @param name Assigning the name of the creature as a string
	 */
	public void setName(String name){
		this.name = name;
	}

	/**Method that sets the column position of the creature
	 * @param column the new column position of the creature
	 */
	public void setColumn(int column){
		this.column = column;
	}

	/**method that sets the board that the creature is on
	 * @param board The board number the creature is on
	 */
	public void setBoard(int board){
		this.board = board;
	}

	/**Method that sets the row positon of the creature
	 * @param row The row position the creature is in
	 */
	public void setRow(int row){
		this.row = row;
	}

	/**Method that returns the current column the creature is in
	 * @return The column the creature is in as an integer
	 */
	public int getColumn(){
		return this.column;
	}

	/**Method that returns the current row the creature is in
	 * @return The column the creature is in as an integer
	 */
	public int getRow(){
		return this.row;
	}

	/**Method that returns the current board the creature is on
         * @return The board number the creature is on as an Integer
         */
	public int getBoard(){
		return this.board;
	}
}
