/*
 * Copyright 2016 Martin Fink & Moriz Martiner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mandm.astar.a_start_solver;

import com.mandm.astar.a_start_solver.model.Solver;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;

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

    private PriorityBlockingQueue<Field> openList = new PriorityBlockingQueue<>();

    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    private long time;

    private boolean targetFound;

    public AStarMultiThread(GridProvider gridProvider) {
        super(gridProvider);
        calculateHeuristicCost();
    }

    public void solve() {

        openList.add(GRID_PROVIDER.getStartField());

        Solver[] solvers = new Solver[8];

        Arrays.setAll(solvers, n -> new Solver());

        for (Solver solver : solvers) {
            executorService.execute(solver);
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
                    System.out.println(String.valueOf(System.nanoTime() - time));
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
