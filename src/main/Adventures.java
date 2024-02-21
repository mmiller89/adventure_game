package main;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Adventures {

    public static void adventureOne(Heroes player) throws InterruptedException {
         //The adventure of the shifting sands, level one.
        Enemy desertWarrior = new Enemy("Desert Warrior", 20, 10, 3, 3, 4,
                "Organic", "Armored", "None", "None", "Easy", 10, 5);

        boolean liedAboutName;


        System.out.println("You approach a desolate desert town, the sands bellowing at your back.\n");
        TimeUnit.SECONDS.sleep(3);

        System.out.println("The locals eye you warily as you approach the nearest inn. You've traveled for days. Your lips are cracked, your stomach gurgles, and you desperately need sleep.\n");
        System.out.println("The door creaks open, a maroon paint staining your fingers, and inside you find the innkeeper tiding her desk. The room is dimly lit, and no other patrons can be seen.\n");
        TimeUnit.SECONDS.sleep(5);

        System.out.println("--Delfaa--\nGreetings traveler. You'll find little comfort here, as our town has been plagued by a curse.");
        System.out.println("Tell me your name... (type it below)");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        if (name.equalsIgnoreCase(player.getPlayerName())){
            System.out.println("--Delfaa--\n" + name + " is it?");
            liedAboutName = false;
        } else {
            System.out.println("--Delfaa--\nWe both know that's not true. Your eyes betray your lips, traveler. No matter, I suppose.");
            liedAboutName = true;
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("As I was saying, our town is cursed. A man in pale white robes visited us not a fortnite ago.\nHis gaze was that of a demon, I'm sure of it. Not long after, the people started to crave manflesh.");

        System.out.println("\nAs the words leave Delfaa's lips, the door that brought you here slams open. A burly man approaches, a stark smell wafting toward you. He's been drinking.\n");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("--Desert Warrior--\nWhose this outsider Delfaa? We can't suffer any more of these foreigners...\n");
        System.out.println("Delfaa glances in your direction, then back at the man.\n");
        TimeUnit.SECONDS.sleep(2);
        String battleOrFlee = didYouLie(liedAboutName);
        if (battleOrFlee.equals("battle")){
            GameManager.battle(player, desertWarrior, 0);
        }


    }

    public static String didYouLie(boolean lied){
        if (lied){
            System.out.println("--Delfaa--\nI agree. He would lie to me about his very name. Kill him quickly!\n");
            System.out.println("Delfaa walks behind the inn, grabs a long scimitar. She approaches, sliding the blade to the warrior with her foot.\n");
            return "battle";
        } else {
            System.out.println("--Delfaa--\nDon't be brash, Hector. I don't sense ill will from this one.\n");
            System.out.println("--Hector--\nIt took but one of them to put a spell on us all. I won't have it! Get out, harbinger of doom!\n");
            System.out.println("Will you \"Fight\" or \"Flee\"? (type it below)");
            Scanner scn = new Scanner(System.in);
            String choice = scn.nextLine();
            if (choice.equalsIgnoreCase("fight")){
                return "battle";
            } else {
                return "flee";
            }

        }
    }
}
