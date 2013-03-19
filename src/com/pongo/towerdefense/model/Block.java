package com.pongo.towerdefense.model;

import com.pongo.towerdefense.tools.Vector;

public class Block {
	public Vector position;
	public int width;
	public int height;
	
	public Block(Vector position, int width, int height){
		this.position = position;
		this.width = width;
		this.height = height;
	}

}
