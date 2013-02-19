package com.pongo.towerdefense.samples;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.os.Bundle;

import com.pongo.towerdefense.GameActivity;
import com.pongo.towerdefense.GameListener;
import com.pongo.towerdefense.tools.Mesh;
import com.pongo.towerdefense.tools.Mesh.PrimitiveType;

public class OrthoSample extends GameActivity implements GameListener {

	Mesh mesh;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setGameListener(this);

	}

	@Override
	public void setup(GameActivity activity, GL10 gl) {
		mesh = new Mesh(gl, 3, false, false, false);
		mesh.vertex(0, 0, 0);
		mesh.vertex(400, 0, 0);
		mesh.vertex(200, activity.getHeight() , 0);
		
		

	}

	@Override
	public void mainLoopItration(GameActivity activity, GL10 gl) {
		gl.glViewport(0, 0, activity.getWidth(), getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, 0, activity.getWidth(), 0, activity.getHeight());

		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		GLU.gluLookAt( gl, 0, activity.getHeight()/2, 1, 0, activity.getHeight()/2, 0, 0, 1, 0 );


		mesh.render(PrimitiveType.Triangles);

	}

}
