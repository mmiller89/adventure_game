package main;

public class Armor {

    //Armor is class specific.

    private int healthIncrease;
    private int defenseIncrease;
    private String armorName;

    private int cost;

    public Armor (int healthIncrease, int defenseIncrease, String armorName, int cost){
        this.healthIncrease = healthIncrease;
        this.defenseIncrease = defenseIncrease;
        this.armorName = armorName;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
