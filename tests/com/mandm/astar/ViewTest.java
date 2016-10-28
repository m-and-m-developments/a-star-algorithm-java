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