package GUI;

import beans.Maze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MazeChoicePanel extends JPanel {


    public MazeChoicePanel() {
        super();
        JScrollBar jScrollBar = new JScrollBar();
        add(jScrollBar);
        for(Maze maze : Maze.mazes) {
            JButton mazeButton = new JButton(maze.getTitle());
            mazeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println(maze.getTitle() + maze.toString());
                    GridPanel gridPanel = new GridPanel(maze);
                    gridPanel.repaint();
                    add(gridPanel);
                }
            });
            add(mazeButton);

        }
    }



}
