package beans;

import java.util.ArrayList;

public class Maze {
    public static ArrayList<Maze> mazes = new ArrayList<Maze>();
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

    public static ArrayList<Maze> getMazes() {
        return mazes;
    }

    public static void setMazes(ArrayList<Maze> mazes) {
        Maze.mazes = mazes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getSchemaMaze() {
        return schemaMaze;
    }

    public void setSchemaMaze(ArrayList<String> schemaMaze) {
        this.schemaMaze = schemaMaze;
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
