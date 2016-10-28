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

    /**
     * Count of fields
     */
    private int mWidth;

    /**
     * Count of fields
     */
    private int mHeight;

    public RandomGridProvider(int width, int height) {
        super();
        mWidth = width;
        mHeight = height;
        generateGrid();
    }

    /**
     * Generates a random grid with a wall to no-wall ratio of 1:1
     * A start-field and a target-field are set at the first and last field
     */
    @Override
    public void generateGrid() {
        mGrid = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < mWidth; i++) {
            mGrid.add(new ArrayList<>());
        }

        for (int i = 0; i < mWidth; i++) {
            for (int j = 0; j < mHeight; j++) {
                boolean wall = random.nextInt(2) == 0;
                mGrid.get(i).add(new Field(wall ? Field.Status.WALL : Field.Status.EMPTY, i, j));
            }
        }

//        Field start = mGrid.get(random.nextInt(mGrid.size())).get(random.nextInt(mGrid.get(0).size()));
        Field start = mGrid.get(0).get(0);
        start.setStatus(Field.Status.START);
        setStartField(start);

//        Field target = mGrid.get(random.nextInt(mGrid.size())).get(random.nextInt(mGrid.get(0).size()));
        Field target = mGrid.get(mWidth - 1).get(mHeight - 1);
        target.setStatus(Field.Status.END);
        setTargetField(target);
    }

    /**
     * Copies the grid from the given provider
     * Also sets the width and height
     *
     * @param gridProvider the gridProvider from where the grid is copied
     */
    @Override
    public void copyFromProvider(GridProvider gridProvider) {
        super.copyFromProvider(gridProvider);
        if (gridProvider instanceof RandomGridProvider) {
            RandomGridProvider randomGridProvider = (RandomGridProvider) gridProvider;
            mWidth = randomGridProvider.mWidth;
            mHeight = randomGridProvider.mHeight;
        } else if (gridProvider instanceof EmptyGridProvider) {
            EmptyGridProvider emptyGridProvider = (EmptyGridProvider) gridProvider;
            mWidth = emptyGridProvider.mWidth;
            mHeight = emptyGridProvider.mHeight;
        }
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
