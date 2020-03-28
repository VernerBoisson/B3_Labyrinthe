package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

public class GridPanel extends JPanel implements Runnable{
    private char[][] board, saveboard;
    int tmpX,tmpY;
    private boolean over = false, recursion = true;
    private int size;
    private Maze maze;
    private int length=0;
    private int timer, movement, saveTimer=0, saveMovement=0;

    public void setBoard(ArrayList<String> board) {
        int l = board.get(0).length();
        this.board = new char[l][l];
        int i=0;
        for(String str : board){
            System.out.println(str+ i);
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
        this.size = (int) Math.floor(900/board[0].length);
        repaint();
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
                    case 'S': g2.setColor(Color.GREEN.darker());
                        break;
                    case 'G': g2.setColor(Color.RED.darker());
                        break;
                    case 'M': g2.setColor(new Color(88, 41,0));
                        break;
                    case 'T': g2.setColor(new Color(255, 120,5));
                        break;
                    case 'X': g2.setColor(new Color(255, 120,5));
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


    @Override
    public void run() {
        saveboard = board;
        getStart();
        over = false;
        timer = saveTimer;
        movement = saveMovement;
        recursion = true;
        moveFrom(tmpX, tmpY);
    }

    public  boolean isGoal(int x, int y){
        return board[x][y] == 'G';
    }
    public  boolean isWall(int x, int y){
        return board[x][y] == 'W' || board[x][y] == 'X';
    }
    public  boolean isMubTrap(int x, int y){
        return board[x][y] == 'M';
    }
    public  boolean isTrap(int x, int y){
        return board[x][y] == 'T';
    }
    public  boolean isFree(int x, int y){
        return board[x][y] == 'F';
    }
    public  boolean isStart(int x, int y){
        return board[x][y] == 'S';
    }
    public void getStart(){
        int l = board[0].length;
        for(int i=0; i<l; i++){
            for(int j=0; j<l; j++){
                if(isStart(i, j)){
                    tmpX = i;
                    tmpY = j;
                    return;
                }
            }
        }
    }
    public  boolean isVisited(int x, int y){
        return board[x][y] == 'V';
    }

    public void setVisited(int x, int y){
        board[x][y] = 'V';
        tmpX = x;
        tmpY = y;
    }

    private void moveFrom(int x, int y) {
        if(recursion){
            if(isWall(x,y))
                return;
            if(isVisited(x,y))
                return;

            movement++;
            if(isMubTrap(x, y)){
                try {
                    setVisited(x,y);
                    Thread.sleep(500);
                    timer += 500;
                } catch (InterruptedException e) {
                }
            }
            if(isTrap(x,y)){
                saveboard[x][y] = 'X';
                board = saveboard;
                saveMovement = movement;
                saveTimer = timer;
                recursion = false;
                Thread t = new Thread(this);
                t.run();
                return;
            }
            if(isGoal(x,y)){
                this.over = true;
                saveTimer=0;
                saveMovement=0;
                JOptionPane.showMessageDialog(this, "Good job dog!!");
            }

            if(!this.over){
                setVisited(x,y);
                repaint();
                try {Thread.sleep(100);
                    timer+=100;
                    moveFrom(x-1,y);
                    moveFrom(x+1,y);
                    moveFrom(x,y-1);
                    moveFrom(x,y+1);
                } catch (Exception e) { }
            }
        }
    }

}

