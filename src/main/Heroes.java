package main;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Heroes {
	
	//Set initial base stats for any player. Heroes have a hero class, weapons/armor, and abilities.

	private String playerName;
	HeroClass heroClass;

	Weapon weapon;
	Armor armor;
    private int health = 20;

	private int maxHealth = 20;
	private int mana = 10;

	private int maxMana = 10;
	private int attack = 5;
	private int maxAttack = 5;
	private int defense = 3;

	private int maxDefense = 3;
	private int speed = 2;

	private int maxSpeed = 2;

	private String boon;
	private String status;

	private int gold;

	private int level;
	private int experience;
	private int expNext;

	//equipmentList should ONLY hold Weapon or Armor classes.
	private Set<Object> equipmentList = new LinkedHashSet<>();

	private boolean isDefending = false;

	//Inherit className from HeroClass.java
	public Heroes(String playerName, HeroClass heroClass, String boon, String status, int gold){
		this.playerName = playerName;
		this.heroClass = heroClass;
		this.boon = boon;
		this.status = status;
		this.gold = gold;

	}


	//use modifiers from HeroClass.java to adjust stats based on hero class chosen by player.
	//Also sets initial equipment and level/experience.
	public void initialize() {

		if (this.heroClass.getClassName().equals("Warrior")) {
			this.heroClass.setHealthMod(2.0);
			this.heroClass.setManaMod(1.0);
			this.heroClass.setAttackMod(1.5);
			this.heroClass.setDefenseMod(1.5);
			this.heroClass.setSpeedMod(1.0);

			this.weapon = new Weapon(2,-1,"Broadsword");
			this.armor = new Armor(5,2, "Mail Armor");
			equipmentList.add(this.weapon);
			equipmentList.add(this.armor);
		}
		else if (this.heroClass.getClassName().equals("Mage")) {
			this.heroClass.setHealthMod(1.0);
			this.heroClass.setManaMod(3.0);
			this.heroClass.setAttackMod(0.5);
			this.heroClass.setDefenseMod(0.5);
			this.heroClass.setSpeedMod(1.0);
			this.weapon  = new Weapon(1,0,"Oak Staff");
			this.armor = new Armor(0,1, "Cloth Robes");
			equipmentList.add(this.weapon);
			equipmentList.add(this.armor);
		}
		else if (this.heroClass.getClassName().equals("Thief")) {
			this.heroClass.setHealthMod(1.5);
			this.heroClass.setManaMod(2.0);
			this.heroClass.setAttackMod(1.0);
			this.heroClass.setDefenseMod(0.5);
			this.heroClass.setSpeedMod(2.0);
			this.weapon = new Weapon(1,1,"Dagger");
			this.armor = new Armor(5,1, "Leather Tunic");
			equipmentList.add(this.weapon);
			equipmentList.add(this.armor);
		}

		
		//This code functions as expected.
        this.health = (int) (this.health * this.heroClass.getHealthMod());
        this.mana = (int) (this.mana * this.heroClass.getManaMod());
        this.attack = (int) (this.attack * this.heroClass.getAttackMod());
        this.defense = (int) (this.defense * this.heroClass.getDefenseMod());
        this.speed = (int) (this.speed * this.heroClass.getSpeedMod());

		this.maxHealth = this.health;
		this.maxMana = this.mana;
		this.maxAttack = this.attack;
		this.maxDefense = this.defense;
		this.maxSpeed = this.speed;
		this.level = 1;
		this.experience = 0;
		this.expNext = 100;

		equipmentOn();

	}

	public void listStats(){
		System.out.println("Health: " + this.maxHealth);
		System.out.println("Mana: " + this.maxMana);
		System.out.println("Attack: " + this.maxAttack);
		System.out.println("Defense: " + this.maxDefense);
		System.out.println("Speed: " + this.maxSpeed);

	}

	public void listAbilities(){
		for (Ability ability : this.heroClass.getAbilityList()){
			System.out.println(ability.getAbilityName() + ": " + ability.getDescription());
			System.out.println("Type: " + ability.getAttackType() + " | Mana Cost: " + ability.getManaCost());
			System.out.println();
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
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

	public void restoreMP(){
		this.mana = this.maxMana;
	}

	public void fullRestore(){
		this.health = this.maxHealth;
		this.mana = this.maxMana;
		this.attack = this.maxAttack;
		this.defense = this.maxDefense;
		this.speed = this.maxSpeed;
		this.boon = "None";
		this.status = "None";
	}

		//Initializing equipment on character creation.
	public void equipmentOn(){
		this.maxAttack += this.weapon.getAttackPower();
		this.maxSpeed += this.weapon.getSpeed();
		this.maxHealth += this.armor.getHealthIncrease();
		this.maxDefense += this.armor.getDefenseIncrease();
	}

	//When upgrading equipment.

	public void equipmentOn(Weapon newEquip){
		if (newEquip != null){
			this.maxAttack -= this.weapon.getAttackPower();
			this.maxAttack += newEquip.getAttackPower();
			this.maxSpeed -= this.weapon.getSpeed();
			this.maxSpeed += newEquip.getSpeed();

			equipmentList.remove(this.weapon);
			equipmentList.add(newEquip);
			//Failsafe to ensure stats never drop below 0.
            this.maxAttack = Math.max(this.maxAttack, 0);
			this.maxSpeed = Math.max(this.maxSpeed, 0);
		}
	}

	public void equipmentOn(Armor newEquip){
		this.maxDefense -= this.armor.getDefenseIncrease();
		this.maxDefense += newEquip.getDefenseIncrease();
		this.maxHealth -= this.armor.getHealthIncrease();
		this.maxHealth += newEquip.getHealthIncrease();
		equipmentList.remove(this.armor);
		equipmentList.add(newEquip);

		//Failsafe to ensure stats never drop below 0.
		this.maxDefense = Math.max(this.maxDefense, 0);
		this.maxHealth = Math.max(this.maxHealth, 1);

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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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

	public boolean isDefending() {
		return isDefending;
	}

	public void setDefending(boolean defending) {
		isDefending = defending;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getExpNext() {
		return expNext;
	}

	public void setExpNext(int expNext) {
		this.expNext = expNext;
	}
}
