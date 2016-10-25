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
    protected Field startField;
    protected Field targetField;

    public List<List<Field>> getGrid() {
        if (mGrid == null) {
            generateGrid();
        }
        return mGrid;
    }

    public abstract void generateGrid();

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

    public void copyFromProvider(GridProvider gridProvider) {
        mGrid = gridProvider.getGrid();
        startField = gridProvider.getStartField();
        targetField = gridProvider.getTargetField();
    }

    /**
     * Checks if the field is valid
     * Removes mutliple start and end fields
     *
     * @return true if the field is valid
     */
    public boolean validateField() {
        if (mGrid == null) {
            return false;
        }

        mGrid.forEach(row -> row.forEach(field -> {
            if (targetField == null && field.getStatus() == Field.Status.END) {
                targetField = field;
            } else if (startField == null && field.getStatus() == Field.Status.START) {
                startField = field;
            } else if ((field != startField && field.getStatus() == Field.Status.START)
                    || (field != targetField && field.getStatus() == Field.Status.END)) {
                field.setStatus(Field.Status.EMPTY);
            }
        }));

        return startField != null && targetField != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();


        mGrid.forEach(row -> {
            row.forEach(field -> builder
                    .append(String.valueOf(field.getStatus().mNum))
                    .append(';'));
            builder.append('\n');
        });

        return builder.toString();
    }
}
