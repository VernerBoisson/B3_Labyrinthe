package GUI;

import beans.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

public class CellPanel extends JPanel {

    private int size;
    private Cell cellBoard[][];


    private boolean isEditMaze;
    private Maze maze;

    public CellPanel(int size, Optional<Maze> maze) {
        setLayout(new GridLayout(20, 1));
        setBorder(new EmptyBorder(0, 500, 0, 0));

        this.isEditMaze = true;
        this.size = size;
        this.cellBoard = new Cell[size][size];
        int cellSize = (int) Math.floor(800 / size);
        System.out.println(!maze.isPresent());
        if (!maze.isPresent()){
            this.isEditMaze = false;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.cellBoard[i][j] = new Cell(150 + i + i * cellSize, 70 + j + j * cellSize, cellSize, cellSize, 'F', i, j);
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
                    cellBoard[i][j] = new Cell(150 + i + i * cellSize, 50 + j + j * cellSize, cellSize, cellSize, mazeSchema[i][j], i, j);
                }
            }
            JLabel title = new JLabel(this.maze.getTitle());
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
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

        int countn;

        while (c.size() != 0) {

            a = c.get(c.size() - 1);
            b = d.get(d.size() - 1);

            for (int i = 0; i < 2000; i++) {
                int dir = random.nextInt(5);
                countn = 0;

                switch (dir) {
                    case 0:
                        if (a + 2 < size) {
                            if (cellBoard[a + 1][b].getType() == 'W' && cellBoard[a + 2][b].getType() == 'W') {
                                a += 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                a += 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');

                                if (a + 2 < size)
                                    if (cellBoard[a + 2][b].getType() == 'W')
                                    countn += 1;
                                if (a - 2 > 0)
                                    if (cellBoard[a - 2][b].getType() == 'W')
                                    countn += 1;
                                if (b + 2 < size)
                                    if (cellBoard[a][b + 2].getType() == 'W')
                                        countn += 1;
                                if (b - 2 > 0)
                                    if (cellBoard[a][b - 2].getType() == 'W')
                                        countn += 1;
                                if (countn >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }

                            }
                        }
                        break;
                    case 1:
                        if (b + 2 < size) {
                            if (cellBoard[a][b + 1].getType() == 'W' && cellBoard[a][b + 2].getType() == 'W') {
                                b += 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                b += 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                if (a + 2 < size)
                                    if (cellBoard[a + 2][b].getType() == 'W')
                                        countn += 1;
                                if (a - 2 > 0)
                                    if (cellBoard[a - 2][b].getType() == 'W')
                                        countn += 1;
                                if (b + 2 < size)
                                    if (cellBoard[a][b + 2].getType() == 'W')
                                        countn += 1;
                                if (b - 2 > 0)
                                    if (cellBoard[a][b - 2].getType() == 'W')
                                        countn += 1;
                                if (countn >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                            }


                        }
                        break;
                    case 2:
                        if (a - 2 > 0) {
                            if (cellBoard[a - 1][b].getType() == 'W' && cellBoard[a - 2][b].getType() == 'W') {
                                a -= 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                a -= 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                if (a + 2 < size)
                                    if (cellBoard[a + 2][b].getType() == 'W')
                                        countn += 1;
                                if (a - 2 > 0)
                                    if (cellBoard[a - 2][b].getType() == 'W')
                                        countn += 1;
                                if (b + 2 < size)
                                    if (cellBoard[a][b + 2].getType() == 'W')
                                        countn += 1;
                                if (b - 2 > 0)
                                    if (cellBoard[a][b - 2].getType() == 'W')
                                        countn += 1;
                                if (countn >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                            }
                        }
                        break;
                    case 3:
                        if (b - 2 > 0) {

                            if (cellBoard[a][b - 1].getType() == 'W' && cellBoard[a][b - 2].getType() == 'W') {
                                b -= 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                b -= 1;
                                cellBoard[a][b].setColor(Color.WHITE);
                                cellBoard[a][b].setType('F');
                                if (a + 2 < size)
                                    if (cellBoard[a + 2][b].getType() == 'W')
                                        countn += 1;
                                if (a - 2 > 0)
                                    if (cellBoard[a - 2][b].getType() == 'W')
                                        countn += 1;
                                if (b + 2 < size)
                                    if (cellBoard[a][b + 2].getType() == 'W')
                                        countn += 1;
                                if (b - 2 > 0)
                                    if (cellBoard[a][b - 2].getType() == 'W')
                                        countn += 1;
                                if (countn >= 2) {
                                    c.add(a);
                                    d.add(b);
                                }
                            }

                        }
                        break;
                }
            }
            repaint();

            c.remove(c.size() - 1);
            d.remove(d.size() - 1);


        }
    }








//
//
//
//
//
//
//
//
//
//
//
//
//                        if (cellBoard[a][b].getType() == 'W' && cellBoard[a+1][b].getType() == 'W' && cellBoard[a][b+1].getType() == 'W' && cellBoard[a-1][b].getType() == 'W' && cellBoard[a][b-1].getType() == 'W') {
//                            cellBoard[a][b].setColor(Color.WHITE);
//                            cellBoard[a][b].setType('F');
//                            repaint();
//                            for (int k = 0; k < 1000; k++) {
//                                int dir = random.nextInt(5);
//
//                                switch (dir) {
//                                    case 0:
//                                        if (a + 2 < size) {
//                                            if (cellBoard[a + 1][b].getType() == 'W' && cellBoard[a + 2][b].getType() == 'W') {
//                                                a += 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                                a += 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                            }
//                                        }
//                                        break;
//                                    case 1:
//                                        if (b + 2 < size) {
//                                            if (cellBoard[a][b + 1].getType() == 'W' && cellBoard[a][b + 2].getType() == 'W') {
//                                                b += 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                                b += 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                            }
//
//                                        }
//                                        break;
//                                    case 2:
//                                        if (a - 2 > 0) {
//                                            if (cellBoard[a - 1][b].getType() == 'W' && cellBoard[a - 2][b].getType() == 'W') {
//                                                a -= 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                                a -= 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                            }
//                                        }
//                                        break;
//                                    case 3:
//                                        if (b - 2 > 0) {
//
//                                            if (cellBoard[a][b - 1].getType() == 'W' && cellBoard[a][b - 2].getType() == 'W') {
//                                                b -= 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                                b -= 1;
//                                                cellBoard[a][b].setColor(Color.WHITE);
//                                                cellBoard[a][b].setType('F');
//                                            }
//
//                                        }
//                                        break;
//                                }
//
//                                repaint();
//
//                            }
//                        }
//                }
//            }
//        }









//
//        int a = 0;
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                cellBoard[i][j].setColor(Color.WHITE);
//                cellBoard[i][j].setType('F');
//                if (a >= size) {
//                    if (a % 2 == 0) {
//                        cellBoard[i][j].setColor(Color.WHITE);
//                        cellBoard[i][j].setType('F');
//
//                    } else {
//                        cellBoard[i][j].setColor(Color.BLACK);
//                        cellBoard[i][j].setType('W');
//                    }
//                    if (a >= size * 2)
//                        a = 0;
//                }
//
//                if (random.nextBoolean() == true) {
//                    if (a % 2 == 0 && cellBoard[i][j].getColor() == Color.WHITE) {
//
//                        cellBoard[i][j].setColor(Color.BLACK);
//                        cellBoard[i][j].setType('W');
//                    }
//                }
//                a += 1;
//            }
//        }
//

//    }
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
        int count = 0;
        int index = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                params = params.concat("maze[" + i + "]=" + cellBoard[i][j].getType() + "&");
            }
        }

        params = params.concat("title=" + title + "&author=" + author );


        String url = "http://localhost:8080/";
        if (isEditMaze){
            url = url.concat(String.valueOf(maze.getId()) + "?_method=patch");
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header

        con.setRequestMethod("POST");

        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        //Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());


    }

}
