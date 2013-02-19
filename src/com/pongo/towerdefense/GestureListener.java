package com.pongo.towerdefense;

import com.pongo.towerdefense.screens.GameLoop;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

	TowerDefense towerDefense;

	public GestureListener(TowerDefense towerDefense) {
		super();
		this.towerDefense = towerDefense;

	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent ev) {
		if (towerDefense.screen instanceof GameLoop) {
			GameLoop loop = (GameLoop) towerDefense.screen;
			loop.field.touchX = (int) (ev.getX() * ev.getXPrecision());
			loop.field.touchY = (int) (towerDefense.getHeight() - ev.getY() *ev.getXPrecision());
			loop.field.isTouched = true;

		}
		return true;
	}
}
