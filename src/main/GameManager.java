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

    public static void battle(Heroes player, Enemy enemy){
        //When enemies have abilites, create a seperate list.
        List<Ability> ability_list = new ArrayList<>(player.heroClass.getAbilityList());
        boolean battleActive = true;


        System.out.println("A " + enemy.getName() + " appears!");

        while(battleActive) {
            //When enemies start having abilities, import two separate lists of abilities.
            String outcome = BattleTurnManager.takeTurn(player, enemy, ability_list);
            if (outcome.equals("battle over")) {
                battleActive = false;
            }


        }
    }



}
