package GUI;

import beans.Maze;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class MazeGUI {
    private JFrame jframe;
    private CellPanel cellPanel;
    private ToolPanel toolPanel;

    public MazeGUI(){
        jframe = new JFrame();
        Toolkit tk = Toolkit.getDefaultToolkit();
        jframe.setSize(new Dimension(1280, 1080));
        jframe.setTitle("Maze Edit");
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cellPanel = new CellPanel(19, Optional.<Maze>empty());
        jframe.add(cellPanel, BorderLayout.CENTER);
        toolPanel = new ToolPanel(cellPanel);
        toolPanel.setMaximumSize(new Dimension(50 ,50));
        toolPanel.setMinimumSize(new Dimension(50 ,50));
        jframe.add(toolPanel, BorderLayout.EAST);




        JFrame newPopup = new JFrame();
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3,1,10,10));
        newPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel labMazeSize = new JLabel("Maze size");
        JButton createMaze =  new JButton("Create");
        JSpinner mazeSize = new JSpinner(new SpinnerNumberModel(10, 2,100,1));
        //gridPanel = new GridPanel();

        createMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jframe.remove(cellPanel);
                jframe.remove(toolPanel);
                cellPanel = new CellPanel((Integer)mazeSize.getValue(), Optional.<Maze>empty());
                toolPanel = new ToolPanel(cellPanel);
                jframe.add(cellPanel, BorderLayout.CENTER);
                jframe.add(toolPanel, BorderLayout.EAST);
                jframe.setVisible(true);
            }
        });

        labMazeSize.setPreferredSize(new Dimension( 100,30));
        mazeSize.setPreferredSize(new Dimension( 300,30));


        newPanel.add(labMazeSize);
        newPanel.add(mazeSize);
        newPanel.add(createMaze);
        newPopup.add(newPanel);
        newPopup.pack();









        //Pop up save maze
        JFrame savePopup = new JFrame();
        JPanel savepanel = new JPanel();
        savepanel.setLayout(new GridLayout(6,1, 10,10));
        JLabel labTitle = new JLabel("Maze title");
        JTextField title = new JTextField();
        JLabel labAuthor = new JLabel("Maze creator");
        JTextField author = new JTextField();
        JButton saveMaze =  new JButton("Save");
        JLabel labError = new JLabel("Your maze must have a start and a goal point, please add them to save it !");


        saveMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (cellPanel.isSolvable()) {
                        cellPanel.save(title.getText(), author.getText());
                        savePopup.setVisible(false);
                        jframe.remove(cellPanel);
                        jframe.remove(toolPanel);
                        cellPanel = new CellPanel(15, Optional.empty());
                        toolPanel = new ToolPanel(cellPanel);
                        jframe.add(cellPanel, BorderLayout.CENTER);
                        jframe.add(toolPanel, BorderLayout.EAST);
                        jframe.setVisible(true);
                    } else {
                        savepanel.add(labError);
                        savePopup.add(savepanel);
                        savePopup.pack();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        labTitle.setPreferredSize(new Dimension( 100,30));
        title.setPreferredSize(new Dimension( 300,30));
        labAuthor.setPreferredSize(new Dimension( 100,30));
        author.setPreferredSize(new Dimension( 300,30));

        savepanel.add(labTitle);
        savepanel.add(title);
        savepanel.add(labAuthor);
        savepanel.add(author);
        savepanel.add(saveMaze);
        savepanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        savePopup.add(savepanel);
        savePopup.pack();































        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMaze = new JMenuItem("New");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem edit = new JMenuItem("Edit a maze");

        newMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                newPopup.setVisible(true);

            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                savePopup.setVisible(true);

            }
        });


        //Pop up edit maze

        JFrame editPopup = new JFrame();
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(3,1,10,10));
        JLabel labSelectMaze = new JLabel("Select a maze");
        JComboBox selectMaze = new JComboBox();

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String response = MyGetRequest();
                ParseResponse(response);
                selectMaze.removeAllItems();
                for(Maze maze : Maze.mazes) {
                    selectMaze.addItem(maze.getId() + " " + maze.getTitle());
                }
                editPopup.setVisible(true);

            }
        });






        //getRequest();


        JButton openSelectedMaze = new JButton("Open this Maze");


        openSelectedMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(selectMaze.getSelectedItem());
                //getRequest();

                for(Maze maze : Maze.mazes) {
                    String mazeName = maze.getId() + " " + maze.getTitle();

                    if (mazeName.equals(selectMaze.getSelectedItem().toString())){
                        jframe.remove(cellPanel);
                        jframe.remove(toolPanel);
                        cellPanel = new CellPanel(maze.getSchemaMaze().length,Optional.of(maze));
                        toolPanel = new ToolPanel(cellPanel);
                        jframe.add(cellPanel, BorderLayout.CENTER);
                        jframe.add(toolPanel, BorderLayout.EAST);
                        jframe.setVisible(true);
                        editPopup.setVisible(false);
                        break;
                    }
                }
                selectMaze.removeAllItems();
                for(Maze maze : Maze.mazes) {

                    selectMaze.addItem(maze.getId() + " " + maze.getTitle());
                }
            }
        });


        labSelectMaze.setPreferredSize(new Dimension( 100,30));
        selectMaze.setPreferredSize(new Dimension( 100,30));

        editPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        editPanel.add(labSelectMaze);
        editPanel.add(selectMaze);
        editPanel.add(openSelectedMaze);
        editPopup.add(editPanel);
        editPopup.pack();




        menu.add(newMaze);
        menu.addSeparator();
        menu.add(save);
        menu.addSeparator();
        menu.add(edit);
        menuBar.setPreferredSize(new Dimension(200, 50));
        menuBar.add(menu);
        jframe.setJMenuBar(menuBar);





        jframe.setVisible(true);

    }
