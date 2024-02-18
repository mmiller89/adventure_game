Author: Michael Miller

This project was inspired by my love for role playing games. It is a simple turn based RPG adventure that is still in it's infancy.

LANGUAGES/FRAMEWORKS: Java

SKILLS: Uses principles of OOP to manage classes, heroes, enemies, game states and more.

LESSONS AND CHALLENGES: As more classes and files are added, I am learning how to manage a larger application. I also want to go through the code to clean up certain portions and have every class managing itself better.

FUTURE GOALS: I would like to use a Java Framework to move this game onto some sort of GUI.

HOW TO USE: This game runs in the console. It is a work in progress and not all features are available.






Class Guide

Warrior
********
Warriors have higher health, attack, and defense, but poor speed and mana. They are one of the easier classes to start with.

Battle Options:
Attack - Damages and restores health (25% of attack damage).
Defend - Reduces damage taken by enemy's next attack by 75%.
Heroic Strike - Damages enemy and grants player "Enhance" boon, causing next Attack to do double damage.
Crushing Strike - High damage attack, but reduces player's strength by 40% for remainder of battle (this will probably be tuned down soon).

Strategy:
Warrior can approach combat in two ways. You can alternate Heroic Strike/Attack to do good damage and keep yourself healthy. A riskier approach is to weave in Crushing Strike, especially if the enemy is low on health.


Mage
********
Mages have very high mana. Their remaining stats are mediocre.

Battle Options:
Attack - Damages and restore's 20% of maximum mana.
Mana Shield - Reduces damage taken by 50% while active, but lose 10% mana when attacked. If Mage has 0 mana, Mana Shield is removed.
Frostbolt - Damages enemy and stacks the status "Chill." When Chill goes past stack 2, it becomes Frostbite. Enemies with Frostbite will take double damage from Frostbolt.
Focus - Removes Mana Shield, next damaging spell does 50% more damage.

Strategy:
Get Mana Shield up immediately, as Mage's will die quickly without it. Stack Frostbolt until Frostbite is on the enemy, and then use Focus and Frostbolt again for triple damage. This is the basic combo, but it is important to weave in melee attacks to keep your mana up for Mana Shield. Using Focus leaves you vulnerable, so it's also a high risk tactic.

If the enemy has powerful attacks, it's advisable to forgo Focus entirely unless you spot an opening in their strategy (such as when they are healing or performing non-damaging actions).



Thief
********
Thiefs have high speed, decent health, and mediocre remaining stats.

Battle Options:
Attack - Damages and has a 25% chance to give player "Evasion." While active, players have a 50% chance to dodge attacks.
Defend - Reduces damage taken by enemy's next attack by 75%.
Slice and Dice - Does 4x damage if Thief's speed is higher than enemies, or half if slower. High mana cost.
Cripple - Low damage attack. If under "Evasion", reduces enemy speed by 20% and restores all mana, consuming "Evasion."

Strategy:
Thieves can go about combat in several ways. If you are faster than the enemy naturally, you can use Slice and Dice immediately. If not, it isn't worth using the ability, and you are better off using attack until Evasion activates, then Cripple to start lowering the enemy's speed. Slice and Dice has a high mana cost, so in longer fights you will need to use the Evasion/Cripple combo to restore your mana.

If the enemy is particularly powerful, you can also try to activate Evasion and then keep using Defend until the enemy has spent all their mana.


