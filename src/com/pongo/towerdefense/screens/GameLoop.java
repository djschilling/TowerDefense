package com.pongo.towerdefense.screens;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.gl.Renderer;
import com.pongo.towerdefense.input.InputManager;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.Enemy;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Ninja;
import com.pongo.towerdefense.model.Panzer;
import com.pongo.towerdefense.model.Richtung;
import com.pongo.towerdefense.model.Tower;
import com.pongo.towerdefense.model.Tower1;
import com.pongo.towerdefense.model.Vector;

public class GameLoop implements GameScreen {

	public GameField field;
	public Renderer renderer;
	private InputManager inputManager;

	public GameLoop(GL10 gl, TowerDefense activity) {
		initalizeField2();

		field.startEnemies();
		inputManager = new InputManager(0, 0, field.width, field.height, field);
		renderer = new Renderer(gl, activity, field, inputManager);

	}

	private void initalizeField() {
		ArrayList<Vector> route = new ArrayList<Vector>();
		route.add(new Vector(0, 200, 0));
		route.add(new Vector(100, 200, 0));
		route.add(new Vector(100, 100, 0));
		route.add(new Vector(400, 100, 0));
		route.add(new Vector(400, 400, 0));
		route.add(new Vector(100, 400, 0));
		route.add(new Vector(100, 600, 0));
		route.add(new Vector(1000, 600, 0));
		route.add(new Vector(1000, 0, 0));

		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < 20; i++) {
			enemies.add(new Ninja(route, Richtung.Osten));
		}
		for (int i = 0; i < 4; i++) {
			enemies.add(new Panzer(route, Richtung.Osten));
		}

		field = new GameField(enemies, 2000, 1200);
		field.addBlocks(0, 20, 0, 20);
		field.addBlocks(30, 100, 30, 100);
		field.addTower(new Aussichtsturm(new Vector(50, 400, 0)));
		field.addTower(new Tower1(new Vector(1000, 400, 0)));
	}

	private void initalizeField2() {
		ArrayList<Vector> route = new ArrayList<Vector>();
		route.add(new Vector(0, 650, 0));
		route.add(new Vector(250, 650, 0));
		route.add(new Vector(250, 250, 0));
		route.add(new Vector(550, 250, 0));
		route.add(new Vector(550, 1000, 0));
		route.add(new Vector(200, 1000, 0));
		route.add(new Vector(200, 1300, 0));
		route.add(new Vector(1300, 1300, 0));
		route.add(new Vector(1300, 850, 0));
		route.add(new Vector(1500, 850, 0));
		route.add(new Vector(1500, 550, 0));
		route.add(new Vector(1050, 550, 0));
		route.add(new Vector(1050, 250, 0));
		route.add(new Vector(1750, 250, 0));
		route.add(new Vector(1750, 1600, 0));
		route.add(new Vector(1250, 1600, 0));
		route.add(new Vector(1250, 2000, 0));

		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < 50; i++) {
			enemies.add(new Ninja(route, Richtung.Osten));
		}

		field = new GameField(enemies, 2000, 2000);
		field.addBlocks(0, 600, 0, 200);
		field.addBlocks(0, 200, 200, 2000);
		field.addBlocks(300, 950, 300, 500);
		field.addBlocks(700, 950, 0, 300);
		field.addBlocks(950, 1350, 0, 150);
		field.addBlocks(1350, 2000, 0, 1200);
		field.addBlocks(1050, 1250, 250, 600);
		field.addBlocks(200, 1250, 600, 1000);
		field.addBlocks(700, 950, 0, 300);
		field.addBlocks(600, 1250, 1000, 1250);
		field.addBlocks(600, 800, 1250, 1450);
		field.addBlocks(300, 500, 1100, 1700);
		field.addBlocks(500, 900, 1550, 1700);
		field.addBlocks(900, 1350, 1350, 1700);
		field.addBlocks(1350, 1550, 1200, 1700);
		field.addBlocks(200, 1650, 1800, 2000);
		field.addBlocks(1650, 2000, 1300, 2000);
		
		
	}

	public GameLoop(GL10 gl, TowerDefense activity, GameField field) {
		this.field = field;
		renderer = new Renderer(gl, activity, field, inputManager);

	}

	@Override
	public void update(TowerDefense activity) {

		field.startAction(activity.getDeltaTime());

	}

	@Override
	public void render(GL10 gl, TowerDefense activity) {
		renderer.render(gl);

	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		renderer.dispose();

	}

	@Override
	public boolean inputTip(MotionEvent ev, TowerDefense activity) {
		return inputManager.tip(ev, activity);
	}

	@Override
	public boolean inputScroll(TowerDefense activity, int distanceX,
			int distanceY) {
		return inputManager.scroll(activity, distanceX, distanceY);
	}

}
