import GUI.MazeGUI;
import netscape.javascript.JSObject;

import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;
import java.net.URLConnection;


public class main {
    public static void main(String[] args) throws Exception {
        MazeGUI gui = new MazeGUI();

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());

        UIManager.setLookAndFeel ("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        }
    }
}