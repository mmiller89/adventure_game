package main;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BattleTurnManager{


    public static String takeTurn(Heroes player, Enemy enemy, List<Ability> ability_list){
        boolean playerDeath = false;
        boolean enemyDeath = false;
        if (player.getSpeed() >= enemy.getSpeed()){
            playerAttack(player, enemy, ability_list);
            enemyDeath = enemy.getHealth() <= 0;
            if (!enemyDeath){
                enemyAttack(player, enemy);
                playerDeath = player.getHealth() <= 0;
            }
        }
        else {
            enemyAttack(player, enemy);
            playerDeath = player.getHealth() <= 0;
            if (!playerDeath){
                playerAttack(player, enemy, ability_list);
                enemyDeath = enemy.getHealth() <= 0;
            }
        }

        if (enemyDeath){
            System.out.println();
            System.out.println("You have vanquished enemy " + enemy.getName() + "!");
            //Player is restored in arena fights.
            player.setHealth(player.getMaxHealth());
            player.setMana(player.getMaxMana());
            return "battle over";
        }
        if (playerDeath) {
            System.out.println();
            System.out.println("You have been defeated by enemy " + enemy.getName() + "!");
            //Player is restored in arena fights.
            player.setHealth(player.getMaxHealth());
            player.setMana(player.getMaxMana());
            return "battle over";
        }
        return "";
    }

    public static void playerAttack(Heroes player, Enemy enemy, List<Ability> ability_list) {
        int choice;
        System.out.println();
        System.out.println(player.getPlayerName() + " | " + "Health: " + player.getHealth() + " Mana: " + player.getMana());
        System.out.println(enemy.getName() + " | " + "Health: " + enemy.getHealth() + " Mana: " + enemy.getMana());
        System.out.println("What will you do? Type a number.");
        System.out.println("1 - Attack");
        int iterator = 2;
        for (Ability a : ability_list) {
            System.out.println(iterator + " - " + a.getAbilityName() + " | " + "Mana Cost: " + a.getManaCost());
            iterator++;
        }

        Scanner scn = new Scanner(System.in);
        int damage = 0;
        String chosenAbility = null;
        boolean attackSuccess = true;

        try {
            choice = scn.nextInt();
        } catch (Exception e) {
            choice = -1;
        }

        if (choice > 0 && choice <= ability_list.size() + 1){
            if (choice == 1) {
                damage = DamageCalculator.damageCalculator(player, enemy, "player");
                chosenAbility = "a weapon";
            } else if (player.validateMana(ability_list.get(choice - 2))){
                damage = DamageCalculator.damageCalculator(player, enemy, ability_list.get(choice - 2), "player");
                chosenAbility = ability_list.get(choice - 2).getAbilityName();
            } else { attackSuccess = false; }
        } else { attackSuccess = false; }




        if (attackSuccess){
            System.out.println(player.getPlayerName() + " did " + damage + " damage to " + enemy.getName() + " with " + chosenAbility + "!");
            enemy.setHealth(enemy.getHealth() - damage);
        }
        else {
            System.out.println(player.getPlayerName() + "'s attack failed!");
        }

    }
    public static void enemyAttack(Heroes player, Enemy enemy){
        System.out.println();
        System.out.println(enemy.getName() + " attacks " + player.getPlayerName() + "!");
        int damage = DamageCalculator.damageCalculator(player, enemy, "enemy");
        System.out.println(player.getPlayerName() + " takes " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
    }


}
