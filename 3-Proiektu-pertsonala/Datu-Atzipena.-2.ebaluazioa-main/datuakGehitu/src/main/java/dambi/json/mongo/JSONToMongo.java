package dambi.json.mongo;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class JSONToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBAS");
            MongoCollection<Document> collection = database.getCollection("Players");

            Path jsonFilePath = Paths.get(
                "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\json\\mongoToJSON.json");

            try (FileReader fileReader = new FileReader(jsonFilePath.toFile())) {
                JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    JsonObject playerObject = jsonObject.getAsJsonObject("player");

                    Document document = new Document();
                    document.append("playerName", new Document("name", playerObject.get("name").getAsString())
                            .append("team", playerObject.get("team").getAsString())
                            .append("conference", playerObject.get("conference").getAsString()));

                    for (String fieldName : jsonObject.keySet()) {
                        if (!fieldName.equals("player")) {
                            switch (fieldName) {
                                    case "gamesPlayed":
                                    String stringValue = jsonObject.getAsJsonPrimitive(fieldName).getAsString();
                                    int intValue;
                                    if (stringValue.isEmpty()) {
                                        intValue = 0;
                                    } else {
                                        intValue = Integer.parseInt(stringValue);
                                    }
                                    document.append(fieldName, intValue);
                                    break;
                                    case "minutesPerGame":
                            case "dunksMade":
                            case "stops":
                            case "pts":
                            case "oreb":
                            case "dreb":
                            case "ast":
                            case "stl":
                            case "blk":
                                    String stringValueDouble = jsonObject.getAsJsonPrimitive(fieldName).getAsString();
                                    double doubleValue;
                                    if (stringValueDouble.isEmpty()) {
                                        doubleValue = 0.0; 
                                    } else {
                                        doubleValue = Double.parseDouble(stringValueDouble);
                                    }
                                    document.append(fieldName, doubleValue);
                                    break;
                                default:
                                    document.append(fieldName, jsonObject.getAsJsonPrimitive(fieldName).getAsString());
                                    break;
                            }
                        }
                    }
                    collection.insertOne(document);

                    System.out.println("Documento insertado en MongoDB:");
                    System.out.println(document.toJson());
                    System.out.println("---------------------------------");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
