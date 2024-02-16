package main;

import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {

    public static int damageCalculator(Heroes player, Enemy enemy, Ability ability, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.8, 1.2);
        if (attackingEntity.equals("player")) {
            //validateBeforeEffects()
            int damage = (int) (ability.getBaseDamage() + (player.getAttack() * damageVariance * ability.typeModifier(enemy)) - enemy.getDefense());
            validateAfterEffects(player, enemy, ability, damage, attackingEntity);
            return damage > 0 ? damage : 0;
        }
        //if not player, then enemy
        else if (attackingEntity.equals("enemy")) {
            int damage = (int) (ability.getBaseDamage() + (enemy.getAttack() * damageVariance * ability.typeModifier(enemy)) - player.getDefense());
            return damage > 0 ? damage : 0;
        }
        return 0;
    }

    public static int damageCalculator(Heroes player, Enemy enemy, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.8, 1.2);
        if (attackingEntity.equals("player")) {
            int damage = (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
            return damage > 0 ? damage : 0;
        }
        //if not player, then enemy
        else if (attackingEntity.equals("enemy")){
            int damage = (int) ((enemy.getAttack() * damageVariance) - player.getDefense());
            return damage > 0 ? damage : 0;
        }
        return 0;
    }

    public static void validateAfterEffects(Heroes player, Enemy enemy, Ability ability, int damage, String attackingEntity){
        if (attackingEntity.equals("player")){
            if (ability.getAdditionalEffect().equals("absorb_hp")){
                int restoredHealth = damage * 4;
                int newHealth = player.getHealth() + restoredHealth;
                player.setHealth(Math.min(newHealth, player.getMaxHealth()));
                System.out.println();
                System.out.println(player.getPlayerName() + " drained " + restoredHealth + " health from " + enemy.getName() + " with " + ability.getAbilityName() + "!");
                System.out.println();
            }
        }


    }

}
