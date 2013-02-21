package com.pongo.towerdefense.screens;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.gl.Renderer;
import com.pongo.towerdefense.input.InputManager;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.Block;
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
	private InputManager inputManager;

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
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(int i = 0; i < 10; i++){
			blocks.add(new Block(new Vector(i*20, 500, 0), 20, 20));
		}
		field = new GameField(enemies, blocks, 1000, 1000);
		field.addTower(new Aussichtsturm(new Vector(50, 400, 0)));
		field.addTower(new Tower1(new Vector(1000, 400, 0)));

		field.startEnemies();
		inputManager = new InputManager(0, 0, 2040, 1000,field);
		renderer = new Renderer(gl, activity, field);
		
	}

	public GameLoop(GL10 gl, TowerDefense activity, GameField field) {
		this.field = field;
		renderer = new Renderer(gl, activity, field);

	}

	@Override
	public void update(TowerDefense activity) {
		
		field.startAction(activity.getDeltaTime());

	}

	@Override
	public void render(GL10 gl, TowerDefense activity) {
		renderer.render(gl, activity, field, inputManager);

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
