package com.pongo.towerdefense;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.model.GameField;
import com.pongo.towerdefense.screens.GameLoop;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class TowerDefense extends Activity implements Renderer, OnTouchListener{
	
	private GameScreen screen;
	private GameField field = null;
	private long start = System.nanoTime();
	private int frames = 0;
	private GLSurfaceView glSurface;
	private int width;
	private int height;
	private long lastFrameStart;
	private float deltaTime;
	private int touchY;
	private int touchX;
	private boolean isTouched;
	private boolean firstFrame = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glSurface = new GLSurfaceView(this);
		glSurface.setRenderer(this);
		setContentView(glSurface);
		lastFrameStart = 0;
		glSurface.setOnTouchListener(this);
		
		
	}

	
	public void setup(GL10 gl) {
		screen = new GameLoop(gl, this);
		
	}

	
	public void mainLoopItration(GL10 gl) {
		screen.update(this);
		screen.render(gl, this);
		frames++;
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			this.touchX = (int) event.getX();
			this.touchY = (int) event.getY();
			isTouched = true;
		}

		if (event.getAction() == MotionEvent.ACTION_UP)
			isTouched = false;

		return true;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		long currentFrameStart = System.nanoTime();
		if (firstFrame) {
			deltaTime = 0.1f;
			firstFrame = false;
		} else {
			deltaTime = (currentFrameStart - lastFrameStart) / 1000000000.0f;
		}
		lastFrameStart = currentFrameStart;

		mainLoopItration(gl);
		
	}
	
	public float getDeltaTime(){
		return deltaTime;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.width = width;
		this.height = height;
		
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		setup(gl);
		
	}
	@Override
	public void onPause() {
		super.onPause();
		glSurface.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		glSurface.onResume();
	}

	
}
