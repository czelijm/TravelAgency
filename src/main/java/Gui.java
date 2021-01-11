import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.SimpleFormatter;

public class Gui implements WindowListener {
    private static int x = 0;
    private static int y = 0;
    private static int width = 1280;
    private static int height = 720;
    private static int fontSize = 17;
    private static int rowHeight = 30;
    private static int paneBound = 10;

    private JFrame frame = new JFrame("Offers");
    JTable table;
    DefaultTableModel model = new DefaultTableModel();
    List dbAsList ;

    public Gui(List dbAsList) {
        this.dbAsList = dbAsList;
    }

    public void run(){

        //prepare table and table's model. Table will be displayed on the window
        table = new JTable();
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

        JComboBox languageBox = new JComboBox();
        languageBox.addItem("en");
        languageBox.addItem("pl");
        languageBox.setBounds(paneBound,y+350,200,25);
        languageBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = table.getRowCount()-1; i > -1 ; i--) {
                    model.removeRow(i);
                }
                var item = languageBox.getSelectedItem().toString();
                populateTable(item);
            }
        });
        frame.add(languageBox);

        //turn on frame (window app)
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        populateTable("en");
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
        //populateTable("en");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void populateTable(String languageCodeString){

        Locale localeToTranslate = new Locale(languageCodeString);
//        AtomicReference<String> destinationCountry = new AtomicReference<>();

        try {
            dbAsList.stream().forEach(m->{
                TravelOffer t = (TravelOffer) m;
                Locale localeFromTranslate = new Locale(t.getLocale().split("-")[0]);

                String destinationCountry = LocaleUtility.getCountryTranslateFromLocaleToLocale(t.getDestinationCountry(),localeFromTranslate,localeToTranslate);
                String formattedPrice = LocaleUtility.convertToCurrencyFormat(t.getPrice(),localeFromTranslate,localeToTranslate);
                String formattedPlace = LocaleUtility.convertPlaceLanguage(t.getPlace().toString(),localeFromTranslate,localeToTranslate);

//                Date formattedDepartue = LocaleUtility.formatDateNoHours(t.getDepartue(),"yyyy-MM-dd");
//                Date formattedArrival = LocaleUtility.formatDateNoHours(t.getArrive(),"yyyy-MM-dd");

                Object[] arr = {
                        destinationCountry,
                        LocaleUtility.formatDateNoHours(t.getDepartue(),"yyyy-MM-dd"),
                        LocaleUtility.formatDateNoHours(t.getArrive(),"yyyy-MM-dd"),
                        formattedPlace,
                        formattedPrice,
                        t.getCurrency()
                };
                model.addRow(arr);
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
