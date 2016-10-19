package com.mandm.astar.ui.widget;

import com.mandm.astar.ui.FontManager;
import com.mandm.astar.ui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Color;
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
//        System.out.println("Mouse: " + Mouse.getX() + "," + Mouse.getY());

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
        Color color = getColor();

        glBegin(GL_QUADS);
            glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
            glVertex2f(mPosX, mPosY);
            glVertex2f(mPosX, mPosY + mHeight);
            glVertex2f(mPosX + mWidth, mPosY + mHeight);
            glVertex2f(mPosX + mWidth, mPosY);
        glEnd();


        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            mFont.drawString(mPosX + mWidth / 2 - mFont.getWidth(mText) / 2, mPosY + mHeight / 2 - mFont.getHeight(mText) / 2, mText);
        glDisable(GL_BLEND);
    }

    public Color getColor() {
        Color color = new Color();

        if (mHover) {
            color.set(155, 155, 155, 0);
        } else if (isHeld) {
            color.set(140, 140, 140, 0);
        } else {
            color.set(120, 120, 120, 0);
        }

        return color;
    }
}
