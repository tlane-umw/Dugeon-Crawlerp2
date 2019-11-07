//This class sets up the enemy object in order for use in the Dungeon class.
public class Enemy{
	private String name;
	private int health;
	private int damage;
	private EnemyType type;
	private Item drop;

	Enemy(EnemyType type, String name, int health, int damage, Item drop){
		this.type = type;
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.drop = drop;
	}

	//This returns the name of the enemy.
	public String getName(){
		return name;
	}
	//This returns the type of enemy.
	public EnemyType getType(){
		return type;
	}

	//a method to return an item after the player defeats an enemy
	//did not have enough time to implement this into our program
	public Item getDrop(){
		return drop;
	}
	//standard getter and setters
	public int getHealth(){
		return this.health;
	}
	public int getDamage(){
		return this.damage;
	}
	public void setHealth(int newHealth){
		this.health = newHealth;
	}

}
