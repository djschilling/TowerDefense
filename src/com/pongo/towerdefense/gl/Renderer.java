package com.pongo.towerdefense.gl;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.pongo.towerdefense.TowerDefense;
import com.pongo.towerdefense.gl.PopUp.BUTTON;
import com.pongo.towerdefense.input.InputManager;
import com.pongo.towerdefense.model.Block;
import com.pongo.towerdefense.model.BuildingGround;
import com.pongo.towerdefense.model.Bullet;
import com.pongo.towerdefense.model.Enemy;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.model.Richtung;
import com.pongo.towerdefense.model.Tower;
import com.pongo.towerdefense.tools.Font;
import com.pongo.towerdefense.tools.Font.FontStyle;
import com.pongo.towerdefense.tools.Font.Text;
import com.pongo.towerdefense.tools.Texture.TextureFilter;
import com.pongo.towerdefense.tools.OwnMesh;
import com.pongo.towerdefense.tools.Texture;
import com.pongo.towerdefense.tools.Vector;
import com.pongo.towerdefense.tools.Texture.TextureWrap;

public class Renderer {

	private OwnMesh enemy;
	private OwnMesh tower;
	private ArrayList<OwnMesh> blocks;
	private OwnMesh bullet;
	private Font font;
	private Text framesPerSecond;
	private InputManager inputManager;
	private TowerDefense activity;
	private GameField field;
	private OwnMesh test;
	private Texture testTexture;
	private Bitmap bitmap;
	private Texture enemyTexture;
	private OwnMesh buildingGround;
	private Texture buildingGroundTexture;
	public boolean now;
	public boolean initialized;
	private PopUp popUp;

	public Renderer(GL10 gl, TowerDefense activity, GameField field, InputManager manager) {
		this.inputManager = manager;
		this.activity = activity;
		this.field = field;
		now = false;
		initialized = false;

		initializeEnemy(gl);
		initializeBlocks(gl);
		initializeBuildingGround(gl);
		initializeTower(gl);
		initializeBullet(gl);
		initalizeFont(gl);
		popUp = new PopUp(gl, activity);
		popUp.addButton(gl, BUTTON.BUILD);
		popUp.addButton(gl, BUTTON.CANCEL);
		initialized = true;

	}

