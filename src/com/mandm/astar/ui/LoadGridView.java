package com.mandm.astar.ui;

import com.mandm.astar.grid.EmptyGridProvider;
import com.mandm.astar.grid.GridProvider;
import com.mandm.astar.grid.LoadGridProvider;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.GridRenderer;
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
    private final Button load;
    private final Button save;
    private final Button exit;
    private final Button setSize;

    public LoadGridView(int width, int height, GuiScreen parent) {
        super(0, 0, width, height);

        GridProvider gridProvider = new EmptyGridProvider(60, 120, true);

        GridRenderer renderer = new GridRenderer(gridProvider, width, 600);

        apply = new Button(0, 600, "Apply");
        load = new Button(apply.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Load grid");
        save = new Button(load.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Save");
        setSize = new Button(save.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Set Size");
        exit = new Button(setSize.getPosX() + Button.BUTTON_DEFAULT_WIDTH, 600, "Exit");

        apply.addClickListener(actionPerformer -> {
            if (gridProvider.validateField()) {
                parent.closeChild(gridProvider);
            } else {
                Log.i("Please select a start and target field!");
            }
        });
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
        setSize.addClickListener(actionPerformer -> {
            String[] options = {"1", "2", "10", "20", "30","60"};

            String userInput = (String) JOptionPane.showInputDialog(null,
                    "Pixel size...",
                    "Choose pixel size",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            int input = Integer.parseInt(userInput);

            GridProvider gridProvider1 = new EmptyGridProvider(renderer.getHeight() / input, renderer.getWidth() / input, true);

            gridProvider.copyFromProvider(gridProvider1);
        });


        // Needs to be first because it has the highest priority so the buttons do not clear the
        // mouse buffer
        addView(renderer);
        addView(apply);
        addView(load);
        addView(save);
        addView(exit);
        addView(setSize);
    }
}
