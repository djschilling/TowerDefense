package com.pongo.towerdefense.model;

import java.util.ArrayList;

import com.pongo.towerdefense.tools.Vector;

public class GameField {

	private ArrayList<Enemy> waitingEnemies;
	private ArrayList<Enemy> walkingEnemies;
	private ArrayList<Enemy> lostEnemies;
	private ArrayList<Enemy> deadEnemies;
	private boolean startEnemies;
	private ArrayList<Tower> tower;
	public ArrayList<Tower> towerToBuild;
	private float totalTime;
	private float enemyCounter;
	public boolean isTouched;
	public ArrayList<Block> blocks;
	public int width;
	public int height;
	public ArrayList<Bullet> bullets;
	public ArrayList<Bullet> explodingBullets;
	public ArrayList<BuildingGround> buildingGrounds;

	public GameField(ArrayList<Enemy> enemies, int width, int height) {
		this.startEnemies = false;
		this.waitingEnemies = enemies;
		this.walkingEnemies = new ArrayList<Enemy>();
		this.lostEnemies = new ArrayList<Enemy>();
		this.tower = new ArrayList<Tower>();
		this.deadEnemies = new ArrayList<Enemy>();
		this.totalTime = 0;
		this.enemyCounter = 0;
		this.blocks = new ArrayList<Block>();
		this.width = width;
		this.height = height;
		this.towerToBuild = new ArrayList<Tower>();
		this.bullets = new ArrayList<Bullet>();
		this.explodingBullets = new ArrayList<Bullet>();
		this.buildingGrounds = new ArrayList<BuildingGround>();
	}

	public void startEnemies() {
		startEnemies = true;
	}

	public void addTower(Tower tower) {
		this.tower.add(tower);
	}

	public void startAction(float deltaTime) {
		totalTime += deltaTime;
		if (towerToBuild.size() > 0) {
			tower.addAll(towerToBuild);
			towerToBuild.clear();
		}

		moveEnemies(deltaTime);
		fireTowers(deltaTime);
		updateBullets(deltaTime);
	}

	private void updateBullets(float deltaTime) {

		if (explodingBullets.size() >= 0) {
			explodingBullets.clear();
		}
		ArrayList<Bullet> bulletsToDestroy = new ArrayList<Bullet>();
		for (Bullet bullet : bullets) {
			Enemy enemy = bullet.update(deltaTime);
			if (bullet.isExploded()) {
				bulletsToDestroy.add(bullet);
				explodingBullets.add(bullet);
			}

			if (enemy != null) {
				walkingEnemies.remove(enemy);
				deadEnemies.add(enemy);
			}

		}
		for (Bullet bullet : bulletsToDestroy) {
			bullets.remove(bullet);
		}

	}

	public void addBlocks(int bottom, int top, int left, int right) {
		blocks.add(new Block(new com.pongo.towerdefense.tools.Vector(left, bottom, 0), right - left, top - bottom));
	}

	private void fireTowers(float deltaTime) {
		for (Tower actualTower : tower) {
			Bullet bullet = actualTower.update(walkingEnemies, deltaTime);
			if (bullet != null && !bullet.noBullet) {
				bullets.add(bullet);
			}
		}

	}

	private void moveEnemies(float deltaTime) {
		if (!walkingEnemies.isEmpty()) {
			ArrayList<Enemy> toDelete = new ArrayList<Enemy>();
			for (Enemy enemy : walkingEnemies) {
				boolean last = enemy.update(deltaTime, true);
				if (last) {
					toDelete.add(enemy);
					lostEnemies.add(enemy);
					enemy.onField = false;
				}
			}
			if (!toDelete.isEmpty()) {
				for (Enemy enemy : toDelete) {
					walkingEnemies.remove(enemy);
				}
			}
		}

		if (startEnemies == true && waitingEnemies.size() > 0 && totalTime > enemyCounter * 1.5) {
			Enemy enemy = waitingEnemies.remove(0);
			enemy.update(deltaTime, true);
			walkingEnemies.add(enemy);
			enemyCounter++;
		}
	}

	public ArrayList<Enemy> getWalkingEnemies() {
		// TODO Auto-generated method stub
		return walkingEnemies;
	}

	public ArrayList<Tower> getTower() {
		// TODO Auto-generated method stub
		return tower;
	}

	public void addBlock(Block block) {
		blocks.add(block);
	}

	public void addBuildingGround(BuildingGround buildingGround) {
		buildingGrounds.add(buildingGround);
	}

	public BuildingGround isOnBuildingGround(Vector input) {
		for (BuildingGround currentBuildingGround : buildingGrounds) {
			if (Vector.isIn(input, currentBuildingGround.position.x, currentBuildingGround.position.y, currentBuildingGround.position.x + currentBuildingGround.WIDTH, currentBuildingGround.position.y + currentBuildingGround.HEIGHT)) {
				return currentBuildingGround;
			}
		}
		return null;
	}
}
