package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static GUI.ToolPanel.GlobalColor;
import static GUI.ToolPanel.GlobalType;

class GridPanel extends JPanel {
    private List<Cell> shapes;

    public GridPanel() {
        int size = 50;
        this.shapes = new ArrayList<Cell>();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                shapes.add(new Cell(i+i*size,j+j*size,size,size));
            }
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Cell item : shapes) {
                    if (item.getShape().contains(e.getPoint())) {

                        if(GlobalType == "S"){
                            for(Cell tmp : shapes){
                                if(tmp.getType() == "S"){
                                    tmp.setType("F");
                                    tmp.setColor(Color.WHITE);
                                }
                            }
                        }
                        if(GlobalType == "G"){
                            for(Cell tmp : shapes){
                                if(tmp.getType() == "G"){
                                    tmp.setType("F");
                                    tmp.setColor(Color.WHITE);
                                }
                            }
                        }
                        item.setColor(GlobalColor);
                        item.setType(GlobalType);
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        for (Cell item : shapes) {
            g2.setColor(item.getColor());
            g2.fill(item.getShape());
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

}
