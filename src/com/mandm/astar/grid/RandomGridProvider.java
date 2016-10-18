package com.mandm.astar.grid;

import java.util.ArrayList;
import java.util.List;
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
        super();
        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void generateGrid() {
        mGrid = new ArrayList<>(mWidth);

        for (List<Field> row : mGrid) {
            row = new ArrayList<>(mHeight);
        }

        Random random = new Random();

        for (List<Field> row : mGrid) {
            for (Field field : row) {
                field = new Field(/*random.nextBoolean() ? 1 : 0*/);
            }
        }
    }
}
