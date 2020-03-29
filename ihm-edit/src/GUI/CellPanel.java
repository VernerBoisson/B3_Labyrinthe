package GUI;

import javax.swing.*;
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

    public CellPanel(int size, Optional<char [][]> charTypeBoard) {

        this.isEditMaze = true;
        this.size = size;
        this.cellBoard = new Cell[size][size];
        int cellSize = (int) Math.floor(800/size);
        System.out.println(!charTypeBoard.isPresent());
        if (!charTypeBoard.isPresent())
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.cellBoard[i][j] = new Cell(i + i * cellSize, j + j * cellSize, cellSize, cellSize, 'F', i, j);
                }
            }
        else {
            char [][] test = charTypeBoard.get();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cellBoard[i][j] = new Cell(i + i * cellSize, j + j * cellSize, cellSize, cellSize, test[i][j], i, j);
                }
            }
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

    public void randomWall(){
        Random random = new Random();

        int a = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellBoard[i][j].setColor(Color.WHITE);
                cellBoard[i][j].setType('F');
                if (a >= size) {
                    if (a % 2 == 0) {
                        cellBoard[i][j].setColor(Color.WHITE);
                        cellBoard[i][j].setType('F');

                    } else {
                        cellBoard[i][j].setColor(Color.BLACK);
                        cellBoard[i][j].setType('W');
                    }
                    if (a >= size * 2)
                        a = 0;
                }

                if (random.nextBoolean() == true) {
                    if (a % 2 == 0 && cellBoard[i][j].getColor() == Color.WHITE) {

                        cellBoard[i][j].setColor(Color.BLACK);
                        cellBoard[i][j].setType('W');
                    }
                }
                a += 1;
            }
        }


        repaint();
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
