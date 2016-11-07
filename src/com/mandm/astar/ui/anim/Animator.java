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

package com.mandm.astar.ui.anim;

import com.mandm.astar.ui.widget.View;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 20.10.2016.
 *
 * @author Martin
 */
public class Animator {

    public static void animate(Animation animation, List<View> views) {
        new Thread(() -> animation.animationLoop(views)).start();
    }

    public static void animate(Animation animation, View... views) {
        animate(animation, Arrays.asList(views));
    }
}
