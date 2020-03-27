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
import java.util.List;

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

class GridPanel extends JPanel {
    private ArrayList<Cell> shapes;
    private ArrayList<String> tableau;
    public GridPanel() {
        int size = 50;
        this.shapes = new ArrayList<Cell>();
        this.tableau = new ArrayList<String>();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                shapes.add(new Cell(i+i*size,j+j*size,size,size));
            }
        }
        for (Cell item : shapes) {
            tableau.add(item.getType());
        }
        System.out.println(tableau);
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

    public void save(String title, String author) throws Exception{
        try {

        } catch (Exception e){

        }
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

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "maze=WWWWWWWWWW&maze=WFFFFWFFFW&maze=WFWWFWFWFW&maze=WFWFFWFWFW&maze=WFWFWWFWFW&maze=WFWFFWFWFW&maze=WFWFWFFWFW&maze=WFWFWFWWFW&maze=WSWFFFFWGW&maze=WWWWWWWWWW&title='papa'";

        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(test);
        wr.flush();
        wr.close();

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
        System.out.println(tableau);


    }
}
