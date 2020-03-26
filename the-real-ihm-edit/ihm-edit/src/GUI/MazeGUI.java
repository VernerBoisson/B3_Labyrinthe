package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class MazeGUI {
    public MazeGUI(){
        JFrame jframe = new JFrame();
        jframe.setSize(1024, 800);
        jframe.setTitle("Maze Edit");
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setLayout(new BorderLayout(0, 5));


        GridPanel gridPanel = new GridPanel();
        jframe.add(gridPanel, BorderLayout.NORTH);

        ToolPanel toolPanel = new ToolPanel(gridPanel);
        jframe.add(toolPanel, BorderLayout.SOUTH);

        jframe.setVisible(true);

    }
}
