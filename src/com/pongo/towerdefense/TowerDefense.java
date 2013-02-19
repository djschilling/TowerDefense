package com.pongo.towerdefense;

import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.screens.GameLoop;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class TowerDefense extends GameActivity implements GameListener{
	
	private GameScreen screen;
	private GameField field = null;
	private long start = System.nanoTime();
	private int frames = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setGameListener(this);
		
		
		
	}

	@Override
	public void setup(GameActivity activity, GL10 gl) {
		screen = new GameLoop(gl, activity);
		
	}

	@Override
	public void mainLoopItration(GameActivity activity, GL10 gl) {
		screen.update(activity);
		screen.render(gl, activity);
		frames++;
		if(System.nanoTime() -start > 1000000000){
			
		}
		
	}

}
