package main;
import java.util.*;


public class Game {
	
	
	
	public static void main(String[] args) {
		
		boolean loopContinue = true;
		String classEntry = null;
		Scanner scn = new Scanner(System.in);
		
		
		
		System.out.println("What is your heroes name? Write it below...");
		String n = scn.nextLine();
		
		
		
		while (loopContinue) {
			System.out.println("Pick your class: Warrior, Mage, or Thief?");
			classEntry = scn.nextLine().trim().toLowerCase();
			if (classEntry.equals("warrior") || classEntry.equals("mage") || classEntry.equals("thief")) {
				loopContinue = false;
			}
			else {
				System.out.println("You did not enter a correct class name. Try again."); 
			}
		}
		classEntry = classEntry.substring(0,1).toUpperCase() + classEntry.substring(1);
		HeroClass classChoice = new HeroClass(classEntry);
		Heroes player = new Heroes(n,classChoice);
		
		System.out.println("Welcome " + player.getPlayerName() + "!");
		System.out.println("You have chosen to be a " + player.heroClass.getClassName() + "?");
		System.out.println("Calculating new stats based on your class...");
		System.out.println();
		player.setStats();
		player.listStats();

		System.out.println();
		System.out.println("Ability List: ");
		player.listAbilities();

		Enemy goblin = new Enemy("Goblin", 30, 10, 5, 2, 2);

		GameManager.battleBegin(player, goblin);


		

		
		

	}

}
