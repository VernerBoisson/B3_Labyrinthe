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
        jframe.setSize(1024, 800);
        jframe.setTitle("Maze Resolve");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gridPanel = new GridPanel();
        this.toolBar = new ToolBar(this);

        this.mazeChoicePanel = new MazeChoicePanel(gridPanel);
        jframe.add(toolBar, BorderLayout.NORTH);
        jframe.add(gridPanel, BorderLayout.CENTER);

        jframe.add(mazeChoicePanel, BorderLayout.EAST);

        jframe.setSize(1280, 1080);
        jframe.setVisible(true);
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
