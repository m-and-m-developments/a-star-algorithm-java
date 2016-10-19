package com.mandm.astar.grid;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public abstract class GridProvider {

    protected List<List<Field>> mGrid;

    public GridProvider() {
    }

    public List<List<Field>> getGrid() {
        return mGrid;
    }

    protected abstract void generateGrid();

}
