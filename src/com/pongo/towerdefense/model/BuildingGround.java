package com.pongo.towerdefense.model;

import com.pongo.towerdefense.tools.Vector;

public class BuildingGround {
	public Vector position;
	public boolean occupied;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public BuildingGround(Vector position){
		this.position = position;
		occupied = false;
	}

}
