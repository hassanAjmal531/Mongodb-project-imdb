import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;


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
public class test {
    public static void main(String arg[]){
        try{
        Document b = new Document("genres", "Short");

       BasicDBObject basicDBObject = new BasicDBObject("imdb.rating","6.7");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);

        cursor = cursor.sort(new BasicDBObject("year", -1));



        MongoCursor<Document> iterator = cursor.iterator();
       FindIterable<Document> cursor2 = cursor.sort(basicDBObject);
        MongoCursor<Document> iterator2 = cursor2.iterator();

        if(iterator.hasNext()) {
            //sort(cu);
            //sortByRuntime(cursor2);

            while (iterator.hasNext()) {
                System.out.println(iterator.next().get("imdb.rating"));
            }



        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static MongoCursor<Document> sortByrating(FindIterable<Document> cursor){

        BasicDBObject basicDBObject = new BasicDBObject("imdb.rating",1); // for sorting by imdb raiting
        BasicDBObject basicDBObject1 = new BasicDBObject("imdb.votes",1); // for sort by votes
        BasicDBObject basicDBObject2 = new BasicDBObject("awards.award",1);// for sort in the basis of awards
        try{
            Document b = new Document("genres", "Short");
            FindIterable<Document> cursor2 = cursor.sort(basicDBObject);
            MongoCursor<Document> iterator = cursor2.iterator();

            if(iterator.hasNext()) {
                return iterator;

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
 /*   public void test(){
      /*  // code can be placed in the action lister where the fucntion binds
       // MongoCursor<Document> cursor = sortByrating(/* pass the cursor in here);//// function will return cursor,,,, paramters/ args nee to be passed
        Document document = new Document();
        Document document1 = new Document();

        while(cursor.hasNext()){
            // Store the values in the varible or directly set in the Jtable
            document = cursor.next();
            document.getString("title");// getting the value of the title
            document.getInteger("year");
            document.getInteger("runtime");
            document.getString("plot");
            document.getString("type");
            document.get("directors").toString(); // getting the whole array of directors and casting them in string form
            document1 =(Document) document.get("imbd");// getting the imdb and storing it in another document as it is an embedded document
            document1.getInteger("rating");// now getting the value from the embedded document
            document1.getInteger("votes");
            document.get("countries").toString();// same as above in getting the value of directors
            document.get("genres").toString();// same as for the directors

        }

    }*/
}