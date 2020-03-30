package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {
    private MazeGUI gui;
    public ToolBar (MazeGUI gui) {
        this.gui = gui;
        ImageIcon playIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/play1.png")));
        Image playImg = playIcon.getImage();
        Image newPlayImg = playImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(newPlayImg);
        JButton play = new JButton(playIcon);

        ImageIcon refreshIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/refresh.png")));
        Image refreshImg = refreshIcon.getImage(); // transform it
        Image newRefreshImg = refreshImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(newRefreshImg);  // transform it back
        JButton refresh = new JButton(refreshIcon);

        ImageIcon stopIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/stop1.png")));
        Image stopImg = stopIcon.getImage(); // transform it
        Image newStopImg = stopImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        stopIcon = new ImageIcon(newStopImg);  // transform it back
        JButton stop = new JButton(stopIcon);

        ImageIcon cleanIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/clean.png"))); // load the image to a imageIcon
        Image cleanImg = cleanIcon.getImage(); // transform it
        Image newCleanImg = cleanImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        cleanIcon = new ImageIcon(newCleanImg);  // transform it back
        JButton clean = new JButton(cleanIcon);


        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    Thread gameThread = new Thread(gui.getGridPanel());
                    gui.getGridPanel().setRunning(true);
                    gameThread.start();
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.getMazeChoicePanel().setVisible(false);
                gui.getMazeChoicePanel().removeAll();
                gui.getJframe().remove(gui.getMazeChoicePanel());
                gui.setMazeChoicePanel(new MazeChoicePanel(gui.getGridPanel()));
                gui.getJframe().add(gui.getMazeChoicePanel(), BorderLayout.EAST);
                gui.getJframe().setVisible(true);
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.getGridPanel().setStop(true);
                gui.getGridPanel().setRunning(false);
            }
        });

        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String response = gui.getMazeChoicePanel().MyGetRequest();
                gui.getMazeChoicePanel().ParseResponse(response);

                for(Maze maze : Maze.mazes) {
                    if (maze.getId() == gui.getGridPanel().getMaze().getId()){
                        gui.getGridPanel().setMaze(maze);
                        gui.getGridPanel().setBoard(maze.getSchemaMaze());
                        gui.getGridPanel().setRunning(false);
                        gui.getGridPanel().setStop(true);
                    }
                }
            }
        });

        add(play);
        add(stop);
        add(clean);
        add(refresh);
    }
}
