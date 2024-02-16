package main;

public class Enemy {

    private String name;
    private int health;

    private int mana;
    private int attack;
    private int defense;
    private int speed;

    //must be one of following: Organic, Machine, Mystical, Unarmored, Armored, Bare-Armed
    private String typeOne;
    private String typeTwo;

    private String boon;
    private String status;

    public Enemy(String name, int health, int mana, int attack, int defense, int speed, String typeOne, String typeTwo, String boon, String status){
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.boon = boon;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getType() {
        return typeOne + typeTwo;
    }

    public void setTypeOne(String typeOne) {
        this.typeOne = typeOne;
    }

    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo;
    }

    public String getBoon() {
        return boon;
    }

    public void setBoon(String boon) {
        this.boon = boon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