	private void initializeBuildingGround(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		buildingGround = new OwnMesh(gl, 4, false, true);
		buildingGround.setVertex(0, 0, 0);
		buildingGround.setVertex(100, 0, 0);
		buildingGround.setVertex(100, 100, 0);
		buildingGround.setVertex(0, 100, 0);
		buildingGround.setTexture(0, 1);
		buildingGround.setTexture(1, 1);
		buildingGround.setTexture(1, 0);
		buildingGround.setTexture(0, 0);

		try {
			bitmap = BitmapFactory.decodeStream(activity.getAssets().open("buildingGround.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		buildingGroundTexture = new Texture(gl, bitmap, TextureFilter.MipMap, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
		bitmap.recycle();

	}

	private void initializeBullet(GL10 gl) {
		bullet = new OwnMesh(gl, 4, false, false);
		bullet.setVertex(0, 0, 0);

	}

	private void initalizeFont(GL10 gl) {
		font = new Font(gl, activity.getAssets(), "Times New Roman.ttf", 50, FontStyle.Bold);
		framesPerSecond = font.newText(gl);
	}

	private void initializeTower(GL10 gl) {
		tower = new OwnMesh(gl, 4, true, false);
		tower.setVertex(-50, -50, 0);
		tower.setVertex(50, -50, 0);
		tower.setVertex(50, 50, 0);
		tower.setColor(1, 0, 0, 1);
		tower.setVertex(-50, 50, 0);
		tower.setColor(1, 0, 0, 1);
		tower.setColor(1, 0, 0, 1);
		tower.setColor(1, 0, 0, 1);
	}

	private void initializeBlocks(GL10 gl) {
		blocks = new ArrayList<OwnMesh>();
		for (Block actualBlock : field.blocks) {
			blocks.add(new OwnMesh(gl, 4, true, false));
			int size = blocks.size();
			blocks.get(size - 1).setVertex(actualBlock.position.x, actualBlock.position.y + actualBlock.height, 0);
			blocks.get(size - 1).setVertex(actualBlock.position.x + actualBlock.width, actualBlock.position.y + actualBlock.height, 0);
			blocks.get(size - 1).setVertex(actualBlock.position.x + actualBlock.width, actualBlock.position.y, 0);
			blocks.get(size - 1).setVertex(actualBlock.position.x, actualBlock.position.y, 0);
			blocks.get(size - 1).setColor(0.5f, 0.2f, 0.05f, 1f);
			blocks.get(size - 1).setColor(0.5f, 0.2f, 0.05f, 1f);
			blocks.get(size - 1).setColor(0.5f, 0.2f, 0.05f, 1f);
			blocks.get(size - 1).setColor(0.5f, 0.2f, 0.05f, 1f);
		}
	}

	private void initializeEnemy(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		enemy = new OwnMesh(gl, 4, false, true);
		enemy.setVertex(-15, -15, 0);
		enemy.setVertex(15, -15, 0);
		enemy.setVertex(15, 15, 0);
		enemy.setVertex(-15, 15, 0);
		enemy.setTexture(0, 1);
		enemy.setTexture(1, 1);
		enemy.setTexture(1, 0);
		enemy.setTexture(0, 0);

		try {
			bitmap = BitmapFactory.decodeStream(activity.getAssets().open("ic_launcher-web.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		enemyTexture = new Texture(gl, bitmap, TextureFilter.MipMap, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
		bitmap.recycle();
	}

	public void render(GL10 gl) {

		gl.glViewport(0, 0, activity.getWidth(), activity.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		set2DProjection(gl);

		GLU.gluLookAt(gl, inputManager.screenX, inputManager.screenY, 1, inputManager.screenX, inputManager.screenY, 0, 0, 1, 0);

		renderBlocks(gl);
		renderBuildingGround(gl);
		renderEnemies(gl, field.getWalkingEnemies());
		renderTower(gl, field.getTower());
		renderBullet(gl, field.bullets);
		if (now) {
			
			popUp.render(gl, new Vector(100, 100, 0));
		}

		renderText(gl, activity.framesPerSecond);

	}

	private void renderBuildingGround(GL10 gl) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		buildingGroundTexture.bind();
		for (BuildingGround currentBuildingGround : field.buildingGrounds) {
			if (!currentBuildingGround.occupied) {
				gl.glPushMatrix();

				gl.glTranslatef(currentBuildingGround.position.x, currentBuildingGround.position.y, currentBuildingGround.position.z);
				buildingGround.render(GL10.GL_TRIANGLE_FAN);
				gl.glPopMatrix();
			}
		}
		gl.glDisable(GL10.GL_TEXTURE_2D);

	}

	private void set2DProjection(GL10 gl) {
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, 0, activity.getWidth(), 0, +activity.getHeight());

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	private void renderBullet(GL10 gl, ArrayList<Bullet> bullets) {
		gl.glPointSize(10);
		for (Bullet bullet : bullets) {
			gl.glPushMatrix();
			gl.glTranslatef(bullet.position.x, bullet.position.y, bullet.position.z);
			this.bullet.render(GL10.GL_POINTS);
			gl.glPopMatrix();
		}

	}

	private void renderText(GL10 gl, int framesPerSecond) {
		set2DProjection(gl);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glPushMatrix();
		gl.glTranslatef(activity.getWidth() - 100, activity.getHeight() - 30, 0);

		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		this.framesPerSecond.setText(String.valueOf(framesPerSecond));
		this.framesPerSecond.render();
		gl.glPopMatrix();
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);

	}

	private void renderBlocks(GL10 gl) {
		for (OwnMesh actualBlock : blocks) {
			actualBlock.render(GL10.GL_TRIANGLE_FAN);

		}
	}

	private void renderEnemies(GL10 gl, ArrayList<Enemy> enemies) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		enemyTexture.bind();
		for (Enemy actualEnemy : enemies) {
			gl.glPushMatrix();
			gl.glTranslatef(actualEnemy.actualPosition.x, actualEnemy.actualPosition.y, actualEnemy.actualPosition.z);
			if (actualEnemy.richtung == Richtung.Osten) {
				gl.glRotatef(-90, 0, 0, 1);
			} else if (actualEnemy.richtung == Richtung.Sueden) {

				gl.glRotatef(180, 0, 0, 1);
			} else if (actualEnemy.richtung == Richtung.Westen) {

				gl.glRotatef(90, 0, 0, 1);
			}
			enemy.render(GL10.GL_TRIANGLE_FAN);
			gl.glPopMatrix();
		}
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);

	}

	private void renderTower(GL10 gl, ArrayList<Tower> towerList) {

		for (Tower actualTower : towerList) {
			gl.glPushMatrix();
			gl.glTranslatef(actualTower.position.x, actualTower.position.y, actualTower.position.z);
			tower.render(GL10.GL_TRIANGLE_FAN);
			gl.glPopMatrix();
		}
	}

	public void dispose() {
		enemy.dispose();
		tower.dispose();
		for (OwnMesh currenBlock : blocks) {
			currenBlock.dispose();
		}
		font.dispose();
	}
}
