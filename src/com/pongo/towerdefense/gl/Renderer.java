package com.pongo.towerdefense.gl;

import java.util.ArrayList;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.input.InputManager;
import com.pongo.towerdefense.model.Block;
import com.pongo.towerdefense.model.Enemy;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Richtung;
import com.pongo.towerdefense.model.Tower;
import com.pongo.towerdefense.tools.Mesh;
import com.pongo.towerdefense.tools.Mesh.PrimitiveType;

public class Renderer {

	private Mesh enemy;
	private Mesh tower;
	private Mesh block;
	private Mesh linie;
	private GameField field;

	public Renderer(GL10 gl, TowerDefense activity, GameField field) {
		this.field = field;
		enemy = new Mesh(gl, 3, true, false, false);
		enemy.color(0, 1, 0, 1);
		enemy.vertex(0, 0, 0);
		enemy.color(0, 1, 0, 1);
		enemy.vertex(50, 0, 0);
		enemy.color(0, 1, 0, 1);
		enemy.vertex(25, 50, 0);

		block = new Mesh(gl, 4, false, false, false);
		block.vertex(0, 20, 0);
		block.vertex(20, 20, 0);
		block.vertex(20, 0, 0);
		block.vertex(0, 0, 0);

		tower = new Mesh(gl, 1, true, false, false);
		tower.color(1, 0, 0, 1);
		tower.vertex(0, 0, 0);

//		linie = new Mesh(gl, 2, true, false, false);
//		linie.color(255, 140, 0, 1);
//		linie.vertex(25, 25, 0);
//		linie.color(255, 140, 0, 1);
//		linie.vertex(700, 700, 0);
//		linie.color(255, 140, 0, 1);
//		linie.vertex(400, 400, 0);
//		linie.color(255, 140, 0, 1);
//		linie.vertex(150, 450, 0);

	}

	public void render(GL10 gl, TowerDefense activity, GameField field,
			InputManager inputManager) {
		gl.glViewport(0, 0, activity.getWidth(), activity.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, inputManager.screenX, inputManager.screenX
				+ activity.getWidth(), inputManager.screenY,
				inputManager.screenY + activity.getHeight());

		// gl.glMatrixMode(GL10.GL_MODELVIEW);
		// gl.glLoadIdentity();
		//
		// GLU.gluLookAt(gl, inputManager.screenX, inputManager.screenY, 1,
		// inputManager.screenX, inputManager.screenY, 0, 0, 1, 0);

		renderEnemies(gl, field.getWalkingEnemies());
		renderTower(gl, field.getTower());

		renderBlocks(gl);
//		renderLinie(gl);

	}

	private void renderLinie(GL10 gl) {
		gl.glLineWidth(10);
		linie.render(PrimitiveType.LineStrip);

	}

	private void renderBlocks(GL10 gl) {
		for (Block actualBlock : field.blocks) {
			gl.glPushMatrix();
			gl.glTranslatef(actualBlock.position.x, actualBlock.position.y,
					actualBlock.position.z);

			block.render(PrimitiveType.TriangleFan);
			gl.glPopMatrix();

		}

	}

	private void renderEnemies(GL10 gl, ArrayList<Enemy> enemies) {
		for (Enemy actualEnemy : enemies) {
			gl.glPushMatrix();
			gl.glTranslatef(actualEnemy.actualPosition.x,
					actualEnemy.actualPosition.y, actualEnemy.actualPosition.z);

			if (actualEnemy.richtung == Richtung.Osten) {
				gl.glRotatef(-90, 0, 0, 1);
			} else if (actualEnemy.richtung == Richtung.Sueden) {

				gl.glRotatef(180, 0, 0, 1);
			} else if (actualEnemy.richtung == Richtung.Westen) {

				gl.glRotatef(90, 0, 0, 1);
			}
			enemy.render(PrimitiveType.Triangles);
			gl.glPopMatrix();
		}

	}

	private void renderTower(GL10 gl, Vector<Tower> towerList) {

		gl.glPointSize(30);
		for (Tower actualTower : towerList) {
			gl.glPushMatrix();
			gl.glTranslatef(actualTower.position.x, actualTower.position.y,
					actualTower.position.z);
			tower.render(PrimitiveType.Points);
			gl.glPopMatrix();
		}
	}

	public void dispose() {
		enemy.dispose();
		tower.dispose();
	}
}
