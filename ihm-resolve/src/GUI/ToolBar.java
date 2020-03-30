package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {
    private GridPanel gridPanel;
    public ToolBar (GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        ImageIcon playIcon = new ImageIcon("icons/play1.png"); // load the image to a imageIcon
        Image playImg = playIcon.getImage(); // transform it
        Image newPlayImg = playImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newPlayImg);  // transform it back
        JButton play = new JButton(playIcon);

        ImageIcon pauseIcon = new ImageIcon("icons/pause.png"); // load the image to a imageIcon
        Image pauseImg = pauseIcon.getImage(); // transform it
        Image newPauseImg = pauseImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        pauseIcon = new ImageIcon(newPauseImg);  // transform it back
        JButton pause = new JButton(pauseIcon);

        ImageIcon stopIcon = new ImageIcon("icons/stop1.png"); // load the image to a imageIcon
        Image stopImg = stopIcon.getImage(); // transform it
        Image newStopImg = stopImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        stopIcon = new ImageIcon(newStopImg);  // transform it back
        JButton stop = new JButton(stopIcon);
        Thread gameThread = new Thread(gridPanel);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    gridPanel.setBoard(gridPanel.getMaze().getSchemaMaze());
                synchronized (gameThread){
                    gameThread.start();
                }
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    synchronized (gameThread){
                        gameThread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }

        });

        add(play);
        //add(pause);
        //add(stop);

    }
}
