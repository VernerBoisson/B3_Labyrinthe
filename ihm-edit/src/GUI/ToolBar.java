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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class ToolBar extends JToolBar {
    private MazeGUI gui;

    public ToolBar(MazeGUI gui) {
        this.gui = gui;

        // Save Button
        ImageIcon saveIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/save.png"))); // load the image to a imageIcon
        Image saveImg = saveIcon.getImage(); // transform it
        Image newSaveImg = saveImg.getScaledInstance(40, 40,  Image.SCALE_SMOOTH); // scale it the smooth way
        saveIcon = new ImageIcon(newSaveImg);  // transform it back
        JButton save = new JButton(saveIcon);

        // edit Button
        ImageIcon editIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/edit.png"))); // load the image to a imageIcon
        Image editImg = editIcon.getImage(); // transform it
        Image newEditImg = editImg.getScaledInstance(40, 40,  Image.SCALE_SMOOTH); // scale it the smooth way
        editIcon = new ImageIcon(newEditImg);  // transform it back
        JButton edit = new JButton(editIcon);

        // newMaze Button
        ImageIcon newIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/new.png"))); // load the image to a imageIcon
        Image newImg = newIcon.getImage(); // transform it
        Image newNewImg = newImg.getScaledInstance(40, 40,  Image.SCALE_SMOOTH); // scale it the smooth way
        newIcon = new ImageIcon(newNewImg);  // transform it back
        JButton newMaze = new JButton(newIcon);


        // newPopup Jframe
        JFrame newPopup = new JFrame("New");
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3,1,10,10));
        newPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel labMazeSize = new JLabel("Maze size");
        JButton createMaze =  new JButton("Create");
        JSpinner mazeSize = new JSpinner(new SpinnerNumberModel(11, 5,99,2));

        newMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                newPopup.setVisible(true);
            }
        });

        createMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.getCellPanel().removeAll();
                gui.getToolPanel().removeAll();
                gui.getJframe().remove(gui.getCellPanel());
                gui.getJframe().remove(gui.getToolPanel());
                gui.setCellPanel(new CellPanel((Integer)mazeSize.getValue(), Optional.<Maze>empty()));
                gui.setToolPanel(new ToolPanel(gui.getCellPanel()));
                gui.getCellPanel().setVisible(true);
                gui.getToolPanel().setVisible(true);
                gui.getJframe().add(gui.getCellPanel(), BorderLayout.CENTER);
                gui.getJframe().add(gui.getToolPanel(), BorderLayout.EAST);

                gui.getJframe().setVisible(true);

                newPopup.setVisible(false);
            }
        });
        labMazeSize.setPreferredSize(new Dimension( 300,30));
        mazeSize.setPreferredSize(new Dimension( 300,30));
        newPanel.add(labMazeSize);
        newPanel.add(mazeSize);
        newPanel.add(createMaze);
        newPopup.add(newPanel);
        newPopup.pack();
        newPopup.setLocationRelativeTo(null);


        //Pop up save maze
        JFrame savePopup = new JFrame("Save");
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new GridLayout(6,1, 10,10));
        JLabel labTitle = new JLabel("Maze title");
        JTextField title = new JTextField();
        JLabel labAuthor = new JLabel("Maze creator");
        JTextField author = new JTextField();
        JButton saveMaze =  new JButton("Save");
        JLabel labError = new JLabel("Your maze must have a start and a goal point, please add them to save it !");
        labError.setVisible(false);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (gui.getCellPanel().isEditMaze()){
                    title.setText(gui.getCellPanel().getMaze().getTitle());
                    author.setText(gui.getCellPanel().getMaze().getAuthor());
                }
                else {
                    title.setText("");
                    author.setText("");
                }
                labError.setVisible(false);
                savePopup.setVisible(true);
            }
        });

        saveMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (gui.getCellPanel().isSolvable()) {
                        gui.getCellPanel().save(title.getText(), author.getText());
                        savePopup.setVisible(false);
                        gui.getJframe().remove(gui.getCellPanel());
                        gui.getJframe().remove(gui.getToolPanel());
                        gui.setCellPanel( new CellPanel(15, Optional.empty()));
                        gui.setToolPanel(new ToolPanel(gui.getCellPanel()));
                        gui.getJframe().add(gui.getCellPanel(), BorderLayout.CENTER);
                        gui.getJframe().add(gui.getToolPanel(), BorderLayout.EAST);
                        gui.getJframe().setVisible(true);
                    } else {
                        labError.setVisible(true);
                        savePopup.add(savePanel);
                        savePopup.pack();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        labTitle.setPreferredSize(new Dimension( 300,30));
        title.setPreferredSize(new Dimension( 300,30));
        labAuthor.setPreferredSize(new Dimension( 300,30));
        author.setPreferredSize(new Dimension( 300,30));

        savePanel.add(labTitle);
        savePanel.add(title);
        savePanel.add(labAuthor);
        savePanel.add(author);
        savePanel.add(saveMaze);
        savePanel.add(labError);

        savePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        savePopup.add(savePanel);
        savePopup.pack();
        savePopup.setLocationRelativeTo(null);


        //Pop up edit maze
        JFrame editPopup = new JFrame("Edit");
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(3,1,10,10));
        JLabel labSelectMaze = new JLabel("Select a maze");
        JComboBox selectMaze = new JComboBox();
        JButton openSelectedMaze = new JButton("Open this Maze");

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String response = MyGetRequest();
                ParseResponse(response);
                selectMaze.removeAllItems();
                for(Maze maze : Maze.mazes) {
                    selectMaze.addItem(maze.getId() + " " + maze.getTitle());
                }
                editPopup.setVisible(true);
            }
        });

        openSelectedMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                for(Maze maze : Maze.mazes) {
                    String mazeName = maze.getId() + " " + maze.getTitle();

                    if (mazeName.equals(selectMaze.getSelectedItem().toString())){
                        gui.getJframe().remove(gui.getCellPanel());
                        gui.getJframe().remove(gui.getToolPanel());
                        gui.setCellPanel(new CellPanel(maze.getSchemaMaze().length,Optional.of(maze)));
                        gui.setToolPanel( new ToolPanel(gui.getCellPanel()));
                        gui.getJframe().add(gui.getCellPanel(), BorderLayout.CENTER);
                        gui.getJframe().add(gui.getToolPanel(), BorderLayout.EAST);
                        gui.getJframe().setVisible(true);
                        editPopup.setVisible(false);
                        break;
                    }
                }
            }
        });

        labSelectMaze.setPreferredSize(new Dimension( 300,30));
        selectMaze.setPreferredSize(new Dimension( 300,30));
        editPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        editPanel.add(labSelectMaze);
        editPanel.add(selectMaze);
        editPanel.add(openSelectedMaze);
        editPopup.add(editPanel);
        editPopup.pack();
        editPopup.setLocationRelativeTo(null);


        add(save);
        add(newMaze);
        add(edit);
    }

    public String MyGetRequest()   {
        try {
            URL urlForGetRequest = new URL("http://localhost:8080/");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();

                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }

                in.close();
                return response.toString();
            } else {
                System.out.println("GET NOT WORKED");
            }
            return null;
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ParseResponse(String response)   {
        try {
            JSONArray jsonArray = new JSONArray(response);
            Maze.mazes.removeAll(Maze.mazes);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                JSONArray arr = jsonObject.getJSONArray("maze");
                int len = arr.length();
                char[][] maze = new char[len][len];
                for (int j = 0; j < len; j++)
                {
                    JSONArray arr2 = (JSONArray) arr.get(j);
                    for(int k =0; k<len; k++){
                        String str = (String) arr2.get(k);
                        maze[j][k] = (char) str.charAt(0);
                    }
                }
                Maze.mazes.add(new Maze((int) jsonObject.get("id"),(String) jsonObject.get("title"),(String) jsonObject.get("author"), maze));
            }
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }
}
