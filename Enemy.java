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
		super(name);
		this.health = health;
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
	/*public void moveEnemy(){

		int number2 = random.nextInt(4);
		boolean validMove = false;
		boolean validMove2 = false;
		if (((doesEnemyMove % 2) == 0) && (dungeonGameBoard[enemyRow][enemyColumn] == 'E')) {
			while(validMove == false) {
				int number = random.nextInt(4);
				if((number == 0) && (dungeonGameBoard[(enemyRow - 1)][enemyColumn] == ' ')){
					dungeonGameBoard[enemyRow][enemyColumn] = ' ';
					enemyRow--;
					dungeonGameBoard[enemyRow][enemyColumn] = 'E';
					System.out.println("Enemy Row = " + enemyRow);
					System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 1) && (dungeonGameBoard[enemyRow][(enemyColumn - 1)] == ' ')) {
					dungeonGameBoard[enemyRow][enemyColumn] = ' ';
					enemyColumn--;
					dungeonGameBoard[enemyRow][enemyColumn] = 'E';
					System.out.println("Enemy went west!!!!!!!!!!!!!!");
					System.out.println("Enemy Row = " + enemyRow);
					System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 2) && (dungeonGameBoard[(enemyRow + 1)][enemyColumn] == ' ')) {
					dungeonGameBoard[enemyRow][enemyColumn] = ' ';
					enemyRow++;
					dungeonGameBoard[enemyRow][enemyColumn] = 'E';
					System.out.println("Enemy Row = " + enemyRow);
					System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else if ((number == 3) && (dungeonGameBoard[enemyRow][(enemyColumn + 1)] == ' ')) {
					dungeonGameBoard[enemyRow][enemyColumn] = ' ';
					enemyColumn++;
					dungeonGameBoard[enemyRow][enemyColumn] = 'E';
					System.out.println("Enemy Row = " + enemyRow);
					System.out.println("Enemy column = " + enemyColumn);
					validMove = true;
				} else {
					System.out.println("Enemy Row = " + enemyRow);
					System.out.println("Enemy column = " + enemyColumn);
					System.out.println("Enemy wasn't moved. It was trying to go in " + number + " Trying again now.");
					validMove = false;
				}
			}

		}
		if (((doesEnemyMove % 2) == 0) && (dungeonGameBoard[enemyRow2][enemyColumn2] == 'E')){
			while(validMove2 == false) {
				number2 = random.nextInt(4);
				if((number2 == 0) && (dungeonGameBoard[(enemyRow2 - 1)][enemyColumn2] == ' ')){
					dungeonGameBoard[enemyRow2][enemyColumn2] = ' ';
					enemyRow2--;
					dungeonGameBoard[enemyRow2][enemyColumn2] = 'E';
					System.out.println("Enemy Row = " + enemyRow2);
					System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 1) && (dungeonGameBoard[enemyRow2][(enemyColumn2 - 1)] == ' ')) {
					dungeonGameBoard[enemyRow2][enemyColumn2] = ' ';
					enemyColumn2--;
					dungeonGameBoard[enemyRow2][enemyColumn2] = 'E';
					System.out.println("Enemy went west!!!!!!!!!!!!!!");
					System.out.println("Enemy Row = " + enemyRow2);
					System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 2) && (dungeonGameBoard[(enemyRow2 + 1)][enemyColumn2] == ' ')) {
					dungeonGameBoard[enemyRow2][enemyColumn2] = ' ';
					enemyRow2++;
					dungeonGameBoard[enemyRow2][enemyColumn2] = 'E';
					System.out.println("Enemy Row = " + enemyRow2);
					System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else if ((number2 == 3) && (dungeonGameBoard[enemyRow2][(enemyColumn2 + 1)] == ' ')) {
					dungeonGameBoard[enemyRow2][enemyColumn2] = ' ';
					enemyColumn2++;
					dungeonGameBoard[enemyRow2][enemyColumn2] = 'E';
					System.out.println("Enemy Row = " + enemyRow2);
					System.out.println("Enemy column = " + enemyColumn2);
					validMove2 = true;
				} else {
					System.out.println("Enemy Row = " + enemyRow2);
					System.out.println("Enemy column = " + enemyColumn2);
					System.out.println("Enemy wasn't moved. It was trying to go in " + number2 + " Trying again now.");
					validMove2 = false;
				}
			}

		}

		doesEnemyMove = doesEnemyMove + 1;
	}
	*/
}
