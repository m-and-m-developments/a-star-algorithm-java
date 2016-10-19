package com.mandm.astar;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.Renderer;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Main {

    public static void main(String[] args) {
        GridProvider gridProvider = new RandomGridProvider(512, 512);

        Renderer renderer = new Renderer(gridProvider);

        new Thread(renderer).start();
    }

}
