package main.entities;

import main.interfaces.Drawable;

public abstract class Entity implements Drawable {

    protected String name;
	protected int health, movespeed, attack, defence, experience, level;

	public Entity() {
		this("Monster", 10, 30, 3, 0, 0, 0);
	}
	
	public Entity(String name) {
		this(name, 10, 30, 3, 0, 0, 0);
	}
	
	public Entity(String name, int health, int movespeed, int attack, int defence, int experience, int level) {
		this.name = name;
		this.health = health;
		this.movespeed = movespeed;
		this.attack = attack;
		this.defence = defence;
		this.experience = experience;
		this.level = level;
	}
	
	public String getName() 	{ return name; }
	public int getHealth() 		{ return health; }
	public int getMovespeed()	{ return movespeed; }
	public int getAttack()		{ return attack; }
	public int getDefence()		{ return defence; }
	public int getExperience()	{ return experience; }
	public int getLevel()		{ return level; }
	
	public void setName(String name) 			{ this.name = name; }
	public void setHealth(int health)			{ this.health = health; }
	public void setMovespeed(int movespeed)		{ this.movespeed = movespeed; }
	public void setAttack(int attack)			{ this.attack = attack; }
	public void setDefence(int defence)			{ this.defence = defence; }
	public void setExperience(int experience)	{ this.experience = experience; }
	public void setLevel(int level)				{ this.level = level; }

}
