package main;

public class Ability {
    //Standard ability class, holding all the information about the ability.
    //Abilities with custom effects will be handled here.

    private String abilityName;
    private String description;
    private String attackType;
    private int manaCost;
    private int baseDamage;

    private String additionalEffect;

    public Ability (String abilityName, String description, String attackType, int manaCost, int baseDamage,
                    String additionalEffect){
        this.abilityName = abilityName;
        this.description = description;
        this.attackType = attackType;
        this.manaCost = manaCost;
        this.baseDamage = baseDamage;
        this.additionalEffect = additionalEffect;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public String getAdditionalEffect() {
        return additionalEffect;
    }

    public void setAdditionalEffect(String additionalEffect) {
        this.additionalEffect = additionalEffect;
    }

    //Ability types - Piercing, slashing, crushing, fire, ice, lightning, magic.
    //Additional effects:
    //Piercing - 25% extra damage to unarmored targets.
    //Slashing - 25% extra damage to bare-arm targets.
    //Crushing - 25% extra damage to armored targets.
    //Fire - 25% extra damage to organic targets.
    //Lightning - 25% extra damage to machines.
    //Ice - 25% extra damage to mystical/elemental targets.

    public double typeModifier(Enemy enemy){
        if (this.getAttackType().contains("Fire") && enemy.getType().contains("Organic")){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        if (this.getAttackType().contains("Lightning") && enemy.getType().contains("Machine")){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        if (this.getAttackType().contains("Ice") && (enemy.getType().contains("Mystical") || enemy.getType().contains("Elemental"))){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        if (this.getAttackType().contains("Piercing") && enemy.getType().contains("Unarmored")){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        if (this.getAttackType().contains("Slashing") && enemy.getType().contains("Bare-Armed")){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        if (this.getAttackType().contains("Crushing") && enemy.getType().contains("Armored")){
            System.out.println("Weakness hit! Damage increased!");
            return 1.25;
        }
        return 1.0;
    }
}
