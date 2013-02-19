package com.pongo.towerdefense.gl;

import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.TowerDefense;

public interface GameScreen {
	public void update(TowerDefense activity);
	public void render(GL10 gl, TowerDefense activity);
	public boolean isDone();
	public void dispose();

}
