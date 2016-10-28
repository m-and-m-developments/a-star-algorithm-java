package com.mandm.astar.ui.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
public abstract class View {

    private static int nextID = 1;
    protected final int mID;
    protected int mPosX;
    protected int mPosY;
    protected int mWidth;
    protected int mHeight;
    protected boolean mIsHeld;

    protected List<ActionListener> listeners;

    public View() {
        listeners = new ArrayList<>();

        mID = nextID++;
    }

    public View(int posX, int posY, int width, int height) {
        this();
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

    public final int getID() {
        return mID;
    }

    public void addClickListener(ActionListener listener) {
        listeners.add(listener);
    }

    public void removeClickListener(ActionListener listener) {
        listeners.remove(listener);
    }

    public void onClick() {
        listeners.forEach(listener -> listener.onClick(View.this));
    }

    public abstract void update();

    public abstract void render();

    public interface ActionListener {
        void onClick(View actionPerformer);
    }

}
