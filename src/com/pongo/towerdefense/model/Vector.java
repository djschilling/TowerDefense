package com.pongo.towerdefense.model;



public class Vector {
	public float x;
	public float y;
	public float z;
	
	public Vector(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float distanceTo(Vector otherVector) 
	{	
		float a = otherVector.x -this.x;
		float b = otherVector.y - this.y;
		float c = otherVector.z - this.z;
		return (float)Math.sqrt( a * a + b * b + c * c);
	}	
}