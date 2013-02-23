package com.pongo.towerdefense.model;

import java.util.ArrayList;

public class Enemy implements Comparable<Enemy> {

	public int remainingLife;
	public Vector lastPosition;
	public ArrayList<Vector> route;
	public float progress;
	public int routeProgress;
	public boolean onField;
	public int speed;
	public Vector actualPosition;
	public Richtung richtung;
	public boolean aendertRichtung;
	public int width;
	public int height;
	public ArrayList<Bullet> incomingBullets;
	public int totalLife;

	public Enemy(ArrayList<Vector> route, int speed, int remainingLife, Richtung richtung, int height, int width) {
		this.progress = -1;
		this.routeProgress = 0;
		this.onField = false;
		this.lastPosition = new Vector(0, 0, 0);
		this.route = route;
		this.speed = speed;
		this.remainingLife = totalLife = remainingLife;
		this.richtung = richtung;
		this.aendertRichtung = false;
		this.height = height;
		this.width = width;
		incomingBullets = new ArrayList<Bullet>();
		
	}

	public boolean update(float deltaTime, boolean general) {
		if (routeProgress + 1 == route.size()) {
			this.onField = false;
			return true;
		}
		if (progress + 1 == 0) {
			progress++;
			onField = true;
			actualPosition = new Vector(route.get(routeProgress).x,
					route.get(routeProgress).y, route.get(routeProgress).z);
			return false;
		}
		if (general) {
			lastPosition.x = actualPosition.x;
			lastPosition.y = actualPosition.y;
			lastPosition.z = actualPosition.z;
		}
		float toMove;
		if(general){
			toMove = deltaTime * speed;
		} else {
			toMove = deltaTime;
		}
		float tooMuch = 0;
		if (actualPosition.x == route.get(routeProgress + 1).x) {
			if (actualPosition.y < route.get(routeProgress + 1).y) {
				actualPosition.y = actualPosition.y + toMove;
				if(aendertRichtung){
					richtung = Richtung.Norden;
					aendertRichtung = false;
				}
				if (actualPosition.y > route.get(routeProgress + 1).y) {
					routeProgress++;
					aendertRichtung = true;
					tooMuch = actualPosition.y - route.get(routeProgress).y;
					actualPosition.y = route.get(routeProgress).y;
				}
			} else {
				actualPosition.y = actualPosition.y - toMove;
				if(aendertRichtung){
					richtung = Richtung.Sueden;
					aendertRichtung = false;
				}
				if (actualPosition.y < route.get(routeProgress + 1).y) {
					routeProgress++;
					aendertRichtung = true;
					tooMuch = route.get(routeProgress).y - actualPosition.y;
					actualPosition.y = route.get(routeProgress).y;
				}
			}

		} else {
			if (actualPosition.x < route.get(routeProgress + 1).x) {
				actualPosition.x = actualPosition.x + toMove;
				if(aendertRichtung){
					richtung = Richtung.Osten;
					aendertRichtung = false;
				}
				if (actualPosition.x > route.get(routeProgress + 1).x) {
					routeProgress++;
					aendertRichtung = true;
					tooMuch = actualPosition.x - route.get(routeProgress).x;
					actualPosition.x = route.get(routeProgress).x;
				}
			} else {
				actualPosition.x = actualPosition.x - toMove;
				if(aendertRichtung){
					richtung = Richtung.Westen;
					aendertRichtung = false;
				}
				if (actualPosition.x < route.get(routeProgress + 1).x) {
					routeProgress++;
					aendertRichtung = true;
					tooMuch = route.get(routeProgress).x - actualPosition.x;
					actualPosition.x = route.get(routeProgress).x;
				}
			}
		}

		if (tooMuch > 0) {
			progress += toMove - tooMuch;
			return update(tooMuch, false);
		}
		progress += toMove;
		return false;

	}

	@Override
	public int compareTo(Enemy other) {
		if (this.progress > other.progress) {
			return -1;
		} else if (this.progress == other.progress) {
			return 0;
		} else {
			return 1;
		}
	}
}
