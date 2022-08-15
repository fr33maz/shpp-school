package com.shpp.p2p.cs.ykapustin.assignment7;

import java.awt.*;
import java.util.Locale;

public class NameSurferEntry implements NameSurferConstants {

    private final String name;
    private final int[] decade;
    private final Color color;
    private final StringBuilder toReturn;

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] person = line.split("\\s");
        this.name = person[0];
        decade = new int[person.length];
        toReturn = new StringBuilder(this.name);
        toReturn.append(" [");
        for(int i = 1; i < person.length; i++) {
            decade[i-1] = Integer.parseInt(person[i]);
            toReturn.append(decade[i]).append(" ");
        }
        toReturn.replace(toReturn.length() -1, toReturn.length(),"]");
        color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return new StringBuilder(name).substring(0,1).toUpperCase(Locale.ROOT) +
                new StringBuilder(name).replace(0,1,"");
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return this.decade[decade];
    }

    /**
     * Return unique color of particular person
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return String.valueOf(toReturn);
    }
}