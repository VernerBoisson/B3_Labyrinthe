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
    private ToolBar toolBar;
    public MazeGUI(){
        jframe = new JFrame();
        jframe.setSize(new Dimension(1280, 1080));
        jframe.setTitle("Maze Edit");
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cellPanel = new CellPanel(19, Optional.<Maze>empty());
        jframe.add(cellPanel, BorderLayout.CENTER);
        toolPanel = new ToolPanel(cellPanel);
        toolBar = new ToolBar(this);

        toolPanel.setMaximumSize(new Dimension(50 ,50));
        toolPanel.setMinimumSize(new Dimension(50 ,50));
        jframe.add(toolPanel, BorderLayout.EAST);
        jframe.add(toolBar, BorderLayout.NORTH);

        jframe.setVisible(true);

    }




    public JFrame getJframe() {
        return jframe;
    }

    public void setJframe(JFrame jframe) {
        this.jframe = jframe;
    }

    public CellPanel getCellPanel() {
        return cellPanel;
    }

    public void setCellPanel(CellPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public void setToolPanel(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }



}
