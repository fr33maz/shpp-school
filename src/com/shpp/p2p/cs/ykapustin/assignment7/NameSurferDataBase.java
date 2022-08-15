package com.shpp.p2p.cs.ykapustin.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    HashMap<String, String> peoplesData = new HashMap<>();
    HashMap<String, Color> color = new HashMap<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(filename));
        String lineFromTheFile = bf.readLine().toLowerCase();
        StringBuilder name = new StringBuilder();

        while (true) {
            for(int i = 0; i < lineFromTheFile.length(); i++) {
                name.append(lineFromTheFile.charAt(i));
                if(lineFromTheFile.charAt(i+1) == ' ') {
                    break;
                }
            }

            peoplesData.put(name.toString(), lineFromTheFile);
            name = new StringBuilder();
            try {
                lineFromTheFile = bf.readLine().toLowerCase();
            } catch (NullPointerException e) {
                break;

            }
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        String personsData = peoplesData.get(name);
        return new NameSurferEntry(personsData);
    }

}