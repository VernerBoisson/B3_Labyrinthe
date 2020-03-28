package GUI;

import javax.swing.*;
import java.awt.*;

public class MazeGUI {
    public MazeGUI(){
        JFrame jframe = new JFrame();
        jframe.setSize(1024, 800);
        jframe.setTitle("Maze Resolve");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridPanel gridPanel = new GridPanel();
        ToolBar toolBar = new ToolBar(gridPanel);

        MazeChoicePanel mazeChoicePanel = new MazeChoicePanel(gridPanel);
        jframe.add(toolBar, BorderLayout.NORTH);
        jframe.add(gridPanel, BorderLayout.CENTER);
        jframe.add(mazeChoicePanel, BorderLayout.EAST);
        jframe.setSize(1280, 1080);
        jframe.setVisible(true);
    }
}
