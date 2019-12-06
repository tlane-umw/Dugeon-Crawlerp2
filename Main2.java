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

	public static Dungeon gameDungeon;
	//public static boolean alreadyDroppedShovel = false;
	public static int numTimesDug = 0;


	public static void main(String[] args) throws FileNotFoundException, IOException {

		boolean alreadyDroppedShovel = false;
		Scanner input = new Scanner(System.in);
		System.out.print("What is your name: ");
		String signature = input.nextLine();
		char playerSymbol = '@';
		System.out.println("Hello, " + signature + ", we hope you are doing well");
		System.out.println("Get ready to dive into the game and be sure to note the instructions...");
		System.out.println();
		System.out.println();
		System.out.println();
		try{
			Thread.sleep(3000);
		}
		catch(InterruptedException goodDaySir){
			System.out.println("Interrupted");
		}

		//plot of the game
		System.out.println("You have accidentally stumbled into a cave, and it locks shut behind you.");
		System.out.println("You must defeat all the enemies (6 in total) to free yourself from the dungeon.");
		System.out.println("Your character is marked by the '@' symbol on the map, starting in the top center.");
		System.out.println("While items are marked as 'I' and the enemies are marked with 'E'");
		System.out.println("Navigate through the different rooms marked with 'D', to pick up items and defeat all the enemies!");
		System.out.println("Good luck!");
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException newException){
			System.out.println("Interrupted!");
		}
		System.out.println();

		//seting up the game
		boolean keepPlaying = true;
		Player player = new Player(signature, 100,  playerSymbol);
		gameDungeon = new Dungeon(player, playerSymbol);

		//commands of the game
		System.out.println("Here are the commands for the game. Select one of the characters then hit 'enter'");
		System.out.println("W : move your character up on the board");
		System.out.println("A : move your character to the left on the board");
		System.out.println("S : move your character down on the board");
		System.out.println("D : move your character to the right on the board");
		System.out.println("M : equip a current armor in your inventory to your player");
		System.out.println("O : equip a current weapon in your inventory to your player");
		System.out.println("T : to drop an item from your inventory");
		System.out.println("L : list all the items in your inventory");
		System.out.println("I : dig in your current location with your shovel");
		System.out.println("N : drink a potion from your inventory to boost your players health");
		System.out.println("P : Print the commands of the game again");
		System.out.println("V : Save the game");
		System.out.println("R : Restore your previously saved gamed");
		System.out.println("Q : quit out of the game");
		try{
			Thread.sleep(5000);
		}
		catch (InterruptedException printCommands){
			System.out.println("Interrupted!");
		}

		//loop to keep playing until user quits, player dies, or player wins
		//allows the user to move, print the commands again, equip a weapon or armor, drop an item, or quit
		while(keepPlaying == true){
			try{

				gameDungeon.printBoard();
				System.out.println("P : Print the commands of the game again");
				char userLetter = ' ';
				try{
					String userOption = input.nextLine();
					userLetter = userOption.charAt(0);
				}
				catch(StringIndexOutOfBoundsException nothingEntered){
					keepPlaying = true;
				}

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
					System.out.println("L : list all the items in your inventory");
					System.out.println("N : drink a potion from your inventory to boost your players health");
					System.out.println("I : dig in your current location with your shovel");
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
							Thread.sleep(2500);
						}
						catch(InterruptedException save1){
							System.out.println("Interrupted!");
						}
						SaveRestore saveRestore = new SaveRestore(gameDungeon);
						saveRestore.save(gameDungeon);
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
					SaveRestore saveRestore = new SaveRestore(gameDungeon);
					setGameDungeon(saveRestore.restore());
					if (saveRestore.doesSaveFileExist() == true){
						System.out.println("Hang tight while we restore your game....");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException restore1){
							System.out.println("Interrupted!");
						}
						int savedPlayerBoard = gameDungeon.dungeonPlayer.getCurrentPlayerBoard();
						gameDungeon.setCurrentBoardNum(savedPlayerBoard);
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
				else if (userLetter == 'L'){
					gameDungeon.dungeonPlayer.getInventory().print();
				}
				else if (userLetter == 'N'){
					int currentPotion = gameDungeon.dungeonPlayer.getInventory().usePotion();
					if(currentPotion > 0){
						int currentPlayerHealth = gameDungeon.dungeonPlayer.getHealth();
						System.out.println("Before you drank your potion you had a health of " + currentPlayerHealth);
						int newPlayerHealth = currentPlayerHealth + currentPotion;
						System.out.println("Your new health is " + newPlayerHealth);
						gameDungeon.dungeonPlayer.setHealth(newPlayerHealth);
					}
				}
				else if (userLetter == 'I'){
					int currentNumDigs = numTimesDug;
					int numDigsLeft = 9 - numTimesDug;
					if (currentNumDigs == 9){
						System.out.println("You cannot dig anymore! You broke your shovel and dropped it!");
						try{
							Thread.sleep(2500);
						}
						catch (InterruptedException noMoreDigs){
						}
						gameDungeon.dungeonPlayer.getInventory().drop(1);
					}
					else{
						System.out.println("Be sure to use your digs, carefully!");
						System.out.println("This is your " + (currentNumDigs + 1) + " time digging, you can only dig " + (numDigsLeft - 1) + " more times!");
						try{
							Thread.sleep(2500);
						}
						catch (InterruptedException numDigsLeftUhOh){

						}

						Item dugUpItem = ItemGenerator.generate();
						System.out.println("You have dug up a " + dugUpItem.getName() + ".");
						System.out.println("Would you like to pick it up? Enter 'Y' for yes and 'N' for no.");
						boolean validDugUp = false;
						while(validDugUp == false){
							try{
								String dugUpItemYesNo = input.nextLine();
								char dugUpChar = dugUpItemYesNo.charAt(0);
								if(dugUpChar == 'N'){
									System.out.println("You decided to bury the item back in the ground.");
									try {
										Thread.sleep(2500);
									}
									catch(InterruptedException noPickUpDug){

									}
									validDugUp = true;
								}
								else if (dugUpChar == 'Y'){
									boolean couldUserAdd = gameDungeon.dungeonPlayer.getInventory().add(dugUpItem);
									if (couldUserAdd == true){
										System.out.println("Success! You added this item to your inventory!");
										break;
									}
									validDugUp = true;
								}
								else{
									System.out.println("Not a valid input.");
									System.out.println("You have found a " + dugUpItem.getName() + ".");
									System.out.println("Please enter a 'Y' for yes or 'N' for no.");
									dugUpItemYesNo = input.nextLine();
									validDugUp = false;
								}
							}

							catch(StringIndexOutOfBoundsException noDugOption){
								System.out.println("Your command was not recognied. Please enter 'Y' or 'N'");
								validDugUp = false;
							}
						}
						numTimesDug++;
						setNumTimesDug(numTimesDug);
						gameDungeon.dungeonPlayer.setNumDigs(numTimesDug);

					}
				}


				else if ((userLetter == 'W') || (userLetter == 'A') || (userLetter == 'S') || (userLetter == 'D')){

					System.out.println();

					int currentBoardNum = gameDungeon.getCurrentBoardNum();

					char[][] oldBoard = gameDungeon.getWorld().getCurrentBoard(currentBoardNum);

					char[][] newBoard = gameDungeon.dungeonPlayer.move(userLetter, oldBoard, gameDungeon);

					gameDungeon.getWorld().setNewBoard(currentBoardNum, newBoard);

					gameDungeon.setCurrentBoardNum(gameDungeon.getPlayer().getCurrentPlayerBoard());

					gameDungeon.moveEnemies();
				}
				else{
					System.out.println("Your command was not recognized, enter 'P' to print the list of commands again.");
					keepPlaying = true;
				}
			}
			catch(InputMismatchException wrong){
				System.out.println("Your command was not recognized, enter 'P' to print the list of commands again.");
				keepPlaying = true;
			}
		}

	}
	public static void setGameDungeon(Dungeon newDungeon){
		gameDungeon = newDungeon;
	}
	public static Dungeon getGameDungeon(){
		return gameDungeon;
	}
	public static void setNumTimesDug(int newNumTimesDug){
		numTimesDug = newNumTimesDug;
	}
}
