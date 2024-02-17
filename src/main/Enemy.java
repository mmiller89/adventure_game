package main;

import java.util.*;

public class Enemy {

    private String name;
    private int health;
    private int maxHealth;

    private int mana;
    private int maxMana;
    private int attack;
    private int maxAttack;
    private int defense;
    private int maxDefense;
    private int speed;
    private int maxSpeed;

    //must be one of following: Organic, Machine, Mystical, Unarmored, Armored, Bare-Armed
    private String typeOne;
    private String typeTwo;

    private String boon;
    private String status;

    private String difficulty;

    private Set<Ability> enemyAbilityList = new LinkedHashSet<>();


    private Ability regularAttack = new Ability
            ("attack",
                    "", "None", 0, 1,
                    "none", "", -1, -1);
    private Ability tackle = new Ability
            ("Tackle",
                    "", "None", 0, 3,
                    "none", "", -1, -1);
    private Ability splash = new Ability
            ("Splash",
                    "", "None", 4, 4,
                    "none", "", -1, -1);
    private Ability waterShield = new Ability
            ("Water Shield",
                    "", "Non-Damage", 10, 0,
                    "water shield", "", 2, 2);
    private Ability chargeUp = new Ability
            ("Charge",
                    "", "None", 0, 0,
                    "charge", "", -1, -1);
    private Ability shadowBolt = new Ability
            ("Shadowbolt",
                    "", "None", 2, 3,
                    "shadowbolt", "", -1, -1);

    public Enemy(String name, int health, int mana, int attack, int defense, int speed, String typeOne, String typeTwo, String boon, String status, String difficulty){
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.attack = attack;
        this.maxAttack = attack;
        this.defense = defense;
        this.maxDefense = defense;
        this.speed = speed;
        this.maxSpeed = speed;
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.boon = boon;
        this.status = status;
        this.difficulty = difficulty;


        this.enemyAbilityList.add(regularAttack);

        if (this.name.equals("Goblin")){
            this.enemyAbilityList.add(tackle);
        }
        if (this.name.equals("Water Elemental")){
            this.enemyAbilityList.add(splash);
            this.enemyAbilityList.add(waterShield);
        }
        if (this.name.equals("Possessed Machine")){
            this.enemyAbilityList.add(chargeUp);
            this.enemyAbilityList.add(shadowBolt);
        }

    }

    public boolean validateMana(Ability ability){
        int manaCost = ability.getManaCost();
        return this.mana >= manaCost;
    }

    public boolean validateAndRemoveMana(Ability ability){
        int manaCost = ability.getManaCost();
        boolean check = validateMana(ability);
        if (check){
            this.setMana(this.getMana() - manaCost);
            return true;
        }
        return false;
    }

    public void restoreStats(){
        this.attack = this.maxAttack;
        this.defense = this.maxDefense;
        this.speed = this.maxSpeed;
    }

    public void restoreHPMP(){
            this.health = this.maxHealth;
            this.mana = this.maxMana;
    }

    public void fullRestore(){
        this.health = this.maxHealth;
        this.mana = this.maxMana;
        this.attack = this.maxAttack;
        this.defense = this.maxDefense;
        this.speed = this.maxSpeed;
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
        return typeOne + " " + typeTwo;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Ability> getEnemyAbilityList(){
        return enemyAbilityList;
    }

    public Ability getAbilityInList(String choice){
        //Tackle was passed in
        for (Ability a : enemyAbilityList){
            if (a.getAbilityName().equals(choice)){
                return a;
            }
        }
        return null;

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
