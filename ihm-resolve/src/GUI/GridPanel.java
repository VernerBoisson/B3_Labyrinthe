package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GridPanel extends JPanel {
    private int size = 50;
    private Maze maze;
    private char[][] board;
    private int length=0;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
        System.out.println(this.board[0][4]);
        repaint();
    }

    public void setBoard(ArrayList<String> board) {
        int l = board.get(1).length();
        this.board = new char[l][];
        int i=0;
        for(String str : board){
            this.board[i] = str.toCharArray();
            i++;
        }
    }


    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        setBoard(maze.getSchemaMaze());
        this.length = board[0].length;
    }

    public GridPanel(){
        setVisible(true);
        setSize(600,600);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.length; j++){
                switch (board[i][j]) {
                    case 'V': g2.setColor(Color.YELLOW);
                        break;
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

