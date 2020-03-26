package test;

import GUI.MazeGUI;
import beans.Maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

public class Main {
    public static ArrayList<Maze> mazes = new ArrayList<Maze>();

    public static void main(String[] args) {
	// write your code here
        MazeGUI mazeGUI = new MazeGUI();
        try{
            String response = MyGetRequest();
            //System.out.println(response);
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                System.out.println(jsonArray.get(i).toString());
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                ArrayList<String> maze = new ArrayList<String>();

                JSONArray arr = jsonObject.getJSONArray("maze");
                for (int j = 0; j < arr.length(); j++)
                {
                    maze.add(arr.get(j).toString());
                }

                mazes.add(new Maze((int) jsonObject.get("id"),(String) jsonObject.get("title"),(String) jsonObject.get("author"), maze));
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        for(Maze maze : mazes){
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
                StringBuffer append = response.append(readLine);
            }

            in.close();
            return response.toString();
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }
}
