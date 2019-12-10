import java.util.ArrayList;
import java.util.Random;

/**The secret locations class is only responsible for keeping an ArrayList of the Hole locations. And if the user digs in one of those locations, then they will exit the Dungeon.
 * @author Tyler Viacara
 */
public class SecretLocations{
	ArrayList<Hole> holeList = new ArrayList<Hole>();
	Random random = new Random();

	/**Constructor that takes in the current Dungeon to set the hole Locations
	 * @param holeDungeon The initial Dungeon just for the purposes of setting the hole locations
	 */
	SecretLocations(Dungeon holeDungeon){
		Hole newHole1 = new Hole(1,1,1);
		Hole newHole2 = new Hole(1,18,1);
		holeList.add(newHole1);
		holeList.add(newHole2);
		Hole newHole;
		//adding the inital holes to the gameBoard
		for (int z = 0; z < 3; z++){
			for (int y = 0; y < 5; y++){
				boolean validHoleLocation = false;
				while(validHoleLocation == false){
					int holeRow = random.nextInt((15) + 1);
					int holeColumn = random.nextInt((15) +1);
					char[][] currentHoleBoard = holeDungeon.getWorld().getCurrentBoard(z);
					if(currentHoleBoard[holeRow][holeColumn] == ' '){
						newHole = new Hole(holeRow, holeColumn, z);
						validHoleLocation = true;
					}
					else {
						validHoleLocation = false;
					}
				}
			}
		}

	}

	/**Method that returns true if the players location matches one of the hole locations, false otherwise
	 * @param holePlayer The current player to get their current location and what board they are on
	 */
	public boolean secrettunnel(Player holePlayer){
		for (int i = 0; i < holeList.size(); i++)
			if (holeList.get(i).getBoard() == holePlayer.getCurrentPlayerBoard()){
				if((holeList.get(i).getRow() == holePlayer.getRow()) && (holeList.get(i).getColumn() == holePlayer.getColumn())){
					return true;
				}
			}
	return false;
	}
}
