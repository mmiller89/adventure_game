package main;

public class Armor {

    private int healthIncrease;
    private int defenseIncrease;
    private String armorName;

    public Armor (int healthIncrease, int defenseIncrease, String armorName){
        this.healthIncrease = healthIncrease;
        this.defenseIncrease = defenseIncrease;
        this.armorName = armorName;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public String getHealthIncreaseString() {
        return String.valueOf(healthIncrease);
    }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    public int getDefenseIncrease() {
        return defenseIncrease;
    }

    public String getDefenseIncreaseString() {
        return String.valueOf(defenseIncrease);
    }

    public void setDefenseIncrease(int defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }
}
