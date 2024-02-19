package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


//Manages the different game states(battle, shops, traveling, etc.). As the game expands, this will
//become a parent class and the different game states will be their own classes/files.

public class GameManager {

    public static void guide(){
        Scanner scn = new Scanner(System.in);
        boolean looping = true;
        System.out.println("With a thud, you place the heavy book down in front of you.");
        while (looping) {
            System.out.println("What chapter will you read? Type 'close' to put the book away.");
            System.out.println("Attack | Abilities | Defending | Types");
            String choice = scn.nextLine().trim().toLowerCase();
            switch (choice) {
                case "attack" -> {
                    System.out.println("Attack, which is Option 1 in combat, has a unique effects depending on your class");
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

    public static void adventure(Heroes player){
        System.out.println("Adventures coming soon!");
    }

    public static void shop (Heroes player){
        System.out.println("Shop coming soon!");
    }

    public static void arena(Heroes player, Enemy enemy, int currentTurn){
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
