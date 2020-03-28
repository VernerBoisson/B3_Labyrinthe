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
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        JPanel buttonPanel = new JPanel();

        setViewportView(buttonPanel);

        JLabel jlab = new JLabel("mamacito");

        this.gridPanel = gridPanel;
        JButton jButtonExectue = new JButton("Execute");
        jButtonExectue.setPreferredSize(new Dimension(100 ,100));
        jButtonExectue.setBackground(Color.GREEN);
        buttonPanel.add(jButtonExectue);

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

            mazeButton.setPreferredSize(new Dimension(100 ,70));
            buttonPanel.add(mazeButton);
        }
        System.out.println(Maze.mazes.size());
        buttonPanel.setLayout(new GridLayout(Maze.mazes.size() + 1, 1));

        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 15));



    }




}
