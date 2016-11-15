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

    public static final int margin = 4;

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

        mPosX = posX;
        mPosY = posY;
        mText = text;
        calcWidth();
        calcHeight();
    }

    public void calcWidth() {
        mWidth = mFont.getWidth(mText) + 2 * margin;
    }

    public void calcHeight() {
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

    public Font getFont() {
        return mFont;
    }
}
