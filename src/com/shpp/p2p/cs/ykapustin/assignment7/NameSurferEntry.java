package com.shpp.p2p.cs.ykapustin.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {
    private String name;
    private String[] decade;
    private StringBuilder toReturn;
	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] person = line.split("\\s+");
        this.name = person[0];
        decade = new String[person.length - 1];
        toReturn = new StringBuilder(name);
        toReturn.append(" [");
        for(int i = 1; i < person.length; i++) {
            decade[i-1] = person[i];
            toReturn.append(decade[i]).append(" ");
        }
        toReturn.replace(toReturn.length() -1, toReturn.length(),"]");
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return Integer.parseInt(this.decade[decade]);
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return String.valueOf(toReturn);
    }
}

