package com.mandm.astar.a_start_solver;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;
import com.mandm.astar.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/21/2016
 */
public final class AStarSolver {
    private static final double ORTHOGONAL_COST = 1;
    private static final double DIAGONAL_COST = Math.sqrt(2) + ORTHOGONAL_COST;

    public static void solve(final GridProvider GRID_PROVIDER) {

        new Thread(() -> {
            long nanos = System.nanoTime();
            for (List<Field> list : GRID_PROVIDER.getGrid()) {
                for (Field field : list) {
                    field.setHeuristicCost(Math.sqrt(Math.pow(Math.abs(GRID_PROVIDER.getTargetField().getX_POSITION() - field.getX_POSITION()), 2) + Math.pow(Math.abs(GRID_PROVIDER.getTargetField().getY_POSITION() - field.getY_POSITION()), 2)));
                }
            }

            PriorityQueue<Field> openList = new PriorityQueue<>();
            openList.add(GRID_PROVIDER.getStartField());
            Field current;

            while (true) {
                current = openList.poll();
                if (current == null) {
                    return;
                }
                if (current.getStatus() == Field.Status.END) {
                    break;
                }
                current.setStatus(Field.Status.ACTIVE);

                for (Field tmp : getNeighbourFields(GRID_PROVIDER, current)) {
                    checkAndUpdateCost(openList, current, tmp, current.getY_POSITION() != tmp.getY_POSITION() && current.getX_POSITION() != tmp.getX_POSITION() ? DIAGONAL_COST : ORTHOGONAL_COST);
                }

            }
            current = current.getParent();
            while (current.getStatus() != Field.Status.START) {
                current.setStatus(Field.Status.FOUND);
                current = current.getParent();
            }

            Log.d(String.format("Time: %s ms", (System.nanoTime() - nanos) / 1000000));
        }).start();
    }

    private static void checkAndUpdateCost(PriorityQueue<Field> openList, Field current, Field tmp, double cost) {
        if (tmp == null || tmp.getStatus() != Field.Status.EMPTY && tmp.getStatus() != Field.Status.END) {
            return;
        }
        double finalCost = current.getFinalCost() + tmp.getX_POSITION() != current.getX_POSITION() && tmp.getY_POSITION() != current.getX_POSITION() ? DIAGONAL_COST : ORTHOGONAL_COST;

        boolean inOpen = openList.contains(tmp);
        if (!inOpen || finalCost < tmp.getFinalCost()) {
            tmp.setFinalCost(finalCost);
            tmp.setParent(current);
            if (!inOpen) {
                openList.add(tmp);
            }
            tmp.setStatus(Field.Status.ACTIVE);
        }
    }

    private static List<Field> getNeighbourFields(GridProvider gridProvider, Field field) {
        List<Field> list = getOrthogonalNeighbours(gridProvider, field);
        list.addAll(getDiagonalNeighbours(gridProvider, field));
        return list;
    }

    private static List<Field> getOrthogonalNeighbours(GridProvider gridProvider, Field field) {
        List<Field> list = new ArrayList<>();

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

    private static List<Field> getDiagonalNeighbours(GridProvider gridProvider, Field field) {
        List<Field> list = new ArrayList<>();

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
