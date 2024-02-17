package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


//Manages the different game states(battle, shops, traveling, etc.). As the game expands, this will
//become a parent class and the different game states will be their own classes/files.

public class GameManager {

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



}
