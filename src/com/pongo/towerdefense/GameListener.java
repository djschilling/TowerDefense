package com.pongo.towerdefense;

import javax.microedition.khronos.opengles.GL10;

public interface GameListener {
	
	public void setup(GameActivity activity, GL10 gl);
	public void mainLoopItration(GameActivity activity, GL10 gl);

}
