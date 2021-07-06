import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Comments extends JFrame{

    JTextField textField;
    JTable table;
    JLabel payment_label;
    private ObjectId objectId;
    public static void main(String[] args) {
        //new Comments(o);
    }

    public Comments(ObjectId id){
        initialize();
        this.objectId = id;
    }

    private void initialize(){
        setTitle("Comments");
        getContentPane().setBackground(Color.WHITE);
        setSize(860,486);
        setLayout(null);


        JLabel pnr_no_label = new JLabel("Movie NAme");
        pnr_no_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnr_no_label.setBounds(60, 160, 150, 26);
        add(pnr_no_label);


        textField = new JTextField();
        textField.setBounds(200, 160, 150, 26);
        add(textField);
        table = new JTable();
        table.setBounds(45, 329, 766, 87);
        add(table);


        JButton Show = new JButton("Generate");
        Show.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Show.setBackground(Color.BLACK);
        Show.setForeground(Color.WHITE);
        Show.setBounds(200, 210, 150, 30);
        add(Show);


        Show.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                Document document = new Document();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                MongoCursor<Document> mongoCursor = mainClass.showCommentsForSelectedMovie(objectId.toString());
                while(mongoCursor.hasNext()){
                    document= mongoCursor.next();
                    model.addRow(new String[]{
                            document.getString("name"),
                            document.getString("Text")
                    });


                }
                table.setModel(model);
            }
        });



        payment_label = new JLabel("Reciept");
        payment_label.setForeground(Color.BLUE);
        payment_label.setFont(new Font("Tahoma", Font.PLAIN, 31));
        payment_label.setBounds(51, 17, 300, 39);
        add(payment_label);


        setSize(960,590);
        setLocation(200,0);
        setVisible(true);
    }
}