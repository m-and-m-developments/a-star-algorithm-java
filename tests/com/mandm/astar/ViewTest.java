package com.mandm.astar;

import com.mandm.astar.ui.widget.Button;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Created on 10/26/16.
 *
 * @author martin
 */
public class ViewTest {

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
            @Override
            public void render() {

            }
        };

        button.addClickListener(actionPerformer -> assertSame(button, actionPerformer));
    }

}