package com.mandm.astar.ui.widget;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.Color;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glRecti;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
 */
public class Field extends View implements Comparable {
    protected final int X_POSITION;
    protected final int Y_POSITION;
    protected boolean mNeedsRender;

    protected double mHeuristicCost;
    protected double finalCost;
    protected Status mStatus;
    protected Field parent;

    public Field(Status status, int yPosition, int xPosition) {
        super();
        setStatus(status);
        X_POSITION = xPosition;
        Y_POSITION = yPosition;
    }

    @Override
    public void update() {
    }

    public Color getColor() {
        switch (mStatus) {
            case EMPTY:
                return new Color(Color.WHITE);
            case ACTIVE:
                return new Color(Color.BLUE);
            case WALL:
                return new Color(Color.BLACK);
            case FOUND:
                return new Color(Color.GREEN);
            case START:
                return new Color(Color.CYAN);
            case END:
                return new Color(Color.RED);
            default:
                throw new IllegalStateException("Unhandled state in method getColor()");
        }
    }

    @Override
    public void render() {
        if (mNeedsRender) {
//            mNeedsRender = false;
            Color color = getColor();
            glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
            glRecti(mPosX, mPosY, mPosX + mWidth, mPosY + mHeight);
        }
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        if ((mStatus != Status.START && mStatus != Status.END) || status != Status.ACTIVE) {
            mStatus = status;
            mNeedsRender = true;
        }
    }

    public Field getParent() {
        return parent;
    }

    public void setParent(Field parent) {
        this.parent = parent;
    }

    public double getHeuristicCost() {
        return mHeuristicCost;
    }

    public void setHeuristicCost(Field targetField, final double ORTHOGONAL_COST, final double DIAGONAL_COST) {
        mHeuristicCost = Math.sqrt(Math.pow(Math.abs(targetField.getX_POSITION() - X_POSITION), 2) + Math.pow(Math.abs(targetField.getY_POSITION() - Y_POSITION), 2));
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public int getX_POSITION() {
        return X_POSITION;
    }

    public int getY_POSITION() {
        return Y_POSITION;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return (int) Math.round(getHeuristicCost() - ((Field) o).getHeuristicCost());
    }

    public enum Status {
        EMPTY(0),
        ACTIVE(1),
        WALL(2),
        FOUND(3),
        START(4),
        END(5);

        public int mNum;

        Status(int num) {
            this.mNum = num;
        }

        public static Status parseStatus(int num) {
            switch (num) {
                case 0:
                    return EMPTY;
                case 1:
                    return ACTIVE;
                case 2:
                    return WALL;
                case 3:
                    return FOUND;
                case 4:
                    return START;
                case 5:
                    return END;
                default:
                    return null;
            }
        }
    }
}
