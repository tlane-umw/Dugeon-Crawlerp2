import java.util.ArrayList;

/**The world class handles all three of the gameboards in an arraylist of gameboard, also handles setting and getting the correct gameboards.
 * @author Tyler Viacara
 */
public class World{

	private ArrayList<GameBoard> world = new ArrayList<GameBoard>();

	/**Default constructor that adds all three of the desired gameboards to the arraylist of gameboards
	 */
	public World (){
		GameBoard gameBoard0 = new GameBoard(0);
		world.add(gameBoard0);

		GameBoard gameBoard1 = new GameBoard(1);
		world.add(gameBoard1);

		GameBoard gameBoard2 = new GameBoard(2);
		world.add(gameBoard2);

	}

	/**Returns the 2D array based on the number that is passed in
	 * @param num The number of the gameboard you want to get based on the number that is passed in
	 * @return The 2D array for the corresponding game board in the world arraylist
	 */
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

	/**Method that sets a new gameboard based on the number passed in and the new 2D gameboard itself
	 * @param num The number of the board you want to overwrite as an integer
	 * @param newBoard The new 2D array that you are setting after the player or enemy moves
	 */
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
