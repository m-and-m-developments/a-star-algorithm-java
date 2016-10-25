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
