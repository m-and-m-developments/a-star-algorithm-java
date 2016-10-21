package com.mandm.astar.ui.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20.10.2016.
 *
 * @author Martin
 */
public abstract class ViewGroup extends View {

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

    @Override
    public void update() {
        mViews.forEach(View::update);
    }

    @Override
    public void render() {
        mViews.forEach(View::render);
    }
}
