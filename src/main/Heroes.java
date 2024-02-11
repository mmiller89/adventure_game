package main;

public class Heroes extends HeroClass {
	
	//Set initial base stats for any player.
	private String name;
	private int health = 20;
	private int mana = 10;
	private int attack = 5;
	private int defense = 3;
	private int speed = 2;	
	
	//Inherit className from HeroClass.java
	public Heroes(String name, String className) {
		super(className);
		this.name = name;
	}
	
	//use modifiers from HeroClass.java to adjust stats based on hero class chosen by player.
	public void setStats() {
		//The if statement is not being recognized. All mods being set as 0 regardless.
		if (this.getClassName().toLowerCase().equals("warrior")) {
			this.setHealthMod(2.0);
			this.setManaMod(0.0);
			this.setAttackMod(2.0);
			this.setDefenseMod(1.0);
			this.setSpeedMod(1.0);
		}
		else if (this.getClassName().toLowerCase().equals("mage")) {
			this.setHealthMod(1.0);
			this.setManaMod(3.0);
			this.setAttackMod(1.0);
			this.setDefenseMod(0.5);
			this.setSpeedMod(1.0);
		}
		else if (this.getClassName().toLowerCase().equals("thief")) {
			this.setHealthMod(1.5);
			this.setManaMod(1.0);
			this.setAttackMod(1.5);
			this.setDefenseMod(0.5);
			this.setSpeedMod(3.0);
		}
		
		//This code functions as expected.
		this.health *= this.getHealthMod();
		this.mana *= this.getManaMod();
		this.attack *= this.getAttackMod();
		this.defense *= this.getDefenseMod();
		this.speed *= this.getSpeedMod();

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
	

}
