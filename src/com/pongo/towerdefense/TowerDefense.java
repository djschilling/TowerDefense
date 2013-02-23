package com.pongo.towerdefense;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.pongo.towerdefense.gl.GameScreen;
import com.pongo.towerdefense.input.GestureListener;
import com.pongo.towerdefense.input.InputManager;
import com.pongo.towerdefense.screens.GameLoop;

public class TowerDefense extends Activity implements Renderer {

	public GameScreen screen;
	private int frames = 0;
	private GLSurfaceView glSurface;
	private int width;
	private int height;
	private long lastFrameStart;
	private float deltaTime;
	public boolean isTouched;
	private boolean firstFrame = true;
	public Input input;
	public InputManager inputManager;
	private GestureDetector gestureDedector;
	private GestureListener gestureListener;
	private float time = 0;
	public int framesPerSecond = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glSurface = new GLSurfaceView(this);
		glSurface.setRenderer(this);
		setContentView(glSurface);
		lastFrameStart = 0;
		input = new Input();
		gestureListener = new GestureListener(this, 0, 0);
		gestureDedector = new GestureDetector(this, gestureListener);

		// glSurface.setOnTouchListener(input);

	}

	public void setup(GL10 gl) {
		screen = new GameLoop(gl, this);
		gestureListener.setScreen(screen);

	}

	public void mainLoopItration(GL10 gl) {
		// screen.input(this);
		screen.update(this);
		screen.render(gl, this);
		
		frames++;
		time += deltaTime;
		if (time > 1) {
			framesPerSecond = frames;
			frames = 0;
			time = 0;
		}

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

	public float getDeltaTime() {
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
		firstFrame = true;
		super.onResume();
		glSurface.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gestureDedector.onTouchEvent(event))
			return true;
		else
			return false;
	}

}
