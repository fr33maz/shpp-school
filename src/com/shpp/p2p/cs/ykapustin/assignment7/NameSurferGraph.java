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
        add(new GLine(0, HEAD_N_BOTTOM_STEP, getWidth(), HEAD_N_BOTTOM_STEP));
        add(new GLine(0, getHeight() - HEAD_N_BOTTOM_STEP, getWidth(), getHeight() - HEAD_N_BOTTOM_STEP));
        int spaceBetweenLines = getWidth() / 12 + 1;
        for (int i = 0; i < getWidth(); i += spaceBetweenLines) {
            add(new GLine(i, 0, i, getHeight()));
            add(new GLabel(Integer.toString(decade), i + 1, getHeight()));
            decade += 10;
        }
        addAllPersons();
    }

    private void addAllPersons() {

        for (NameSurferEntry person : persons) {
            Color color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

            int[] statics = new int[12];
            for (int j = 0; j < statics.length; j++) {
                statics[j] = person.getRank(j);
            }
            int spaceBetweenLines = getWidth() / 12 + 1;
            int xCoordinate = 0;
            int yCoordinatePrevious = 0;
            for (int aStatic : statics) {
                double a = aStatic * OPTIMIZATION;
                double b = getHeight();
                int yCoordinate = (int) (a * b);
                if (yCoordinate < getHeight() / 2) {
                    yCoordinate += HEAD_N_BOTTOM_STEP;
                } else {
                    yCoordinate -= HEAD_N_BOTTOM_STEP;
                }
                addPersonsStatics(person.getName(), Integer.toString(aStatic),
                        xCoordinate, yCoordinate, yCoordinatePrevious, color);
                yCoordinatePrevious = yCoordinate;
                xCoordinate += spaceBetweenLines;

            }
        }

    }

    private void addPersonsStatics(String personsName, String personsStatics, int xCoordinate,
                                   int yCoordinate, int yCoordinatePrevious,Color color) {
        String size = "default-" + getWidth() / 85;
        int step = 1 + addNewPersonName(personsName, xCoordinate, yCoordinate, size,color);
        addNewPersonsStats(personsStatics, xCoordinate, yCoordinate, size, step,color);
        addStaticsLine(xCoordinate,yCoordinate,yCoordinatePrevious,color);
    }

    private void addStaticsLine(int xCoordinate, int yCoordinate, int yCoordinatePrevious, Color color) {
        yCoordinate = coordinateCorrection(yCoordinate);
        yCoordinatePrevious = coordinateCorrection(yCoordinatePrevious);
        GLine gLine = new GLine(xCoordinate - getWidth() / NUMBER_OF_DECADES -1,yCoordinatePrevious,xCoordinate, yCoordinate);
        gLine.setColor(color);
        add(gLine);
    }


    private void addNewPersonsStats(String personsStatics, int xCoordinate, int yCoordinate, String size,int step,Color color) {
        yCoordinate = coordinateCorrection(yCoordinate);
        if(personsStatics.equals("0")) {
            personsStatics = "*";
        }
        GLabel gLabel = new GLabel(personsStatics,xCoordinate + step,yCoordinate);
        gLabel.setColor(color);
        gLabel.setFont(size);
        add(gLabel);

    }

    private int coordinateCorrection(int yCoordinate) {
        if(yCoordinate == HEAD_N_BOTTOM_STEP) {
            yCoordinate = getHeight() - HEAD_N_BOTTOM_STEP;
        }
        return yCoordinate;
    }

    private int addNewPersonName(String personsName, int xCoordinate, int yCoordinate, String size, Color color) {
        yCoordinate = coordinateCorrection(yCoordinate);
        GLabel gLabel = new GLabel(personsName, xCoordinate, yCoordinate);
        gLabel.setFont(size);
        gLabel.setColor(color);
        add(gLabel);
        return (int) gLabel.getWidth();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        persons = new ArrayList<>();
        removeAll();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (!persons.contains(entry)) {
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
