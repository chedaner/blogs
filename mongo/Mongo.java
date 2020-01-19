package com.hellokoding.springboot.mongo;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class Mongo {
    public static void main(String[] agrs){
        System.out.println("tt");
        MongoDBDaoImpl instance = MongoDBDaoImpl.getInstance("127.0.0.1", 27017);
        //isExits
//        Map<String, Object> isExitsMap = new HashMap<>();
//        isExitsMap.put("name","Tom");
//        boolean exits = instance.isExits("pets", "cats", isExitsMap);
//        System.out.println("exits:"+exits);

        //insert
//        Map<String, Object> insertMap = new HashMap<>();
//        insertMap.put("name","ttt");
//        boolean insert = instance.insert("pets", "cats", insertMap);
//        System.out.println("insert:"+insert);

        //deleteById
//        boolean deleteById = instance.deleteById("pets","cats","5dfc3efaae87631e80aefc91");
//        System.out.println("deleteById:"+deleteById);

        //delete
//        Map<String, Object> deleteMap = new HashMap<>();
//        deleteMap.put("name","ttt");
//        boolean String = instance.delete("pets", "cats", deleteMap);
//        System.out.println("String:"+String);

        //updateOne
//        Map<String, Object> updateOneFilterMapMap = new HashMap<>();
//        updateOneFilterMapMap.put("name","Tom");
//        Map<String, Object> updateOneUpdateMapMap = new HashMap<>();
//        updateOneUpdateMapMap.put("name","ttom");
//        boolean updateOne = instance.updateOne("pets", "cats", updateOneFilterMapMap,updateOneUpdateMapMap);
//        System.out.println("updateOne:"+updateOne);

        //updateById
//        boolean updateById = instance.updateById("pets", "cats", "5e0018ba1c433625709de4a3",new Document("name", "Tom"));
//        System.out.println("updateById:"+updateById);

        //findById
//        Document doc = instance.findById("pets","cats","5e0018ba1c433625709de4a3");
//        System.out.println("doc:"+doc);

        //find
//        List<Document> documents = instance.find("pets", "cats", eq("name", "Tom"));
//        System.out.println(Arrays.toString(documents.toArray()));


//        List<String> pets = instance.getAllCollections("pets");
//        System.out.println(Arrays.asList(pets).toString());


    }
}
