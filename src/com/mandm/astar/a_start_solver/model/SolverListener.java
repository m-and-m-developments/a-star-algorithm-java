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

package com.mandm.astar.a_start_solver.model;

/**
 * Project: a-star-algorithm-java
 * Created by Moriz Martiner
 * Created on 11/2/2016
 */
public interface SolverListener {

    default void onStart() {
    }

    default void onStartTimer() {
    }

    default void onStopTimer() {
    }

    default void onFinishedUnsuccessful() {

    }

    default void onFinished() {
    }
}
