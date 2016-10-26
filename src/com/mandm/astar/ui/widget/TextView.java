package com.mandm.astar.ui.widget;

import com.mandm.astar.res.FontManager;
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

    public TextView(int posX, int posY, int width, int height, String text) {
        super(posX, posY, width, height);
        mFont = FontManager.getFontBig();
        mText = text;
    }

    public TextView(int posX, int posY, String text) {
        super();
        mFont = FontManager.getFontBig();

        mWidth = mFont.getWidth(text);
        mHeight = mFont.getHeight(text);
        mPosX = posX;
        mPosY = posY;
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
        mWidth = mFont.getWidth(text);
        mHeight = mFont.getHeight(text);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_TEXTURE_2D);
        mFont.drawString(mPosX + mWidth / 2 - mFont.getWidth(mText) / 2, mPosY + mHeight / 2 - mFont.getHeight(mText) / 2, mText, org.newdawn.slick.Color.black);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }
}
