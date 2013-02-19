package com.pongo.towerdefense;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.pongo.towerdefense.R;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends Activity implements Renderer, OnTouchListener {

	private GLSurfaceView glSurface;
	private int width;
	private int height;
	private long lastFrameStart;
	private float deltaTime;
	private GameListener listener;
	private int touchY;
	private int touchX;
	private boolean isTouched;
	private boolean firstFrame = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		glSurface = new GLSurfaceView(this);
		glSurface.setRenderer(this);
		setContentView(glSurface);
		lastFrameStart = 0;
		glSurface.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
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

		listener.mainLoopItration(this, gl);

		// TODO Auto-generated method stub

	}

	public float getDeltaTime() {
		return deltaTime;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.width = width;
		this.height = height;

		if (listener != null) {
			listener.setup(this, gl);
		}
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setGameListener(GameListener gameListener) {
		this.listener = gameListener;
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

	public int getTouchY() {
		return touchY;
	}

	public int getTouchX() {
		return touchX;
	}

	public boolean isTouched() {
		return isTouched;
	}

}
