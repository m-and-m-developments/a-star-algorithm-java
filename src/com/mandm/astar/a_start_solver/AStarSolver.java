package com.mandm.astar.a_start_solver;

import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.ui.widget.Field;

import java.util.PriorityQueue;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 10/21/2016
 */
public final class AStarSolver {
    private static final double V_H_COST = 10;
    private static final double DIAGONAL_COST = Math.sqrt(2) * V_H_COST;

    public static void solve(final GridProvider GRID_PROVIDER) {
        System.out.println(V_H_COST + " " + DIAGONAL_COST);

        new Runnable() {
            @Override
            public void run() {
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
                    Field tmp;

                    if (current.getX_POSITION() - 1 >= 0) {
                        tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() - 1).get(current.getY_POSITION());
                        checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + V_H_COST);

                        if (current.getY_POSITION() - 1 >= 0) {
                            tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() - 1).get(current.getY_POSITION() - 1);
                            checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + DIAGONAL_COST);
                        }

                        if (current.getY_POSITION() + 1 < GRID_PROVIDER.getGrid().get(0).size()) {
                            tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() - 1).get(current.getY_POSITION() + 1);
                            checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + DIAGONAL_COST);
                        }
                    }

                    if (current.getY_POSITION() - 1 >= 0) {
                        tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION()).get(current.getY_POSITION() - 1);
                        checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + V_H_COST);
                    }

                    if (current.getY_POSITION() + 1 < GRID_PROVIDER.getGrid().get(0).size()) {
                        tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION()).get(current.getY_POSITION() + 1);
                        checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + V_H_COST);
                    }

                    if (current.getX_POSITION() + 1 < GRID_PROVIDER.getGrid().size()) {
                        tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() + 1).get(current.getY_POSITION());
                        checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + V_H_COST);

                        if (current.getY_POSITION() - 1 >= 0) {
                            tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() + 1).get(current.getY_POSITION() - 1);
                            checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + DIAGONAL_COST);
                        }

                        if (current.getY_POSITION() + 1 < GRID_PROVIDER.getGrid().get(0).size()) {
                            tmp = GRID_PROVIDER.getGrid().get(current.getX_POSITION() + 1).get(current.getY_POSITION() + 1);
                            checkAndUpdateCost(openList, current, tmp, current.getFinalCost() + DIAGONAL_COST);
                        }
                    }
                }
                current = current.getParent();
                while (current.getStatus() != Field.Status.START) {
                    current.setStatus(Field.Status.FOUND);
                    current = current.getParent();
                }
            }
        }.run();

    }

    private static void checkAndUpdateCost(PriorityQueue<Field> openList, Field current, Field tmp, double cost) {
        if (tmp == null || tmp.getStatus() != Field.Status.EMPTY && tmp.getStatus() != Field.Status.END) return;
        double finalCost = tmp.getmHeuristicCost() + cost;

        boolean inOpen = openList.contains(tmp);
        if (!inOpen || finalCost < tmp.getFinalCost()) {
            tmp.setFinalCost(finalCost);
            tmp.setParent(current);
            if (!inOpen) openList.add(tmp);
            tmp.setStatus(Field.Status.ACTIVE);
        }
    }
}
