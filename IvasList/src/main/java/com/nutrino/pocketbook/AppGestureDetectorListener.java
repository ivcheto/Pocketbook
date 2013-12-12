package com.nutrino.pocketbook;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class AppGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
    private static final float MIN_X_DELTA_THRESHOLD = 300;
    private static final float MAX_Y_DELTA_THRESHOLD = 60;
    private TagListActivity mTagListActivity;

    public AppGestureDetectorListener(TagListActivity tagListActivity) {
        mTagListActivity = tagListActivity;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        final float xDelta = motionEvent2.getRawX() - motionEvent.getRawX();
        final float yDelta = motionEvent2.getRawY() - motionEvent.getRawY();
        if (xDelta > MIN_X_DELTA_THRESHOLD) {
            if (yDelta < MAX_Y_DELTA_THRESHOLD) {
                mTagListActivity.deleteMarkedTag();
                return true;
            }
        }
        mTagListActivity.moveMarkedTag(v);
        return false;
    }
}
