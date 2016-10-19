package com.mandm.astar.render;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;
import com.mandm.astar.ui.widget.View;

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
        mGrid = gridProvider.getGrid();

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
        mGrid.forEach(row -> row.forEach(Field::update));
    }

    @Override
    public void render() {
        mGrid.forEach(row -> row.forEach(Field::render));
    }
}
