package GUI;

import beans.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

public class CellPanel extends JPanel {

    private int size;
    private Cell cellBoard[][];
    private boolean isEditMaze;
    private Maze maze;

    public CellPanel(int size, Optional<Maze> maze) {
        setBorder(new EmptyBorder(12, 0, 0, 0));
        this.isEditMaze = true;
        this.size = size;
        this.cellBoard = new Cell[size][size];
        int cellSize = 850 / size;
        if (!maze.isPresent()){
            this.isEditMaze = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.cellBoard[i][j] = new Cell(150 + i + i * cellSize, 60 + j + j * cellSize, cellSize , cellSize, 'F');
                }
            }
            JLabel title = new JLabel("Unsaved Maze");
            title.setFont(new java.awt.Font("Dialog", 5, 20));
            add(title, BorderLayout.CENTER);
        }
        else {
            this.maze = maze.get();
            this.isEditMaze = true;

            char [][] mazeSchema = this.maze.getSchemaMaze();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cellBoard[i][j] = new Cell(150 + i + i * cellSize, 60 + j + j * cellSize, cellSize, cellSize, mazeSchema[i][j]);
                }
            }
            JLabel title = new JLabel(this.maze.getTitle());
            title.setFont(new java.awt.Font("Dialog", 5, 20));
            add(title, BorderLayout.CENTER);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (cellBoard[i][j].getShape().contains(e.getPoint())) {
                            if (GlobalType == 'S') {
                                for (int k = 0; k < size; k++) {
                                    for (int l = 0; l < size; l++) {
                                        if (cellBoard[k][l].getType() == 'S') {
                                            cellBoard[k][l].setType('F');
                                            cellBoard[k][l].setColor(Color.WHITE);
                                        }
                                    }
                                }
                            }
                            if (GlobalType == 'G') {
                                for (int k = 0; k < size; k++) {
                                    for (int l = 0; l < size; l++) {
                                        if (cellBoard[k][l].getType() == 'G') {
                                            cellBoard[k][l].setType('F');
                                            cellBoard[k][l].setColor(Color.WHITE);
                                        }
                                    }
                                }
                            }
                            cellBoard[i][j].setColor(GlobalColor);
                            cellBoard[i][j].setType(GlobalType);
                        }
                    }
                }
                repaint();
            }
         });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for(int i=0; i<size; i++){
                    for(int j=0; j<size; j++){
                        if (cellBoard[i][j].getShape().contains(e.getPoint())) {
                            if (GlobalType == 'W' || GlobalType == 'F'){
                                cellBoard[i][j].setColor(GlobalColor);
                                cellBoard[i][j].setType(GlobalType);
                            }
                        }
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g2.setColor(cellBoard[i][j].getColor());
                g2.fill(cellBoard[i][j].getShape());
            }
        }
        g2.dispose();
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellBoard[i][j].setColor(Color.WHITE);
                cellBoard[i][j].setType('F');

            }
        }
        repaint();
    }

    public void randomWall() {

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellBoard[i][j].setColor(Color.BLACK);
                cellBoard[i][j].setType('W');

            }
        }
        ArrayList<Integer> c = new ArrayList<Integer>();
        ArrayList<Integer> d = new ArrayList<Integer>();


        int a = 1;
        int b = 1 ;

        c.add(a);
        d.add(b);

        cellBoard[a][b].setColor(Color.WHITE);
        cellBoard[a][b].setType('F');
        while (c.size() != 0) {


            a = c.get(c.size() - 1);
            b = d.get(d.size() - 1);
            int[] stuck = new int[4];
            while ((stuck[0] + stuck[1] + stuck[2] + stuck[3]) != 10) {
                int dir = random.nextInt(4);
                switch (dir) {
                    case 0:
                        if (a + 2 < size) {
                            if (cellBoard[a + 1][b].getType() == 'W' && cellBoard[a + 2][b].getType() == 'W') {
                                a += 1;
                                setPath(a,b);
                                a += 1;
                                setPath(a,b);
                                if (checkNeighbors(a,b) >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                                for (int i = 0; i < stuck.length ; i++) {
                                    stuck[i] = 0;
                                }
                            }
                        }
                        break;
                    case 1:
                        if (b + 2 < size) {
                            if (cellBoard[a][b + 1].getType() == 'W' && cellBoard[a][b + 2].getType() == 'W') {
                                b += 1;
                                setPath(a,b);
                                b += 1;
                                setPath(a,b);
                                if (checkNeighbors(a,b) >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                                for (int i = 0; i < stuck.length ; i++) {
                                    stuck[i] = 0;
                                }
                            }
                        }
                        break;
                    case 2:
                        if (a - 2 > 0) {
                            if (cellBoard[a - 1][b].getType() == 'W' && cellBoard[a - 2][b].getType() == 'W') {
                                a -= 1;
                                setPath(a,b);
                                a -= 1;
                                setPath(a,b);
                                if (checkNeighbors(a,b) >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                                for (int i = 0; i < stuck.length ; i++) {
                                    stuck[i] = 0;
                                }
                            }
                        }
                        break;
                    case 3:
                        if (b - 2 > 0) {
                            if (cellBoard[a][b - 1].getType() == 'W' && cellBoard[a][b - 2].getType() == 'W') {
                                b -= 1;
                                setPath(a,b);
                                b -= 1;
                                setPath(a,b);
                                if (checkNeighbors(a,b) >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                                for (int i = 0; i < stuck.length ; i++) {
                                    stuck[i] = 0;
                                }
                            }
                        }
                        break;
                }
                stuck[dir] = dir+1;
            }
            c.remove(c.size() - 1);
            d.remove(d.size() - 1);
        }
        repaint();
    }

    public void setPath(int a,int b) {
        cellBoard[a][b].setColor(Color.WHITE);
        cellBoard[a][b].setType('F');
    }

    public int checkNeighbors(int a, int b){
        int wallNeighbors = 0;
        if (a + 2 < size) {
            if (cellBoard[a + 2][b].getType() == 'W')
                wallNeighbors += 1;
        }
        if (a - 2 > 0) {
            if (cellBoard[a - 2][b].getType() == 'W')
                wallNeighbors += 1;
        }
        if (b + 2 < size) {
            if (cellBoard[a][b + 2].getType() == 'W')
                wallNeighbors += 1;
        }
        if (b - 2 > 0) {
            if (cellBoard[a][b - 2].getType() == 'W')
                wallNeighbors += 1;
        }
        return wallNeighbors;
    }


    public void wallFill(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellBoard[i][j].setColor(Color.BLACK);
                cellBoard[i][j].setType('W');
            }
        }
        repaint();
    }

    public boolean isSolvable(){
        boolean start = false;
        boolean goal = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cellBoard[i][j].getType() == 'S') {
                    start = true;
                }

                if (cellBoard[i][j].getType() == 'G') {
                    goal = true;
                }
            }
        }
        if (start && goal)
            return true;
        else
            return false;
    }


    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public boolean isEditMaze() {
        return isEditMaze;
    }

    public void setEditMaze(boolean editMaze) {
        isEditMaze = editMaze;
    }

    public void save(String title, String author) throws Exception{

        String params = "";
        String url = "http://localhost:8080/";

        if (isEditMaze){
            url = url.concat(maze.getId() + "?_method=patch");
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                params = params.concat("maze[" + i + "]=" + cellBoard[i][j].getType() + "&");
            }
        }
        if (title != "")
            params = params.concat("title=" + title + "&" );
        if (author != "")
            params = params.concat("author=" + author + "&");

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();
        new BufferedReader( new InputStreamReader(con.getInputStream()));
    }

    public void delete(int id) throws Exception{

        String url = "http://localhost:8080/" + id + "?_method=delete";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        new BufferedReader( new InputStreamReader(con.getInputStream()));

    }
}
