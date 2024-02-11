package main;
import java.util.Scanner;


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
		
		Heroes player = new Heroes(n, classEntry);
		
		System.out.println("Welcome " + player.getName() + "!");
		System.out.println("You have chosen to be a " + player.getClassName() + "?");
		System.out.println("Calculating new stats based on your class...");
		System.out.println();
		player.setStats();
		
		System.out.println("Health: " + player.getHealth());
		System.out.println("Mana: " + player.getMana());
		System.out.println("Attack: " + player.getAttack());
		System.out.println("Defense: " + player.getDefense());
		System.out.println("Speed: " + player.getSpeed());
		

		
		

	}

}
