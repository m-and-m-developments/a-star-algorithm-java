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

package com.mandm.astar.ui.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20.10.2016.
 *
 * @author Martin
 */
public class ViewGroup extends View {

    protected List<View> mViews;

    public ViewGroup(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        mViews = new ArrayList<>();
    }

    public void addView(View view) {
        mViews.add(view);
    }

    public void removeView(View view) {
        mViews.remove(view);
    }

    public void removeView(int id) {
        mViews.stream()
                .filter(view -> view.getID() == id)
                .forEach(view -> mViews.remove(view));
    }

    public List<View> getViews() {
        return mViews;
    }

    @Override
    public void update() {
        mViews.forEach(View::update);
    }

    @Override
    public void render() {
        mViews.forEach(View::render);
    }
}
