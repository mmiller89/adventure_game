package main;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BattleTurnManager{

    public static String takeTurn(Heroes player, Enemy enemy, List<Ability> ability_list, List<Ability> enemy_ability_list, int currentTurn){
        boolean playerDeath = false;
        boolean enemyDeath = false;
        if (player.getSpeed() >= enemy.getSpeed()){
            playerAttack(player, enemy, ability_list, currentTurn);
            enemyDeath = enemy.getHealth() <= 0;
            if (!enemyDeath){
                enemyAttack(player, enemy, enemy_ability_list, currentTurn);
                playerDeath = player.getHealth() <= 0;
            }
        }
        else {
            enemyAttack(player, enemy, enemy_ability_list, currentTurn);
            playerDeath = player.getHealth() <= 0;
            if (!playerDeath){
                playerAttack(player, enemy, ability_list, currentTurn);
                enemyDeath = enemy.getHealth() <= 0;
            }
        }
        turnPassBoons(player, enemy, ability_list, enemy_ability_list);

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

    public static void playerAttack(Heroes player, Enemy enemy, List<Ability> ability_list, int currentTurn) {
        int choice;
        System.out.println();
        System.out.println("Turn " + currentTurn);
        System.out.println(player.getPlayerName() + " | " + "Health: " + player.getHealth() + " | " + " Mana: " + player.getMana() + " | " + " Boon: " + player.getBoon() + " | " + " Status: " + player.getStatus());
        System.out.println(enemy.getName() + " | " + "Health: " + enemy.getHealth() + " | " + " Mana: " + enemy.getMana() + " | " + " Boon: " + enemy.getBoon() + " | " + " Status: " + enemy.getStatus());
        System.out.println("What will you do? Type a number.");
        if (player.getBoon().equals("Enhance")){
            System.out.println("1 - Attack (Enhanced)");
        } else {
            System.out.println("1 - Attack");
        }
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
            } else if (player.validateAndRemoveMana(ability_list.get(choice - 2))){
                damage = DamageCalculator.damageCalculator(player, enemy, ability_list.get(choice - 2), "player");
                chosenAbility = ability_list.get(choice - 2).getAbilityName();
            } else { attackSuccess = false; }
        } else { attackSuccess = false; }




        if (attackSuccess){
            System.out.println(player.getPlayerName() + " did " + damage + " damage to " + enemy.getName() + " with " + chosenAbility + "!");
            enemy.setHealth(enemy.getHealth() - damage);
        } else {
            System.out.println(player.getPlayerName() + "'s attack failed!");
        }
        scn.nextLine();

    }
    public static void enemyAttack(Heroes player, Enemy enemy, List<Ability> enemy_ability_list, int currentTurn){


        Ability chosenAbility = enemy.getAbilityInList(EnemyAI.chooseAbility(player, enemy, currentTurn, enemy_ability_list));
        System.out.println();
        if (chosenAbility.getAttackType().equals("Non-Damage") && enemy.validateAndRemoveMana(chosenAbility)){
            System.out.println(enemy.getName() + " uses " + chosenAbility.getAbilityName() + "!");
            DamageCalculator.damageCalculator(player, enemy, chosenAbility, "enemy");
        } else if (enemy.validateAndRemoveMana(chosenAbility)) {
            System.out.println(enemy.getName() + " uses " + chosenAbility.getAbilityName() + " on " + player.getPlayerName() + "!");
            int damage = DamageCalculator.damageCalculator(player, enemy, chosenAbility, "enemy");
            System.out.println(player.getPlayerName() + " takes " + damage + " damage!");
            player.setHealth(player.getHealth() - damage);
        } else {
            System.out.println(enemy.getName() + " attempted to use its turn, but failed!");
        }

    }


    //Handles turn count down for buffs.
    public static void turnPassBoons(Heroes player, Enemy enemy, List<Ability> ability_list, List<Ability> enemy_ability_list){


        for (Ability e: enemy_ability_list){
            if (e.getTurnsRemaining() > 0){
                e.setTurnsRemaining(e.getTurnsRemaining() - 1);
                if (e.getTurnsRemaining() == 0) {

                    //Set individual behavior per each buff.
                    if (enemy.getBoon().equals("Water Shield")){
                        enemy.setDefense((int) (enemy.getDefense() * 0.5));
                        System.out.println("Water shield expired, defense returns to normal.");
                        enemy.setBoon("None");
                        e.setTurnsRemaining(e.getTurnDuration());
                    }
                }
            }

        }
    }



}
