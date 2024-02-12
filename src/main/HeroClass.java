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
					"A searing blow on an enemy.", "Slashing", 0));
			abilityList.add(new Ability("Crushing Blow",
					"Leap up and strike down hard on one enemy", "Crushing", 0));
		}
		if (this.className.equals("Mage")){
			//abilityList.add("Icebolt");
			//abilityList.add("Blaze");
		}
		if (this.className.equals("Thief")){
			//abilityList.add("Slice and Dice");
			//abilityList.add("Crippling Strike");
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
	
