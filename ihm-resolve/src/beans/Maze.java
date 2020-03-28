package beans;

import java.util.ArrayList;

public class Maze {
    public static ArrayList<Maze> mazes = new ArrayList<Maze>();
    private int id;
    private String title;
    private String author;
    private char[][] schemaMaze;
    private String time;
    private String movement;

    public Maze(int id, String title, String author, char[][] schemaMaze){
        this.id = id;
        this.title = title;
        this.author = author;
        this.schemaMaze = schemaMaze;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public char[][]getSchemaMaze() {
        return schemaMaze;
    }


    @Override
    public String toString() {
        return "Maze{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", schemaMaze=" + schemaMaze +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
