package com.mandm.astar.ui;

import com.mandm.astar.render.GLHelper;
import com.mandm.astar.ui.widget.View;
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

    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 640;


    public GuiScreen() {
        this.views = new ArrayList<>();

        GLHelper.initDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, false);

    }

    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        views.forEach(View::render);

        Display.update();
    }

    protected void update() {
        while (!Display.isCloseRequested()) {
            views.forEach(View::update);

            render();
        }
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
