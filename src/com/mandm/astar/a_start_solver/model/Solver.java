package com.mandm.astar.a_start_solver.model;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/31/2016
 */
public abstract class Solver {
    protected final GridProvider GRID_PROVIDER;

    public Solver(GridProvider gridProvider) {
        if (gridProvider.getStartField() == null) {
            throw new NullPointerException("Start field cannot be empty");
        }

        if (gridProvider.getTargetField() == null) {
            throw new NullPointerException("Target field cannot be empty");
        }

        GRID_PROVIDER = gridProvider;
    }

    public abstract void solve();

    protected static List<Field> getOrthogonalAndDiagonalNeighbours(GridProvider gridProvider, Field field) {
        List<Field> list = getOrthogonalNeighbours(gridProvider, field);
        list.addAll(getDiagonalNeighbours(gridProvider, field));
        return list;
    }

    protected static List<Field> getOrthogonalNeighbours(GridProvider gridProvider, Field field) {
        List<Field> list = new ArrayList<>(4);

        // Field on position 2
        if (field.getY_POSITION() - 1 >= 0) {
            list.add(gridProvider.getGrid().get(field.getY_POSITION() - 1).get(field.getX_POSITION()));
        }


        if (field.getX_POSITION() - 1 >= 0) {
            list.add(gridProvider.getGrid().get(field.getY_POSITION()).get(field.getX_POSITION() - 1));
        }

        if (field.getX_POSITION() + 1 < gridProvider.getGrid().get(field.getY_POSITION()).size()) {
            list.add(gridProvider.getGrid().get(field.getY_POSITION()).get(field.getX_POSITION() + 1));
        }

        if (field.getY_POSITION() + 1 < gridProvider.getGrid().size()) {
            list.add(gridProvider.getGrid().get(field.getY_POSITION() + 1).get(field.getX_POSITION()));
        }

        return list;
    }

    protected static List<Field> getDiagonalNeighbours(GridProvider gridProvider, Field field) {
        List<Field> list = new ArrayList<>(4);

        if (field.getY_POSITION() - 1 >= 0) {
            if (field.getX_POSITION() - 1 >= 0) {
                list.add(gridProvider.getGrid().get(field.getY_POSITION() - 1).get(field.getX_POSITION() - 1));
            }

            if (field.getX_POSITION() + 1 < gridProvider.getGrid().get(field.getY_POSITION()).size()) {
                list.add(gridProvider.getGrid().get(field.getY_POSITION() - 1).get(field.getX_POSITION() + 1));
            }
        }

        if (field.getY_POSITION() + 1 < gridProvider.getGrid().size()) {
            if (field.getX_POSITION() - 1 >= 0) {
                list.add(gridProvider.getGrid().get(field.getY_POSITION() + 1).get(field.getX_POSITION() - 1));
            }

            if (field.getX_POSITION() + 1 < gridProvider.getGrid().get(field.getY_POSITION()).size()) {
                list.add(gridProvider.getGrid().get(field.getY_POSITION() + 1).get(field.getX_POSITION() + 1));
            }
        }

        return list;
    }
}
