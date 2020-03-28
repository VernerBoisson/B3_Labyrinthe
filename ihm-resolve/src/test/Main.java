package test;

import GUI.MazeGUI;
import beans.Maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());

        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

        String response = MyGetRequest();
        ParseResponse(response);
        MazeGUI mazeGUI = new MazeGUI();

        for(Maze maze : Maze.mazes){
            System.out.println(maze.toString());
        }
    }

    public static String MyGetRequest() throws IOException {

        URL urlForGetRequest = new URL("http://localhost:8080/");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection)urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            return response.toString();
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }

    public static void ParseResponse(String response){
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
    }
}
