package com.shpp.p2p.cs.ykapustin.assignment7;

/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

public interface NameSurferConstants {
    /**
     * The width of the text field
     */
    public static final int TEXT_FIELD_SIZE = 30;
    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 800;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * The name of the file containing the data
     */
    public static final String NAMES_DATA_FILE = "names-data.txt";

    /**
     * The first decade in the database
     */
    public static final int START_DECADE = 1900;
    /**
     * Created to optimize the rank to screen since
     * screen might be big either small
     */
    public static final double OPTIMIZATION = .001;
    /**
     * The size of line on the header and bottom
     */
    public static final int HEAD_N_BOTTOM_STEP = 15;

    /**
     * The number of decades
     */
    public static final int NDECADES = 12;

    /**
     * The maximum rank in the database
     */
    public static final int MAX_RANK = 1000;

    /**
     * The number of pixels to reserve at the top and bottom
     */
    public static final int GRAPH_MARGIN_SIZE = 20;

}
