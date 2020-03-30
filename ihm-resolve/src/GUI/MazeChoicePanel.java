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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MazeChoicePanel extends JScrollPane {

    private GridPanel gridPanel;

    public MazeChoicePanel(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        ImageIcon playIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/play1.png")));
        Image playImg = playIcon.getImage();
        Image newPlayImg = playImg.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(newPlayImg);

        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        JPanel buttonPanel = new JPanel();
        setViewportView(buttonPanel);

        String response = MyGetRequest();
        ParseResponse(response);



        for(Maze maze : Maze.mazes) {

            JButton mazeButton = new JButton(maze.getId()+ " " + maze.getTitle());
            mazeButton.setRolloverIcon(playIcon);


            mazeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String response = MyGetRequest();
                    ParseResponse(response);
                    for(Maze mazee : Maze.mazes) {
                        String mazeName = mazee.getId() + " " + mazee.getTitle();
                        if (mazeName.equals(mazeButton.getText())) {
                            gridPanel.setMaze(mazee);
                            gridPanel.setBoard(mazee.getSchemaMaze());
                            gridPanel.setRunning(false);
                        }
                    }
                }
            });
            mazeButton.setPreferredSize(new Dimension(150 ,70));
            buttonPanel.add(mazeButton);
        }
        buttonPanel.setLayout(new GridLayout(Maze.mazes.size() + 1, 1, 0, 5));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 12));
    }

    public String MyGetRequest()   {
        try {
            URL urlForGetRequest = new URL("http://localhost:8080/");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ParseResponse(String response)   {
        try {
            JSONArray jsonArray = new JSONArray(response);
            Maze.mazes.removeAll(Maze.mazes);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                JSONArray arr = jsonObject.getJSONArray("maze");
                int len = arr.length();
                char[][] maze = new char[len][len];
                for (int j = 0; j < len; j++)
                {
                    JSONArray arr2 = (JSONArray) arr.get(j);
                    for(int k =0; k<len; k++){
                        String str = (String) arr2.get(k);
                        maze[j][k] = (char) str.charAt(0);
                    }
                }
                Maze.mazes.add(new Maze((int) jsonObject.get("id"),(String) jsonObject.get("title"),(String) jsonObject.get("author"), maze));
            }
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }
}
