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

package com.mandm.astar.res;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created on 21.10.2016.
 *
 * @author Martin
 */
public class TextureManager {

    public static final String RESOURCE_PACKAGE = "com/mandm/astar/res/pictures/";

    public static Texture button;
    public static Texture buttonHover;
    public static Texture buttonClicked;

    static {
        init();
    }

    private static void init() {
        try {
            button = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(RESOURCE_PACKAGE + "button.png"));
            buttonHover = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(RESOURCE_PACKAGE + "button_hover.png"));
            buttonClicked = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(RESOURCE_PACKAGE + "button_clicked.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
