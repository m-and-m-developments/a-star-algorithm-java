package com.mandm.astar.ui.widget;

import org.lwjgl.util.Color;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glRecti;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Field extends View implements Comparable {
    private final int X_POSITION;
    private final int Y_POSITION;
    protected boolean mNeedsRender;
    private double mHeuristicCost;
    private double finalCost;
    private Status mStatus;
    private Field parent;

    public Field(Status status, int xPosition, int yPosition) {
        super();
        setStatus(status);
        X_POSITION = xPosition;
        Y_POSITION = yPosition;
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
            case START:
                return new Color(Color.CYAN);
            case END:
                return new Color(Color.RED);
            default:
                throw new IllegalStateException("Unhandled state in method getColor()");
        }
    }

    @Override
    public void render() {
        if (mNeedsRender) {
//            mNeedsRender = false;
            Color color = getColor();
            glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);


            glRecti(mPosX, mPosY, mPosX + mWidth, mPosY + mHeight);
//            glBegin(GL_QUADS);
//            glVertex2f(mPosX, mPosY);
//            glVertex2f(mPosX, mPosY + mHeight);
//            glVertex2f(mPosX + mWidth, mPosY + mHeight);
//            glVertex2f(mPosX + mWidth, mPosY);
//            glEnd();
        }
    }

    public double getDistance() {
        return mHeuristicCost;
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
        if ((mStatus != Status.START && mStatus != Status.END) || status != Status.ACTIVE) {
            mStatus = status;
            mNeedsRender = true;
        }
    }

    public Field getParent() {
        return parent;
    }

    public void setParent(Field parent) {
        this.parent = parent;
    }

    public double getHeuristicCost() {
        return mHeuristicCost;
    }

    public void setHeuristicCost(double mHeuristicCost) {
        this.mHeuristicCost = mHeuristicCost;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public int getX_POSITION() {
        return X_POSITION;
    }

    public int getY_POSITION() {
        return Y_POSITION;
    }

    @Override
    public int compareTo(Object o) {
        return (int) Math.round(mHeuristicCost);
    }

    public enum Status {
        EMPTY,
        ACTIVE,
        WALL,
        FOUND,
        START,
        END
    }
}
