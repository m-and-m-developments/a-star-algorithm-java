package com.mandm.astar.a_start_solver;

import com.mandm.astar.a_start_solver.model.Solver;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/21/2016
 */
public final class AStarSolver extends Solver {
    private static final double ORTHOGONAL_COST = 1;
    private static final double DIAGONAL_COST = Math.sqrt(2) + ORTHOGONAL_COST;

    public AStarSolver(GridProvider gridProvider) {
        super(gridProvider);
        calculateHeuristicCost();
    }

    public void solve() {
        new Thread(() -> {
            PriorityQueue<Field> openList = new PriorityQueue<>();
            openList.add(GRID_PROVIDER.getStartField());
            Field current;

            long time = System.nanoTime();

            while (true) {
                current = openList.poll();
                if (current == null) {
                    return;
                }
                if (current.getStatus() == Field.Status.END) {
                    break;
                }
                current.setStatus(Field.Status.ACTIVE);

                for (Field tmp : getOrthogonalAndDiagonalNeighbours(GRID_PROVIDER, current)) {
                    solveNode(openList, current, tmp);
                }
            }

            System.out.println(String.valueOf(System.nanoTime() - time));

            current = current.getParent();
            while (current.getStatus() != Field.Status.START) {
                current.setStatus(Field.Status.FOUND);
                current = current.getParent();
            }
        }).start();
    }

    private static void solveNode(PriorityQueue<Field> openList, Field current, Field tmp) {
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

    private void calculateHeuristicCost() {
        for (List<Field> list : GRID_PROVIDER.getGrid()) {
            for (Field field : list) {
                field.setHeuristicCost(GRID_PROVIDER.getTargetField(), ORTHOGONAL_COST, DIAGONAL_COST);
            }
        }
    }
}
