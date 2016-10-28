package com.mandm.astar.ui.widget;

import com.mandm.astar.grid.GridProvider;

import java.util.List;

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

        //line and row where the loop is
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

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                mGrid.get(i).get(j).setStatus(grid.get(i).get(j).getStatus());
            }
        }

        mGrid.forEach(row -> row.forEach(Field::update));
    }

    @Override
    public void render() {
        mGrid.forEach(row -> row.forEach(Field::render));
    }
}
