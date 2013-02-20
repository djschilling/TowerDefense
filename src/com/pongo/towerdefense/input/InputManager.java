package com.pongo.towerdefense.input;

import com.pongo.towerdefense.Input;
import com.pongo.towerdefense.TouchMode;
import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Vector;

public class InputManager {
	
	public int screenX;
	public int screenY;
	public int fieldWidth;
	public int fieldHeight;
	private Input input;
	private GameField field;
	
	public InputManager(int screenX, int screenY, int fieldWidth, int fieldHeight, Input input, GameField field){
		this.screenX = screenX;
		this.screenY = screenY;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.input = input;
		this.field = field;
	}
	
	public void update(TowerDefense activity){
		if (input.touchMode == TouchMode.EndTip) {
			field.addTower((new Aussichtsturm(new Vector(input.firstTouchX+screenX,
					input.firstTouchY+screenY, 0))));
			input.touchMode = TouchMode.No;
		}
		if(input.touchMode == TouchMode.Scroll || input.touchMode == TouchMode.EndScroll){
			int newX = screenX + input.prevTouchX -input.touchX;
			int newY = screenY + input.prevTouchY -input.touchY;
			if(newX + activity.getWidth() <= fieldWidth && newX >= 0){				
				screenX = newX;
			}
			if(newY +activity.getHeight() <= fieldHeight && newY >= 0){
				
				screenY = newY;
			}
			if(input.touchMode == TouchMode.EndScroll){
				input.touchMode = TouchMode.No;
			}
		}
	}

}
