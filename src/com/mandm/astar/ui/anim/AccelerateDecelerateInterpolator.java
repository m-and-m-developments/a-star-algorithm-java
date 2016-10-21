package com.mandm.astar.ui.anim;

/**
 * Created on 21.10.2016.
 *
 * @author Martin
 */
public class AccelerateDecelerateInterpolator implements Interpolator {

    @Override
    public double getInperpolation(double input) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
