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

	private Set<Ability> abilityList = new LinkedHashSet<>();

	private Ability heroicStrike = new Ability
			("Heroic Strike",
			"A slashing blow to one target. Enhances weapon, causing your next regular attack to do 200% damage.", "Slashing", 2, 3,
					"heroic strike", "I'll suffer you no longer!", -1, -1);
	private Ability crushingBlow = new Ability("Crushing Blow",
			"Crush an enemy. High power, but hero loses 40% attack for remainder of battle.", "Crushing", 0, 10,
			"crushing blow", "A devastating blow to you, fiend!", -1, -1);
	private Ability frostBolt = new Ability(
			"Frostbolt",
			"Assault a foe with a frosty ice lance. Each hit increases cold level. At level 3, enemy gains Frostbite, taking double damage from this spell.", "Ice", 3, 6,
			"frostbolt", "", -1, -1);

	private Ability focus = new Ability(
			"Focus",
			"Gain 'Focused' boon, increasing damage of next attack spell by 50%. Lose Mana Shield if active.", "Non-Damage", 2, 0,
			"focus", "", -1, -1);
	private Ability sliceAndDice = new Ability(
			"Slice and Dice",
			"Dashes to target and slashes. Does quadruple damage if speed is higher than enemy, or half damage if lower.", "Slashing", 10, 3,
			"slice and dice", "", -1, -1);
	private Ability cripple = new Ability(
			"Cripple",
			"A piercing stab. Consume 'Evasion' status to restore mana and reduce enemy speed by 30%.", "Piercing", 0, 2,
			"cripple", "", -1, -1);

	public HeroClass(String className){
		this.className = className;
		if (this.className.equals("Warrior")){
			abilityList.add(heroicStrike);
			abilityList.add(crushingBlow);
		}
		if (this.className.equals("Mage")){
			abilityList.add(frostBolt);
			abilityList.add(focus);
		}
		if (this.className.equals("Thief")){
			abilityList.add(sliceAndDice);
			abilityList.add(cripple);
		}

		//Maybe a time themed job that manipulates the turn variable to do more damage/take less damage.
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
	
