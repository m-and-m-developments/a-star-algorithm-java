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

/**
 * Created on 21.10.2016.
 *
 * @author Martin
 */
public class SlideAnimation extends Animation {

    private int mDeltaX;
    private int mDeltaY;

    public SlideAnimation(int duration, Interpolator interpolator, int deltaX, int deltaY) {
        super(duration, interpolator);
        mDeltaX = deltaX;
        mDeltaY = deltaY;
    }

    @Override
    protected void animate(double percentCompleted, double delta) {
        mAnimationViews.forEach(view -> {
            view.setPosX(view.getPosX() + (int) (mDeltaX * mInterpolator.getInperpolation(percentCompleted)));
            view.setPosY(view.getPosY() + (int) (mDeltaY * mInterpolator.getInperpolation(percentCompleted)));
        });
    }
}
