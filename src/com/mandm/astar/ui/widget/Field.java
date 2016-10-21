package com.mandm.astar.ui.widget;

import org.lwjgl.util.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Field extends View {

    private final double mDistance;
    protected boolean mNeedsRender;
    private Status mStatus;

    public Field(Status status, int xPosition, int yPosition, int xTarget, int yTarget) {
        super();
        setStatus(status);
        mDistance = Math.sqrt(Math.pow(Math.abs(xTarget - xPosition), 2) + Math.pow(Math.abs(yTarget - yPosition), 2));
    }

    @Override
    public void update() {
    }

    public Color getColor() {
        switch (mStatus) {
            case EMPTY:
                return new Color(Color.WHITE);
            case ACTIVE:
                return new Color(Color.BLUE);
            case WALL:
                return new Color(Color.BLACK);
            case FOUND:
                return new Color(Color.GREEN);
            default:
                throw new IllegalStateException("Unhandled state in method getColor()");
        }
    }

    @Override
    public void render() {
        if (mNeedsRender) {
            mNeedsRender = false;
            Color color = getColor();
            glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);

            glBegin(GL_QUADS);
            glVertex2f(mPosX, mPosY);
            glVertex2f(mPosX, mPosY + mHeight);
            glVertex2f(mPosX + mWidth, mPosY + mHeight);
            glVertex2f(mPosX + mWidth, mPosY);
            glEnd();
        }
    }

    public double getDistance() {
        return mDistance;
    }

    public boolean isNeedsRender() {
        return mNeedsRender;
    }

    public void setNeedsRender(boolean mNeedsRender) {
        this.mNeedsRender = mNeedsRender;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
        mNeedsRender = true;
    }

    public enum Status {
        EMPTY,
        ACTIVE,
        WALL,
        FOUND
    }
}
