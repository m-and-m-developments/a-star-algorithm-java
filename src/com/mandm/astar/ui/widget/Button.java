package com.mandm.astar.ui.widget;

import com.mandm.astar.res.FontManager;
import com.mandm.astar.res.TextureManager;
import com.mandm.astar.ui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Font;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
public class Button extends View {

    public static final int BUTTON_DEFAULT_HEIGHT = 40;
    public static final int BUTTON_DEFAULT_WIDTH = 120;

    private String mText;
    private Font mFont;

    protected boolean mHover;
    protected boolean mEnabled = true;

    public Button(int posX, int posY, int width, int height, String text) {
        super(posX, posY, width, height);
        mText = text;
        mFont = FontManager.getFontMedium();
    }

    public Button(int posX, int posY, String text) {
        this(posX, posY, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT, text);
    }

    @Override
    public void update() {
        if (!mEnabled) {
            mHover = false;
            isHeld = false;
            return;
        }

        if (Mouse.getX() >= mPosX && Mouse.getX() <= mPosX + getWidth()
                && GuiScreen.WINDOW_HEIGHT - Mouse.getY() >= mPosY
                && GuiScreen.WINDOW_HEIGHT - Mouse.getY() <= mPosY + getHeight()
                ) {
            mHover = !Mouse.isButtonDown(0) && !Mouse.isButtonDown(1);
            isHeld = !mHover;

            while (Mouse.next()) {
                if (Mouse.getEventButtonState()) {
                    if (Mouse.getEventButton() == 0 || Mouse.getEventButton() == 1) {
                        onClick();
                    }
                }
            }
        } else {
            mHover = false;
        }
    }

    @Override
    public void render() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(1, 1, 1, 1);

        int x2 = mPosX + mWidth;
        int y2 = mPosY + mHeight;

        glEnable(GL_TEXTURE_2D);
        if (mHover) {
            TextureManager.buttonHover.bind();
        } else if (isHeld) {
            TextureManager.buttonClicked.bind();
        } else {
            TextureManager.button.bind();
        }

        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2f(mPosX, mPosY);
            glTexCoord2f(0, 1);
            glVertex2f(mPosX, y2);
            glTexCoord2f(1, 1);
            glVertex2f(x2, y2);
            glTexCoord2f(1, 0);
            glVertex2f(x2, mPosY);
        }
        glEnd();

        mFont.drawString(mPosX + mWidth / 2 - mFont.getWidth(mText) / 2, mPosY + mHeight / 2 - mFont.getHeight(mText) / 2, mText, mEnabled ? org.newdawn.slick.Color.black : org.newdawn.slick.Color.gray);

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }
}
