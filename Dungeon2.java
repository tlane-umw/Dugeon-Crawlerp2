import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
class Dungeon2{
	Hashtable<Integer, Integer[]> location = new Hashtable<Integer, Integer[]>();
	private boolean alreadyExecuted = false;
	ArrayList<Integer> value = new ArrayList<Integer>();
	ArrayList<Integer> value2 = new ArrayList<Integer>();
	Enemy villian = EnemyGenerator.generate();
	ArrayList<Integer[]> enemy = new ArrayList<Integer[]>();
	Integer[] placement = new Integer[]{0, 0};
	char[][] dungeonGameBoard;
	Player dungeonPlayer;
	char playerSymbol;
	Random random = new Random();
	Dungeon2(Player dungeonPlayer, char playerSymbol){
                this.dungeonGameBoard = gameBoard;
		this.dungeonPlayer = dungeonPlayer;
		this.playerSymbol = playerSymbol;
	}
	public Hashtable<Integer, Integer[]>  printBoard(){
                //System.out.println("About to print the board!");
		int count = 1;
		int number = random.nextInt(4) + 3;
		if(!alreadyExecuted) {
			enemy = villian.getInitialLocation();
			for(int i = 0; i < enemy.size(); i++){
				Integer[] foe = enemy.get(i);
				this.dungeonGameBoard[foe[0]][foe[1]] = 'E';
			}			
                        for(int i = 0; i < number; i++){
                                value.add(random.nextInt(17) + 2);
                                value2.add(random.nextInt(17) + 2);
                                if(this.dungeonGameBoard[value.get(i)][value2.get(i)] == ' '){
                                        this.dungeonGameBoard[value.get(i)][value2.get(i)] = 'I';
                                        placement = new Integer[]{value.get(i), value2.get(i)};
                                        location.put(count, placement);
                                        count++;
                                }
                        }
                        alreadyExecuted = true;
                        return location;
                }
                for (int i = 0; i < 20; i++){
                        for (int j = 0; j < 20; j++){
                                if (this.dungeonGameBoard[i][j] == ' '){
                                        System.out.print('*');
                                } else {
                                        System.out.print(this.dungeonGameBoard[i][j]);
                                }
			}
                        System.out.println();
                }
		return location;
                //System.out.println("Done printing board!");
	}
	public boolean turn(char userMove){
		return false;
	}
	public void makeFalse(){
		int reddit = 3;
	}
	public Integer[] playerLocation(){
		Integer[] position = new Integer []{2,2};
		return position;
	}
}


