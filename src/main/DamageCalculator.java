package main;

import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {

    public static int damageCalculator(Heroes player, Enemy enemy, Ability ability, String attackingEntity){



        double damageVariance = ThreadLocalRandom.current().nextDouble(0.9, 1.1);
        //Player using ability.
        if (attackingEntity.equals("player")) {
            int damage = (int) (ability.getBaseDamage() + (player.getAttack() * damageVariance * ability.typeModifier(enemy)) - enemy.getDefense());
            double afterEffects = validateAfterEffects(player, enemy, ability, damage, attackingEntity);
            damage = (int) (damage * afterEffects);
            return Math.max(damage, 1);
        }
        //Enemy using ability (not available yet).
            else if (attackingEntity.equals("enemy")) {

                validateBuff(player, enemy, ability, attackingEntity);

                int damage = (int) (ability.getBaseDamage() + (enemy.getAttack() * damageVariance) - player.getDefense());
                double afterEffects = validateAfterEffects(player, enemy, ability, damage, attackingEntity);
                damage = (int) (damage * afterEffects);
                return Math.max(damage, 1);
        }
        return 0;
    }

    public static int damageCalculator(Heroes player, Enemy enemy, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.9, 1.1);
        //Player using normal attack.
        if (attackingEntity.equals("player")) {
            int manaRestored = Math.max((int)(player.getMaxMana() * 0.10), 1);
            player.setMana(Math.min(player.getMana() + manaRestored, player.getMaxMana()));
            int damage = (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
            if (player.getBoon().equals("Enhance")){
                damage = damage * 2;
                player.setBoon("None");
            }
            System.out.println(player.getPlayerName() + " restored " + manaRestored + " mana.");
            return Math.max(damage, 1);
        }

        return 0;
    }


    public static double validateBeforeEffects(Heroes player, Enemy enemy, Ability ability, int damage, String attackingEntity){
        return 0.0;
    }

    public static double validateAfterEffects(Heroes player, Enemy enemy, Ability ability, int damage, String attackingEntity){
        if (attackingEntity.equals("player")){
            //For ability Absorb Life
            if (ability.getAdditionalEffect().contains("absorb life")){
                int restoredHealth = damage * 4;
                int newHealth = player.getHealth() + restoredHealth;
                player.setHealth(Math.min(newHealth, player.getMaxHealth()));
                System.out.println();
                System.out.println(player.getPlayerName() + " drained " + restoredHealth + " health from " + enemy.getName() + " with " + ability.getAbilityName() + "!");
                System.out.println();
            }
            //For ability Absorb Life

            //For ability Slice and Dice
            if (ability.getAdditionalEffect().contains("slice and dice") && player.getSpeed() > enemy.getSpeed()){
                System.out.println(player.getPlayerName() + " is faster, damage doubled!");
                return 2.0;
            } else if (ability.getAdditionalEffect().contains("slice and dice") && player.getSpeed() < enemy.getSpeed()){
                System.out.println(player.getPlayerName() + " is slower, damage halved!");
                return 0.5;
            }
            //For ability Slice and Dice

            //For ability Cripple
            if (ability.getAdditionalEffect().contains("cripple")){
                enemy.setSpeed(Math.max(enemy.getSpeed() - 1, 0));
                System.out.println(enemy.getName() + "'s speed reduced by 1 to " + enemy.getSpeed() + ".");
            }
            //For ability Cripple

            if (ability.getAdditionalEffect().contains("heroic strike")){
                player.setBoon("Enhance");
            }
            if (ability.getAdditionalEffect().contains("crushing blow")){
                int reducedAttack = player.getAttack() * 0.20 >= 1 ? (int) (player.getAttack() * 0.20) : 1;
                player.setAttack(player.getAttack() - reducedAttack);
                System.out.println("A fatiguing blow reduces " + player.getPlayerName() + "'s attack by " + reducedAttack + ".");
            }
        }
        return 1.0;

    }

    public static void validateBuff(Heroes player, Enemy enemy, Ability ability, String entity){

        if (entity.equals("enemy")){
            if (ability.getAdditionalEffect().equals("water shield") && enemy.getBoon().equals("None")){
                ability.setTurnsRemaining(ability.getTurnDuration());
                System.out.println(enemy.getName() + " coats itself in a watery barrier! Defense increased!");
                enemy.setBoon("Water Shield");
                enemy.setDefense(enemy.getDefense() * 2);

            }
        }

    }



}
