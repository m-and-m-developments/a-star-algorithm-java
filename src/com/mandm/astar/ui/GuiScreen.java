package com.mandm.astar.ui;

import com.mandm.astar.a_start_solver.AStarSolver;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.render.GLHelper;
import com.mandm.astar.render.GridRenderer;
import com.mandm.astar.ui.anim.AccelerateDecelerateInterpolator;
import com.mandm.astar.ui.anim.Animation;
import com.mandm.astar.ui.anim.Animator;
import com.mandm.astar.ui.anim.SlideAnimation;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.View;
import com.mandm.astar.ui.widget.ViewGroup;
import com.mandm.astar.util.Log;
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
    private final ViewGroup solveGroup;
    private final ViewGroup generateGroup;
    private final Button solve;
    private final Button generateGrid;
    private final Button loadGrid;
    private final Button exit;
    protected List<View> views;
    private boolean closeRequested;

    private GridProvider gridProvider;

    public GuiScreen() {
        this.views = new ArrayList<>();

        GLHelper.initDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, false);

        solveGroup = new ViewGroup(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        generateGroup = new ViewGroup(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gridProvider = new RandomGridProvider(5, 10);
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
            AStarSolver.solve(gridProvider);
            solve.setEnabled(false);
        });
        generateGrid.addClickListener(view -> {
            gridProvider.generateGrid();
            solve.setEnabled(true);
        });
        loadGrid.addClickListener(view -> {
            Animation animation1 = new SlideAnimation(500, solveGroup, new AccelerateDecelerateInterpolator(),-WINDOW_WIDTH, 0);
            Animation animation2 = new SlideAnimation(500, generateGroup, new AccelerateDecelerateInterpolator(),-WINDOW_WIDTH, 0);

            animation1.addAnimationListener(animation -> {
                Log.d("OK!");
            });

            Animator.animate(animation1);
            Animator.animate(animation2);

        });
        exit.addClickListener(view -> closeRequested = true);

        addView(solveGroup);
    }

    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(.933333333f, .933333333f, .933333333f, 0);

        views.forEach(View::render);

        Display.update();
    }

    protected void update() {
        while (!Display.isCloseRequested() && !closeRequested) {
            views.forEach(View::update);

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
}
