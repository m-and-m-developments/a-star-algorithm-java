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
