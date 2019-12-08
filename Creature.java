import java.util.Scanner;
import java.util.Hashtable;
<<<<<<< HEAD

/**The creature class is the parent class for enemy and player. It handles location, which includes row column and board, along with their health and name
 * @author Chris Papp, Tyler Viacara
 */
=======
/**
*This class is the parent class of both the Player and Enemy class.It holds the getter and setter methods used by each classes.
*@Author Chirstopher Papp,Tyler,Toby
*/
>>>>>>> 1261dba524e06ac98e9805cebec8770a59808826
public class Creature{
	//instance variables
	private String name;
	private int row;
	private int column;
	private int board;
	private int health;

<<<<<<< HEAD
	/**Constructor that takes in the name and health of the creature
	 * @param name The name of the creature as a string.
	 * @param health The integer of the beginning health of the creature
	 */
=======
	//constructor
	/**
	* A contrustor that takes in name and health and sets them.
	* @param name The name of the creature.
	* @param health The health of the creature.
	*/
>>>>>>> 1261dba524e06ac98e9805cebec8770a59808826
	Creature (String name, int health){
		this.name = name;
		this.health = health;

	}
<<<<<<< HEAD
	/** Constructor that takes in the name of the creature as a string, and their position on a specific board as an int
	 * @param name The name of the creature as a string.
	 * @param row The int position of the creature in the row
	 * @param column The int position of the creature in the column
	 * @param board The int position of the creature on which board they are on
	 */
=======
	/**
	* A constructor that takes in a name, row, column, and board number to be set.
	* @param name The name of the creature.
	* @param row The row of the creature on the board.
	* @param column The column of the creature on the board.
	* @param board The board that the creature is on.
	*/
>>>>>>> 1261dba524e06ac98e9805cebec8770a59808826
	Creature(String name, int row, int column, int board){
		this.name = name;
		this.row = row;
		this.column = column;
		this.board = board;
	}
<<<<<<< HEAD

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
=======
	//blank default constructor
	/**
	* The default constructor, takes in no parameters and sets nothing.
	*/
	Creature(){
	}

	//set & get methods
	/**
	* A method that returns the name of the creature.
	* @return name The name of the creature as a String.
	*/
	public String getName(){
		return name;
	}
	/**
	* A method that returns the health of the creature.
	* @return health  The health  of the creature as a int.
	*/
	public int getHealth(){
		return health;
	}
	/**
	* A method that takes the health as an int  of a creature and sets it.
	* @param health  The new health of a creature as an int.
	*/
	public void setHealth(int health){
		this.health = health;
	}	
	/**
	* A method that takes the name as a string of a creature and sets it.
	* @param name  The new name of a creature as a string.
	*/
	public void setName(String name){
		this.name = name;
	}	
	/**
	* A method that takes the column  of a creature and sets it.
	* @param column  The new column on the board of a creature as an int.
	*/
	public void setColumn(int column){
		this.column = column;
	}	
	/**
	* A method that takes the board a creature is on and sets it.
	* @param board  The new board number of a creature as an int.
	*/
	public void setBoard(int board){
		this.board = board;
	}	
	/**
	* A method that takes the row of a creature and sets it.
	* @param row  The new row on the board of a creature as an int.
	*/
	public void setRow(int row){
		this.row = row;
	}	
	/**
	* A method that returns the column location of the creature.
	* @return column The column location of the creature as an int.
	*/

	public int getColumn(){
		return this.column;
	}	
	/**
	* A method that returns the row location  of the creature.
	* @return row  The row location of the creature as an int.
	*/

	public int getRow(){
		return this.row;
	}	
	/**
	* A method that returns the board the creature is on.
	* @return board  The board the creature is on as an int.
	*/

>>>>>>> 1261dba524e06ac98e9805cebec8770a59808826
	public int getBoard(){
		return this.board;
	}
}

