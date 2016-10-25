package com.mandm.astar.render;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;
import com.mandm.astar.ui.widget.View;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class GridRenderer extends View {

    private GridProvider mGridProvider;
    private List<List<Field>> mGrid;

    public GridRenderer(GridProvider gridProvider, int width, int height) {
        super(0, 0, width, height);
        mGridProvider = gridProvider;
        setGrid();
    }

    protected void setGrid() {
        mGrid = mGridProvider.getGrid();

        //line and row where we are
        //positions[0] = line
        //positions[1] = row
        final int[] positions = {0, 0};
        mGrid.forEach(line -> {
            positions[1] = 0;
            line.forEach(field -> {
                field.setHeight(mHeight / mGrid.size());
                field.setWidth(mWidth / mGrid.get(positions[0]).size());

                field.setPosX(field.getWidth() * positions[1]);
                field.setPosY(field.getHeight() * positions[0]);

                positions[1]++;
            });
            positions[0]++;
        });
    }

    @Override
    public void update() {
        List<List<Field>> grid = mGridProvider.getGrid();

        if (grid.size() != mGrid.size() || grid.get(0).size() != mGrid.get(0).size()) {
            setGrid();
        }

        for (int i = 0; i < mGrid.size(); i++) {
            for (int j = 0; j < mGrid.get(i).size(); j++) {
                mGrid.get(i).get(j).setStatus(grid.get(i).get(j).getStatus());
            }
        }

        mGrid.forEach(row -> row.forEach(Field::update));
    }

    @Override
    public void render() {
        glBegin(GL_LINE);
        glColor3f(1, 0, 0);

        glLineWidth(2);
        for (int i = 0; i < mGrid.size(); i++) {
            int x = i * (mWidth / mGrid.size());
            glVertex2f(x, 0);
            glVertex2f(x, mHeight);
        }

        for (int i = 0; i < mGrid.get(0).size(); i++) {
            int y = i * (mHeight / mGrid.get(0).size());
            glVertex2f(0, y);
            glVertex2f(mWidth, y);
        }
        glEnd();

        mGrid.forEach(row -> row.forEach(Field::render));
    }
}
