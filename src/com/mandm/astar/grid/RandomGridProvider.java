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
        super();
        mWidth = width;
        mHeight = height;
        mGrid = new ArrayList<>();
        generateGrid();
    }

    @Override
    public void generateGrid() {
        mGrid = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < mWidth; i++) {
            mGrid.add(new ArrayList<>());
        }

        for (int i = 0; i < mWidth; i++) {
            for (int j = 0; j < mHeight; j++) {
                boolean wall = random.nextInt(3) == 0;
                mGrid.get(i).add(new Field(wall ? Field.Status.WALL : Field.Status.EMPTY, i, j));
            }
        }

        Field start = mGrid.get(random.nextInt(mGrid.size())).get(random.nextInt(mGrid.get(0).size()));
        start.setStatus(Field.Status.START);
        setStartField(start);

        Field target = mGrid.get(random.nextInt(mGrid.size())).get(random.nextInt(mGrid.get(0).size()));
        target.setStatus(Field.Status.END);
        setTargetField(target);
    }
}
