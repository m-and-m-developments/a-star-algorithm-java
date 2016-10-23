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

    public RandomGridProvider(int xStart, int yStart, int xTarget, int yTarget, int width, int height) {
        super(xStart, yStart, xTarget, yTarget);
        mWidth = width;
        mHeight = height;
        generateGrid();
    }

    @Override
    protected void generateGrid() {
        mGrid = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < mWidth; i++) {
            mGrid.add(new ArrayList<>());
            for (int j = 0; j < mHeight; j++) {
                mGrid.get(i).add(new Field(random.nextBoolean() ? Field.Status.WALL : Field.Status.EMPTY, i, j, getXTarget(), getYTarget()));
            }
        }
    }
}
