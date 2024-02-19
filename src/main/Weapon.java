package main;

//Weapons influence attack and speed stats
//Swords - high power, lowers speed
//Daggers - low power, raises speed.
//Staves - low power, raises mana.
//Weapons can be swapped around between classes.
public class Weapon {

    private int attackPower;
    private int speed;

    private int mana;
    private String weaponName;

    private int cost;

    private String purchaseTag;

    public Weapon (int attackPower, int speed, int mana, String weaponName, int cost, String purchaseTag){
        this.attackPower = attackPower;
        this.speed = speed;
        this.mana = mana;
        this.weaponName = weaponName;
        this.cost = cost;
        this.purchaseTag = purchaseTag;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPurchaseTag() {
        return purchaseTag;
    }

    public void setPurchaseTag(String purchaseTag) {
        this.purchaseTag = purchaseTag;
    }
}
