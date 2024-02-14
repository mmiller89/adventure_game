package main;

public class Ability {

    private String abilityName;
    private String description;
    private String attackType;
    private int manaCost;
    private int baseDamage;

    public Ability (String abilityName, String description, String attackType, int manaCost, int baseDamage){
        this.abilityName = abilityName;
        this.description = description;
        this.attackType = attackType;
        this.manaCost = manaCost;
        this.baseDamage = baseDamage;
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
}
