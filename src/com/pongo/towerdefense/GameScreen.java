package com.pongo.towerdefense;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;


public interface GameScreen {
	public boolean inputScroll(TowerDefense activity, int distanceX, int distanceY);
	public boolean inputTip(MotionEvent ev, TowerDefense activity);
	public void update(TowerDefense activity);
	public void render(GL10 gl, TowerDefense activity);
	public boolean isDone();
	public void dispose();

}
