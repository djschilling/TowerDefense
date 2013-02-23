package com.pongo.towerdefense.tools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class OwnMesh {

	private GL10 gl;
	private int numberOfVertex;
	private ByteBuffer buffer1;
	private ByteBuffer buffer2;
	private ByteBuffer buffer3;

	private FloatBuffer vertices;
	private FloatBuffer colors;
	private FloatBuffer textures;

	private boolean color;
	private boolean texture;

	private int addedVertex;
	private int addedColors;
	private int addedTextures;

	private OwnMesh lastMesh;

	private final int SIZE_OF_FLOAT = 4;

	public OwnMesh(GL10 gl, int numberOfVertex, boolean color, boolean texture) {
		this.gl = gl;
		this.color = color;
		this.texture = texture;
		this.numberOfVertex = numberOfVertex;
		this.addedVertex = this.addedColors = this.addedTextures = 0;

		initializeBuffer();

	}

	private void initializeBuffer() {
		this.buffer1 = ByteBuffer.allocateDirect(numberOfVertex * 3 * SIZE_OF_FLOAT);
		buffer1.order(ByteOrder.nativeOrder());
		vertices = buffer1.asFloatBuffer();

		if (color) {
			this.buffer2 = ByteBuffer.allocateDirect(numberOfVertex * 4 * SIZE_OF_FLOAT);
			buffer2.order(ByteOrder.nativeOrder());

			colors = buffer2.asFloatBuffer();
		}
		if (texture) {
			this.buffer3 = ByteBuffer.allocateDirect(numberOfVertex * 2 * SIZE_OF_FLOAT);
			buffer3.order(ByteOrder.nativeOrder());
			textures = buffer3.asFloatBuffer();
		}
	}

	public void setVertex(float x, float y, float z) {
		vertices.put(x);
		vertices.put(y);
		vertices.put(z);
		addedVertex++;
		if (addedVertex >= numberOfVertex) {
			vertices.rewind();
	

		}
	}

	public void setColor(float r, float g, float b, float alpha) {
		colors.put(r);
		colors.put(g);
		colors.put(b);
		colors.put(alpha);
		addedColors++;
		if (addedColors >= numberOfVertex) {
			 colors.rewind();
		
		}
	}

	public void setTexture(float s, float t) {
		textures.put(s);
		textures.put(t);
		addedTextures++;
		if (addedTextures >= numberOfVertex) {
			textures.rewind();

		}
	}

	public void render(int drawMode) {
		if (this == lastMesh) {
			gl.glDrawArrays(drawMode, 0, numberOfVertex);
		} else {

			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		}

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
		if (color) {
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colors);
		}
		if (texture) {
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textures);
		}
		gl.glDrawArrays(drawMode, 0, numberOfVertex);
	}

	public void dispose() {
		buffer1 = null;
		buffer2 = null;
		buffer3 = null;
		vertices = null;
		colors = null;
		textures = null;

	}

	public int getAddedVertex() {
		return numberOfVertex;
	}

	public void reset() {
		addedVertex = addedColors = addedTextures = 0;
		initializeBuffer();
	}

}
