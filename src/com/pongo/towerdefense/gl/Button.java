package com.pongo.towerdefense.gl;

import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.tools.Font;
import com.pongo.towerdefense.tools.Vector;
import com.pongo.towerdefense.tools.Font.FontStyle;
import com.pongo.towerdefense.tools.Font.Text;
import com.pongo.towerdefense.tools.OwnMesh;

public class Button {
	private OwnMesh background;
	private String inhalt;
	private Font font;
	private Text text;
	public Button(GL10 gl, String inhalt, TowerDefense activity){
		this.inhalt = inhalt;
		
		background = new OwnMesh(gl, 4, true, false);
		background.setVertex(0, 0, 0);
		background.setVertex(150, 0, 0);
		background.setVertex(150, 70, 0);
		background.setVertex(0, 70, 0);
		background.setColor(0, 0, 0, 1);
		background.setColor(0, 0, 0, 1);
		background.setColor(0, 0, 0, 1);
		background.setColor(0, 0, 0, 1);
		
		font = new Font(gl, activity.getAssets(), "Times New Roman.ttf", 30, FontStyle.Bold);
		text = font.newText(gl);
		
		
	}
	
	public void render(GL10 gl, Vector position){
		gl.glPushMatrix();
		gl.glTranslatef(position.x, position.y, 0);
		background.render(GL10.GL_TRIANGLE_FAN);
		gl.glPopMatrix();
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glPushMatrix();
		gl.glTranslatef(position.x + 3, position.y + 50, 0);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		text.setText(inhalt);
		text.render();
		gl.glPopMatrix();
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}

}
