package com.mandm.astar.ui;

import com.mandm.astar.a_start_solver.AStarSolver;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.Window;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.GridRenderer;
import com.mandm.astar.ui.widget.View;
import com.mandm.astar.ui.widget.ViewGroup;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

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

        Window.create(WINDOW_WIDTH, WINDOW_HEIGHT);

        solveGroup = new ViewGroup(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gridProvider = new RandomGridProvider(150, 300);
        GridRenderer renderer = new GridRenderer(gridProvider, WINDOW_WIDTH, 600);
        solveGroup.addView(renderer);


        solve = new Button(0, 600, "Solve!");
        generateGrid = new Button(solve.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Generate grid");
        loadGrid = new Button(generateGrid.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Load grid");
        exit = new Button(loadGrid.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Exit", Color.red);

        solveGroup.addView(solve);
        solveGroup.addView(generateGrid);
        solveGroup.addView(loadGrid);
        solveGroup.addView(exit);

        solve.addClickListener(view -> {
            AStarSolver.solve(gridProvider);
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

    /**
     * Closes the childView
     *
     * @param gridProvider if this parameter is not null, the grid of the parameter gets tǵiven to this gridManager
     */
    public void closeChild(GridProvider gridProvider) {
        child = null;
        if (gridProvider != null) {
            this.gridProvider.copyFromProvider(gridProvider);
            solve.setEnabled(true);
        }
    }
}
