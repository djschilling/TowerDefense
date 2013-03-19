package com.pongo.towerdefense.model;

import java.util.ArrayList;

import com.pongo.towerdefense.tools.Vector;

public class Tower {

	private int range;
	private int damage;
	public Enemy currentEnemy;
	public Vector position;
	public float reloadTime;
	public float waitedTime;

	public Tower(Vector position, int range, int damage, float reloadTime) {
		this.position = position;
		this.range = range;
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.waitedTime = 0;
	}

	public Bullet update(ArrayList<Enemy> enemies, float deltaTime) {
		waitedTime += deltaTime;
		if(waitedTime >= reloadTime){

			if (currentEnemy == null || this.position.distanceTo(currentEnemy.actualPosition) > range || currentEnemy.remainingLife <= 0 || !currentEnemy.onField) {
				currentEnemy = null;
				for (Enemy enemy : enemies)
					if (this.position.distanceTo(enemy.actualPosition) <= range) {
						int damageOnThisEnemy = 0;
						for (Bullet bullet : enemy.incomingBullets) {
							damageOnThisEnemy += bullet.strength;
						}
						if (damageOnThisEnemy < enemy.totalLife) {
							this.currentEnemy = enemy;
							break;
						}
					}
			}
			if (currentEnemy == null) {
				return null;
			} else {
				waitedTime = 0;
				return new Bullet(new Vector(position.x, position.y, position.z), 300, currentEnemy, this.damage, this);
			}
		}
		return null;
	}
}