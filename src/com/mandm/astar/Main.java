package com.mandm.astar;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.GLHelper;
import com.mandm.astar.render.Renderer;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Main {

    public static void main(String[] args) {
        GridProvider gridProvider = new RandomGridProvider(40, 40);

        GLHelper.initDisplay(Renderer.WINDOW_WIDTH, Renderer.WINDOW_HEIGHT, false);

        Renderer renderer = new Renderer(gridProvider);

        new Thread(renderer).start();

    }

}
