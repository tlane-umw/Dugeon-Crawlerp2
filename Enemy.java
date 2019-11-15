//This class sets up the enemy object in order for use in the Dungeon class.
public class Enemy extends Creature{
	private String name;
	private int health;
	private int damage;
	private EnemyType type;
	private Item drop;

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

}