//
//
//    public void getRequest() {
//
//        try {
//
//            String response = MyGetRequest();
//            JSONArray jsonArray = new JSONArray(response);
//            Maze.mazes.clear();
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
//                ArrayList<String> maze = new ArrayList<String>();
//
//                JSONArray arr = jsonObject.getJSONArray("maze");
//                for (int j = 0; j < arr.length(); j++) {
//                    maze.add(arr.get(j).toString());
//                }
//                Maze.mazes.add(new Maze((int) jsonObject.get("id"), (String) jsonObject.get("title"), (String) jsonObject.get("author"), maze));
//            }
//
//
//        } catch (
//                IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        for (Maze maze : Maze.mazes) {
//            System.out.println(maze.toString());
//        }
//    }



//
//    public static String MyGetRequest() throws IOException {
//
//        URL urlForGetRequest = new URL("http://localhost:8080/");
//        String readLine = null;
//        HttpURLConnection conection = (HttpURLConnection)urlForGetRequest.openConnection();
//        conection.setRequestMethod("GET");
//        int responseCode = conection.getResponseCode();
//        if (responseCode == 200) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
//            StringBuffer response = new StringBuffer();
//
//            while((readLine = in.readLine()) != null) {
//                StringBuffer append = response.append(readLine);
//            }
//
//            in.close();
//            return response.toString();
//        } else {
//            System.out.println("GET NOT WORKED");
//        }
//        return null;
//    }
//
//
//    public static void ParseResponse(String response){
//        JSONArray jsonArray = new JSONArray(response);
//
//        for (int i = 0; i < jsonArray.length(); i++)
//        {
//            System.out.println(jsonArray.get(i).toString());
//            JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
//            JSONArray arr = jsonObject.getJSONArray("maze");
//            int l = arr.length();
//            char[][] maze = new char[l][l];
//            for (int j = 0; j < l; j++)
//            {
//                JSONArray arr2 = (JSONArray) arr.get(j);
//                for(int k =0; k<l; k++){
//                    String str = (String) arr2.get(k);
//                    maze[j][k] = (char) str.charAt(0);
//                }
//                System.out.println(arr.get(j).getClass().getName());
//            }
//
//            Maze.mazes.add(new Maze((int) jsonObject.get("id"),(String) jsonObject.get("title"),(String) jsonObject.get("author"), maze));
//        }
//    }
//





    public String MyGetRequest()   {
        try {
            URL urlForGetRequest = new URL("http://localhost:8080/");
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();

                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }

                in.close();
                return response.toString();
            } else {
                System.out.println("GET NOT WORKED");
            }
            return null;
        } catch (
                IOException  e) {
            e.printStackTrace();
            return null;

        }
    }

    public void ParseResponse(String response)   {
        try {
        JSONArray jsonArray = new JSONArray(response);

        for (int i = 0; i < jsonArray.length(); i++)
        {
            System.out.println(jsonArray.get(i).toString());
            JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
            JSONArray arr = jsonObject.getJSONArray("maze");
            int l = arr.length();
            char[][] maze = new char[l][l];
            for (int j = 0; j < l; j++)
            {
                JSONArray arr2 = (JSONArray) arr.get(j);
                for(int k =0; k<l; k++){
                    String str = (String) arr2.get(k);
                    maze[j][k] = (char) str.charAt(0);
                }
                System.out.println(arr.get(j).getClass().getName());
            }

            Maze.mazes.add(new Maze((int) jsonObject.get("id"),(String) jsonObject.get("title"),(String) jsonObject.get("author"), maze));
        }
        } catch (
                JSONException  e) {
            e.printStackTrace();
        }
    }





}
