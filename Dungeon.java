//import statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;

//instance variables
class Dungeon{
	private static int numEnemiesDefeated = 0;
	int playerColumn;
	int playerRow;
	int enemyColumn;
	int enemyRow;
	int enemyColumn2;
	int enemyRow2;
	private static int currentBoard = 1;
	private World world;
	private boolean alreadyExecuted = false;
	private boolean alreadyExecuted2 = false;
	private boolean onItem = false;
	private boolean itemExistence = false;
	private static int doesEnemyMove = 2;
	ArrayList<Integer> value = new ArrayList<Integer>();
	ArrayList<Integer> value2 = new ArrayList<Integer>();
	ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	ArrayList<Player> player = new ArrayList<Player>();
	Random random = new Random();
	char playerSymbol;
	Integer[] placement = new Integer[]{0, 0};
	Player dungeonPlayer;
	Hashtable<Integer, Integer[]> location = new Hashtable<Integer, Integer[]>();

	//constructor with a 2D char array and a new player 
	Dungeon(Player dungeonPlayer, char playerSymbol){
		this.dungeonPlayer = dungeonPlayer;
		this.playerSymbol = playerSymbol;
		world = new World();
		playerColumn = 8;
		playerRow = 1;
		enemyColumn = 2;
		enemyRow = 15;
		enemyColumn2 = 17;
		enemyRow2 = 15;
}
	//printing the gameboard showing the players location, enemies location, and items locations
	public Hashtable<Integer, Integer[]>  printBoard(){
		//System.out.println("About to print the board!");
		int number = random.nextInt(4) + 3;
		int count = 1;
		if(!alreadyExecuted) {
			for(int i = 0; i < number; i++){
				value.add(random.nextInt(17) + 2);				
				value2.add(random.nextInt(17) + 2);
				if(world.getCurrentBoard(currentBoard)[value.get(i)][value2.get(i)] == ' ');
				world.getCurrentBoard(currentBoard)[value.get(i)][value2.get(i)] = 'I';
				placement = new Integer[]{value.get(i), value2.get(i)};
				location.put(count, placement);
				count++;

			}
			alreadyExecuted = true;
			return location;	
		}
		world.getCurrentBoard(currentBoard)[dungeonPlayer.getRow()][dungeonPlayer.getColumn()] = playerSymbol;
		for (int i = 0; i < 20; i++){
			for (int j = 0; j < 20; j++){
				if (world.getCurrentBoard(currentBoard)[i][j] == ' '){
					System.out.print('*');
				} else {
					System.out.print(world.getCurrentBoard(currentBoard)[i][j]);
				}
			}
			System.out.println();
		}
		return location;
		//System.out.println("Done printing board!");
	}

		//keeping track of where the user is
	public Integer[] playerLocation(){
		Integer[] position = new Integer[]{playerRow, playerColumn};
		return position;
	}
	//making the item dissapear after you pick it up, if you dont the item will reappear and a new item will be created
	public void makeFalse(){
		onItem = false;
		itemExistence = false;
	}
	public Player getDungeonPlayer(){
		return this.dungeonPlayer;
	}
	
	public int getCurrentBoardNum(){
		return this.currentBoard;
	
	}

	public World getWorld(){
		return this.world;
	}
	public Player getPlayer(){
		return this.dungeonPlayer;
	}

	


}

