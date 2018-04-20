package main.entities;

public abstract class Entity {

	int health, movespeed, attack, defence, experience, level;
	
	String name;
	
	public Entity() {
		this("Monster", 10, 30, 3, 0, 0, 0);
	}
	
	public Entity(String name) {
		this(name, 10, 30, 3, 0, 0, 0);
	}
	
	public Entity(String name, int health, int movespeed,
					int attack, int defence, int experience,
					int level) {
		this.name = name;
		this.health = health;
		this.movespeed = movespeed;
		this.attack = attack;
		this.defence = defence;
		this.experience = experience;
		this.level = level;
	}
	
	public String getName() 	{return name;}
	public int getHealth() 		{return health;}
	public int getMovespeed()	{return movespeed;}
	public int getAttack()		{return attack;}
	public int getDefence()		{return defence;}
	public int getExperience()	{return experience;}
	public int getLevel()		{return level;}
	
	public void setName(String newName) {name = newName;}
	public void setHealth(int newHP)	{health = newHP;}
	public void setMovespeed(int newMS)	{movespeed = newMS;}
	public void setAttack(int newATK)	{attack = newATK;}
	public void setDefence(int newDEF)	{defence = newDEF;}
	public void setExperience(int newXP){experience = newXP;}
	public void setLevel(int newLV)		{level = newLV;}
}
