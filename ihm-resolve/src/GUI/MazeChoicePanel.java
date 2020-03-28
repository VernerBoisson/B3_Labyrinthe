package GUI;

import beans.Maze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MazeChoicePanel extends JPanel {

    private GridPanel gridPanel;

    public MazeChoicePanel() {
        super();
        setLayout(new FlowLayout());
        this.gridPanel = new GridPanel();
        add(this.gridPanel, FlowLayout.LEFT);
        JScrollBar jScrollBar = new JScrollBar();
        add(jScrollBar);
        JButton jButtonExectue = new JButton("Execute");
        add(jButtonExectue, FlowLayout.RIGHT);

        jButtonExectue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gridPanel.setBoard(gridPanel.getMaze().getSchemaMaze());
                Thread gameThread = new Thread(gridPanel);
                gameThread.start();
            }
        });

        for(Maze maze : Maze.mazes) {
            JButton mazeButton = new JButton(maze.getTitle());
            mazeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println(maze.getTitle() + maze.toString());
                    gridPanel.setMaze(maze);
                    gridPanel.repaint();
                }
            });
            add(mazeButton, FlowLayout.CENTER);
        }
    }




}
