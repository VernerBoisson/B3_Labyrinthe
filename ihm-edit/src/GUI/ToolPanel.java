package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel{
    public static Color GlobalColor = Color.WHITE;
    public static char GlobalType = 'F';
    public CellPanel Maze;

    public ToolPanel(CellPanel maze){
        Maze = maze;

        JButton start = new JButton("Start");
        JButton goal = new JButton("Goal");
        JButton randomWall = new JButton("Random maze");
        JButton mudTrap = new JButton("Mud trap");
        JButton wall = new JButton("Wall");
        JButton eraser = new JButton("Eraser");
        JButton clear = new JButton("Clear all");
        JButton wallFill = new JButton("Wall everything");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.GREEN.darker());
                setGlobalType('S');
            }
        });
        goal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.RED.darker());
                setGlobalType('G');
            }
        });
        randomWall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze.randomWall();

            }

        });
        mudTrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(new Color(88, 41,0));
                setGlobalType('M');
            }
        });
        wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.BLACK);
                setGlobalType('W');
            }
        });
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGlobalColor(Color.WHITE);
                setGlobalType('F');
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Maze.clear();
            }
        });

        wallFill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Maze.wallFill();
            }
        });


        start.setBackground((Color.GREEN.darker()));
        start.setForeground(Color.WHITE);

        goal.setBackground(Color.RED.darker());
        goal.setForeground(Color.WHITE);

        randomWall.setBackground(new Color(133, 133, 129));
        randomWall.setForeground(Color.WHITE);

        mudTrap.setBackground(new Color(88, 41,0));
        mudTrap.setForeground(Color.WHITE);

        wall.setBackground(Color.BLACK);
        wall.setForeground(Color.WHITE);

        eraser.setBackground(Color.WHITE);
        eraser.setForeground(Color.BLACK);

        clear.setBackground(Color.WHITE);
        clear.setForeground(Color.BLACK);

        wallFill.setBackground(Color.DARK_GRAY);
        wallFill.setForeground(Color.BLACK);

        setLayout(new GridLayout(8, 1));
        add(start);
        add(goal);
        add(mudTrap);
        add(wall);
        add(wallFill);
        add(randomWall);
        add(eraser);
        add(clear);
        setPreferredSize(new Dimension(150 ,50));

    }

    public static void setGlobalColor(Color color){
        GlobalColor = color;
    }

    public static void setGlobalType(char type){
        GlobalType = type;
    }

}
