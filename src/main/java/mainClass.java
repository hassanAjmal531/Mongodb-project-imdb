
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import com.mongodb.MongoClient.*;

import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.util.Scanner;


public class mainClass {
    public static MongoClient mongoClient;
    public static DB db;
    public static DBCollection dbCollection;


    public static void main (String[] arg)  {
       boolean  flag = false;
        Scanner in = new Scanner(System.in);
        String args = "";
        try {
            int count = 0;
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            BasicDBObject basicDBObject = null;
            MongoCollection<Document> collection = null;
          //  MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
            MongoCollection<Document> CommentCollection = mongoClient.getDatabase("movie").getCollection("comments");
         //   System.out.println(collection.countDocuments());
            FindIterable<Document> cursor = null;
            FindIterable<Document> CommentsCursor;
            MongoCursor<Document> iterator = null;
            MongoCursor<Document> iterator2;
           //MongoCursor<Document> Cursor = collection.find(new BasicDBObject("title", "Blacksmith Scene")).cursor();
           System.out.println("please select the option\n1.movie title\2.actor name\3.director name\ngenre");
           //args = in.next();
            args = "John Ott";
            Document doc = new Document("cast", args);
         /*  cursor = collection.find(new Document("title", "Blacksmith Scene"));
           String id = String.valueOf(cursor.iterator().next().get("_id"));
           CommentsCursor = CommentCollection.find(new Document("movie_id", id));*/

            cursor = collection.find(doc);
            System.out.println(cursor.iterator().next());
           //System.out.println(CommentsCursor.iterator().next());
           /* cursor = collection.find(new Document("cast", "John Ott"));
            System.out.println(cursor.iterator().next());
            cursor = collection.find(new Document("directors", "William K.L. Dickson"));
            System.out.println(cursor.iterator().next());
            cursor = collection.find(new Document("genres", "Short"));
            System.out.println(cursor.iterator().next());*/
            //cursor = collection.find(new BasicDBObject("title",args));
        /*  switch(args){
                case "1":
                   collection = mongoClient.getDatabase("movie").getCollection("movies");

                    System.out.println("please enter movie name");
                    args = in.next();
                    cursor = collection.find(new Document("cast", args));

                    break;
                case "2":
                    System.out.println("please enter actor name");
                    args = in.next();
                    cursor = collection.find(new Document("directors", "William K.L. Dickson"));
                    break;
                case "3":
                    System.out.println("please enter director name");
                    args = in.next();
                    cursor = collection.find(new Document("genres", "Short"));
                    break;
                case "4":
                    System.out.println("please enter genre name");
                    args = in.next();
                    cursor = collection.find(new BasicDBObject("title",args));
                    break;
                default:
                    System.out.println("please select a correct name");
                    break;

            }
           iterator = cursor.iterator();

            while(iterator.hasNext()) {
                System.out.println(iterator);


            }

            */







        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
