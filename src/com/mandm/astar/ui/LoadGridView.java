package com.mandm.astar.ui;

import com.mandm.astar.grid.EmptyGridProvider;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.LoadGridProvider;
import com.mandm.astar.render.GridRenderer;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.ViewGroup;
import com.mandm.astar.util.Log;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created on 10/24/16.
 *
 * @author martin
 */
public class LoadGridView extends ViewGroup {

    private final Button apply;
    private final Button clear;
    private final Button load;
    private final Button save;
    private final Button exit;

    public LoadGridView(int width, int height, GuiScreen parent) {
        super(0, 0, width, height);

        GridProvider gridProvider = new EmptyGridProvider(60, 120, true);

        GridRenderer renderer = new GridRenderer(gridProvider, width, 600);

        apply = new Button(0, 600, "Apply");
        clear = new Button(apply.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Clear grid");
        load = new Button(clear.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Load grid");
        save = new Button(load.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Save");
        exit = new Button(save.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Exit");

        apply.addClickListener(actionPerformer -> {
            if (gridProvider.validateField()) {
                parent.closeChild(gridProvider);
            } else {
                Log.i("Please select a start and target field!");
            }
        });
        clear.addClickListener(actionPerformer -> gridProvider.generateGrid());
        load.addClickListener(actionPerformer -> {

            try {
                GridProvider provider = new LoadGridProvider();

                gridProvider.copyFromProvider(provider);
            } catch (IOException ignored) {
            }
        });
        save.addClickListener(actionPerformer -> {

            JFileChooser fileChooser = new JFileChooser();

            int retVal = fileChooser.showSaveDialog(null);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())){
                    writer.write(gridProvider.toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        exit.addClickListener(actionPerformer -> parent.closeChild(null));

        // Needs to be first because it has the highest priority so the buttons do not clear the
        // mouse buffer
        addView(renderer);
        addView(apply);
        addView(clear);
        addView(load);
        addView(save);
        addView(exit);
    }
}
