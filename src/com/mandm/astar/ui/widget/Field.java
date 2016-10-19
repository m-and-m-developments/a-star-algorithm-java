package com.mandm.astar.ui.widget;

import org.lwjgl.util.Color;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Field extends View {

    protected boolean mNeedsRender;
    private int mStatus;

    public Field(int status) {
        super(0, 0, 0, 0);
        setStatus(status);
    }

    @Override
    public void update() {
    }

    public Color getColor() {
        Random random = new Random();
        return new Color(random.nextInt(2), random.nextInt(2), random.nextInt(2));
    }

    @Override
    public void render() {
        if (mNeedsRender) {
//            mNeedsRender = false;
            Color color = getColor();
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glBegin(GL_QUADS);
                glVertex2f(mPosX, mPosY);
                glVertex2f(mPosX, mPosY + mHeight);
                glVertex2f(mPosX + mWidth, mPosY + mHeight);
                glVertex2f(mPosX + mWidth, mPosY);
            glEnd();
        }
    }

    public void setStatus(int status) {
        mStatus = status;
        mNeedsRender = true;
    }

}
