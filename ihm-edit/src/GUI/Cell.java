package GUI;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cell {
    private Color color;
    private char type;
    private Shape shape;
    private int locX;
    private int locY;


    public Cell(int x, int y, double width, double height, char type ) {

        this.shape = new Rectangle2D.Double(x, y, width, height);
        this.type = type;
        switch (type) {
            case 'W': this.color = Color.BLACK;
                break;
            case 'S': this.color = Color.GREEN.darker();
                break;
            case 'G': this.color = Color.RED.darker();
                break;
            case 'M': this.color = new Color(88, 41,0);
                break;
            case 'T': this.color = new Color(255, 120,5);
                break;
            default: this.color = Color.WHITE;
        }
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}