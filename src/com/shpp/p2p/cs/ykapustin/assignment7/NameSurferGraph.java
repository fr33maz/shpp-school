package com.shpp.p2p.cs.ykapustin.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        makeTheSetUp();

    }

    public void makeTheSetUp() {
        int decade = START_DECADE;
        add(new GLine(0,15,APPLICATION_WIDTH,15));
        add(new GLine(0,APPLICATION_HEIGHT - HEADER_SIZE - 15,APPLICATION_WIDTH,APPLICATION_HEIGHT - HEADER_SIZE - 15));
        for(int i = 0; i < APPLICATION_WIDTH; i+= SPACE_BETWEEN_LINES) {
            add(new GLine(i,0,i,APPLICATION_HEIGHT));
            add(new GLabel(Integer.toString(decade),i+1,APPLICATION_HEIGHT - HEADER_SIZE));
            decade+=10;
        }
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {

    }
    public void whatever() {
        GOval gOval = new GOval(50,50,50,50);
        gOval.setFillColor(Color.red);
        gOval.setFilled(true);
        add(gOval);
    }

	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        System.out.println(entry.getName());
        for(int i = 0; i < 11;i++) {
            System.out.println(entry.getRank(i));
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {

    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
