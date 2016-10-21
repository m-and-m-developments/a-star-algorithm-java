package com.mandm.astar.ui;

import com.mandm.astar.render.GLHelper;
import com.mandm.astar.ui.anim.AccelerateDecelerateInterpolator;
import com.mandm.astar.ui.anim.Animation;
import com.mandm.astar.ui.anim.Animator;
import com.mandm.astar.ui.anim.SlideAnimation;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.View;
import com.mandm.astar.util.Log;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
@SuppressWarnings("WeakerAccess")
public class GuiScreen {

    protected List<View> views;

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 640;


    public GuiScreen() {
        this.views = new ArrayList<>();

        GLHelper.initDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, false);

        Button button = new Button(20, 600, "Click Me!");

        views.add(button);

        final int[] i = {1};
        button.addClickListener((View actionPerformer) -> {
            Log.d("onClick!");
            Animation animation = new SlideAnimation(1000, button, new AccelerateDecelerateInterpolator(), 400 * i[0], 0);
            Animator.animate(animation);
            i[0] *= -1;
        });

    }

    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(.933333333f, .933333333f, .933333333f, 0);

        views.forEach(View::render);

        Display.update();
    }

    protected void update() {
        while (!Display.isCloseRequested()) {
            views.forEach(View::update);

            render();
        }

        Display.destroy();
    }

    public void addView(View view) {
        views.add(view);
    }

    /**
     * After this method is called, the thread in which
     * the function is called will be blocked
     */
    public void start() {
        update();
    }

    public int getWidth() {
        return WINDOW_WIDTH;
    }
}
