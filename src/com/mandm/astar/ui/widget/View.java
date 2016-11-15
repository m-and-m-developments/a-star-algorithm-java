/*
 * Copyright 2016 Martin Fink & Moriz Martiner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
