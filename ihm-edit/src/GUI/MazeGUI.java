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
    private JFrame jframe;
    private GridPanel gridPanel;
    private ToolPanel toolPanel;
    public MazeGUI(){
        jframe = new JFrame();
        Toolkit tk = Toolkit.getDefaultToolkit();
        jframe.setSize(new Dimension(1280, 1080));
        jframe.setTitle("Maze Edit");
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridPanel = new GridPanel(20);
        jframe.add(gridPanel, BorderLayout.CENTER);


        toolPanel = new ToolPanel(gridPanel);
        toolPanel.setMaximumSize(new Dimension(50 ,50));
        toolPanel.setMinimumSize(new Dimension(50 ,50));
        jframe.add(toolPanel, BorderLayout.EAST);


        JFrame newPopup = new JFrame();
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3,1));
        JLabel labMazeSize = new JLabel("Maze size");
        JButton createMaze =  new JButton("Create");
        JSpinner mazeSize = new JSpinner(new SpinnerNumberModel(10, 2,100,1));
        //gridPanel = new GridPanel();

        createMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jframe.remove(gridPanel);
                jframe.remove(toolPanel);
                gridPanel = new GridPanel((Integer)mazeSize.getValue());
                toolPanel = new ToolPanel(gridPanel);
                jframe.add(gridPanel, BorderLayout.CENTER);
                jframe.add(toolPanel, BorderLayout.EAST);
                jframe.setVisible(true);
            }
        });

        labMazeSize.setPreferredSize(new Dimension( 100,30));
        mazeSize.setPreferredSize(new Dimension( 300,30));


        newPanel.add(labMazeSize);
        newPanel.add(mazeSize);
        newPanel.add(createMaze);
        newPopup.add(newPanel);
        newPopup.pack();
        //Pop up save maze
        JFrame savePopup = new JFrame();
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
        JMenuItem newMaze = new JMenuItem("New");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem edit = new JMenuItem("Edit a maze");

        newMaze.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPopup.setVisible(true);

            }
        });

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
        menu.add(newMaze);
        menu.addSeparator();
        menu.add(save);
        menu.addSeparator();
        menu.add(edit);
        menuBar.setPreferredSize(new Dimension(200, 50));
        menuBar.add(menu);
        jframe.setJMenuBar(menuBar);





        jframe.setVisible(true);

    }
    public void newMaze(int size){

    }
}
