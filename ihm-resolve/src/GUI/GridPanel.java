package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GridPanel extends JPanel {
    private int size = 50;
    private Maze maze;

    public GridPanel(Maze maze){
        this.maze = maze;
        setVisible(true);
        setSize(600,600);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        int length = maze.getSchemaMaze().size();
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                g2.setColor(Color.BLUE);
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

