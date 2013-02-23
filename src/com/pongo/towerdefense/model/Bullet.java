package com.pongo.towerdefense.model;

public class Bullet {
	public Vector position;
	private Vector endPosition;
	private int speed;
	private Enemy enemy;
	private boolean explode;
	private int strength;
	private Vector startPosition;
	private Vector startToEnd;
	private float progress;
	public boolean noBullet;
	private float distance;
	private Tower tower;

	public Bullet(Vector position, int speed, Enemy enemy, int strength, Tower tower) {
		this.startPosition = position;
		this.speed = speed;
		this.enemy = enemy;
		explode = false;
		progress = 0;
		noBullet = false;
		this.position = new Vector(0, 0, 0);
		this.strength = strength;
		this.tower = tower;
		calculateEnd();
	}

	private void calculateEnd() {
		distance = startPosition.distanceTo(enemy.actualPosition);
		float timeToMove = distance / speed;
		saveEnemyData();
		if(enemy.update(timeToMove, true)){
			noBullet = true;
		}
		endPosition = new Vector(enemy.actualPosition.x, enemy.actualPosition.y, enemy.actualPosition.z);
		writeBackEnemyData();

		startToEnd = endPosition.minus(startPosition);

	}

	public Enemy update(float deltaTime) {
		progress += deltaTime;
		
		Vector.add(startPosition, startToEnd.multiply((progress * speed)/distance), position);
		if (startPosition.distanceTo(position) >= startPosition.distanceTo(endPosition)) {
			enemy.remainingLife -= strength;
			explode = true;
			if (enemy.remainingLife <= 0) {
				tower.currentEnemy = null;
				return enemy;
			}
		}
		return null;
	}

	public boolean isExploded() {
		return explode;
	}

	private Vector savedLastPosition;
	private Vector savedActualPosition;
	private int savedRouteProgress;
	private float savedProgress;
	private Richtung savedRichtung;
	private boolean savedAendertRichtung;

	private void writeBackEnemyData() {
		enemy.lastPosition.x = savedLastPosition.x;
		enemy.lastPosition.y = savedLastPosition.y;
		enemy.lastPosition.z = savedLastPosition.z;
		savedLastPosition = null;

		enemy.actualPosition.x = savedActualPosition.x;
		enemy.actualPosition.y = savedActualPosition.y;
		enemy.actualPosition.z = savedActualPosition.z;
		savedActualPosition = null;

		enemy.routeProgress = savedRouteProgress;
		enemy.progress = savedProgress;
		enemy.richtung = savedRichtung;
		enemy.aendertRichtung = savedAendertRichtung;

	}

	private void saveEnemyData() {
		savedLastPosition = new Vector(enemy.lastPosition.x, enemy.lastPosition.x, enemy.lastPosition.x);
		savedActualPosition = new Vector(enemy.actualPosition.x, enemy.actualPosition.y, enemy.actualPosition.z);
		savedRouteProgress = enemy.routeProgress;
		savedProgress = enemy.progress;
		savedRichtung = enemy.richtung;
		savedAendertRichtung = enemy.aendertRichtung;

	}
}
