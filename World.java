import java.util.ArrayList;


public class World{

	private ArrayList<GameBoard> world = new ArrayList<GameBoard>();


	public World (){
		GameBoard gameBoard0 = new GameBoard(0);
		world.add(gameBoard0);

		GameBoard gameBoard1 = new GameBoard(1);
		world.add(gameBoard1);

		GameBoard gameBoard2 = new GameBoard(2);
		world.add(gameBoard2);

	}
	public char[][] getCurrentBoard(int num){
		if (num == 1){
			return world.get(1).getGameBoard();
		}
		else if (num == 0){
			return world.get(0).getGameBoard();
		}
		else{
			return world.get(2).getGameBoard();
		}
	}
	public void setNewBoard(int num, char [][] newBoard){

		GameBoard newGameBoard = new GameBoard(newBoard);
		if (num == 1){
			world.set(1, newGameBoard);
		}
		else if (num == 0){
			world.set(0, newGameBoard);
		}
		else{
			world.set(2, newGameBoard);
		}
	}

	
}