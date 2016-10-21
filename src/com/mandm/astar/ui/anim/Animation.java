package com.mandm.astar.ui.anim;

import com.mandm.astar.ui.widget.View;

/**
 * Created on 20.10.2016.
 * A animation for a View
 * The animation needs to be run in a seperate thread so it
 * doesn't block any other threads
 *
 * @author Martin
 */
public abstract class Animation {

    protected int mDuration;
    protected View mAnimationView;
    double mPercentCompleted;
    protected Interpolator mInterpolator;


    Animation(int duration, View animationView, Interpolator interpolator) {
        mDuration = duration;
        mAnimationView = animationView;
        mInterpolator = interpolator;
    }

    protected void animationLoop() {
        long nanosBegin = System.nanoTime();
        long nanosSinceBegin;
        mDuration *= 1000000;

        while ((nanosSinceBegin = System.nanoTime() - nanosBegin) < mDuration) {
            mPercentCompleted = (double) nanosSinceBegin / mDuration;
            animate(mPercentCompleted);
        }
    }

    protected abstract void animate(double percentCompleted);
}
