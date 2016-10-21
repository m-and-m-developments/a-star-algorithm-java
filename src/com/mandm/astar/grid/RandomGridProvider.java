package com.mandm.astar.grid;

import com.mandm.astar.ui.widget.Field;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class RandomGridProvider extends GridProvider {

    private int mWidth;
    private int mHeight;

    public RandomGridProvider(int width, int height) {
        mWidth = width;
        mHeight = height;
        generateGrid();
    }

    @Override
    protected void generateGrid() {
        mGrid = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < mHeight; i++) {
            mGrid.add(new ArrayList<>());
            for (int j = 0; j < mWidth; j++) {
                mGrid.get(i).add(new Field(random.nextInt(3) % 2));
            }
        }
    }
}
