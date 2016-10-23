package com.mandm.astar.a_start_solver;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/21/2016
 */
public class AStarSolver {
    private final GridProvider GRID_PROVIDER;

    private List<Field> openList = new ArrayList<>();

    private Field bestField;

    public AStarSolver(GridProvider gridProvider) {
        GRID_PROVIDER = gridProvider;
    }

    public void solve() {
        openList.add(GRID_PROVIDER.getStartField());
        Field temp;
        while (true) {
            temp = bestField;
            for (Field field : openList) {
                if (bestField == null) {
                    bestField = field;
                } else if (field.getDistance() < bestField.getDistance()) {
                    bestField = field;
                }
            }

            bestField.setPreviousField(temp);
            openList.remove(bestField);

        }
    }
}
