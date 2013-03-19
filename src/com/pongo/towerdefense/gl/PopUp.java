package com.pongo.towerdefense.gl;

import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.tools.OwnMesh;
import com.pongo.towerdefense.tools.Vector;

public class PopUp {
	public enum BUTTON{
		CANCEL, BUILD
	}

	private OwnMesh background;
	private Button buttonBuild;
	private Button buttonCancel;
	private TowerDefense activity;

	public PopUp(GL10 gl, TowerDefense activity) {
		this.activity = activity;
		background = new OwnMesh(gl, 4, false, false);
		background.setVertex(0, 0, 0);
		background.setVertex(400, 0, 0);
		background.setVertex(400, 300, 0);
		background.setVertex(0, 300, 0);
	}
	
	public void addButton(GL10 gl,BUTTON button){
		if(button == BUTTON.BUILD){
			buttonBuild = new Button(gl, "Build", activity);
		} else if(button == BUTTON.CANCEL){
			buttonCancel = new Button(gl, "Cancel", activity);
		}
	}

	public void render(GL10 gl, Vector position) {
		gl.glPushMatrix();
		gl.glTranslatef(position.x, position.y, 0);
		background.render(GL10.GL_TRIANGLE_FAN);
		gl.glPopMatrix();
		if(buttonBuild != null){
			buttonBuild.render(gl, new Vector(position.x + 200 , position.y + 3 , 0));
		} 
		if(buttonCancel != null){
			buttonCancel.render(gl, new Vector(position.x + 3, position.y + 3, 0));
		}
	}
}
