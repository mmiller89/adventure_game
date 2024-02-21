package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//Manages the different game states(battle, shops, traveling, etc.). As the game expands, this will
//become a parent class and the different game states will be their own classes/files.

public class GameManager {

    public static void guide(){
        Scanner scn = new Scanner(System.in);
        boolean looping = true;
        System.out.println("With a thud, you place the heavy book down in front of you.");
        while (looping) {
            System.out.println("What chapter will you read? Type 'close' to put the book away.");
            System.out.println("Attack | Abilities | Defending | Types | Close");
            String choice = scn.nextLine().trim().toLowerCase();
            switch (choice) {
                case "attack" -> {
                    System.out.println("Attack, which is Option 1 in combat, has a unique effects depending on your class.");
                    System.out.println("Warrior - 25% of the damage is restored as health.\nMage - Restores 20% of maximum MP.\nThief - 25% chance to grant Boon 'Evasion', granting a 50% chance to dodge attacks.");
                }
                case "abilities" ->
                        System.out.println("Abilities use your MP and have unique effects. Your turn will fail if you don't have enough MP to use them.");
                case "defending" ->
                        System.out.println("Defending reduces damage from the enemy's next attack by 75%. Useful to block a wind up attack!");
                case "types" ->
                        System.out.println("Organic - 25% extra damage from Fire\nMachine - 25% extra damage from Lightning\nElemental/Mystic - 25% extra damage from Ice\nUnarmored - 25% extra damage from Piercing\nArmored - 25% extra damage from Crushing\nBare-Armed - 25% extra damage from Slashing");
                case "close" -> {
                    System.out.println("You close the book, placing it back in your pack.");
                    looping = false;
                }
            }
            System.out.println();
        }




    }

    public static void adventure(Heroes player) throws InterruptedException {

        Adventures.adventureOne(player);
        System.out.println("\n**********\nA new adventure unlocks at level 4, 7, and 10!\n**********");
    }

    public static void shop (Heroes player){
        //Shop weapons
        List<Weapon> weapon_list = new ArrayList<>();

        Weapon oakStaff = new Weapon(1,0,2,"Oak Staff", 0, "OS");
        Weapon wizardsCane = new Weapon(3,0,5,"Wizard's Cane", 50, "WC");
        Weapon aurorStaff = new Weapon(10,0,20,"Auror's Grand Staff", 150, "AS");
        Weapon broadSword = new Weapon(2,-1,0,"Broadsword",0, "BS");
        Weapon platinumSword = new Weapon(7,-5,0,"Platinum Sword", 50, "PS");
        Weapon guardianBlade = new Weapon(16,-8,0,"Guardian Blade", 150, "GB");
        Weapon dagger = new Weapon(1,1,0,"Dagger",0, "DA");
        Weapon doublePincers = new Weapon(4,5,0,"Double Pincers", 50, "DP");
        Weapon silentAssassin = new Weapon(10,10,0,"Silent Assassin", 150, "SA");

        Collections.addAll(weapon_list, oakStaff, wizardsCane, aurorStaff, broadSword, platinumSword, guardianBlade, dagger, doublePincers, silentAssassin);




        boolean outerLoop = true;


        System.out.println("You turn the knob gently, the thud of your boots smothered by the sound of metal clinking.\n");
        System.out.println("The shopkeeper greets you warmly.\n");
        while (outerLoop){
            Random rand = new Random();
            int dialogue = rand.nextInt(3);
            String dialogueOne = "Ulysses: \"Welcome traveler. My name is Ulysses! Take a look at my wares!\"\n";
            String dialogueTwo = "Ulysses: \"An adventurer I see! Please, look around!\"\n";
            String dialogueThree = "Ulysses: \"Now, what can I get for you today?\"\n";
            System.out.println(dialogue == 0 ? dialogueOne : dialogue == 1 ? dialogueTwo : dialogueThree);
            System.out.println("Gold: " + player.getGold());
            System.out.println("Weapon: " + player.weapon.getWeaponName());
            System.out.println("Armor: " + player.armor.getArmorName() + "\n");

            List<Weapon> weapon_list_filtered = weapon_list.stream().filter(w -> !player.weapon.getWeaponName().equals(w.getWeaponName())).toList();

            for (Weapon w : weapon_list_filtered){
                System.out.println("==========");
                System.out.println(w.getWeaponName() + " | Attack: " + w.getAttackPower() + " | Speed: " + w.getSpeed() + " | Mana: " + w.getMana() + " | Cost: " + w.getCost() + " Gold" + " | Tag: " + w.getPurchaseTag());
            }

            boolean innerLoop = true;

            while (innerLoop){
                Scanner scn = new Scanner(System.in);
                System.out.println("\nEnter the tag to buy the item. Type Exit to leave the store.");
                String choice = scn.nextLine().trim().toUpperCase();

                if (choice.equals("EXIT")){
                    outerLoop = false;
                    break;
                }

                for (Weapon w : weapon_list_filtered){
                    if (choice.equals(w.getPurchaseTag())){
                        System.out.println("Confirm purchase of " + w.getWeaponName() + " with a cost of " + w.getCost() + " Gold?");
                        System.out.println("1 - Yes");
                        System.out.println("2 - No");

                        int yesOrNo = 0;
                        try {
                            yesOrNo = scn.nextInt();
                        }
                        catch (Exception e) {
                            System.out.println("That's not a number, try again.\n");
                        }

                        if (yesOrNo == 1){
                            if (player.getGold() >= w.getCost()){
                                System.out.println(player.getPlayerName() + " tossed aside " + player.weapon.getWeaponName() + " and equipped " + w.getWeaponName() + ".\n");
                                player.equipmentOn(w);
                                player.setGold(player.getGold() - w.getCost());
                                innerLoop = false;
                            } else {
                                System.out.println("\"Ulysses: You don't have the coin to buy that, friend.\"\n");
                                innerLoop = false;
                            }

                        }
                    }
                } scn.nextLine();
            }

        }





    }

    public static void battle(Heroes player, Enemy enemy, int currentTurn){
        //When enemies have abilities, create a separate list.
        List<Ability> ability_list = new ArrayList<>(player.heroClass.getAbilityList());
        List<Ability> enemy_ability_list = new ArrayList<>(enemy.getEnemyAbilityList());
        boolean battleActive = true;
        System.out.println("A " + enemy.getName() + " appears!");

        while(battleActive) {
            currentTurn++;
            //When enemies start having abilities, import two separate lists of abilities.
            String outcome = BattleTurnManager.takeTurn(player, enemy, ability_list, enemy_ability_list, currentTurn);
            if (outcome.equals("battle over")) {
                enemy.fullRestore();
                player.fullRestore();
                battleActive = false;
            }



        }
    }

    public static void heroStats(Heroes player){
        System.out.println("**********");
        System.out.println("Name: " + player.getPlayerName());
        System.out.println("Class: " + player.heroClass.getClassName());
        System.out.println("Level: " + player.getLevel() + " | Experience: " + player.getExperience() + " / " + player.getExpNext()  + " | Gold: " + player.getGold());
        System.out.println("Weapon: " + player.weapon.getWeaponName() + " | Armor: " + player.armor.getArmorName() + "\n");
        player.listStats();
        System.out.println();
        player.listAbilities();
        System.out.println("**********");

    }



}
