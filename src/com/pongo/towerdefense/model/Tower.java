package com.pongo.towerdefense.model;

import java.util.ArrayList;

public class Tower {

	private int range;
	private int damage;
	private Enemy currentEnemy;
	public Vector position;
	private float reloadTime;
	private float waitedTime;

	public Tower(Vector position, int range, int damage, float reloadTime) {
		this.position = position;
		this.range = range;
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.waitedTime = 0;
	}
	
	public Enemy update(ArrayList<Enemy> enemies, float deltaTime){
		waitedTime+= deltaTime;
		if(waitedTime > reloadTime){
			waitedTime = waitedTime-reloadTime;
			
			if(currentEnemy == null || this.position.distanceTo(currentEnemy.actualPosition) > range){
				currentEnemy = null;
				for(Enemy enemy: enemies)
					if(this.position.distanceTo(enemy.actualPosition) <= range){
						this.currentEnemy = enemy;
						break;
				}
			}
			if(currentEnemy == null){
				return null;
			}
			currentEnemy.remainingLife-= damage;
			if(currentEnemy.remainingLife <= 0){
				Enemy enemy = currentEnemy;
				currentEnemy = null;
				return enemy;
			}
			return null;
		}
		return null;
	}
}