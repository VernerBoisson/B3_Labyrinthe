import GUI.MazeGUI;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;
import java.net.URLConnection;


public class main {
    public static void main(String[] args)  throws Exception {
        MazeGUI gui = new MazeGUI();

        try {


            URL yahoo = new URL("http://localhost:8080/9");
            HttpURLConnection con = (HttpURLConnection) yahoo.openConnection();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //System.out.println(response.toString());

            JSONObject myresponse = new JSONObject(response.toString());

            System.out.println(myresponse.getJSONArray("maze"));

        } catch (Exception e) {
            System.out.println(e);
        }


        String url = "http://localhost:8080/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "maze=WWWWWWWWWW&maze=WFFFFWFFFW&maze=WFWWFWFWFW&maze=WFWFFWFWFW&maze=WFWFWWFWFW&maze=WFWFFWFWFW&maze=WFWFWFFWFW&maze=WFWFWFWWFW&maze=WSWFFFFWGW&maze=WWWWWWWWWW&title='papa'";

        //Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
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