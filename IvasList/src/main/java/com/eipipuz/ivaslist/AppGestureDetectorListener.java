package com.eipipuz.ivaslist;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class AppGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
    private float MIN_X_DELTA_THRESHOLD = 60;
    private float MAX_Y_DELTA_THRESHOLD = 600;
    private HomeActivity mHomeActivity;

    public AppGestureDetectorListener(HomeActivity homeActivity) {
        mHomeActivity = homeActivity;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        final float xDelta = Math.abs(motionEvent2.getRawX() - motionEvent.getRawX());
        if (xDelta > MIN_X_DELTA_THRESHOLD) {
            final float yDelta = Math.abs(motionEvent2.getRawY() - motionEvent.getRawY());
            if (yDelta < MAX_Y_DELTA_THRESHOLD) {
                mHomeActivity.deleteMarkedTag();
            } else {
                Log.d("Gesture", "Almost but no delete, yDelta: " + yDelta);
            }
        } else {
            Log.d("Gesture", "Almost but no delete, xDelta: " + xDelta);
        }

        return false;
    }
}
