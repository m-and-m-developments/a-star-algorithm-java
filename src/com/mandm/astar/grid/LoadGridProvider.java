/*
 * Copyright 2016 Martin Fink & Moriz Martiner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mandm.astar.grid;

import com.mandm.astar.ui.widget.Field;
import com.mandm.astar.util.Log;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 10/25/16.
 *
 * @author martin
 */
public class LoadGridProvider extends GridProvider {

    protected File mFile;

    public LoadGridProvider() throws IOException {
        JFileChooser fileChooser = new JFileChooser();

        int retVal = fileChooser.showOpenDialog(null);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            mFile = fileChooser.getSelectedFile();
        } else {
            throw new IOException("User did not choose a file!");
        }

        generateGrid();
    }

    @Override
    public void generateGrid() {
        mGrid = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(mFile);

            int line = 0;

            while (scanner.hasNext()) {
                final int[] row = {0};
                String[] elements = scanner.nextLine().split(";");

                List<Field> fields = new ArrayList<>();

                int finalLine = line;
                Arrays.stream(elements).forEach(s -> {
                    fields.add(new Field(Field.Status.parseStatus(Integer.parseInt(s)), finalLine, row[0]));
                    row[0]++;
                });

                mGrid.add(fields);
                line++;
            }

        } catch (Exception e) {
            Log.e(e.getMessage());
            Log.e("Error parsing the field!");
            System.exit(1);
        }

    }
}
