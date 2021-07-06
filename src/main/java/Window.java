import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import java.awt.*;
import java.awt.event.*;
import org.bson.*;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class Window extends JFrame{

    JTextField textField;
    JTable table;
    JLabel payment_label;
    JLabel  payment_id_label, Ticket_label, Pay_date_label, CNIC_label,label;
    JComboBox c1, c2;
    private FindIterable<Document> cursor1;// to save the value and parse in the sort button
    private ObjectId objectId;
    public static void main(String[] args) {

        new Window();
    }

    public Window(){
        initialize();
    }

    private void initialize(){
        this.cursor1 = null;
        this.objectId = null;
        setTitle("Movies");
        getContentPane().setBackground(Color.WHITE);
        setSize(1500,1500);
        setLayout(null);

        JLabel pnr_no_label = new JLabel("Search");
        pnr_no_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnr_no_label.setBounds(60, 160, 150, 26);
        add(pnr_no_label);

        textField = new JTextField();
        textField.setBounds(200, 160, 150, 26);
        add(textField);

        table = new JTable();
        table.setBounds(45, 329, 766, 87);
        this.add(table);

        JButton Show = new JButton("SHOW Search");
        Show.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Show.setBackground(Color.BLACK);
        Show.setForeground(Color.WHITE);
        Show.setBounds(200, 210, 150, 26);
        add(Show);

        payment_label = new JLabel("Movies");
        payment_label.setForeground(Color.BLUE);
        payment_label.setFont(new Font("Tahoma", Font.PLAIN, 31));
        payment_label.setBounds(51, 17, 300, 39);
        add(payment_label);

        c1= new JComboBox(new String[]{"movie title", "actor name", "director name", "genre"});
        c1.setBounds(200, 110, 150, 26);
        add(c1);


        setVisible(true);


        Show.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                FindIterable<Document> cursor;

                try {

                    String selectedsearch = c1.getSelectedItem().toString();
                    String data = textField.getText();
                    switch (selectedsearch){
                        case "movie title":
                            cursor1 = searchByTitle(data);
                            break;
                        case "actor name":
                            cursor1 = searchonactor(data);
                            break;
                        case "director name":
                            cursor1 = searchdirector(data);
                            break;
                        case "genre":
                            cursor1 = searchgenre(data);
                            break;
                    }
                    cursor = cursor1;
                    MongoCursor<Document> mongoCursor =cursor.iterator();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    Document document = new Document();
                    Document document1 = new Document();

                    model.setRowCount(0);
                    while(mongoCursor.hasNext()) {
                        document = mongoCursor.next();
                        document1 =(Document) document.get("imdb");// getting the imdb and storing it in another document as it is an embedded document
                        model.addRow(new String[]{

                        document.getString("title"),// getting the value of the title
                        String.valueOf(document.getInteger("year")),
                        String.valueOf(document.getInteger("runtime")),
                        document.getString("plot"),
                        document.getString("type"),
                        document.get("directors").toString(), // getting the whole array of directors and casting them in string form

                        String.valueOf(document1.getDouble("rating")),// now getting the value from the embedded document
                        String.valueOf(document1.getInteger("votes")),
                        document.get("countries").toString(),// same as above in getting the value of directors
                        document.get("genres").toString()// same as for the directors


                        });
                    }
                    table.setModel(model);

                   // table.setModel(DbUtils.resultSetToTableModel());

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });



        JLabel pnr_no_label1 = new JLabel("Sort");
        pnr_no_label1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnr_no_label1.setBounds(660, 160, 150, 26);
        add(pnr_no_label1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(800, 160, 150, 26);
        add(textField1);

        JTable table1 = new JTable();
        table1.setBounds(700, 329, 600, 100);
        add(table1);

        JButton Show1 = new JButton("Sort/filter");
        Show1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Show1.setBackground(Color.BLACK);
        Show1.setForeground(Color.WHITE);
        Show1.setBounds(660, 210, 150, 26);
        add(Show1);

        c2= new JComboBox(new String[]{"Rating ", "Awards", "Year", "Runtime", "Votes"});
        c2.setBounds(600, 110, 150, 26);
        add(c2);


        Show1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                BasicDBObject basicDBObject = new BasicDBObject("imdb.rating",1); // for sorting by imdb raiting
                BasicDBObject basicDBObject1 = new BasicDBObject("imdb.votes",1); // for sort by votes
                BasicDBObject basicDBObject2 = new BasicDBObject("awards.award",1);// for sort in the basis of awards
                MongoCursor<Document> iterator = null;
                String selectedsearch = c2.getSelectedItem().toString();
                try {
                    switch (selectedsearch){
                        case "Rating":
                            iterator = sort(cursor1, basicDBObject);
                            break;
                        case "Votes":
                            iterator = sort(cursor1,basicDBObject1);
                            break;
                        case "Awards":
                            iterator= sort(cursor1,basicDBObject2);
                            break;
                        case "Year":
                            iterator= sortbyyear(cursor1);
                            break;
                        case "Runtime":
                            iterator= sortbyruntime(cursor1);
                            break;
                    }

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    Document document = new Document();
                    Document document1 = new Document();

                    model.setRowCount(0);
                    while(iterator.hasNext()) {
                        document = iterator.next();
                        document1 =(Document) document.get("imdb");// getting the imdb and storing it in another document as it is an embedded document
                        model.addRow(new String[]{

                                document.getString("title"),// getting the value of the title
                                String.valueOf(document.getInteger("year")),
                                String.valueOf(document.getInteger("runtime")),
                                document.getString("plot"),
                                document.getString("type"),
                                document.get("directors").toString(), // getting the whole array of directors and casting them in string form
                                String.valueOf(document1.getDouble("rating")),// now getting the value from the embedded document
                                String.valueOf(document1.getInteger("votes")),
                                document.get("countries").toString(),// same as above in getting the value of directors
                                document.get("genres").toString()// same as for the directors


                        });
                    }
                    table.setModel(model);



                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });


        setVisible(true);

        setSize(1500,1500);
        setLocation(0,0);
        setVisible(true);

    }
    private FindIterable<Document> searchByTitle(String title){
        return mainClass.searchByTitle(title);
    }
    private FindIterable<Document> searchgenre(String genre){
        return mainClass.searchByGenre(genre);
    }
    private FindIterable<Document> searchdirector(String dname){
        return  mainClass.searchByDirectorName(dname);
    }
    private FindIterable<Document> searchonactor(String actorName){
        return mainClass.searchByActorName(actorName);
    }
    private MongoCursor<Document> sortbyyear(FindIterable<Document> cursor){

        return mainClass.sortONYear(cursor);
    }
    private MongoCursor<Document> sortbyruntime(FindIterable<Document> cursor){
        return mainClass.sortByRuntime(cursor);
    }
    private MongoCursor<Document>sort(FindIterable<Document> cursor, BasicDBObject basicDBObject){

        return mainClass.sortByrating(cursor, basicDBObject);
    }
    private String getId(){

        String title = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
        FindIterable<Document> findIterable = mainClass.searchByTitle(title);
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()){
            objectId = cursor.next().getObjectId("_id");
            break;
        }



        return null;
    }

// new Comments(objectId).setVisible(true);     to pass in the comments constuctor
}