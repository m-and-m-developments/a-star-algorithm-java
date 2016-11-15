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

package com.mandm.astar.grid;

import com.mandm.astar.ui.widget.Field;
import com.mandm.astar.ui.widget.SelectableField;

import java.util.ArrayList;

/**
 * Created on 10/24/16.
 *
 * @author martin
 */
public class EmptyGridProvider extends GridProvider {

    protected int mWidth;
    protected int mHeight;
    protected boolean mSelectableFields;

    public EmptyGridProvider(int width, int height, boolean selectableFields) {
        mWidth = width;
        mHeight = height;
        mSelectableFields = selectableFields;
        generateGrid();
    }

    /**
     * Generates a empty grid
     */
    @Override
    public void generateGrid() {
        mGrid = new ArrayList<>();

        for (int i = 0; i < mWidth; i++) {
            mGrid.add(new ArrayList<>());
            for (int j = 0; j < mHeight; j++) {
                if (mSelectableFields) {
                    mGrid.get(i).add(new SelectableField(Field.Status.EMPTY, i, j));
                } else {
                    mGrid.get(i).add(new Field(Field.Status.EMPTY, i, j));
                }
            }
        }
    }

    /**
     * Copies the grid from the given provider
     * Also sets the width and height
     *
     * @param gridProvider the gridProvider from where the grid is copied
     */
    @Override
    public void copyFromProvider(GridProvider gridProvider) {
        super.copyFromProvider(gridProvider);
        if (gridProvider instanceof RandomGridProvider) {
            RandomGridProvider randomGridProvider = (RandomGridProvider) gridProvider;
            mWidth = randomGridProvider.getWidth();
            mHeight = randomGridProvider.getHeight();
        } else if (gridProvider instanceof EmptyGridProvider) {
            EmptyGridProvider emptyGridProvider = (EmptyGridProvider) gridProvider;
            mWidth = emptyGridProvider.mWidth;
            mHeight = emptyGridProvider.mHeight;
        }
    }

}
