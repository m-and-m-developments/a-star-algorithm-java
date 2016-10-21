package com.mandm.astar.ui.anim;

/**
 * Created on 20.10.2016.
 *
 * @author Martin
 */
public class Animator {

    public static void animate(Animation animation) {
        new Thread(animation::animationLoop).start();
    }
}
