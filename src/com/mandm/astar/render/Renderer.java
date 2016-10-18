package com.mandm.astar.render;

import com.mandm.astar.grid.Field;
import com.mandm.astar.grid.GridProvider;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Renderer implements Runnable {

    private GridProvider mGridProvider;
    private List<List<Field>> mGrid;

    public static final int WINDOW_WIDTH = 20;
    public static final int WINDOW_HEIGHT = 20;

    public Renderer(GridProvider gridProvider) {
        mGridProvider = gridProvider;
        mGrid = mGridProvider.getGrid();

        FieldElement element = new FieldElement(null, 0, 0, 10, 10);

    }

    @Override
    public void run() {

        while (true) {

            for (List<Field> row : mGrid) {
                for (Field field : row) {

                }
            }

        }

    }


    protected class FieldElement {

        Field mField;
        float mPosX;
        float mPosY;
        float mWidth;
        float mHeight;

        /**
         * Calculates the position and width/height for the format the gou needs (x: -1 ... +1; y: -1 ... +1)
         *
         * @param field the field which is stored in this wrapper class
         * @param line  in which line this field is placed
         * @param row   the row in which this field is placed
         * @param lines how many lines there are in the grid
         * @param rows  how many rows there are in the grid
         */
        protected FieldElement(Field field, int line, int row, int lines, int rows) {
            mField = field;

            //Calculating the height and width in pixels for each field
            int pixelWidth = WINDOW_WIDTH / rows;
            int pixelHeight = WINDOW_HEIGHT / lines;

            // calculating the width and height into floating point numbers from 0 < x < 2
            mWidth = (float) pixelWidth / WINDOW_WIDTH;
            mHeight = (float) pixelHeight / WINDOW_HEIGHT;

            // calculating the position for the field into floating point numbers so they match the opengl raster

            // y
            // ---------------|1|-----------------
            // |               |                 |
            // |               |                 |
            // |               |                 |
            // |-1|----------------------------|1|  --> x
            // |               |                 |
            // |               |                 |
            // |               |                 |
            // --------------|-1|-----------------
            mPosX = (mWidth * 2 * row) - 1;
            mPosY = (mHeight * -2 * line) + 1;
        }

        // TODO: 18.10.2016 Change this so it calls the {@link Field#needsRender} function
        boolean needsRender() {
            return true;
        }

    }
}
