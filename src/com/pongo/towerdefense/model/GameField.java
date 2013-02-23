package com.pongo.towerdefense.model;

import java.util.ArrayList;
import java.util.Vector;

import com.pongo.towerdefense.Input;
import com.pongo.towerdefense.TouchMode;

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
	public final int BLOCK_SIZE = 10;

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
	}

	public void addBlocks(int bottom, int top, int left, int right) {
		blocks.add(new Block(new com.pongo.towerdefense.model.Vector(left, bottom, 0), right-left, top-bottom));
	}

	private void fireTowers(float deltaTime) {
		for (Tower actualTower : tower) {
			Enemy enemy = actualTower.update(walkingEnemies, deltaTime);
			if (enemy != null) {
				walkingEnemies.remove(enemy);
				deadEnemies.add(enemy);
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

		if (startEnemies == true && waitingEnemies.size() > 0
				&& totalTime > enemyCounter * 3) {
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
}
