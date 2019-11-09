//import
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.InputMismatchException;
public class Main2{

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		System.out.print("What is your name: ");
		String signature = input.nextLine();
		char playerSymbol = ' ';

		boolean validName = false;
		while(validName == false){
			try{
				playerSymbol = signature.charAt(0);
				validName = true;
			}
			catch (InputMismatchException e){
				System.out.println("You did not enter a valid name.");
				System.out.println("Please enter your name again");
				signature = input.nextLine();
			}
		}

		//plot of the game
		System.out.println("You have accidentally stumbled into a cave, and it locks shut behind you.");
		System.out.println("You must defeat all the enemies to free yourself from the dungeon.");
		System.out.println("Your character is marked by the the first letter of your name on the map, starting in the top center.");
	       	System.out.println("While items are marked as 'I' and the enemies are marked with 'E'");
		System.out.println();

		//creating new gameboard
		//we will make a gameboard class for the next part of the project

		//seting up the game
		boolean keepPlaying = true;
		Player player = new Player(signature, 10);
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
		System.out.println("Q : quit out of the game");


		//Enumeration<Integer> number;
		//System.out.println(location);
		//
		//loop to keep playing until user quits, player dies, or player wins
		//allows the user to move, print the commands again, equip a weapon or armor, drop an item, or quit
		while(keepPlaying == true){
			location = gameDungeon.printBoard();
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
				System.out.println("P : Print the commands of the game again");
				System.out.println("Q : quit out of the game");

				System.out.println();
				keepPlaying = true;
			}
			else if (userLetter == 'O'){
				gameDungeon.dungeonPlayer.getInventory().equipWeapon();
			}
			else if (userLetter == 'M'){
				gameDungeon.dungeonPlayer.getInventory().equipArmor();
			}
			else if (userLetter == 'T'){
				gameDungeon.dungeonPlayer.getInventory().drop();
			}
			else if ((userLetter == 'W') || (userLetter == 'A') || (userLetter == 'S') || (userLetter == 'D')){
				String str = "";
				String str2 = "";
				if (gameDungeon.movePlayer(userLetter) == true){
					for(int i = 1; i < size + 1; i++){
						for (int place : location.get(i)){
							str = str + place;
						}
						for (int place2 : gameDungeon.playerLocation()){
							str2 = str2 + place2;
						}
						if(str.equals(str2)){
							if(player.itemQuestion(groundItems.get(i)) == true){
								gameDungeon.makeFalse();
								keepPlaying = true;
							} else {
								keepPlaying = true;
							}
						}
						str = "";
						str2 = "";
					}
				}
			}
			else{
				System.out.println("Your command was not recognized, enter 'P' to print the list of commands again.");
				keepPlaying = true;
			}

		}

	}	

}
