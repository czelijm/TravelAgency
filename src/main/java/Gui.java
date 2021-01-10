import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Gui {
    static int x = 320;
    static int y = 320;
    static int width = 320;
    static int height = 320;
    static int fontSize = 17;
    static int rowHeight = 30;
    static int paneBound = 10;
    public void run(){

        //prepare table and table's model. Table will be displayed on the window
        JTable table = new JTable();
        Object[] columns = {null,null,null,null,null,null};

        DefaultTableModel model = new DefaultTableModel();

        //initialize app's widow
        JFrame frame = new JFrame("Offers");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setForeground(Color.WHITE);
        frame.setBounds(x,y,width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.WHITE);
        table.setSelectionForeground(Color.RED);
        table.setGridColor(Color.RED);
        table.setFont(new Font(" Tahoma",Font.PLAIN,fontSize));
        table.setRowHeight(rowHeight);
        table.setAutoCreateRowSorter(true);
//        table.setAutoCreateColumnsFromModel(true);

        JScrollPane pane = new JScrollPane(table);
        pane.setBackground(Color.WHITE);
        pane.setForeground(Color.RED);
        pane.setBounds(paneBound,paneBound,300 ,300);
        frame.getContentPane().add(pane);

        Object[] row = new Object[6];



        //turn on frame (window app)
        frame.setVisible(true);

    }
}
