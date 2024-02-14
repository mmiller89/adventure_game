package main;

import java.util.List;

public class Heroes {
	
	//Set initial base stats for any player. Hero holds heroClass object as well as weapon / armor objects (eventually).
	private String playerName;
	HeroClass heroClass;
    private int health = 20;

	private int maxHealth = 20;
	private int mana = 10;

	private int maxMana = 10;
	private int attack = 5;
	private int defense = 3;
	private int speed = 2;

	//Inherit className from HeroClass.java
	public Heroes(String playerName, HeroClass heroClass){
		this.playerName = playerName;
		this.heroClass = heroClass;

	}


	//use modifiers from HeroClass.java to adjust stats based on hero class chosen by player.
	public void setStats() {
		//The if statement is not being recognized. All mods being set as 0 regardless.
		if (this.heroClass.getClassName().equals("Warrior")) {
			this.heroClass.setHealthMod(2.0);
			this.heroClass.setManaMod(1.0);
			this.heroClass.setAttackMod(1.5);
			this.heroClass.setDefenseMod(1.5);
			this.heroClass.setSpeedMod(1.0);
		}
		else if (this.heroClass.getClassName().equals("Mage")) {
			this.heroClass.setHealthMod(1.0);
			this.heroClass.setManaMod(3.0);
			this.heroClass.setAttackMod(1.0);
			this.heroClass.setDefenseMod(0.5);
			this.heroClass.setSpeedMod(1.0);
		}
		else if (this.heroClass.getClassName().equals("Thief")) {
			this.heroClass.setHealthMod(1.5);
			this.heroClass.setManaMod(1.0);
			this.heroClass.setAttackMod(1.5);
			this.heroClass.setDefenseMod(0.5);
			this.heroClass.setSpeedMod(2.0);
		}
		
		//This code functions as expected.
		this.health *= (int) this.heroClass.getHealthMod();
		this.mana *= (int) this.heroClass.getManaMod();
		this.attack *= (int) this.heroClass.getAttackMod();
		this.defense *= (int) this.heroClass.getDefenseMod();
		this.speed *= (int) this.heroClass.getSpeedMod();

		this.maxHealth = this.health;
		this.maxMana = this.mana;

	}

	public void listStats(){
		System.out.println("Health: " + this.health);
		System.out.println("Mana: " + this.mana);
		System.out.println("Attack: " + this.attack);
		System.out.println("Defense: " + this.defense);
		System.out.println("Speed: " + this.speed);

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
}