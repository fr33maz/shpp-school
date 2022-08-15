package com.shpp.p2p.cs.ykapustin.assignment7;

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

    /**
     * This class is responsible for detecting when the buttons are
     * clicked. In case if button "Graph" pressed is calling findPerson()
     * class otherwise calling NameSurferGraph.clear() class to clear
     * the board.
     */
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "Graph")) {
            findPerson(jtf.getText().toLowerCase());
        } else {
            nameSurferGraph.clear();
        }
    }

    /**
     This method is responsible for finding the person
     and pass it to the nameSurferGraph class
     */
    private void findPerson(String name) {
        try {
            nameSurferGraph.addEntry(dataBase.findEntry(name));
        } catch (NullPointerException n) {
            System.out.println("Unable to find person with name: " + jtf.getText());
        }
    }

    /**
     This method does exactly the same as actionPerformed method with only
     difference that it's listening for keyboard and after "Enter" pressed
     calling findPerson() class.
     */
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            findPerson(jtf.getText().toLowerCase(Locale.ROOT));
        }
    }
}