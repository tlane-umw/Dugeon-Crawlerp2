import java.util.Hashtable;
import java.util.ArrayList;
//This class sets up the enemy object in order for use in the Dungeon class.
public class Enemy extends Creature{
	private String name;
	private int health;
	private int damage;
	private EnemyType type;
	private Item drop;
	ArrayList<Integer[]> enemyLocation = new ArrayList<Integer[]>();
	private int row;
	private int column;
	private int board;
	public ArrayList<Integer[]> getInitialLocation(){
		enemyLocation.add(new Integer[]{15,2,0});
        	enemyLocation.add(new Integer[]{15,17,0});
		enemyLocation.add(new Integer[]{15,2,1});
        	enemyLocation.add(new Integer[]{15,17,1});
		enemyLocation.add(new Integer[]{15,2,2});
        	enemyLocation.add(new Integer[]{15,17,2});
		return enemyLocation;
	}
	Enemy(EnemyType type, String name, int health, int damage, Item drop){
		super(name, health);
		this.type = type;
		this.damage = damage;
		this.drop = drop;
	}

	//This returns the type of enemy.
	public EnemyType getType(){
		return type;
	}
	public String getName(){
		return name;

	}
	//a method to return an item after the player defeats an enemy
	//did not have enough time to implement this into our program
	public Item getDrop(){
		return drop;
	}
	public int getDamage(){
		return this.damage;
	}
	public void setInitialLocation(int row, int column, int board){
		this.row = row;
		this.column = column;
		this.board = board;
	}

}
