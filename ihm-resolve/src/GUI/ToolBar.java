package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {
    private MazeGUI gui;
    public ToolBar (MazeGUI gui) {
        this.gui = gui;
        ImageIcon playIcon = new ImageIcon("icons/play1.png"); // load the image to a imageIcon
        Image playImg = playIcon.getImage(); // transform it
        Image newPlayImg = playImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newPlayImg);  // transform it back
        JButton play = new JButton(playIcon);

        ImageIcon refreshIcon = new ImageIcon("icons/refresh.png"); // load the image to a imageIcon
        Image refreshImg = refreshIcon.getImage(); // transform it
        Image newRefreshImg = refreshImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        refreshIcon = new ImageIcon(newRefreshImg);  // transform it back
        JButton refresh = new JButton(refreshIcon);

        ImageIcon stopIcon = new ImageIcon("icons/stop1.png"); // load the image to a imageIcon
        Image stopImg = stopIcon.getImage(); // transform it
        Image newStopImg = stopImg.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        stopIcon = new ImageIcon(newStopImg);  // transform it back
        JButton stop = new JButton(stopIcon);
        Thread gameThread = new Thread(gui.getGridPanel());

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    gui.getGridPanel().setBoard(gui.getGridPanel().getMaze().getSchemaMaze());
                    Thread gameThread = new Thread(gui.getGridPanel());

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

                repaint();
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }

        });

        add(play);
        add(refresh);

    }
}
