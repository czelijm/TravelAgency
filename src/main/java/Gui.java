import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Currency;
import java.util.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Locale;

public class Gui implements WindowListener {
    private static int x = 0;
    private static int y = 0;
    private static int width = 1280;
    private static int height = 720;
    private static int fontSize = 17;
    private static int rowHeight = 30;
    private static int paneBound = 10;

    private JFrame frame = new JFrame("Offers");
    DefaultTableModel model = new DefaultTableModel();
    List dbAsList ;

    public Gui(List dbAsList) {
        this.dbAsList = dbAsList;
    }

    public void run(){

        //prepare table and table's model. Table will be displayed on the window
        JTable table = new JTable();
//        Object[] columns = {null,null,null,null,null,null};
        Object[] columns = {"","","","","",""};
        frame.addWindowListener(this);
//        DefaultTableModel model = new DefaultTableModel();

        //initialize app's widow
//        JFrame frame = new JFrame("Offers");
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
        pane.setBounds(paneBound,paneBound,1000 ,300);
        frame.getContentPane().add(pane);

        Object[] row = new Object[6];

        //turn on frame (window app)
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
        try {
            dbAsList.stream().forEach(m->{
                TravelOffer t = (TravelOffer) m;
//                Locale locale = t.getLocale().contains("-")? Locale.forLanguageTag(t.getLocale()): new Locale(t.getLocale());
               // Object[] arr = {t.getDestinationCountry(),t.getDepartue(),t.getArrive(),t.getPlace(),t.getPrice(), Currency.getInstance(locale).getSymbol()};
                Object[] arr = {t.getDestinationCountry(),t.getDepartue(),t.getArrive(),t.getPlace(),t.getPrice(), t.getCurrency()};
                model.addRow(arr);
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}
