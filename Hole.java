/**The Hole class is the locations of the secret locations in the Dungeon. If the player digs in a location of a hole, they exit the Dungeon and beat the game.
 * @author Tyler Viacara
 */
public class Hole{
	private int row, column, board;

	/**Constructor that takes in the hole's row, column and board
	 * @@param row The row position of the hole as an integer
	 * @param column The column position of the hole as an integer
	 * @param board The board number the hole is on as an integer
	 */
	Hole(int row, int column, int board){
		this.row = row;
		this.column = column;
		this.board = board;
	}

	/**Method that returns the hole's row as an integer
	 * @return The hole's row position as an integer
	 */
	public int getRow(){
		return this.row;
	}

	/**Method that returns the hole's column as an integer
	 * @return The hole's column position as an integer
	 */
	public int getColumn(){
		return this.column;
	}

	/**Method that returns what board the hole is on as an integer
	 * @return The board number the hole is on as an integer
	 */
	public int getBoard(){
		return this.board;
	}

	/**Method that sets the hole's row position
	 * @param row The integer value that will be assigned to this hole
	 */
	public void setRow(int row){
		this.row = row;
	}

	/**Method that sets the hole's column position
	 * @param column The integer value that will be assigned to this hole
	 */
	public void setColumn(int column){
		this.column = column;
	}

	/**Method that sets what board the hole is on
	 * @param board The board number the hole is on as an integer
	 */
	public void setBoard (int board){
		this.board = board;
	}
}
