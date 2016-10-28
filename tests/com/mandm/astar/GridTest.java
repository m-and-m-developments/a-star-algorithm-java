package com.mandm.astar;

import com.mandm.astar.a_start_solver.AStarSolver;
import com.mandm.astar.grid.EmptyGridProvider;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.RandomGridProvider;
import com.mandm.astar.ui.widget.Field;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created on 10/26/16.
 *
 * @author martin
 */
public class GridTest {

    @Test
    public void testEmptyGridProvider() {
        GridProvider provider = new EmptyGridProvider(200, 200, false);

        provider.getGrid().forEach(row -> row.forEach(field -> {
            assertSame(field.getStatus(), Field.Status.EMPTY);
        }));

        assertEquals(provider.getGrid().size(), 200);
        assertEquals(provider.getGrid().get(0).size(), 200);
    }

    @Test
    public void testRandomGridProviderSize() {
        GridProvider provider = new RandomGridProvider(256, 256);

        assertEquals(provider.getGrid().size(), 256);
        assertEquals(provider.getGrid().get(0).size(), 256);
    }

    @Test
    public void testRandomGridProvider() {
        GridProvider provider = new RandomGridProvider(200, 200);
        final boolean[] hasStart = new boolean[1];
        final boolean[] hasTarget = new boolean[1];

        provider.getGrid().forEach(row -> {
            row.forEach(field -> {
                if (field.getStatus() == Field.Status.END) {
                    hasTarget[0] = true;
                } else if (field.getStatus() == Field.Status.START) {
                    hasStart[0] = true;
                }
            });
        });


        assertTrue(hasStart[0]);
        assertTrue(hasTarget[0]);
    }

    @Test(timeout = 1000)
    public void testAStarSolver() {
        GridProvider gridProvider = new RandomGridProvider(200, 200);

        AStarSolver.solve(gridProvider);
    }

    @Test(timeout = 1000)
    public void testAStarSolver2() {
        GridProvider gridProvider = new EmptyGridProvider(200, 200, false) {
            @Override
            public void generateGrid() {
                super.generateGrid();
                startField = mGrid.get(0).get(0);
                targetField = mGrid.get(199).get(199);
            }
        };

        AStarSolver.solve(gridProvider);
    }

}
