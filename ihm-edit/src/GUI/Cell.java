package GUI;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cell {
    private Color color;
    private String type;
    private Shape shape;


    public Cell( int x, int y, int width,int height) {
        //Pourquoi super ?
        super();
        this.shape = new Rectangle2D.Double(x, y, width, height);
        this.color = Color.WHITE;
        this.type = "F";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}