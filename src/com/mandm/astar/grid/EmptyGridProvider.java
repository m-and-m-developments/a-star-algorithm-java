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
