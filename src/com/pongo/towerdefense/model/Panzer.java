package com.pongo.towerdefense.model;

import java.util.ArrayList;

public class Panzer extends Enemy {
	public static final int SPEED = 50;
	public static final int LIFE = 400;

	public static final int WIDTH = 50;
	public static final int HEIGHT = 25;

	public Panzer(ArrayList<Vector> route, Richtung richtung) {
		super(route, SPEED, LIFE, richtung, HEIGHT, WIDTH);

	}

}