import com.mongodb.*;

/**
 * Created by ahmed mar3y on 09/04/2018.
 */
public class main {


    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // create these database when insert row
        DB database = mongoClient.getDB("mydb");
        mongoClient.getDatabaseNames().forEach(System.out::println);
        database.createCollection("customers", null);
        database.getCollectionNames().forEach(System.out::println);

// create When Insert into
        // ---------------------- insert ---------------------
        DBCollection collection = database.getCollection("customers");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Shubham");
        document.put("company", "Baeldung");
        collection.insert(document);

        // -------------------- update ------------------------
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Shubham");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "John");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.update(query, updateObject);


        // --------------- select ---------------------
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("company", "Baeldung");
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // -------------- select By Id ----------------------
        BasicDBObject searchQuery1 = new BasicDBObject();
        searchQuery1.put("_id", "5acb6f3ff54b0930300c33cf");
        DBObject one = collection.findOne(searchQuery);
        System.out.println(one.get("name"));


        // ------ delete -----------------------
        BasicDBObject searchDelete = new BasicDBObject();
        searchDelete.put("_id", "5acb6f3ff54b0930300c33cf");
        collection.remove(searchQuery);


    }

}
