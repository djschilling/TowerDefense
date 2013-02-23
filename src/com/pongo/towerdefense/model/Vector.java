package com.pongo.towerdefense.model;

public class Vector {
	public float x;
	public float y;
	public float z;

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float distanceTo(Vector otherVector) {
		float a = otherVector.x - this.x;
		float b = otherVector.y - this.y;
		float c = otherVector.z - this.z;
		return (float) Math.sqrt(a * a + b * b + c * c);
	}

	public Vector minus(Vector otherVector) {
		return new Vector(this.x - otherVector.x, this.y - otherVector.y, this.z - otherVector.z);
	}
	
	public static void add(Vector vector1,Vector vector2,Vector result){
		result.x = vector1.x + vector2.x;
		result.y = vector1.y + vector2.y;
		result.z = vector1.z + vector2.z;
	}
	public Vector multiply(float factor){
		return new Vector(x *factor, y * factor, z * factor);
	}
}