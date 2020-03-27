package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GridPanel extends JPanel {
    private int size = 50;
    private Maze maze;

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public GridPanel(){
        setVisible(true);
        setSize(600,600);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int length;
        Graphics2D g2 = (Graphics2D) g.create();
        if(maze != null){
            length = maze.getSchemaMaze().size();
        }else{
            length = 0;
        }
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                char[] str = maze.getSchemaMaze().get(i).toCharArray();
                System.out.println(str[j]);
                switch (str[j]) {
                    case 'W': g2.setColor(Color.BLACK);
                        break;
                    case 'F': g2.setColor(Color.WHITE);
                        break;
                    case 'S': g2.setColor(Color.GREEN);
                        break;
                    case 'G': g2.setColor(Color.RED);
                        break;
                    default: g2.setColor(Color.WHITE);
                        break;
                }


                g2.fill(new Rectangle2D.Double(i+i*size,j+j*size, size, size));

            }
        }
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

}

