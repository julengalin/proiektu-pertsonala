package dambi.xml.toMongo;

import org.bson.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class XMLToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBA");
            MongoCollection<Document> collection = database.getCollection("Players");

            Path xmlFilePath = Paths.get("C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\CollegeBasketballPlayers2009-2021.csv");

            List<Document> documents = parseXML(xmlFilePath);
            collection.insertMany(documents);

            System.out.println("Documentos insertados en MongoDB.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Document> parseXML(Path xmlFilePath) {
        List<Document> documents = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(xmlFilePath.toFile());

            NodeList playersList = document.getElementsByTagName("players");

            for (int i = 0; i < playersList.getLength(); i++) {
                Node playersNode = playersList.item(i);

                if (playersNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element playersElement = (Element) playersNode;

                    NodeList playerNodes = playersElement.getElementsByTagName("playerName");

                    for (int j = 0; j < playerNodes.getLength(); j++) {
                        Node playerNode = playerNodes.item(j);

                        if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element playerElement = (Element) playerNode;

                            // Crear un nuevo documento MongoDB para cada jugador
                            Document playerDocument = new Document();
                            NodeList playerFields = playerElement.getChildNodes();

                            for (int k = 0; k < playerFields.getLength(); k++) {
                                Node fieldNode = playerFields.item(k);

                                if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element fieldElement = (Element) fieldNode;
                                    String fieldName = fieldElement.getNodeName();
                                    String fieldValue = fieldElement.getTextContent();

                                    // Agregar campos al documento MongoDB
                                    playerDocument.append(fieldName, fieldValue);
                                }
                            }

                            // Agregar el documento del jugador a la lista
                            documents.add(playerDocument);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return documents;
    }
}
