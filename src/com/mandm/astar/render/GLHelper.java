package com.mandm.astar.render;

import com.mandm.astar.util.Log;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 18.10.2016.
 *
 * @author Martin, Alex Lardschneider
 */
public class GLHelper {

    public static void initDisplay(int width, int height, boolean fullscreen) {
        if (Display.isCreated()
                && Display.getDisplayMode().getWidth() == width
                && Display.getDisplayMode().getHeight() == height
                && Display.isFullscreen() == fullscreen) {
            return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (DisplayMode current : modes) {
                    if (current.getWidth() == width && current.getHeight() == height) {
                        if (targetDisplayMode == null || current.getFrequency() >= freq) {
                            if (targetDisplayMode == null || current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        if (current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()
                                && current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width, height);
            }

            if (targetDisplayMode == null) {
                Log.e("Failed to find value mode: " + width + 'x'
                        + height + " fs=true");
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

            if (!Display.isCreated()) {
                try {
                    ContextAttribs attribs = new ContextAttribs(2, 1)
                            .withForwardCompatible(true)
                            /*.withProfileCore(true)*/;

                    Display.create(new PixelFormat(8, 8, 0, 4));
                    Log.i("Display created!");
                } catch (LWJGLException e) {
                    e.printStackTrace();
                }
            }

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, width, height, 0, 0, 1);
            glMatrixMode(GL_MODELVIEW);


        } catch (LWJGLException e) {
            Log.e("Unable to setup mode " + width + 'x' + height
                    + " fullscreen=" + fullscreen + e);
        }

    }

}
