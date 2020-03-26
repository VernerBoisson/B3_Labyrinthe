package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {
    public static Color GlobalColor = Color.WHITE;
    public static String GlobalType = "F";

    public ToolPanel(){
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
        add(Box.createRigidArea(new Dimension(10, 10)));
    }

    public static void setGlobalColor(Color color){
        GlobalColor = color;
    }

    public static void setGlobalType(String type){
        GlobalType = type;
    }

}
