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

		Set<Enemy> enemy_list_arena = new LinkedHashSet<>();
		List<Enemy> enemy_array_arena = makeArenaEnemyArray(enemy_list_arena);


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
				System.out.println("Pick your class: Warrior, Mage, or Thief? Write it below...");
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
			player = new Heroes(nameChoice,classChoice, "None", "None");

			System.out.println("Welcome " + player.getPlayerName() + "!");
			System.out.println("You have chosen to be a " + player.heroClass.getClassName() + "?");
			System.out.println("Calculating stats...\n");
			TimeUnit.SECONDS.sleep(2);

			player.setStats();
			player.listStats();

			System.out.println("\nLoading skills and abilities...\n");
			TimeUnit.SECONDS.sleep(2);

			System.out.println("Ability List: ");
			player.listAbilities();

			System.out.println("Are you happy with this class?");
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
			System.out.println("\n*****\n1 - Arena\n2 - Shop\n3 - Travel\n4 - Adventure Guide\n5 - Exit (Your hero will not be saved! This is a planned update TBD.)\n*****");
			try {
				intChoice = scn.nextInt();
			}
			catch (Exception e) {
				System.out.println("That's not a number, try again.\n");
			}

			if (intChoice == 1) {

				System.out.println("You enter the arena, the crowd cheering with a glorious fever. You unsheathe your weapon, ready to battle.");

				intChoice = 0;
				boolean innerLoop = true;

				while(innerLoop) {
					System.out.println("Who will you fight?");
					int iterator = 1;

					for (Enemy e : enemy_array_arena) {
						System.out.println(iterator + " - " + e.getName() + " | " + "Difficulty: " + e.getDifficulty() + " | " + "Type: " +
								e.getType());
						iterator++;
					}

					try {
						intChoice = scn.nextInt();
					}
					catch (Exception e) {
						System.out.println("That's not a number, try again.\n");
					}
					GameManager.arena(player, enemy_array_arena.get(intChoice - 1), 0);
					innerLoop = false;

				}
			}
			else if (intChoice == 2) {
				GameManager.shop(player);
			}
			else if (intChoice == 3) {
				GameManager.adventure(player);
			}
			else if (intChoice == 4){
				GameManager.guide();
			}
			else if (intChoice == 5){
				looping = false;
			}
			else {
				System.out.println("Not a valid choice.");
			}
			scn.nextLine();
		}







		

		
		

	}

	public static List<Enemy> makeArenaEnemyArray(Set<Enemy> enemy_list){
		Enemy goblin = new Enemy("Goblin", 30, 10, 4, 2, 5, "Organic", "Unarmored", "None", "None", "Easy");
		Enemy waterElemental = new Enemy("Water Elemental", 50, 25, 6, 4, 5, "Elemental", "None", "None", "None", "Medium");
		Enemy demonMachine = new Enemy("Possessed Machine", 125, 35, 18, 12, 9, "Machine", "None", "None", "None", "Hard");
		enemy_list.add(goblin);
		enemy_list.add(waterElemental);
		enemy_list.add(demonMachine);
        return new ArrayList<>(enemy_list);
	}


}
