package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GridPanel.*;

public class ToolPanel extends JPanel{
    public static Color GlobalColor = Color.WHITE;
    public static String GlobalType = "F";
    public GridPanel Maze;

    public ToolPanel(GridPanel maze){
        Maze = maze;



        JButton start = new JButton("Start");
        JButton goal = new JButton("Goal");
        JButton wall = new JButton("Wall");
        JButton eraser = new JButton("Eraser");
        JButton clear = new JButton("Clear all");
        JButton wallFill = new JButton("Wall everything");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.GREEN);
                setGlobalType("S");
            }
        });
        goal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.RED);
                setGlobalType("G");
            }
        });
        wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.BLACK);
                setGlobalType("W");
            }
        });
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.WHITE);
                setGlobalType("F");
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Maze.clear();
            }
        });

        wallFill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Maze.wallFill();
            }
        });

        start.setPreferredSize(new Dimension( 100,100));
        start.setBackground((Color.GREEN.darker()));
        start.setForeground(Color.WHITE);

        goal.setPreferredSize(new Dimension( 100,100));
        goal.setBackground(Color.RED);
        goal.setForeground(Color.WHITE);

        wall.setPreferredSize(new Dimension( 100,100));
        wall.setBackground(Color.BLACK);
        wall.setForeground(Color.WHITE);


        eraser.setPreferredSize(new Dimension( 100,100));
        eraser.setBackground(Color.WHITE);
        eraser.setForeground(Color.BLACK);

        clear.setPreferredSize(new Dimension( 100,100));
        clear.setBackground(Color.WHITE);
        clear.setForeground(Color.BLACK);


        wallFill.setPreferredSize(new Dimension( 100,100));
        wallFill.setBackground(Color.WHITE);
        wallFill.setForeground(Color.DARK_GRAY);

        setLayout(new GridLayout(1, 6));
        add(start);
        add(goal);
        add(wall);
        add(eraser);
        add(clear);
        add(wallFill);
    }

    public static void setGlobalColor(Color color){
        GlobalColor = color;
    }

    public static void setGlobalType(String type){
        GlobalType = type;
    }

}
