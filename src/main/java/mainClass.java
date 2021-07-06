// Muhammad Hassan Ajmal Reg# FA19-BCS=049   Section BCS-4A
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.*;
import org.bson.types.ObjectId;
import javax.swing.*;

public class mainClass {
    public static MongoClient mongoClient;

    public static FindIterable<Document> searchByTitle(String title){

        try {
            BasicDBObject b = new BasicDBObject("title", title);
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
            FindIterable<Document> cursor = collection.find(b);
            MongoCursor<Document> iterator = cursor.iterator();

            if (iterator.hasNext()) {
                return cursor;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public static FindIterable<Document> searchByActorName(String name){
        try {
            BasicDBObject b = new BasicDBObject("cast", name);
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
            FindIterable<Document> cursor = collection.find(b);
            MongoCursor<Document> iterator = cursor.iterator();
            if (iterator.hasNext()) {
                    Object id = iterator.next().get("_id");
                return cursor;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static FindIterable<Document> searchByDirectorName(String name){
        BasicDBObject b = new BasicDBObject("directors",name);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()){
            return cursor;
            }
        return null;
    }

    public static FindIterable<Document> searchByGenre(String genre){
        BasicDBObject b = new BasicDBObject("genres", genre);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("movies");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        FindIterable<Document> cursor2 = cursor;
        if(iterator.hasNext()){
            return cursor;
            }
        return null;
    }

    public static MongoCursor<Document> showCommentsForSelectedMovie(String id){
        try{
        BasicDBObject b = new BasicDBObject("movie_id",new ObjectId(id));
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoCollection<Document> collection = mongoClient.getDatabase("movie").getCollection("comments");
        FindIterable<Document> cursor = collection.find(b);
        MongoCursor<Document> iterator = cursor.iterator();
        if(iterator.hasNext()){
            return iterator;
        }else{
            JOptionPane.showMessageDialog(null,"0 reviews");
        }
        }catch(Exception e){
            e.printStackTrace();

        }

        return null;
    }
    public static MongoCursor<Document> sortONYear(FindIterable<Document> cursor){
        try {
            BasicDBObject basicDBObject = new BasicDBObject("year", -1);
            FindIterable<Document> cursr = cursor.sort(basicDBObject);
            MongoCursor<Document> iterator = cursr.iterator();

            if (iterator.hasNext()) {

                return iterator;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static MongoCursor<Document> sortByRuntime(FindIterable<Document> cursor){
        try {
            BasicDBObject basicDBObject = new BasicDBObject("runtime", 1);
            FindIterable<Document> cursr = cursor.sort(basicDBObject);
            MongoCursor<Document> iterator = cursr.iterator();

            if (iterator.hasNext()) {

                return iterator;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static MongoCursor<Document> sortByrating(FindIterable<Document> cursor,BasicDBObject bdo){

        try{
            Document b = new Document("genres", "Short");
            FindIterable<Document> cursor2 = cursor.sort(bdo);
            MongoCursor<Document> iterator = cursor2.iterator();

            if(iterator.hasNext()) {
                return iterator;

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public static void main (String[] arg)  {
        new Window().setVisible(true);

    }
}
