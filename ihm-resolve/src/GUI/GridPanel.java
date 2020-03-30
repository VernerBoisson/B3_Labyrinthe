package GUI;

import beans.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GridPanel extends JPanel implements Runnable{
    private volatile char[][] board;
    int tmpX,tmpY;
    private boolean over = false;
    private int size;
    private Maze maze;
    private int length=0;
    private volatile boolean isRunning = false;
    private boolean isStop = false;
    private int timer;
    private int movement;
    private int saveTimer=0;
    private int saveMovement=0;

    public GridPanel(){
        setVisible(true);
        setSize(600,600);
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setStop(boolean stop) { this.isStop = stop; }

    public void setBoard(char[][] board){
        this.board = board;
        this.length = this.board[0].length;
        this.size = (int) Math.floor(850/board[0].length);
        repaint();
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
                g2.fill(new Rectangle2D.Double(150 + i+i*size,60 + j+j*size, size, size));
            }
        }
        g2.dispose();
    }

    @Override
    public void run() {
        timer = saveTimer;
        movement = saveMovement;
        getStart();
        over = false;
        isStop = false;
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
    public  boolean isStart(int x, int y){
        return board[x][y] == 'S';
    }
    public  boolean isVisited(int x, int y){
        return board[x][y] == 'V';
    }
    public void setVisited(int x, int y){
        board[x][y] = 'V';
        tmpX = x;
        tmpY = y;
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
    private void moveFrom(int x, int y) {
        if(isRunning){
            if(isTrap(x,y)){
                saveMovement = movement;
                saveTimer = timer;
                run();
                return;
            }
            if(!this.over && !isWall(x,y) && !isVisited(x,y)){
                movement++;
                boolean isMubTrap = isMubTrap(x,y);
                boolean isGoal = isGoal(x,y);
                setVisited(x,y);
                repaint();
                try {
                    if(isStop){
                        this.over = true;
                        return;
                    }
                    if(isGoal){
                        this.over = true;
                        saveTimer=0;
                        saveMovement=0;
                        int validate = JOptionPane.showConfirmDialog(this, "Resolved ! \n time : " + (float) timer/1000 + "s \n movement number : " + movement, "Results", JOptionPane.DEFAULT_OPTION);
                        if(validate == 0){
                            edit();
                        }
                        return;
                    }
                    if(isMubTrap){
                        Thread.sleep(500);
                        timer += 500;
                    }else{
                        Thread.sleep(100);
                        timer+=100;
                    }
                    try{
                        moveFrom(x-1,y);
                    }catch (Exception e){}
                    try{
                        moveFrom(x+1,y);
                    }catch (Exception e){}
                    try{
                        moveFrom(x,y-1);
                    }catch (Exception e){}
                    try{
                        moveFrom(x,y+1);
                    }catch (Exception e){}
                } catch (Exception e) { }
            }
        }
    }
    public void edit() throws IOException {
        String params = "";
        params = params.concat("timer="+timer+"&movement="+movement);
        String url = "http://localhost:8080/";
        url = url.concat(maze.getId() + "?_method=PATCH");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
    }
}


