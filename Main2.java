//import
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.InputMismatchException;
public class Main2{

	public static void main(String[] args) throws FileNotFoundException, IOException {


		Scanner input = new Scanner(System.in);
		System.out.print("What is your name: ");
		String signature = input.nextLine();
		char playerSymbol = '@';



		//plot of the game
		System.out.println("You have accidentally stumbled into a cave, and it locks shut behind you.");
		System.out.println("You must defeat all the enemies (6 in total) to free yourself from the dungeon.");
		System.out.println("Your character is marked by the '@' symbol on the map, starting in the top center.");
		System.out.println("While items are marked as 'I' and the enemies are marked with 'E'");
		System.out.println("Navigate through the different rooms marked with 'D', to pick up items and defeat all the enemies!");
		System.out.println("Good luck!");
		try{
			Thread.sleep(10000);
		}
		catch(InterruptedException newException){
			System.out.println("Interrupted!");
		}
		System.out.println();

		//seting up the game
		boolean keepPlaying = true;
		Player player = new Player(signature, 100,  playerSymbol);
		Dungeon gameDungeon = new Dungeon(player, playerSymbol);
		Hashtable<Integer, Integer[]> location = new Hashtable<Integer, Integer[]>();
		location = gameDungeon.printBoard();
		int size = location.size();
		Hashtable<Integer, Item> groundItems = player.itemSpawn(size);

		//commands of the game
		System.out.println("Here are the commands for the game. Select one of the characters then hit 'enter'");
		System.out.println("W : move your character up on the board");
		System.out.println("A : move your character to the left on the board");
		System.out.println("S : move your character down on the board");
		System.out.println("D : move your character to the right on the board");
		System.out.println("M : equip a current armor in your inventory to your player");
		System.out.println("O : equip a current weapon in your inventory to your player");
		System.out.println("T : to drop an item from your inventory");
		System.out.println("P : Print the commands of the game again");
		System.out.println("V : Save the game");
		System.out.println("R : Restore your previously saved gamed");
		System.out.println("Q : quit out of the game");
		try{
			Thread.sleep(10000);
		}
		catch (InterruptedException printCommands){
			System.out.println("Interrupted!");
		}


		//Enumeration<Integer> number;
		//System.out.println(location);
		//
		//loop to keep playing until user quits, player dies, or player wins
		//allows the user to move, print the commands again, equip a weapon or armor, drop an item, or quit
		while(keepPlaying == true){
			//location = gameDungeon.printBoard();
			gameDungeon.printBoard();
			System.out.println("P : Print the commands of the game again");

			String userOption = input.nextLine();
			char userLetter = userOption.charAt(0);

			if (userLetter == 'Q'){
				System.out.println("Are you sure you want to quit?");
				System.out.println("Please enter 'Y' if you want to quit, or 'N' if you want to go back to the menu");
				String userContinue = input.nextLine();
				while ((!(userContinue.equals("Y"))) && (!(userContinue.equals("N")))) {
					System.out.println("You did not enter a 'Y' or 'N'.");
					System.out.println("Enter 'Y' to quit or 'N' to exit to the menu");
					userContinue = input.nextLine();
				}
				if (userContinue.equals("Y")){
					keepPlaying = false;
					break;
				}
				else{
					keepPlaying = true;
				}
			}
			else if (userLetter == 'P'){
				System.out.println("The board and commands will now be printed again.");
				System.out.println("W : move your character up on the board");
				System.out.println("A : move your character to the left on the board");
				System.out.println("S : move your character down on the board");
				System.out.println("D : move your character to the right on the board");
				System.out.println("M : equip a current armor in your inventory to your player");
				System.out.println("O : equip a current weapon in your inventory to your player");
				System.out.println("T : to drop an item from your inventory");
				System.out.println("P : Print the commands of the game again");
				System.out.println("V : Save the game");
				System.out.println("R : Resote your previously saved game");
				System.out.println("Q : quit out of the game");

				System.out.println();
				keepPlaying = true;
			}
			else if (userLetter == 'O'){
				gameDungeon.dungeonPlayer.getInventory().equipWeapon();
			}
			else if (userLetter == 'V'){
				System.out.println("Are you sure you want to save your game?");
				System.out.println("Any previous saved files will be overwritten.");
				System.out.println("Enter 'Y' to save, or 'N' to keep playing.");
				String userSave = input.nextLine();
				while ((!(userSave.equals("Y"))) && (!(userSave.equals("N")))){
					System.out.println("You did not enter a 'Y' or 'N'.");
					System.out.println("Enter 'Y' to save your game, or 'N' to keep playing.");
					userSave = input.nextLine();
				}
				if (userSave.equals("Y")){
					System.out.println("Hang tight while we save your game....");
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException save1){
						System.out.println("Interrupted!");
					}
					gameDungeon.save();
					System.out.println(".... Success! We saved your game. Be sure to save again if you keep playing!");
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException save2){
						System.out.println("Interrupted!");
					}
				}
				else{

				}

			}
			else if (userLetter == 'R'){
				gameDungeon.restore();
				if (gameDungeon.doesSaveFileExist() == true){
					System.out.println("Hang tight while we restore your game....");
					System.out.println();
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException restore1){
						System.out.println("Interrupted!");
					}
					int savedPlayerBoard = gameDungeon.dungeonPlayer.getCurrentPlayerBoard();
					gameDungeon.setCurrentBoardNum(savedPlayerBoard);
					//System.out.println("The players column after restoring is " + gameDungeon.dungeonPlayer.getColumn());
					//System.out.println("The players row after restoring is " + gameDungeon.dungeonPlayer.getRow());
					System.out.println(".... Success! We were able to load your game.");
					System.out.println("Get ready to jump back into your game! Good luck!");
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException restore2){
						System.out.println("Interrupted!");
					}
					gameDungeon.printBoard();
				}
			}
			else if (userLetter == 'M'){
				gameDungeon.dungeonPlayer.getInventory().equipArmor();
			}
			else if (userLetter == 'T'){
				gameDungeon.dungeonPlayer.getInventory().drop();
			}
			else if ((userLetter == 'W') || (userLetter == 'A') || (userLetter == 'S') || (userLetter == 'D')){
				//System.out.println("The players column = " + gameDungeon.getPlayer().getColumn());
				//System.out.println();
				//System.out.println("The players row = " + gameDungeon.getPlayer().getRow());
				//System.out.println();

				//System.out.println(playerSymbol);

				//getting the current board the player is on, moving the player on that board, then setting the world board to the updated board after the player moved
				System.out.println();

				int currentBoardNum = gameDungeon.getCurrentBoardNum();

				char[][] oldBoard = gameDungeon.getWorld().getCurrentBoard(currentBoardNum);

				char[][] newBoard = gameDungeon.dungeonPlayer.move(userLetter, oldBoard);

				gameDungeon.getWorld().setNewBoard(currentBoardNum, newBoard);

				gameDungeon.setCurrentBoardNum(gameDungeon.getPlayer().getCurrentPlayerBoard());

				//System.out.println("The current gameboard number is - " + gameDungeon.getCurrentBoardNum());


				String str = "";

				String str2 = "";
				if (gameDungeon.dungeonPlayer.getItemExistence() == true){
					for(int i = 1; i < size + 1; i++){
						for (int place : location.get(i)){
							str = str + " " + place;
						}
						System.out.println(str);
						for (int place2 : gameDungeon.dungeonPlayer.playerLocation()){
							str2 = str2 + " " + place2;
						}
						System.out.println(str2);
						if(str.equals(str2)){
							if(player.itemQuestion(groundItems.get(i)) == true){
								gameDungeon.dungeonPlayer.makeFalse();
								keepPlaying = true;
							} else {
								keepPlaying = true;
							}
						}
						str = "";
						str2 = "";
					}
				}
				gameDungeon.moveEnemies();
			}
			else{
				System.out.println("Your command was not recognized, enter 'P' to print the list of commands again.");
				keepPlaying = true;
			}
		}

	}
}





