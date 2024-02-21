package main;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {

    public static int damageCalculator(Heroes player, Enemy enemy, Ability ability, String attackingEntity){



        double damageVariance = ThreadLocalRandom.current().nextDouble(0.9, 1.1);
        //Player using ability.
        if (attackingEntity.equals("player")) {

            validateBuff(player, enemy, ability, attackingEntity);


            int damage = (int) (ability.getBaseDamage() + (player.getAttack() * damageVariance * ability.typeModifier(enemy)) - enemy.getDefense());
            double afterEffects = validateAfterEffects(player, enemy, ability, damage, attackingEntity);
            damage = (int) (damage * afterEffects);
            return Math.max(damage, 1);
        }
        //Enemy using ability.
            else if (attackingEntity.equals("enemy")) {

                validateBuff(player, enemy, ability, attackingEntity);

                int damage = (int) (ability.getBaseDamage() + (enemy.getAttack() * damageVariance) - player.getDefense());
                double afterEffects = validateAfterEffects(player, enemy, ability, damage, attackingEntity);
                damage = (int) (damage * afterEffects);
                if (player.isDefending()){
                    damage = (int) (damage * 0.25);
                }
                if (player.getBoon().equals("Mana Shield")){
                    damage = (int) (damage * 0.5);
                    int manaLost = (int) (player.getMana() * 0.10);
                    player.setMana(player.getMana() - manaLost);
                    System.out.println(player.getPlayerName() + " loses " + manaLost + " mana due to Mana Shield absorbing damage!");
                    if (player.getMana() <= 0){
                        System.out.print("Mana is now 0. Mana Shield breaks!");
                        player.setBoon("None");
                    }
                }
                return Math.max(damage, 1);
        }
        return 0;
    }

    public static int damageCalculator(Heroes player, Enemy enemy, String attackingEntity){
        double damageVariance = ThreadLocalRandom.current().nextDouble(0.9, 1.1);
        //Mage using normal attack - restore 20% of Max MP.
        if (attackingEntity.equals("player") && player.heroClass.getClassName().equals("Mage")) {
            int manaRestored = Math.max((int)(player.getMaxMana() * 0.20), 1);
            player.setMana(Math.min(player.getMana() + manaRestored, player.getMaxMana()));
            int damage = (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
            System.out.println(player.getPlayerName() + " restores " + manaRestored + " mana.");
            return Math.max(damage, 1);
        }
        //Warrior use normal attack - restore 50% of damage dealt as HP.
        if (attackingEntity.equals("player") && player.heroClass.getClassName().equals("Warrior")){
            int damage = (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
            if (player.getBoon().equals("Enhance")){
                damage = damage * 2;
                player.setBoon("None");
            }
            int healthRestored = Math.max((int)(damage * 0.25), 1);
            player.setHealth(player.getHealth() + healthRestored);
            System.out.println(player.getPlayerName() + " restores " + healthRestored + " health.");
            return Math.max(damage, 1);
        }

        //Thief use normal attack - 25% of getting "Evading" status - 25% chance to avoid attack. While
        //in Evading, Cripple will inflict "Exposed" - enemy speed -20% (permanent) and fully restoring mana.
        if (attackingEntity.equals("player") && player.heroClass.getClassName().equals("Thief")) {
            Random rand = new Random();
            int random = rand.nextInt(4);
            if (random == 3 && player.getBoon().equals("None")){
                System.out.println(player.getPlayerName() + " is evading. Cripple will expose enemy!");
                player.setBoon("Evasion");
            }
            int damage = (int) ((player.getAttack() * damageVariance) - enemy.getDefense());
            return Math.max(damage, 1);

        }

        return 0;
    }


    public static double validateBeforeEffects(Heroes player, Enemy enemy, Ability ability, int damage, String attackingEntity){
        return 0.0;
    }

    //This class handles any final modifiers to damage variable. These usually come from skills. The class can also do extra effects, like Absorb Life.
    public static double validateAfterEffects(Heroes player, Enemy enemy, Ability ability, int damage, String attackingEntity){
        if (attackingEntity.equals("player")){
            //For ability Focus
            if (player.getBoon().equals("Focus") && enemy.getStatus().equals("Frostbite") && ability.getAdditionalEffect().contains("frostbolt")){
                System.out.println(enemy.getName() + " is frostbitten and vulnerable!");
                enemy.setStatus("None");
                return 3.0;
            }
            if (player.getBoon().equals("Focus")) {
                return 1.5;
            }
            //For ability Focus

            //For ability Frostbolt
            if (ability.getAdditionalEffect().contains("frostbolt")){

                if (enemy.getStatus().equals("Frostbite")){
                    System.out.println(enemy.getName() + " is frostbitten and vulnerable!");
                    enemy.setStatus("None");
                    return 2.0;
                }

                String coldLevel = enemy.getStatus().equals("None") ? "Chill (1)" : enemy.getStatus().equals("Chill (1)") ? "Chill (2)" : "Frostbite";
                enemy.setStatus(coldLevel);
            }
            //

            //For ability Slice and Dice
            if (ability.getAdditionalEffect().contains("slice and dice") && player.getSpeed() > enemy.getSpeed()){
                System.out.println(player.getPlayerName() + " is faster, damage quadrupled!");
                return 4.0;
            } else if (ability.getAdditionalEffect().contains("slice and dice") && player.getSpeed() < enemy.getSpeed()){
                System.out.println(player.getPlayerName() + " is slower, damage halved!");
                return 0.5;
            }
            //For ability Slice and Dice

            //For ability Cripple
            if (ability.getAdditionalEffect().contains("cripple")){
                if (player.getBoon().equals("Evasion")){
                    enemy.setSpeed((int) Math.max(enemy.getSpeed() * 0.70, 1));
                    player.restoreMP();
                    player.setBoon("None");
                    System.out.println(player.getPlayerName() + " finds a critical weakness! Speed reduced by 30%! Mana fully restored!");
                }
            }
            //For ability Cripple

            //For ability Heroic Strike
            if (ability.getAdditionalEffect().contains("heroic strike")){
                player.setBoon("Enhance");
            }
            //For ability Heroic Strike

            //For ability Crushing Blow
            if (ability.getAdditionalEffect().contains("crushing blow")){
                int reducedAttack = player.getAttack() * 0.40 >= 1 ? (int) (player.getAttack() * 0.40) : 1;
                player.setAttack(player.getAttack() - reducedAttack);
                System.out.println("A fatiguing blow reduces " + player.getPlayerName() + "'s attack by " + reducedAttack + ".");
            }
            //For ability Crushing Blow
        }
        return 1.0;

    }

    //This class runs before damage calculation, putting buffs on the attacking party as needed.
    //Sets turn duration to max.
    public static void validateBuff(Heroes player, Enemy enemy, Ability ability, String entity){

        if(entity.equals("player")){
            if (ability.getAdditionalEffect().equals("focus") && player.getBoon().equals("None")){
                player.setBoon("Focus");
            } else if (ability.getAdditionalEffect().equals("focus") && player.getBoon().equals("Mana Shield")){
                System.out.println("Mana shield dissipates due to Focus!");
                player.setBoon("Focus");
            }
        }

        if (entity.equals("enemy")){
            if (ability.getAdditionalEffect().equals("water shield") && enemy.getBoon().equals("None")){
                ability.setTurnsRemaining(ability.getTurnDuration());
                System.out.println(enemy.getName() + " coats itself in a watery barrier! Defense increased!");
                enemy.setBoon("Water Shield");
                enemy.setDefense(enemy.getDefense() * 2);
            }
            if (ability.getAdditionalEffect().equals("charge") && enemy.getBoon().equals("None")){
                ability.setTurnsRemaining(ability.getTurnDuration());
                enemy.setBoon("Charging Up");
                System.out.println(enemy.getName() + " revs up it's engine, charging up an attack. Next attack has double power!");
                enemy.setAttack(enemy.getAttack() * 2);

            }

        }

    }



}
