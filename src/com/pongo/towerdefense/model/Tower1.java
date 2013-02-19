package com.pongo.towerdefense.model;

public class Tower1 extends Tower{

	private static final int RANGE = 100;
	private static final int DAMAGE = 300;
	private static final int RELOAD_TIME = 4;
	public Tower1(Vector position) {
		super(position, RANGE, DAMAGE, RELOAD_TIME);
	}

}
