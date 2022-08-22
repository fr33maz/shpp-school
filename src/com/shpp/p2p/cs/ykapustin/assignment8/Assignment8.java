package com.shpp.p2p.cs.ykapustin.assignment8;

import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Assignment8 extends WindowProgram implements Constants {


    private final ArrayList<GObject> rectangle = new ArrayList<>();
    private final boolean[] element = new boolean[N_RECTANGLES];
    private final int[] direction = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};


    public void run() {
        addMouseListeners();
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        createTenRectangles();

        while (true) {
            for (int i = 0; i < rectangle.size(); i++) {
                if (element[i]) {
                    rectangle.get(i).move(direction[i], 0);
                }
                if (!(rectangle.get(i).getX() < getWidth() - WIERD_CORRECTION && rectangle.get(i).getX() > 0)) {
                    direction[i] *= -1;
                }
            }
            pause(100);
        }
    }


    private void createTenRectangles() {
        GRect gRect;
        for (int i = 0; i < N_RECTANGLES; i++) {
            gRect = new GRect(getWidth() / 2.0, i * (BOX_SIZE + SPACE_BETWEEN_BOXES), BOX_SIZE, BOX_SIZE);
            gRect.setFilled(true);
            rectangle.add(gRect);
        }
        for (GObject rect : rectangle) {
            add(rect);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int element = e.getY() / (BOX_SIZE + SPACE_BETWEEN_BOXES);
        arrayModifier(element);

    }

    private void arrayModifier(int element) {
        if (element == 0) {
            for (int i = element; i <= element + 1; i++) {
                this.element[i] = !this.element[i];
            }
        } else if (element == 9) {
            for (int i = element - 1; i <= element; i++) {
                this.element[i] = !this.element[i];
            }
        } else {
            for (int i = element - 1; i <= element + 1; i++) {
                this.element[i] = !this.element[i];
            }
        }
    }
}
