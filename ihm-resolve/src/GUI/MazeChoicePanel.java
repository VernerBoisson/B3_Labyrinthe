package GUI;

import beans.Maze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MazeChoicePanel extends JPanel implements Runnable{
    private char[][] board;
    int tmpX,tmpY;
    private boolean over = true;

    public MazeChoicePanel() {
        super();
        setLayout(new FlowLayout());
        GridPanel gridPanel = new GridPanel();
        add(gridPanel, FlowLayout.LEFT);
        JScrollBar jScrollBar = new JScrollBar();
        add(jScrollBar);
        JButton jButtonExectue = new JButton("Execute");
        add(jButtonExectue, FlowLayout.RIGHT);

        jButtonExectue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBoard(gridPanel.getMaze().getSchemaMaze());
                getStart();
                run();
            }
        });

        for(Maze maze : Maze.mazes) {
            JButton mazeButton = new JButton(maze.getTitle());
            mazeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println(maze.getTitle() + maze.toString());
                    gridPanel.setMaze(maze);
                    gridPanel.repaint();
                }
            });
            add(mazeButton, FlowLayout.CENTER);
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(ArrayList<String> board) {
        int l = board.get(1).length();
        this.board = new char[l][l];
        int i=0;
        for(String str : board){
            this.board[i] = str.toCharArray();
            i++;
        }
    }

    @Override
    public void run() {
        moveFrom(tmpX, tmpY);
    }

    public  boolean isGoal(int x, int y){
        return board[x][y] == 'G';
    }
    public  boolean isWall(int x, int y){
        return board[x][y] == 'W';
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
        if(isWall(x,y))
            return;
        if(isVisited(x,y))
            return;
        if(isGoal(x,y)){
            this.over = true;
            System.out.println("GGGG");
            JOptionPane.showMessageDialog(this, "Good job dog!!");
            System.exit(0);
        }
        if(!this.over){
            setVisited(x,y);
            try {Thread.sleep(10); } catch (Exception e) { }

            moveFrom(x-1,y);
            moveFrom(x+1,y);
            moveFrom(x,y-1);
            moveFrom(x,y+1);
        }
    }
}
