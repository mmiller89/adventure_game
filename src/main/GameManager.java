package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


//Manages the different game states(battle, shops, traveling, etc.). As the game expands, this will
//become a parent class and the different game states will be their own classes/files.

public class GameManager {

    public static void battleBegin(Heroes player, Enemy enemy){
        List<Ability> ability_list = new ArrayList<>(player.heroClass.getAbilityList());
        boolean battleActive = true;

        System.out.println("A " + enemy.getName() + " appears!");

        while(battleActive) {
            boolean deathOccured;
            if (player.getSpeed() >= enemy.getSpeed()){
                deathOccured = playerTurn(player, enemy, ability_list);
                if (deathOccured) {
                    battleActive = false;
                }
                else {
                    deathOccured = enemyTurn(player, enemy);
                    if (deathOccured) {
                        battleActive = false;
                    }
                }

            } else {
                deathOccured = enemyTurn(player, enemy);
                if (deathOccured) {
                    battleActive = false;
                }
                else {
                    deathOccured = playerTurn(player, enemy, ability_list);
                    if (deathOccured) {
                        battleActive = false;
                    }
                }

            }


        }
    }

    public static boolean playerTurn(Heroes player, Enemy enemy, List<Ability> ability_list){
        System.out.println();
        System.out.println(player.getPlayerName() + " | " + enemy.getName());
        System.out.println("Health: " + player.getHealth() + " | " + "Health: " + enemy.getHealth());
        System.out.println("Mana: " + player.getMana() + " | " + "Mana: " + enemy.getMana());
        System.out.println("What will you do? Type a number.");
        System.out.println("1 - Attack");
        int iterator = 2;
        for (Ability a : ability_list) {
            System.out.println(iterator + " - " + a.getAbilityName());
            iterator++;
        }

        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        int damage;
        String chosenAbility;

        if (choice == 1){
            damage = damageCalculator(player, enemy, "player");
            chosenAbility = "a weapon";
        } else if (choice == 2){
            damage = damageCalculator(player, enemy, ability_list.get(0), "player");
            player.setMana(player.getMana() - ability_list.get(0).getManaCost());
            chosenAbility = ability_list.get(0).getAbilityName();

        } else if (choice == 3){
            damage = damageCalculator(player, enemy, ability_list.get(1), "player");
            player.setMana(player.getMana() - ability_list.get(1).getManaCost());
            chosenAbility = ability_list.get(1).getAbilityName();


        } else {
            chosenAbility = "an awkward stare (no action taken)";
            damage = 0;
        }
        System.out.println(player.getPlayerName() + " did " + damage + " damage to " + enemy.getName() + " with " + chosenAbility + "!");
        enemy.setHealth(enemy.getHealth() - damage);
        if (enemy.getHealth() <= 0){
            System.out.println();
            System.out.println("You have vanquished enemy " + enemy.getName() + "!");
            return true;
        }
        return false;
    }

    public static boolean enemyTurn(Heroes player, Enemy enemy){
        System.out.println();
        System.out.println(enemy.getName() + " attacks " + player.getPlayerName() + "!");
        int damage = damageCalculator(player, enemy, "player");
        System.out.println(player.getPlayerName() + " takes " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
        if (player.getHealth() <= 0){
            System.out.println();
            System.out.println("You have been defeated by enemy " + enemy.getName() + "!");
            return true;
        }
        return false;

    }

    public static int damageCalculator(Heroes player, Enemy enemy, Ability ability, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.8,1.2);
        if (attackingEntity.equals("player")) {
            return (int) (ability.getBaseDamage() + (player.getAttack() * damageVariance) - enemy.getDefense());
        }
        //if not player, then enemy
        else {
            return (int) (ability.getBaseDamage() + (enemy.getAttack() * damageVariance) - player.getDefense());
        }
    }

    public static int damageCalculator(Heroes player, Enemy enemy, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.8,1.2);
        if (attackingEntity.equals("player")) {
            return (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
        }
        //if not player, then enemy
        else {
            return (int) ((enemy.getAttack() * damageVariance) - player.getDefense());
        }
    }

}
