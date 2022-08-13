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
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    private ArrayList<NameSurferEntry> persons = new ArrayList<>();
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        update();
    }

    private void makeTheSetUp() {
        addComponentListener(this);
        int decade = START_DECADE;
        add(new GLine(0,15,getWidth(),15));
        add(new GLine(0,APPLICATION_HEIGHT - HEADER_SIZE - 15,getWidth(),APPLICATION_HEIGHT - HEADER_SIZE - 15));
        int spaceBetweenLines = getWidth() / 12 + 1;
        for(int i = 0; i < getWidth(); i+= spaceBetweenLines) {
            add(new GLine(i,0,i,getHeight()));
            add(new GLabel(Integer.toString(decade),i+1,getHeight()));
            decade+=10;
        }
        addAllPersons();
    }

    private void addAllPersons() {
        for(int i = 0; i < persons.size(); i++) {
            NameSurferEntry person = persons.get(i);
            double highest = 0;
            int[] statics = new int[12];
            for(int j = 0; j < NUMBER_OF_DECADES; j++) {
                statics[j] = person.getRank(j);
                if(highest < person.getRank(j)) {
                    highest = person.getRank(j);
                }
            }
            int spaceBetweenLines = getWidth() / 12 + 1;
            highest *=.01;
            for(int j = 0; j < getWidth(); j+= spaceBetweenLines) {
                double location = (statics[j] / highest) * .01;
                add(new GOval(j,getHeight() * highest, 50,50));
            }
        }
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        persons = new ArrayList<>();
        removeAll();
        update();
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
        if(!persons.contains(entry)) {
            persons.add(entry);
        }
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        makeTheSetUp();

    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
       removeAll();
       update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
