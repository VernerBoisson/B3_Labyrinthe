package GUI;


import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

class GridPanel extends JPanel {
    private int size;
    private Cell cellBoard[][];
    private ArrayList<Cell> shapes;
    private ArrayList<String> tableau;
    private char [][] board;
    private boolean isEditMaze;

    public GridPanel(char [][] board) {

        this.isEditMaze = true;
        size = board.length;
        this.board = new char[size][size];
        this.board = board;
        this.shapes = new ArrayList<Cell>();
        this.tableau = new ArrayList<String>();


        int cellSize = (int) Math.floor(800/size);



        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                shapes.add(new Cell(i+i*cellSize,j+j*cellSize,cellSize,cellSize, board[i][j], i, j));

            }
        }
        System.out.println(shapes);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if (GlobalType == 'S') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'S') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        if (GlobalType == 'G') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'G') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                        board[item.getLocX()][item.getLocY()] = GlobalType;
                    }
                }
                repaint();

            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if (GlobalType == 'S') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'S') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        if (GlobalType == 'G') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'G') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                        board[item.getLocX()][item.getLocY()] = GlobalType;
                    }
                }
                repaint();

            }
        });

    }


    public GridPanel(int Size) {
        this.isEditMaze = false;



        size = Size;
        int cellSize = (int) Math.floor(800/size);
        this.board = new char[size][size];

        this.shapes = new ArrayList<Cell>();
        this.tableau = new ArrayList<String>();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                shapes.add(new Cell(i+i*cellSize,j+j*cellSize,cellSize,cellSize, 'F', i, j ));
                board[i][j] = 'F';
            }
        }


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if (GlobalType == 'S') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'S') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        if (GlobalType == 'G') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'G') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                        board[item.getLocX()][item.getLocY()] = GlobalType;
                    }
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if (GlobalType == 'S') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'S') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        if (GlobalType == 'G') {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == 'G') {
                                    tmp.setType('F');
                                    tmp.setColor(Color.WHITE);
                                    board[tmp.getLocX()][tmp.getLocY()] = 'F';

                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                        board[item.getLocX()][item.getLocY()] = GlobalType;
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

        for (Cell item : shapes) {
            g2.setColor(item.getColor());
            g2.fill(item.getShape());
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }



    public void clear(){
        for (Cell item : shapes){
            item.setColor(Color.WHITE);
            item.setType('F');
        }
        repaint();
    }

    public void wallFill(){
        Random random = new Random();
        int count = 0;
        for (Cell item : shapes) {
            if (count == size + 1) {
                random.nextInt(5);

            }
        }
        //shapes.set(5, shapes.get(5));

        int a = 0;
        for (Cell item : shapes) {


            if (a >= size) {
                if (a % 2 == 0) {
                    item.setColor(Color.WHITE);
                    item.setType('F');

                } else {
                    item.setColor(Color.BLACK);
                    item.setType('W');
                }
                if (a >= size*2)
                    a = 0;
            }
            if (random.nextBoolean() == true) {
                if (a % 2 == 0 && item.getColor() == Color.WHITE) {

                    item.setColor(Color.BLACK);
                    item.setType('W');
                }
            }
            a += 1;
        }


        repaint();
    }

    public boolean isSolvable(){
        boolean start = false;
        boolean goal = false;
        for (Cell item : shapes){
            if (item.getType() == 'S') {
                start = true;
            }
            if (item.getType() == 'G'){
                goal = true;
            }
        }
        if (start && goal)
            return true;
        else
            return false;
    }

    public void save(String title, String author) throws Exception{



        tableau.clear();

        String test = "";
        int count = 0;
        int index = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                test = test.concat( "maze[" + i +"]=" + board[i][j] + "&");

            }
//            if (count != 0) {
//
//                tableau.set(index, tableau.get(index).concat(item.getType()));
//            } else{
//                count = size;
//                index += 1;
//                tableau.add(item.getType());
//                System.out.println(tableau);
//
//            }
//            count -= 1;
        }

        System.out.println(tableau.size());

        for (int i = 0; i<tableau.size(); i++){
            test = test.concat("maze=" + tableau.get(i) + "&");

        }

        test = test.concat("title=" + title + "&author=" + author );
        System.out.println(test);


        String url = "http://localhost:8080/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        //Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(test);
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
