package main;

import java.util.List;
import java.util.Random;
public class EnemyAI {

    public static String chooseAbility(Heroes player, Enemy enemy, int currentTurn, List<Ability> enemy_ability_list){
        Random rand = new Random();

        //Finds appropriate enemy and decides their moves based on randomness and/or conditions.
        //To improve this, could create a separate class to generate a number based on various factors.
        //Example, Tackle would gain "priority" points if hero was low on HP, and lose points if hero is guarding.
        if (enemy.getName().equals("Goblin")){
            int attackRand = rand.nextInt(3);
            if (attackRand == 1){
                return "Tackle";
            }
        }
        if (enemy.getName().equals("Water Elemental")){
            int attackRand = rand.nextInt(4);
            if (attackRand == 3 && enemy.getHealth() <= enemy.getMaxHealth() * 0.50
                    && enemy.getBoon().equals("None")
                    && enemy.validateMana(pullAbility("Water Shield", enemy_ability_list))){
                return "Water Shield";
            }
            if (attackRand == 2  && enemy.validateMana(pullAbility("Splash", enemy_ability_list))){
                return "Splash";
            }

        }
        if (enemy.getName().equals("Possessed Machine")){
            int attackRand = rand.nextInt(4);
            if (currentTurn % 5 == 0 && enemy.getBoon().equals("None") && enemy.validateMana(pullAbility("Charge", enemy_ability_list))){
                return "Charge";
            }
            if (attackRand == 1 && enemy.validateMana(pullAbility("Shadowbolt", enemy_ability_list))){
                return "Shadowbolt";
            }

        }
        return "attack";
    }

    public static Ability pullAbility(String name,List<Ability> enemy_ability_list){
        for (Ability a: enemy_ability_list){
            if (a.getAbilityName().equals(name)){
                return a;
            }
        } return enemy_ability_list.getFirst();
    }
}
