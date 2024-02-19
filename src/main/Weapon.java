package main;

//Weapons influence attack and speed stats;
public class Weapon {

    private int attackPower;
    private int speed;
    private String weaponName;

    public Weapon (int attackPower, int speed, String weaponName){
        this.attackPower = attackPower;
        this.speed = speed;
        this.weaponName = weaponName;
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
}
