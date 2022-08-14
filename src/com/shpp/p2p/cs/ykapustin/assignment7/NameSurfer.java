package com.shpp.p2p.cs.ykapustin.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    JTextField jtf = new JTextField(TEXT_FIELD_SIZE);
    NameSurferGraph nameSurferGraph;
    NameSurferDataBase dataBase;
    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name:"), NORTH);
        add(jtf, NORTH);
        jtf.addKeyListener(this);
        add(new JButton("Graph"), NORTH);
        add(new JButton("Clear"), NORTH);
        addActionListeners();
        nameSurferGraph = new NameSurferGraph();
        try {
            dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(nameSurferGraph);
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "Graph")) {
            findPerson(jtf.getText().toLowerCase());
        } else {
            nameSurferGraph.clear();
        }
    }

    private void findPerson(String name) {
        try {
            nameSurferGraph.addEntry(dataBase.findEntry(name));
        } catch (NullPointerException n) {
            System.out.println("Unable to find person with name: " + jtf.getText());
        }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            findPerson(jtf.getText().toLowerCase(Locale.ROOT));
        }
    }
}
