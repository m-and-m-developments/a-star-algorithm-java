package com.mandm.astar.ui.widget;

import com.mandm.astar.res.FontManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/26/16.
 *
 * @author martin
 */
public class TextView extends View {

    protected String mText;
    protected Font mFont;
    protected Color mColor;

    protected static final int margin = 4;

    public TextView(int posX, int posY, int width, int height, String text) {
        super(posX, posY, width, height);
        mFont = FontManager.getFontBig();
        mText = text;
        mColor = Color.black;

        if (width <= 0) {
            calcWidth();
        }
        if (height <= 0) {
            calcHeight();
        }
    }

    public TextView(int posX, int posY, String text) {
        super();
        mFont = FontManager.getFontBig();
        mColor = Color.black;

        calcWidth();
        calcHeight();
        mPosX = posX;
        mPosY = posY;
        mText = text;
    }

    protected void calcWidth() {
        mWidth = mFont.getWidth(mText) + 2 * margin;
    }

    protected void calcHeight() {
        mHeight = mFont.getHeight(mText) + 2 * margin;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
        calcWidth();
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_TEXTURE_2D);
        mFont.drawString(mPosX + mWidth / 2 - mFont.getWidth(mText) / 2, mPosY + mHeight / 2 - mFont.getHeight(mText) / 2, mText, mColor);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }
}
