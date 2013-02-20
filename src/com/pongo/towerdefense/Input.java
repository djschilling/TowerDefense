package com.pongo.towerdefense;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Input implements OnTouchListener{
	public int firstTouchX;
	public int firstTouchY;
	public int touchX;
	public int touchY;
	public TouchMode touchMode;
	
	public Input(){
		touchMode = TouchMode.No;
	}
	
	public void touch(MotionEvent event){
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(touchMode == TouchMode.No){
			touchMode = TouchMode.Start;
			firstTouchX = (int)event.getX();
			firstTouchY = (int)(v.getHeight() - event.getY());
		} else if(touchMode == TouchMode.Start && event.getAction() == MotionEvent.ACTION_MOVE){
			if(event.getAction() == MotionEvent.ACTION_UP){
				touchMode = TouchMode.EndScroll;
			}
			touchX = (int)event.getX();
			touchX = (int)(v.getHeight() - event.getY());
		} else if(touchMode == TouchMode.Start && event.getAction() == MotionEvent.ACTION_UP){
			touchMode = TouchMode.EndTip;
		} else if(touchMode == TouchMode.EndScroll){
			touchX = (int)event.getX();
			touchY = (int)(v.getHeight() - event.getY());
		}
		return true;
	}

}
