package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GridPanel.*;

public class ToolPanel extends JPanel {
    public static Color GlobalColor = Color.WHITE;
    public static String GlobalType = "F";
    public GridPanel Maze;

    public ToolPanel(GridPanel maze){

        Maze = maze;
        final JFrame parent = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel titre = new JLabel("Titre du Labyrinthe");
        JTextField title = new JTextField();
        title.setColumns(10);
        JLabel auteur = new JLabel("Auteur du Labyrinthe");
        JTextField author = new JTextField();
        author.setColumns(10);
        JButton postmaze =  new JButton("Enregistrer");
        postmaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Maze.save(title.getText(), author.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        panel.add(titre);
        panel.add(title);
        panel.add(auteur);
        panel.add(author);
        panel.add(postmaze);

        parent.add(panel);
        parent.pack();

        JButton save = new JButton("Sauvegarder");


        save.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.setVisible(true);



            }
        });


        JButton start = new JButton("Start");
        JButton goal = new JButton("Goal");
        JButton wall = new JButton("Wall");
        JButton eraser = new JButton("Eraser");


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
        add(start);
        add(goal);
        add(eraser);
        add(wall);
        add(save);
        add(Box.createRigidArea(new Dimension(10, 10)));
    }

    public static void setGlobalColor(Color color){
        GlobalColor = color;
    }

    public static void setGlobalType(String type){
        GlobalType = type;
    }

}
