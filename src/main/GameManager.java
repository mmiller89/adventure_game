package main;

import java.lang.reflect.Array;
import java.util.*;

public class GameManager {

    public static void battleBegin(Heroes player, Enemy enemy){
        List<Ability> ability_list = new ArrayList<>(player.heroClass.getAbilityList());
        boolean battleActive = true;

        System.out.println("A " + enemy.getName() + " appears!");

        while(battleActive) {
            boolean deathOccured;
            if (player.getSpeed() > enemy.getSpeed()){
                deathOccured = playerTurn(player, enemy, ability_list);
                if (deathOccured) {battleActive = false;}
                deathOccured = enemyTurn(player, enemy);
                if (deathOccured) {battleActive = false;}

            } else {
                deathOccured = enemyTurn(player, enemy);
                if (deathOccured) {battleActive = false;}
                deathOccured = playerTurn(player, enemy, ability_list);
                if (deathOccured) {battleActive = false;}
            }


        }
    }

    public static boolean playerTurn(Heroes player, Enemy enemy, List<Ability> ability_list){
        System.out.println();
        System.out.println(player.getPlayerName() + " | " + enemy.getName());
        System.out.println("Health: " + player.getHealth() + " | " + "Health: " + enemy.getHealth());
        System.out.println("Mana: " + player.getMana() + " | " + "Mana: " + enemy.getMana());
        System.out.println("What will you do? Type the number.");
        System.out.println("1 - Attack");
        int iterator = 2;
        for (Ability a : ability_list) {
            System.out.println(iterator + " - " + a.getAbilityName());
            iterator++;
        }

        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        int damage;
        if (choice == 1){
            damage = player.getAttack() - enemy.getDefense();
        } else if (choice == 2){
            damage = ability_list.get(0).getBaseDamage() + player.getAttack() - enemy.getDefense();
            player.setMana(player.getMana() - ability_list.get(0).getManaCost());

        } else if (choice == 3){
            damage = ability_list.get(1).getBaseDamage() + player.getAttack() - enemy.getDefense();
            player.setMana(player.getMana() - ability_list.get(1).getManaCost());


        } else {
            damage = 0;
        }
        System.out.println(player.getPlayerName() + " did " + damage + " damage to " + enemy.getName() + "!");
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
        int damage = enemy.getAttack() - player.getDefense();
        System.out.println(player.getPlayerName() + " takes " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
        if (player.getHealth() <= 0){
            System.out.println();
            System.out.println("You have been defeated by enemy " + enemy.getName() + "!");
            return true;
        }
        return false;

    }

}
