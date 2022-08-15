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
import java.util.Objects;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private int resizingWaitTime = 25;
    private ArrayList<NameSurferEntry> persons = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {

        update();

    }

    /**
     * Making set up with lines of decade and calls addAllPersons() method
     */
    private void makeTheSetUp() {
        addComponentListener(this);
        int decade = START_DECADE;
        addBottomAndUpperLineOnTheScreen();
        int spaceBetweenLines = getWidth() / 12 + 1;
        for (int i = 0; i < getWidth(); i += spaceBetweenLines) {
            add(new GLine(i, 0, i, getHeight()));
            add(new GLabel(Integer.toString(decade), i + 1, getHeight()));
            decade += 10;
        }
        addAllPersons();
    }

    /**
     * adds lines on the bottom and the top of the program
     */
    private void addBottomAndUpperLineOnTheScreen() {
        add(new GLine(0, HEAD_N_BOTTOM_STEP, getWidth(), HEAD_N_BOTTOM_STEP));
        add(new GLine(0, getHeight() - HEAD_N_BOTTOM_STEP, getWidth(), getHeight() - HEAD_N_BOTTOM_STEP));
    }

    /**
     * The method retrieves the data from arraylist of NameSurferEntry one by one and
     * display statics of each person
     */
    private void addAllPersons() {

        for (NameSurferEntry person : persons) {

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
                yCoordinate = yCoordinateCorrection(yCoordinate);
                addPersonsStatics(person.getName(), Integer.toString(aStatic),
                        xCoordinate, yCoordinate, yCoordinatePrevious, person.getColor());
                yCoordinatePrevious = yCoordinate;
                xCoordinate += spaceBetweenLines;
            }
        }
    }

    /**
     * Method has to make correction because of lines on the bottom and the top
     * in case if coordinate by y is less than a half of the application height
     * then add the same amount of pixels that was those lines have, otherwise
     * subtract that amount.
     */
    private int yCoordinateCorrection(int yCoordinate) {
        if (yCoordinate < getHeight() / 2) {
            yCoordinate += HEAD_N_BOTTOM_STEP;
        } else {
            yCoordinate -= HEAD_N_BOTTOM_STEP;
        }
        return yCoordinate;
    }

    /**
     * The Method receives all necessary information of the person such as persons name, his rank,
     * coordinates where to place the line and the color of his line. then show his statics
     * on the screen
     */
    private void addPersonsStatics(String personsName, String personsStatics, int xCoordinate,
                                   int yCoordinate, int yCoordinatePrevious, Color color) {
        String size = "default-" + getWidth() / 85;
        int step = 1 + addNewPersonName(personsName, xCoordinate, yCoordinate, size, color);
        addNewPersonsStats(personsStatics, xCoordinate, yCoordinate, size, step, color);
        addStaticsLine(xCoordinate, yCoordinate, yCoordinatePrevious, color);
    }

    /**
     * The method places the line on the canvas on coordinates that was given from his rank
     */
    private void addStaticsLine(int xCoordinate, int yCoordinate, int yCoordinatePrevious, Color color) {
        yCoordinate = coordinateCorrection(yCoordinate);
        yCoordinatePrevious = coordinateCorrection(yCoordinatePrevious);
        GLine gLine = new GLine(xCoordinate - getWidth() / NDECADES - 1, yCoordinatePrevious, xCoordinate, yCoordinate);
        gLine.setColor(color);
        add(gLine);
    }

    /**
     * The method creates and places the label with persons rank
     */
    private void addNewPersonsStats(String personsStatics, int xCoordinate, int yCoordinate, String size, int step, Color color) {
        yCoordinate = coordinateCorrection(yCoordinate);
        if (personsStatics.equals("0")) {
            personsStatics = "*";
        }
        GLabel gLabel = new GLabel(personsStatics, xCoordinate + step, yCoordinate);
        gLabel.setColor(color);
        gLabel.setFont(size);
        add(gLabel);

    }

    /**
     * The method returns correct coordinates by y in case if persons rank is equal to zero
     */
    private int coordinateCorrection(int yCoordinate) {
        if (yCoordinate == HEAD_N_BOTTOM_STEP) {
            yCoordinate = getHeight() - HEAD_N_BOTTOM_STEP;
        }
        return yCoordinate;
    }

    /**
     * The method adds label with persons name on the screen and returns its coordinates to make
     * right position for his rank
     */
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
        update();
    }


    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     * double check if person with following name already on the screen
     * if so then doesn't
     */
    public void addEntry(NameSurferEntry entry) {
        for (NameSurferEntry person : persons) {
            if (Objects.equals(entry.getName(), person.getName())) {
                return;
            }
        }
        persons.add(entry);
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
        removeAll();
        makeTheSetUp();
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    /**
     * Method implements functionality when program was resized.
     * resizingWaitTime variable gives a time for the program
     * to clear stack to avoid StackOverFlowError
     */
    public void componentResized(ComponentEvent e) {
        if (resizingWaitTime == 25) {
            update();
            resizingWaitTime = 0;
        }
        resizingWaitTime++;
    }

    public void componentShown(ComponentEvent e) {
    }
}
