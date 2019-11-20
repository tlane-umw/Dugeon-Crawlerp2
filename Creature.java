import java.util.Scanner;
import java.util.Hashtable;
public class Creature{
       	//instance variables
       	private String name;
	private int row;
	private int column;
	private int board;
        private int health;

	//constructor
	Creature (String name, int health){
		this.name = name;
		this.health = health;

	}
	//blank default constructor
	Creature(){
	}

	//set & get methods
        public String getName(){
                return name;
        }
        public int getHealth(){
                return health;
        }
        public void setHealth(int health){
                this.health = health;
        }
	public void setName(String name){
		this.name = name;
	}
	public void setColumn(int column){
		this.column = column;
	}
	public void setBoard(int board){
		this.board = board;
		}
	public void setRow(int row){
		this.row = row;
	}
}

