package com.pongo.towerdefense.input;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.pongo.towerdefense.GameScreen;
import com.pongo.towerdefense.TowerDefense;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
	private TowerDefense towerDefense;
	public int screenX;
	public int screenY;
	private GameScreen screen;

	public GestureListener(TowerDefense towerDefense, int screenX, int screenY) {
		super();
		this.towerDefense = towerDefense;
		this.screenX = screenX;
		this.screenY = screenY;

	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent ev) {
		if (screen != null) {
			return screen.inputTip(ev, towerDefense);
		}
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		if (screen != null) {
			return screen.inputScroll(towerDefense, (int) distanceX,
					(int) distanceY);
		}
		return false;
	}

	public void setScreen(GameScreen screen) {
		this.screen = screen;
	}

}
