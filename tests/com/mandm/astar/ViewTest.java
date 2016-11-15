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

package com.mandm.astar;

import com.mandm.astar.render.Window;
import com.mandm.astar.ui.widget.Button;
import com.mandm.astar.ui.widget.TextView;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Font;

import static org.junit.Assert.*;

/**
 * Created on 10/26/16.
 *
 * @author martin
 */
public class ViewTest {

    @BeforeClass
    public static void setUp() {
        Window.create(200, 200);
    }

    @AfterClass
    public static void tearDown() {
        Display.destroy();
    }

    @Test
    public void testClickListener() {
        Button button = new Button(0, 0, 0, 0, "ABC", null) {
            @Override
            public void update() {
                try {
                    Thread.sleep(1000);
                    onClick();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        button.addClickListener(actionPerformer -> assertSame(button, actionPerformer));
    }

    @Test
    public void testTextView() {
        TextView textView = new TextView(0, 0, "Text");

        Font font = textView.getFont();

        assertEquals(font.getWidth(textView.getText()) + 2 * TextView.margin, textView.getWidth());
    }

    @Test
    public void testTextView2() {
        TextView textView = new TextView(0, 0, "Text");

        Font font = textView.getFont();

        assertEquals(font.getHeight(textView.getText()) + 2 * TextView.margin, textView.getHeight());
    }

    @Test
    public void testWindowIsCreated() {
        assertTrue(Display.isCreated());
    }

    @Test
    public void testWindowIsActive() {
        assertTrue(Display.isActive());
    }
}