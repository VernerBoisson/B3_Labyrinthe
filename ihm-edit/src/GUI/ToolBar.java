package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {
    private CellPanel cellPanel;
    public ToolBar(CellPanel cellPanel) {
        this.cellPanel = cellPanel   ;
        ImageIcon playIcon = new ImageIcon("icons/save.png"); // load the image to a imageIcon
        Image playImg = playIcon.getImage(); // transform it
        Image newPlayImg = playImg.getScaledInstance(60, 60,  Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newPlayImg);  // transform it back
        JButton save = new JButton(playIcon);


        add(save);

    }
}
