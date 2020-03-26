package beans;

import java.util.ArrayList;

public class Maze {
    private int id;
    private String title;
    private String author;
    private ArrayList<String> schemaMaze;
    private String time;

    public Maze(int id, String title, String author, ArrayList<String> schemaMaze){
        this.id = id;
        this.title = title;
        this.author = author;
        this.schemaMaze = schemaMaze;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
