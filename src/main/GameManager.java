package main;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

public class GameManager {

    public static void battleBegin(Heroes player, Enemy enemy){
        Set<Ability> extract_ab = player.heroClass.getAbilityList();
        Object[] ability_list = extract_ab.toArray();

        System.out.println("A " + enemy.getName() + " appears!");
        System.out.println("What will you do? Type the number.");
        System.out.println("1 - Weapon attack");
        System.out.println("2 - Use " + ability_list[0]);

    }

}
