package GUI;


import javax.net.ssl.HttpsURLConnection;
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
import java.util.List;

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

class GridPanel extends JPanel {
    private ArrayList<Cell> shapes;
    private ArrayList<String> tableau;  
    public GridPanel() {
        int size = 25;
        this.shapes = new ArrayList<Cell>();
        this.tableau = new ArrayList<String>();
        setAlignmentX(Component.CENTER_ALIGNMENT);
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                shapes.add(new Cell(200 +i+i*size,20 + j+j*size,size,size));
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if (GlobalType == "S") {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == "S") {
                                    tmp.setType("F");
                                    tmp.setColor(Color.WHITE);
                                }
                            }
                        }
                        if (GlobalType == "G") {
                            for (Cell tmp : shapes) {
                                if (tmp.getType() == "G") {
                                    tmp.setType("F");
                                    tmp.setColor(Color.WHITE);
                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                    }
                }
                repaint();

//                tableau.clear();
//                for (Cell item : shapes) {
//                    tableau.add(item.getType());
//                }
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
            item.setType("F");
        }
        repaint();
    }

    public void wallFill(){
        for (Cell item : shapes){
            item.setColor(Color.BLACK);
            item.setType("W");
        }
        repaint();
    }

    public boolean isSolvable(){
        boolean start = false;
        boolean goal = false;
        for (Cell item : shapes){
            if (item.getType() == "S") {
                start = true;
            }
            if (item.getType() == "G"){
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


        int count = 0;
        int index = -1;

        for (Cell item : shapes) {
            if (count != 0) {

                tableau.set(index, tableau.get(index).concat(item.getType()));
            } else{
                count = 10;
                index += 1;
                tableau.add(item.getType());
                System.out.println(tableau);

            }
            count -= 1;
        }
        String test = "";
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
