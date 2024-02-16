package main;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Game {
	
	//Game runs the main game and is the starter point of the code.
	
	public static void main(String[] args) throws InterruptedException {
		
		boolean looping = true;
		boolean finalPlayerConfirmLoop = true;

		String classEntry = null;
		String nameChoice = null;

		int intChoice = 0;

		Heroes player = null;

		Scanner scn = new Scanner(System.in);


		while (looping) {
			System.out.println("What is your heroes name? Write it below...");
			nameChoice = scn.nextLine();
			if (nameChoice.isEmpty()){
				System.out.println("That doesn't work, try again.\n");
			} else {
				System.out.println(nameChoice + " right?");
				System.out.println("1 - Yes\n2 - No");
				try {
					intChoice = scn.nextInt();
				} catch (Exception e){
					System.out.println("That's not a number, try again.\n");
				}
				if (intChoice == 1) {
					looping = false;
				}
				scn.nextLine();
			}

		}

		looping = true;
		intChoice = 0;

		while (finalPlayerConfirmLoop){
			while (looping) {
				System.out.println("Pick your class: Warrior, Mage, or Thief?");
				classEntry = scn.nextLine().trim().toLowerCase();
				if (classEntry.equals("warrior") || classEntry.equals("mage") || classEntry.equals("thief")) {
					looping = false;
				}
				else {
					System.out.println("You did not enter a correct class name. Try again.");
				}
			}
			classEntry = classEntry.substring(0,1).toUpperCase() + classEntry.substring(1);
			HeroClass classChoice = new HeroClass(classEntry);
			player = new Heroes(nameChoice,classChoice);

			System.out.println("Welcome " + player.getPlayerName() + "!");
			System.out.println("You have chosen to be a " + player.heroClass.getClassName() + "?");
			System.out.println("Calculating stats...\n");
			TimeUnit.SECONDS.sleep(3);

			player.setStats();
			player.listStats();

			System.out.println("\nLoading skills and abilities...\n");
			TimeUnit.SECONDS.sleep(3);

			System.out.println("Ability List: ");
			player.listAbilities();

			System.out.println("Are you happy with this?");
			System.out.println("1 - Yes\n2 - No");
			try {
				intChoice = scn.nextInt();
			} catch (Exception e){
				System.out.println("That's not a number, try again.\n");
			}
			if (intChoice == 1) {
				finalPlayerConfirmLoop = false;
			} else {
				looping = true;
			}
			scn.nextLine();

		}

		looping = true;
		intChoice = 0;

		while (looping){
			System.out.println("\n*****\n1 - Arena\n2 - Shop\n3 - Travel\n4 - Exit (Your hero will not be saved! This is a planned update TBD.\n*****");
			try {
				intChoice = scn.nextInt();
			}
			catch (Exception e) {
				System.out.println("That's not a number, try again.\n");
			}
			if (intChoice == 1) {
				Enemy goblin = new Enemy("Goblin", 30, 10, 5, 2, 3, "Organic");
				GameManager.battle(player, goblin);
			}
			else if (intChoice == 2) {
				GameManager.shop(player);
			}
			else if (intChoice == 3) {
				GameManager.adventure(player);
			}
			else if (intChoice == 4){
				looping = false;
			}
			scn.nextLine();
		}




		//Enemy goblin = new Enemy("Goblin", 30, 10, 5, 2, 3);

		//GameManager.battleBegin(player, goblin);


		

		
		

	}

	public void resetChoicesInitizliation(){}


}
