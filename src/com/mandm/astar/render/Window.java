package com.mandm.astar.render;

import com.mandm.astar.util.Log;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/26/16.
 *
 * @author martin
 */
public final class Window {

    public static void create(int width, int height) {
        ContextAttribs attribs = new ContextAttribs(2, 1)
                .withForwardCompatible(true);

        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create(new PixelFormat(8, 8, 0, 4));
            Display.setTitle("A* Algorithm");
        } catch (LWJGLException e) {
            Log.e(e.getMessage());
            System.exit(2);
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, height, 0, 0, 1);
        glMatrixMode(GL_MODELVIEW);
    }

}
