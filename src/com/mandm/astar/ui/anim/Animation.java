package com.mandm.astar.ui.anim;

import com.mandm.astar.ui.widget.View;

import java.util.List;

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
    protected List<View> mAnimationViews;
    double mPercentCompleted;
    protected Interpolator mInterpolator;

    protected AnimationListener mListener;

    Animation(int duration, Interpolator interpolator) {
        mDuration = duration;
        mInterpolator = interpolator;
    }

    protected void animationLoop(List<View> views) {
        mAnimationViews = views;
        long nanosBegin = System.nanoTime();
        long nanosSinceBegin;
        mDuration *= 1000000;
        double lastPercent = 0.;

        while ((nanosSinceBegin = System.nanoTime() - nanosBegin) < mDuration) {
            mPercentCompleted = (double) nanosSinceBegin / mDuration;
            animate(mPercentCompleted, mPercentCompleted - lastPercent);
            lastPercent = mPercentCompleted;
        }

        if (mListener != null) {
            mListener.animationFinished(this);
        }
    }

    public void addAnimationListener(AnimationListener listener) {
        mListener = listener;
    }

    protected abstract void animate(double percentCompleted, double delta);

    public interface AnimationListener {
        void animationFinished(Animation animation);
    }
}
