package com.mandm.astar;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.GridRenderer;
import com.mandm.astar.ui.GuiScreen;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Main {

    private static GuiScreen guiScreen;

    public static void main(String[] args) {
        GridProvider gridProvider = new RandomGridProvider(60, 30);

        Thread guiThread = new Thread(() -> {
            guiScreen = new GuiScreen();
            GridRenderer renderer = new GridRenderer(gridProvider, guiScreen.getWidth(), 600);
            guiScreen.addView(renderer);
            guiScreen.start();
        });

        guiThread.start();

    }

}
