
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import com.mongodb.MongoClient.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class mainClass {
    public static MongoClient mongoClient;
    public static DB db;
    public static DBCollection dbCollection;
    static Scanner in = new Scanner(System.in);
    public static boolean searchByTitle(String title){
        System.out.println("zdxfgh");
        String name =in.next();
        BasicDBObject b = new BasicDBObject("title",title);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()) {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            return true;
        }

        return false;
    }
    public static boolean searchByActorName(String name){
        try {


            BasicDBObject b = new BasicDBObject("cast", name);
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
            FindIterable<Document> cursor = collection.find(b);
            MongoCursor<Document> iterator = cursor.iterator();
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    //System.out.println(iterator.next());
                    Object id = iterator.next().get("_id");
                    //System.out.println(iterator.next().get("_id"));
                    showCommentsForSelectedMovie(id.toString());
                }
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }

    public static boolean searchByDirectorName(String name){
        BasicDBObject b = new BasicDBObject("directors",name);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()){
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            return true;
        }

        return false;
    }

    public static boolean searchByGenre(String genre){
        BasicDBObject b = new BasicDBObject("genres", genre);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()){
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            return true;
        }
        return false;
    }

    public static boolean showCommentsForSelectedMovie(String id){
        try{
        BasicDBObject b = new BasicDBObject("movie_id",new ObjectId(id));
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("comments");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()){
            while(iterator.hasNext()){

                System.out.println(iterator.next());
            }

        }else{

            System.out.println("0 reviews");
        }
        }catch(Exception e){
            e.printStackTrace();

        }

        return false;
    }
    public static boolean sort(FindIterable<Document> cursor){
        BasicDBObject basicDBObject = new BasicDBObject("years", 1);
        MongoCursor<Document> iterator  = cursor.sort(basicDBObject).cursor();
        if(iterator.hasNext()){

            System.out.println(iterator);
        }
        return false;
    }
    public static void main (String[] arg)  {
        searchByActorName("John Ott");



       /* try {
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

           /* cursor = collection.find(doc);
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






/*
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
