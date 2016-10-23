package com.mandm.astar.grid;

import com.mandm.astar.ui.widget.Field;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public abstract class GridProvider {

    private final int X_START;
    private final int Y_START;
    private final int X_TARGET;
    private final int Y_TARGET;
    protected List<List<Field>> mGrid;

    public GridProvider(int xStart, int yStart, int xTarget, int yTarget) {
        X_START = xStart;
        Y_START = yStart;
        X_TARGET = xStart;
        Y_TARGET = yTarget;
    }

    public List<List<Field>> getGrid() {
        return mGrid;
    }

    protected abstract void generateGrid();

    public Field getStartField() {
        return getGrid().get(X_START).get(Y_START);
    }

    public int getXStart() {
        return X_START;
    }

    public int getYStart() {
        return Y_START;
    }

    public int getXTarget() {
        return X_TARGET;
    }

    public int getYTarget() {
        return Y_TARGET;
    }
}
