package main;

import java.util.*;

public class HeroClass {
	
	//This is the job class (Warrior, Mage, Thief) that modifies base stats and holds ability objects.

	//HeroClass will have an object Ability that will be added into abilityList on construction.
	
	private String className;
	private double healthMod;
	private double manaMod;
	private double attackMod;
	private double defenseMod;
	private double speedMod;

	private Set<Ability> abilityList = new HashSet<>();

	public HeroClass(String className){
		this.className = className;
		if (this.className.equals("Warrior")){
			abilityList.add(new Ability(
					"Heroic Strike",
					"A searing blow on an enemy.", "Slashing", 2, 3));
			abilityList.add(new Ability("Crushing Blow",
					"Leap up and strike down hard on one enemy", "Crushing", 3, 4));
		}
		if (this.className.equals("Mage")){
			abilityList.add(new Ability(
					"Icebolt",
					"A magic ice lance pierces enemy.", "Ice", 5, 6));
			abilityList.add(new Ability(
					"Absorb Life",
					"Steals a small amount of health from enemy.", "Magic", 2, 1));
		}
		if (this.className.equals("Thief")){
			abilityList.add(new Ability(
					"Slice and Dice",
					"Fast dash that slashes a target", "Slashing", 2, 3));
			abilityList.add(new Ability(
					"Cripple",
					"A stab that reduces enemy speed", "Stabbing", 3, 3));
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
	
