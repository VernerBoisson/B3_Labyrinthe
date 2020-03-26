import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;
import java.net.URLConnection;
import java.util.ArrayList;


public class MazeGUI {
	
	   public static void main (String [] args)  throws Exception {
			JFrame world = new JFrame();
			world.setSize(1024, 800);
			world.setTitle("Maze");
			//     this.setSize(1024,800);
			MazePanel p = new MazePanel();
			p.setLayout(new GridLayout(p.board.length, 31, 3, 3));
			world.setContentPane(p);


			world.setVisible(true);
			Thread gameThread = new Thread(p);
			gameThread.start();


//			try {
//				URL yahoo = new URL("http://localhost:8080/40");
//				HttpURLConnection con = (HttpURLConnection) yahoo.openConnection();
//				BufferedReader in = new BufferedReader(
//					   new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//				   response.append(inputLine);
//				}
//				in.close();
//
//				JSONObject myresponse=new JSONObject(response.toString());
//
//				ArrayList<String> listdata = new ArrayList<String>();
//				char [][] mmama = new char[10][10];
//
//				JSONArray jArray = myresponse.getJSONArray("maze");
//				if (jArray != null) {
//					for (int i = 0; i < jArray.length(); i++){
//
//						char tab[] = jArray.getString(i).toCharArray();
//
//						for (int j = 0; j < jArray.length(); j++ ) {
//							mmama[i][j] = tab[j];
//						}
//						listdata.add(jArray.getString(i));
//					}
//				}
//
//				//public char [][] board = {"eee".toCharArray(),"oooo".toCharArray()};
//
//				System.out.println(myresponse.getJSONArray("maze"));
//				String mama = "mama";
//				System.out.println(mama + mmama[1][1]);
//
//			} catch (Exception e){
//				System.out.println(e);
//			}

	   }
}
