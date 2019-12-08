import java.util.Scanner;
import java.util.Hashtable;
/**
*This class is the parent class of both the Player and Enemy class.It holds the getter and setter methods used by each classes.
*@Author Chirstopher Papp,Tyler,Toby
*/
public class Creature{
	//instance variables
	private String name;
	private int row;
	private int column;
	private int board;
	private int health;

	//constructor
	/**
	* A contrustor that takes in name and health and sets them.
	* @param name The name of the creature.
	* @param health The health of the creature.
	*/
	Creature (String name, int health){
		this.name = name;
		this.health = health;

	}
	/**
	* A constructor that takes in a name, row, column, and board number to be set.
	* @param name The name of the creature.
	* @param row The row of the creature on the board.
	* @param column The column of the creature on the board.
	* @param board The board that the creature is on.
	*/
	Creature(String name, int row, int column, int board){
		this.name = name;
		this.row = row;
		this.column = column;
		this.board = board;
	}
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

	public int getBoard(){
		return this.board;
	}
}

