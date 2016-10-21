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
