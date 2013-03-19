package com.pongo.towerdefense.input;

import android.view.MotionEvent;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.gl.Renderer;
import com.pongo.towerdefense.model.Aussichtsturm;
import com.pongo.towerdefense.model.BuildingGround;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.tools.Vector;

public class InputManager {
	public enum SPIELABLAUF{
		AUSGANG,TURMAUSWAHL, BESTAETIGUNG
	}

	public int screenX;
	public int screenY;
	public int fieldWidth;
	public int fieldHeight;
	private GameField field;
	public SPIELABLAUF spielablauf;
	private BuildingGround buildingGround;
	private Renderer renderer;
	
	public InputManager(int screenX, int screenY, int fieldWidth, int fieldHeight, GameField field) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.field = field;
		spielablauf = SPIELABLAUF.AUSGANG;
		
	}

	public boolean scroll(TowerDefense activity, int distanceX, int distanceY) {
		int distanceYCorrect = -distanceY;
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
		switch (spielablauf) {
		case AUSGANG:
			ausgang(ev, activity);
			break;
		case TURMAUSWAHL:
			turmauswahl();
			break;
		case BESTAETIGUNG:
			
			break;

		default:
			break;
		}
		

		return true;

	}

	private void turmauswahl() {
		// TODO Auto-generated method stub
		
	}

	private void ausgang(MotionEvent ev, TowerDefense activity) {
		
		int x = (int) ev.getX() + screenX;
		int y = activity.getHeight() - (int) ev.getY() + screenY;
		buildingGround = field.isOnBuildingGround(new Vector(x, y, 0));
		if(buildingGround != null){
			if(!buildingGround.occupied){
				
				
				field.towerToBuild.add((new Aussichtsturm(new Vector(buildingGround.position.x + buildingGround.WIDTH/2, buildingGround.position.y + buildingGround.HEIGHT/2, 0))));
				buildingGround.occupied = true;
			} 
		} else {
			renderer.now = !renderer.now;
		}
	}
	public void setRenderer(Renderer renderer){
		this.renderer = renderer;
	}
	


}

