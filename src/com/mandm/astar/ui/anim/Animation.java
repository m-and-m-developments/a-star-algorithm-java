/*
 * Copyright 2016 Martin Fink & Moriz Martiner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
