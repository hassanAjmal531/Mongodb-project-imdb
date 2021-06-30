
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import com.mongodb.MongoClient.*;

import java.net.UnknownHostException;


public class mainClass {
    public static MongoClient mongoClient;
    public static DB db;
    public static DBCollection dbCollection;


    public static void main (String[] arg)  {
       boolean  flag = false;
        try {
            int count = 0;
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            System.out.println("akdnfgjg");

            BasicDBObject basicDBObject = null;
            MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
            System.out.println(collection.countDocuments());

            FindIterable<Document> cursor = collection.find(new BasicDBObject("title","Blacksmith Scene"));
            MongoCursor<Document> iterator = cursor.iterator();

           //MongoCursor<Document> Cursor = collection.find().cursor().;
            while(iterator.hasNext() && count != 5){


               MongoCursor<Document> doc = (MongoCursor<Document>) iterator.next().get("imdb");
               if(doc.hasNext())
               {
                   System.out.println( doc.next().get("rating"));
               }

                count++;

            }
            mongoClient.close();







        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
