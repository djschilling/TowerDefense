package com.pongo.towerdefense.screens;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.Input;
import com.pongo.towerdefense.TouchMode;
import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.gl.Renderer;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.Enemy;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Ninja;
import com.pongo.towerdefense.model.Panzer;
import com.pongo.towerdefense.model.Richtung;
import com.pongo.towerdefense.model.Tower1;
import com.pongo.towerdefense.model.Vector;

public class GameLoop implements GameScreen {

	public GameField field;
	public Renderer renderer;

	public GameLoop(GL10 gl, TowerDefense activity) {
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
		field = new GameField(enemies, activity.input);
		field.addTower(new Aussichtsturm(new Vector(50, 400, 0)));
		field.addTower(new Tower1(new Vector(1000, 400, 0)));

		field.startEnemies();
		renderer = new Renderer(gl, activity);
	}

	public GameLoop(GL10 gl, TowerDefense activity, GameField field) {
		this.field = field;
		renderer = new Renderer(gl, activity);

	}

	@Override
	public void update(TowerDefense activity) {
		
		field.startAction(activity.getDeltaTime());

	}

	@Override
	public void render(GL10 gl, TowerDefense activity) {
		renderer.render(gl, activity, field);

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

}
