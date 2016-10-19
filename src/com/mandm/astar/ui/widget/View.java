package com.mandm.astar.ui.widget;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
public abstract class View {

    protected int mPosX;
    protected int mPosY;
    protected int mWidth;
    protected int mHeight;

    public View(int posX, int posY, int width, int height) {
        this.mPosX = posX;
        this.mPosY = posY;
        this.mWidth = width;
        this.mHeight = height;
    }

    public void setPosition(int posX, int posY) {
        this.mPosX = posX;
        this.mPosY = posY;
    }

    public int getPosX() {
        return mPosX;
    }

    public void setPosX(int posX) {
        this.mPosX = posX;
    }

    public int getPosY() {
        return mPosY;
    }

    public void setPosY(int mPosY) {
        this.mPosY = mPosY;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public abstract void update();

    public abstract void render();

}
