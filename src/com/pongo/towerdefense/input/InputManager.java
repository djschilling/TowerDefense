package com.pongo.towerdefense.input;

import android.view.MotionEvent;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Vector;

public class InputManager {

	public int screenX;
	public int screenY;
	public int fieldWidth;
	public int fieldHeight;
	private GameField field;

	public InputManager(int screenX, int screenY, int fieldWidth,
			int fieldHeight, GameField field) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.field = field;
	}

	public boolean scroll(TowerDefense activity, int distanceX, int distanceY) {
		int distanceYCorrect = - distanceY;
		int newX = (int) (screenX + distanceX);
		int newY = (int) (screenY + distanceYCorrect);
		if (newX + activity.getWidth() <= fieldWidth && newX >= 0) {
			screenX = newX;
		}
		if (newY + activity.getHeight() <= fieldHeight && newY >= 0) {

			screenY = newY;
		}

		return true;
	}

	public boolean tip(MotionEvent ev, TowerDefense activity) {
		field.towerToBuild.add((new Aussichtsturm(new Vector(ev.getX() + screenX,
				(activity.getHeight() - ev.getY()) + screenY, 0))));

		return true;

	}

}
