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

import com.mandm.astar.grid.GridProvider;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class GridRenderer extends View {

    private GridProvider mGridProvider;
    private List<List<Field>> mGrid;

    public GridRenderer(GridProvider gridProvider, int width, int height) {
        super(0, 0, width, height);
        mGridProvider = gridProvider;
        setGridPositions();
    }

    protected void setGridPositions() {
        mGrid = mGridProvider.getGrid();

        //line and row where the loop is
        //positions[0] = line
        //positions[1] = row
        final int[] positions = {0, 0};
        mGrid.forEach(line -> {
            positions[1] = 0;
            line.forEach(field -> {
                field.setHeight(mHeight / mGrid.size());
                field.setWidth(mWidth / mGrid.get(positions[0]).size());

                field.setPosX(field.getWidth() * positions[1]);
                field.setPosY(field.getHeight() * positions[0]);

                positions[1]++;
            });
            positions[0]++;
        });
    }

    @Override
    public void update() {
        List<List<Field>> grid = mGridProvider.getGrid();

        if (grid.size() != mGrid.size() || grid.get(0).size() != mGrid.get(0).size()) {
            setGridPositions();
        }

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                mGrid.get(i).get(j).setStatus(grid.get(i).get(j).getStatus());
            }
        }

        mGrid.forEach(row -> row.forEach(Field::update));
    }

    @Override
    public void render() {
        mGrid.forEach(row -> row.forEach(Field::render));
    }
}
