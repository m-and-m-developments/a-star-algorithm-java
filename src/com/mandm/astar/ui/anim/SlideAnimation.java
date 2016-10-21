package com.mandm.astar.ui.anim;

import com.mandm.astar.ui.widget.View;

/**
 * Created on 21.10.2016.
 *
 * @author Martin
 */
public class SlideAnimation extends Animation {

    int mDeltaX;
    int mDeltaY;
    int mBeginX;
    int mBeginY;

    public SlideAnimation(int duration, View animationView, Interpolator interpolator, int deltaX, int deltaY) {
        super(duration, animationView, interpolator);
        mDeltaX = deltaX;
        mDeltaY = deltaY;
        mBeginX = mAnimationView.getPosX();
        mBeginY = mAnimationView.getPosY();
    }

    @Override
    protected void animate(double percentCompleted) {
        mAnimationView.setPosX(mBeginX + (int) (mDeltaX * mInterpolator.getInperpolation(percentCompleted)));
        mAnimationView.setPosY(mBeginY + (int) (mDeltaY * mInterpolator.getInperpolation(percentCompleted)));
    }
}
