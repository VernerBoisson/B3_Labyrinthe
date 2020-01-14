package Game;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class LevelEditor extends JFrame {
    public LevelEditor() {
        super("Level Editor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize( 1000, 1000);
        this.setLocationRelativeTo(null);

        JPanel CP = (JPanel) this.getContentPane();
        CP.setLayout(new FlowLayout());
        JTable table = new JTable(10, 10);
        table.setBackground(Color.gray);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i)
        int vColIndex = 0;
        TableColum col = table.getColumnModel().getColumn(vColIndex);
        int width = 100;
        col.setPreferredWidth(width);
        table.setBounds(1, 1, 100, 10);
        table.setRowSelectionAllowed(false);
        CP.add (table);
        CP.add( new JButton("Save Labyrinthe"));
        CP.add( new JButton("Add Wall"));
        CP.add( new JButton("Add trap"));

        if (table.isRowSelected()) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            super.setForeground(table.getForeground());
            super.setBackground(table.getBackground());
        }
    }

    public void changecolor() {

    }




    public static void main(String[] args) throws Exception {

    UIManager.setLookAndFeel( new NimbusLookAndFeel() );
    LevelEditor levelEditor = new LevelEditor();
    levelEditor.setVisible( true );
    }
}
