package com.mandm.astar.grid;

import com.mandm.astar.ui.widget.Field;

import java.util.List;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public abstract class GridProvider {
    protected List<List<Field>> mGrid;
    private Field startField;
    private Field targetField;

    public List<List<Field>> getGrid() {
        return mGrid;
    }

    protected abstract void generateGrid();

    public Field getStartField() {
        return startField;
    }

    public void setStartField(Field startField) {
        this.startField = startField;
    }

    public Field getTargetField() {
        return targetField;
    }

    public void setTargetField(Field targetField) {
        this.targetField = targetField;
    }
}
