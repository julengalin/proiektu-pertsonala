package dambi.xml.toMongo;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MongoDBToXML {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBA");
            MongoCollection<Document> collection = database.getCollection("Players");

            List<Document> documents = extractData(collection);

            writeToXML(documents);
            System.out.println("Datos exportados a XML con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Document> extractData(MongoCollection<Document> collection) {
        List<Document> documents = new ArrayList<>();

        FindIterable<Document> cursor = collection.find();
        for (Document document : cursor) {
            documents.add(document);
        }

        return documents;
    }

    private static void writeToXML(List<Document> documents) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            StringBuilder xmlStringBuilder = new StringBuilder("<players>");

            for (Document document : documents) {
                xmlStringBuilder.append("<players>");

                // Extraer datos del campo "player"
                Document playerData = (Document) document.get("players");
                for (String key : playerData.keySet()) {
                    Object value = playerData.get(key);
                    xmlStringBuilder.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
                }

                // Extraer datos del campo "positions"
                List<String> positions = (List<String>) document.get("positions");
                for (String position : positions) {
                    xmlStringBuilder.append("<positions>").append(position).append("</positions>");
                }

                // Extraer el resto de los campos
                for (String key : document.keySet()) {
                    if (!key.equals("_id") && !key.equals("player") && !key.equals("positions")) {
                        Object value = document.get(key);
                        xmlStringBuilder.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
                    }
                }

                xmlStringBuilder.append("</player>");
            }

            xmlStringBuilder.append("</players>");

            // Reemplaza con la ruta real
            FileWriter xmlWriter = new FileWriter("C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\xml\\CollegeBasketballPlayers2009-2021.xml");
            xmlWriter.write(xmlStringBuilder.toString());
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}