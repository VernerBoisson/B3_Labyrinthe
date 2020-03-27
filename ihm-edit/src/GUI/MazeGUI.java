package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class MazeGUI {
    public MazeGUI(){
        JFrame jframe = new JFrame();
        jframe.setSize(600, 800);
        jframe.setTitle("Maze Edit");
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setLayout(new BorderLayout(5, 5));

        GridPanel gridPanel = new GridPanel();
        jframe.add(gridPanel, BorderLayout.NORTH);

        ToolPanel toolPanel = new ToolPanel(gridPanel);
        jframe.add(toolPanel, BorderLayout.SOUTH);


        //Pop up save maze
        final JFrame savePopup = new JFrame();
        JPanel savepanel = new JPanel();
        savepanel.setLayout(new GridLayout(6,1));
        JLabel labTitle = new JLabel("Maze title");
        JTextField title = new JTextField();
        JLabel labAuthor = new JLabel("Maze creator");
        JTextField author = new JTextField();
        JButton postmaze =  new JButton("Save");
        JLabel labError = new JLabel("Your maze must have a start and a goal point, please add them to save it !");


        postmaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (gridPanel.isSolvable()) {
                        gridPanel.save(title.getText(), author.getText());
                        savePopup.setVisible(false);
                    } else {
                        savepanel.add(labError);
                        savePopup.add(savepanel);
                        savePopup.pack();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        labTitle.setPreferredSize(new Dimension( 100,30));
        title.setPreferredSize(new Dimension( 300,30));
        labAuthor.setPreferredSize(new Dimension( 100,30));
        author.setPreferredSize(new Dimension( 300,30));

        savepanel.add(labTitle);
        savepanel.add(title);
        savepanel.add(labAuthor);
        savepanel.add(author);
        savepanel.add(postmaze);
        savepanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        savePopup.add(savepanel);
        savePopup.pack();

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem edit = new JMenuItem("Edit a Maze");



        save.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePopup.setVisible(true);

            }
        });

        edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePopup.setVisible(true);

            }
        });

        menu.add(save);
        menu.addSeparator();
        menu.add(edit);
        menuBar.setPreferredSize(new Dimension(200, 50));
        menuBar.add(menu);
        jframe.setJMenuBar(menuBar);





        jframe.setVisible(true);

    }
}
