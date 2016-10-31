package com.mandm.astar.ui;

import com.mandm.astar.a_start_solver.AStarSolver;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.GLHelper;
import com.mandm.astar.render.GridRenderer;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.View;
import com.mandm.astar.ui.widget.ViewGroup;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 19.10.2016.
 *
 * @author Martin
 */
@SuppressWarnings("WeakerAccess")
public class GuiScreen {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 640;
    private final Button solve;
    private final Button generateGrid;
    private final Button loadGrid;
    private final Button exit;
    private final ViewGroup solveGroup;
    protected List<View> views;
    private boolean closeRequested;
    private ViewGroup child;

    private GridProvider gridProvider;

    public GuiScreen() {
        this.views = new ArrayList<>();

        GLHelper.initDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, false);

        solveGroup = new ViewGroup(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gridProvider = new RandomGridProvider(256, 512);
        GridRenderer renderer = new GridRenderer(gridProvider, getWidth(), 600);
        solveGroup.addView(renderer);


        solve = new Button(20, 600, "Solve!");
        generateGrid = new Button((WINDOW_WIDTH - 20) / 4, 600, "Generate grid");
        loadGrid = new Button((WINDOW_WIDTH - 20) / 2, 600, "Load grid");
        exit = new Button((WINDOW_WIDTH - 20) / 4 * 3, 600, "Exit");

        solveGroup.addView(solve);
        solveGroup.addView(generateGrid);
        solveGroup.addView(loadGrid);
        solveGroup.addView(exit);

        solve.addClickListener(view -> {
            new AStarSolver(gridProvider).solve();
            solve.setEnabled(false);
        });
        generateGrid.addClickListener(view -> {
            gridProvider.generateGrid();
            solve.setEnabled(true);
        });
        loadGrid.addClickListener(view -> {
            child = new LoadGridView(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        });
        exit.addClickListener(view -> closeRequested = true);

        addView(solveGroup);
    }

    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(.93333333f, .93333333f, .93333333f, 0);

        if (child == null) {
            views.forEach(View::render);
        } else {
            child.render();
        }

        Display.update();
    }

    protected void update() {
        while (!Display.isCloseRequested() && !closeRequested) {
            if (child == null) {
                views.forEach(View::update);
            } else {
                child.update();
            }

            render();
        }

        Display.destroy();
    }

    public void addView(View view) {
        views.add(view);
    }

    /**
     * After this method is called, the thread in which
     * the function is called will be blocked
     */
    public void start() {
        update();
    }

    public int getWidth() {
        return WINDOW_WIDTH;
    }

    public void closeChild(GridProvider gridProvider) {
        child = null;
        if (gridProvider != null) {
            this.gridProvider.copyFromProvider(gridProvider);
            solve.setEnabled(true);
        }
    }
}
