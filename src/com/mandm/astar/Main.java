package com.mandm.astar;

import com.mandm.astar.ui.GuiScreen;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Main {

    private static GuiScreen guiScreen;

    public static void main(String[] args) {

        Thread guiThread = new Thread(() -> {
            guiScreen = new GuiScreen();
            guiScreen.start();

        });

        guiThread.start();
    }

}
