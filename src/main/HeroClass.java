package main;

import java.util.*;

public class HeroClass {
	
	//This is the job class (Warrior, Mage, Thief) that modifies base stats and holds a set of ability objects
	//to use in combat.

	
	private String className;
	private double healthMod;
	private double manaMod;
	private double attackMod;
	private double defenseMod;
	private double speedMod;

	private Set<Ability> abilityList = new HashSet<>();

	private Ability heroicStrike = new Ability
			("Heroic Strike",
			"A searing blow on an enemy.", "Slashing", 2, 3,
					"none");
	private Ability crushingBlow = new Ability("Crushing Blow",
			"Leap up and strike down hard on one enemy", "Crushing", 3, 4,
			"none");
	private Ability iceBolt = new Ability(
			"Icebolt",
			"A magic ice lance pierces enemy.", "Ice", 5, 8,
			"none");
	private Ability lifeSteal = new Ability(
			"Absorb Life",
			"Steals a small amount of health from enemy.", "Magic", 3, 1,
			"absorb_hp");
	private Ability sliceAndDice = new Ability(
			"Slice and Dice",
			"Fast dash that slashes a target", "Slashing", 2, 3,
			"none");
	private Ability cripple = new Ability(
			"Cripple",
			"A stab that reduces enemy speed", "Stabbing", 3, 3,
			"none");

	public HeroClass(String className){
		this.className = className;
		if (this.className.equals("Warrior")){
			abilityList.add(heroicStrike);
			abilityList.add(crushingBlow);
		}
		if (this.className.equals("Mage")){
			abilityList.add(iceBolt);
			abilityList.add(lifeSteal);
		}
		if (this.className.equals("Thief")){
			abilityList.add(sliceAndDice);
			abilityList.add(cripple);
		}
	}



	public String getClassName() {
		return className;
	}

	public Set<Ability> getAbilityList(){
		return this.abilityList;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getHealthMod() {
		return healthMod;
	}

	public void setHealthMod(double healthMod) {
		this.healthMod = healthMod;
	}

	public double getManaMod() {
		return manaMod;
	}

	public void setManaMod(double manaMod) {
		this.manaMod = manaMod;
	}

	public double getAttackMod() {
		return attackMod;
	}

	public void setAttackMod(double attackMod) {
		this.attackMod = attackMod;
	}

	public double getDefenseMod() {
		return defenseMod;
	}

	public void setDefenseMod(double defenseMod) {
		this.defenseMod = defenseMod;
	}

	public double getSpeedMod() {
		return speedMod;
	}

	public void setSpeedMod(double speedMod) {
		this.speedMod = speedMod;
	}
	
	}
	
