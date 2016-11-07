package com.mandm.astar.a_start_solver.model;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 11/2/2016
 */
public interface SolverListener {
    default void onStart() {
    }

    ;

    default void onStartTimer() {
    }

    ;

    default void onStopTimer() {
    }

    ;

    default void onFinished() {
    }

    ;
}
