package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeGUI {
    public MazeGUI(){
        JFrame jframe = new JFrame();
        jframe.setSize(1024, 800);
        jframe.setTitle("Maze Resolve");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MazeChoicePanel mazeChoicePanel = new MazeChoicePanel();
        jframe.add(mazeChoicePanel);
        jframe.setVisible(true);
    }
}
