package com.pongo.towerdefense.samples;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.os.Bundle;

import com.pongo.towerdefense.GameActivity;
import com.pongo.towerdefense.GameListener;
import com.pongo.towerdefense.tools.Mesh;
import com.pongo.towerdefense.tools.Mesh.PrimitiveType;

public class PerspectiveSample extends GameActivity implements GameListener {

	Mesh mesh;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setGameListener(this);

	}

	@Override
	public void setup(GameActivity activity, GL10 gl) {
		mesh = new Mesh( gl, 6, true, false, false );				
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 0f, -0.5f, -5 );
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 5f, -0.5f, -5 );
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 0.5f, 0.5f, -5 );		
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( -0.5f, -0.5f, -2 );
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( 0.5f, -0.5f, -2 );
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( 0, 0.5f, -2);
	}

	@Override
	public void mainLoopItration(GameActivity activity, GL10 gl) {
		gl.glViewport(0, 0, activity.getWidth(), getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode( GL10.GL_PROJECTION );
		gl.glLoadIdentity();
		float aspectRatio = (float)activity.getWidth() / activity.getHeight();
		GLU.gluPerspective( gl, 67, aspectRatio, 1, 100 );
		
//		gl.glMatrixMode( GL10.GL_MODELVIEW );
//		gl.glLoadIdentity();
//		GLU.gluLookAt( gl, 1.7f, 0, 1, 1.7f, 0, 0, 0, 1, 0 );

		mesh.render(PrimitiveType.Triangles);

	}

}
