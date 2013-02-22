package com.pongo.towerdefense.model;

public class Aussichtsturm extends Tower{

	private static final int RANGE = 300;
	private static final int DAMAGE = 40;
	private static final int RELOAD_TIME = 1;
	public Aussichtsturm(Vector position) {
		super(position, RANGE, DAMAGE, RELOAD_TIME);
	}

}
