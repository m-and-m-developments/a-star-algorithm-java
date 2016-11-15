package com.mandm.astar.a_start_solver;

import com.mandm.astar.a_start_solver.model.Solver;
import com.mandm.astar.a_start_solver.model.SolverListener;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/31/2016
 */
public class AStarMultiThread extends Solver {
    private static final double ORTHOGONAL_COST = 1;
    private static final double DIAGONAL_COST = Math.sqrt(2) + ORTHOGONAL_COST;

    private static final int NUMBER_OF_THREADS = 8;

    private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private PriorityBlockingQueue<Field> openList = new PriorityBlockingQueue<>();
    private SolverListener solverListener;
    private boolean targetFound;

    public AStarMultiThread(@NotNull GridProvider gridProvider, @Nullable SolverListener solverListener) {
        super(gridProvider);
        calculateHeuristicCost();
        this.solverListener = solverListener;
    }

    public void solve() {
        if (solverListener != null) {
            solverListener.onStart();
        }

        openList.add(GRID_PROVIDER.getStartField());

        Solver[] solvers = new Solver[NUMBER_OF_THREADS];

        Arrays.setAll(solvers, n -> new Solver());


        if (solverListener != null) {
            solverListener.onStartTimer();
        }
        for (Solver solver : solvers) {
            executorService.execute(solver);
        }
        if (solverListener != null) {
            solverListener.onStopTimer();
        }
    }

    private void solveNode(Field current, Field tmp) {
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

    private void targetFound() {
        new Thread(() -> {

            targetFound = true;
            executorService.shutdown();

            Field target = GRID_PROVIDER.getTargetField().getParent();
            while (target.getStatus() != Field.Status.START) {
                target.setStatus(Field.Status.FOUND);
                target = target.getParent();
            }

            if (solverListener != null) {
                solverListener.onFinished();
            }
        }).start();
    }

    private class Solver implements Runnable {

        @Override
        public void run() {
            Field current;

            while (!targetFound) {
                current = openList.poll();
                if (current == null) {
                    return;
                }
                if (current.getStatus() == Field.Status.END) {
                    targetFound();
                    break;
                }
                current.setStatus(Field.Status.ACTIVE);

                for (Field tmp : getOrthogonalAndDiagonalNeighbours(GRID_PROVIDER, current)) {
                    solveNode(current, tmp);
                }
            }
        }
    }
}
