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
	public int scrollCounter;

	public Input() {
		touchMode = TouchMode.No;
		scrollCounter = 0;
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
				prevTouchX = firstTouchX;
				prevTouchY = firstTouchY;
				touchX = (int) event.getX();
				touchY = (int) (v.getHeight() - event.getY());
				scrollCounter++;
				touchMode = TouchMode.Scroll;
			}
		} else if(touchMode == TouchMode.Scroll){
			scrollCounter++;
			prevTouchX = touchX;
			prevTouchY = touchY;
			touchX = (int) event.getX();
			touchY = (int) (v.getHeight() - event.getY());
			if(event.getAction() == MotionEvent.ACTION_UP){
				touchMode = TouchMode.EndScroll;
			}
		} 
		return true;
	}

}
