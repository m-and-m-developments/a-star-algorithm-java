package com.mandm.astar.res;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.InputStream;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
public class FontManager {

    private static final String FONTS_RESOURCE_PACKAGE = "com/mandm/astar/res/fonts/";
    private static final String FONT_ROBOTO = FONTS_RESOURCE_PACKAGE + "roboto.ttf";

    private static FontManager sInstance;

    private TrueTypeFont fontSmall;
    private TrueTypeFont fontMedium;
    private TrueTypeFont fontBig;

    public static FontManager getInstance() {
        if (sInstance == null) {
            sInstance = new FontManager();
        }
        return sInstance;
    }

    private FontManager() {
        load();
    }


    public static TrueTypeFont getFontSmall() {
        return getInstance().fontSmall;
    }

    public static TrueTypeFont getFontMedium() {
        return getInstance().fontMedium;
    }

    public static TrueTypeFont getFontBig() {
        return getInstance().fontBig;
    }

    public void load() {
        fontSmall = loadFont(ResourceLoader.getResourceAsStream(FONT_ROBOTO), Font.PLAIN, 12, true);
        fontMedium = loadFont(ResourceLoader.getResourceAsStream(FONT_ROBOTO), Font.PLAIN, 14, true);
        fontBig = loadFont(ResourceLoader.getResourceAsStream(FONT_ROBOTO), Font.PLAIN, 16, true);
    }

    private TrueTypeFont loadFont(InputStream in, int style, int size, boolean antiAlias) {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, in);
            font = font.deriveFont(style, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadFont(font, antiAlias);
    }

    private TrueTypeFont loadFont(Font font, boolean antiAlias) {
        return new TrueTypeFont(font, antiAlias);
    }

}
