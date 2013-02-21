package com.pongo.towerdefense;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Input implements OnTouchListener {
	public int firstTouchX;
	public int firstTouchY;
	public int prevTouchX;
	public int prevTouchY;
	public int touchX;
	public int touchY;
	public TouchMode touchMode;
	private int genauigkeit = 20;
	
	public Input() {
		touchMode = TouchMode.No;
	}

	public void touch(MotionEvent event) {

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (touchMode == TouchMode.No) {
			touchMode = TouchMode.Start;
			firstTouchX = (int) event.getX();
			firstTouchY = (int) (v.getHeight() - event.getY());
		} else if (touchMode == TouchMode.Start) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				touchMode = TouchMode.EndTip;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				float abweichungX = Math.abs(firstTouchX - event.getX());
				float abweichungY = Math.abs(firstTouchY - event.getY());
				if (abweichungX > genauigkeit
						|| abweichungY>genauigkeit) {
					prevTouchX = firstTouchX;
					prevTouchY = firstTouchY;
					touchX = (int) event.getX();
					touchY = (int) (v.getHeight() - event.getY());
					touchMode = TouchMode.Scroll;
				} else {
					touchMode = TouchMode.EventuallyScroll;
				}
			}
		} else if(touchMode == TouchMode.EventuallyScroll){
			if (event.getAction() == MotionEvent.ACTION_UP) {
				touchMode = TouchMode.EndTip;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				float abweichungX = Math.abs(firstTouchX - event.getX());
				float abweichungY = Math.abs(firstTouchY - event.getY());
				if ( abweichungX > genauigkeit
						|| abweichungY >genauigkeit) {
					prevTouchX = firstTouchX;
					prevTouchY = firstTouchY;
					touchX = (int) event.getX();
					touchY = (int) (v.getHeight() - event.getY());
					touchMode = TouchMode.Scroll;
				} else {
					touchMode = TouchMode.EventuallyScroll;
				}
			}
		}
			else if (touchMode == TouchMode.Scroll) {
			prevTouchX = touchX;
			prevTouchY = touchY;
			touchX = (int) event.getX();
			touchY = (int) (v.getHeight() - event.getY());
			if (event.getAction() == MotionEvent.ACTION_UP) {
				touchMode = TouchMode.EndScroll;
			}
		}
		return true;
	}
}
