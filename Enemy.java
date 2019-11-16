import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
//This class sets up the enemy object in order for use in the Dungeon class.
public class Enemy extends Creature{
	Random random = new Random();
	private String name;
	private int health;
	private int damage;
	private EnemyType type;
	private Item drop;
	ArrayList<Integer[]> enemyLocation = new ArrayList<Integer[]>();
	private int row;
	private int column;
	private int board;
	private static int doesEnemyMove = 0;
	private static int numEnemiesMoved = 0;
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
		super(name);
		this.health = health;
		this.type = type;
		this.damage = damage;
		this.drop = drop;
	}
	Enemy (String name, int row, int column, int board){
		super(name);
		this.row = row;
		this.column = column;
		this.board = board;
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
	public char[][] moveEnemy(char[][] enemyBoard){
		char[][] newEnemyBoard = enemyBoard;
		int number2 = random.nextInt(4);
		boolean validMove = false;
		if (((doesEnemyMove % 2) == 0) && (newEnemyBoard[row][column] == 'E')) {
			while(validMove == false) {
				int number = random.nextInt(4);
				if((number == 0) && (newEnemyBoard[(row - 1)][column] == ' ')){
					newEnemyBoard[row][column] = ' ';
					row--;
					newEnemyBoard[row][column] = 'E';
					System.out.println("Enemy Row = " + row);
					System.out.println("Enemy column = " + column);
					validMove = true;
				} else if ((number == 1) && (newEnemyBoard[row][(column - 1)] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					column--;
					newEnemyBoard[row][column] = 'E';
					System.out.println("Enemy went west!!!!!!!!!!!!!!");
					System.out.println("Enemy Row = " + row);
					System.out.println("Enemy column = " + column);
					validMove = true;
				} else if ((number == 2) && (newEnemyBoard[(row + 1)][column] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					row++;
					newEnemyBoard[row][column] = 'E';
					System.out.println("Enemy Row = " + row);
					System.out.println("Enemy column = " + column);
					validMove = true;
				} else if ((number == 3) && (newEnemyBoard[row][(column + 1)] == ' ')) {
					newEnemyBoard[row][column] = ' ';
					column++;
					newEnemyBoard[row][column] = 'E';
					System.out.println("Enemy Row = " + row);
					System.out.println("Enemy column = " + column);
					validMove = true;
				} else {
					System.out.println("Enemy Row = " + row);
					System.out.println("Enemy column = " + column);
					System.out.println("Enemy wasn't moved. It was trying to go in " + number + " Trying again now.");
					validMove = false;
				}
				if (validMove == true){
					numEnemiesMoved++;
					if (numEnemiesMoved == 6){
						numEnemiesMoved = 0;
						doesEnemyMove++;
					}
				}
			}

		}
		return newEnemyBoard;
	}
	public int getEnemyBoardNum(){
		return this.board;
	}
	
}
