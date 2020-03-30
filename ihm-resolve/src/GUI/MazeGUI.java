package GUI;

import javax.swing.*;
import java.awt.*;

public class MazeGUI {

    private JFrame jframe;
    private GridPanel gridPanel;
    private JToolBar toolBar;
    private MazeChoicePanel mazeChoicePanel;

    public MazeGUI(){
        this.jframe = new JFrame();
        this.jframe.setSize(1024, 800);
        this.jframe.setTitle("Maze Resolve");
        this.jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gridPanel = new GridPanel();
        this.mazeChoicePanel = new MazeChoicePanel(gridPanel);
        this.toolBar = new ToolBar(this);
        this.jframe.add(this.toolBar, BorderLayout.NORTH);
        this.jframe.add(this.gridPanel, BorderLayout.CENTER);
        this.jframe.add(this.mazeChoicePanel, BorderLayout.EAST);
        this.jframe.setSize(1280, 1080);
        this.jframe.setVisible(true);
    }

    public JFrame getJframe() {
        return jframe;
    }

    public void setJframe(JFrame jframe) {
        this.jframe = jframe;
    }

    public GridPanel getGridPanel() {
        return gridPanel;
    }

    public void setGridPanel(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public MazeChoicePanel getMazeChoicePanel() {
        return mazeChoicePanel;
    }

    public void setMazeChoicePanel(MazeChoicePanel mazeChoicePanel) {
        this.mazeChoicePanel = mazeChoicePanel;
    }

}
