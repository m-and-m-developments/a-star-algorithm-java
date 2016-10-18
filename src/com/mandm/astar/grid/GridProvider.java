package com.mandm.astar.grid;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public abstract class GridProvider {

    private List<List<Field>> grid;

    public GridProvider() {
    }

    public abstract List<List<Field>> getGrid();

}
