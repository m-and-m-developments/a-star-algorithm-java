package com.mandm.astar.render;

import com.mandm.astar.grid.Field;
import com.mandm.astar.grid.GridProvider;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Renderer implements Runnable {

    private GridProvider mGridProvider;
    private List<List<FieldElement>> mGrid;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 512;

    public Renderer(GridProvider gridProvider) {
        mGridProvider = gridProvider;
        List<List<Field>> grid = mGridProvider.getGrid();

        mGrid = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            mGrid.add(new ArrayList<>());
            for (int j = 0; j < grid.get(i).size(); j++) {
                mGrid.get(i).add(new FieldElement(grid.get(i).get(j), i, j, grid.size(), grid.get(0).size()));
            }
        }
    }

    @Override
    public void run() {

        GLHelper.initDisplay(Renderer.WINDOW_WIDTH, Renderer.WINDOW_HEIGHT, false);

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            for (List<FieldElement> row : mGrid) {
                for (FieldElement field : row) {
                    field.render();
                }
            }

            Display.update();
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

            mWidth = (float) WINDOW_WIDTH / rows;
            mHeight = (float) WINDOW_HEIGHT / lines;

            mPosX = (mWidth * row);
            mPosY = (mHeight * line);
        }

        // TODO: 18.10.2016 Change this so it calls the {@link Field#needsRender} function
        boolean needsRender() {
            return true;
        }

        public void render() {
            if (needsRender()) {
                Random random = new Random();
                glColor3f(random.nextInt(2), random.nextInt(2), random.nextInt(2)/*0, 0, 1*/);
                glBegin(GL_QUADS);
                glVertex2f(mPosX, mPosY);
                glVertex2f(mPosX, mPosY + mHeight);
                glVertex2f(mPosX + mWidth, mPosY + mHeight);
                glVertex2f(mPosX + mWidth, mPosY);
                glEnd();
            }
        }

    }
}
