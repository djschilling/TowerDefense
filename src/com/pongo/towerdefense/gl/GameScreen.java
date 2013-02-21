package com.pongo.towerdefense.gl;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import com.pongo.towerdefense.Input;
import com.pongo.towerdefense.TowerDefense;

public interface GameScreen {
	public boolean inputScroll(TowerDefense activity, int distanceX, int distanceY);
	public boolean inputTip(MotionEvent ev, TowerDefense activity);
	public void update(TowerDefense activity);
	public void render(GL10 gl, TowerDefense activity);
	public boolean isDone();
	public void dispose();

}
