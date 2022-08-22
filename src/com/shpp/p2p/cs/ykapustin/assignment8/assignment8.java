package com.shpp.p2p.cs.ykapustin.assignment8;

import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class assignment8 extends WindowProgram {
    private final ArrayList<GObject> rectangle = new ArrayList<>();
    private final boolean[] array = new boolean[10];
    private final int[] direction = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};


    public void run() {
        addMouseListeners();
        setSize(300, 250);
        createTenRectangles();

        while (true) {
            for (int i = 0; i < rectangle.size(); i++) {
                if (array[i]) {
                    rectangle.get(i).move(direction[i], 0);
                }
                if (!(rectangle.get(i).getX() < getWidth() - 12 && rectangle.get(i).getX() > 0)) {
                    direction[i] *= -1;
                }
            }
            pause(100);
        }
    }


    private void createTenRectangles() {
        GRect gRect;
        for (int i = 0; i < 10; i++) {
            gRect = new GRect(getWidth() / 2.0, i * 15, 10, 10);
            gRect.setFilled(true);
            rectangle.add(gRect);
        }
        for (GObject ract : rectangle) {
            add(ract);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int element = e.getY() / 15;
        arrayModifier(element);

    }

    private void arrayModifier(int element) {
        if (element == 0) {
            for (int i = element; i <= element + 1; i++) {
                array[i] = !array[i];
            }
        } else if (element == 9) {
            for (int i = element - 1; i <= element; i++) {
                array[i] = !array[i];
            }
        } else {
            for (int i = element - 1; i <= element + 1; i++) {
                array[i] = !array[i];
            }
        }
    }
}
