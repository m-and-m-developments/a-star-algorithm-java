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

import com.mandm.astar.ui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created on 10/24/16.
 *
 * @author martin
 */
public class SelectableField extends Field {

    protected static Status sNextStatus;

    public static void setNextStatus(Status nextStatus) {
        sNextStatus = nextStatus;
    }

    public SelectableField(Status status, int xPosition, int yPosition) {
        super(status, xPosition, yPosition);
    }

    @Override
    public void update() {
        super.update();

//        int mouseButton = Mouse.getEventButton();


        if (/*mouseButton >= 0
                &&*/ Mouse.getX() >= mPosX && Mouse.getX() <= mPosX + getWidth()
                && GuiScreen.WINDOW_HEIGHT - Mouse.getY() >= mPosY
                && GuiScreen.WINDOW_HEIGHT - Mouse.getY() <= mPosY + getHeight()) {
//            Log.e(String.valueOf(mouseButton));
//            if (mouseButton == 0) {
//                mStatus = Status.WALL;
//            } else if (mouseButton == 1) {
//                mStatus = Status.EMPTY;
//            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
                mStatus = Status.WALL;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                mStatus = Status.EMPTY;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
                mStatus = Status.START;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                mStatus = Status.END;
            }
        }

//        Mouse.next();

    }

    @Override
    public void render() {
        super.render();
    }
}
