package GUI;

import beans.Maze;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MazeChoicePanel extends JScrollPane {

    private GridPanel gridPanel;

    public MazeChoicePanel(GridPanel gridPanel) {
        this.gridPanel = gridPanel;

        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        JPanel buttonPanel = new JPanel();
        setViewportView(buttonPanel);

        for(Maze maze : Maze.mazes) {
            JButton mazeButton = new JButton(maze.getTitle());
            mazeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    gridPanel.setMaze(maze);
                }
            });

            mazeButton.setPreferredSize(new Dimension(100 ,70));
            buttonPanel.add(mazeButton);
        }
        buttonPanel.setLayout(new GridLayout(Maze.mazes.size() + 1, 1));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 15));

    }
}
