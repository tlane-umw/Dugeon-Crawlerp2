import java.util.Scanner;
import java.util.Hashtable;
public class Creature{
        private String name;
	private int row;
	private int column;
	private int board;
        private int health;
	Creature (String name){
		this.name = name;

	}
        public String getName(){
                return name;
        }
        public int getHealth(){
                return health;
        }
        public void setHealth(int health){
                this.health = health;
        }
}

