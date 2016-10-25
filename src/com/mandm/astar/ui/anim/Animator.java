package com.mandm.astar.ui.anim;

import com.mandm.astar.ui.widget.View;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 20.10.2016.
 *
 * @author Martin
 */
public class Animator {

    public static void animate(Animation animation, List<View> views) {
        new Thread(() -> animation.animationLoop(views)).start();
    }

    public static void animate(Animation animation, View... views) {
        animate(animation, Arrays.asList(views));
    }
}
