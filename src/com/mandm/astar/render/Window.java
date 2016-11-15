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

package com.mandm.astar.render;

import com.mandm.astar.util.Log;
import org.lwjgl.LWJGLException;
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
