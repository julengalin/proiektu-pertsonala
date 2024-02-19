package dambi;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;


public class App {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBA");
            MongoCollection<Document> collection = database.getCollection("Players");

            Path csvFilePath = Paths.get(
                    "C:\\Users\\mcram\\Desktop\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\CollegeBasketballPlayers2009-2021.csv");

                    try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                        List<String[]> records = csvReader.readAll();
                        System.out.println("Total de registros en el archivo: " + records.size());
                    
                        String[] columnNames = records.get(0);
                    
                        for (int i = 1; i < records.size(); i++) {
                            String[] record = records.get(i);
                            Document document = new Document();
                            for (int j = 0; j < columnNames.length; j++) {
                                document.append(columnNames[j], record[j]);
                            }
                            System.out.println("Sartzen dokumentua MongoDBn:");
                            System.out.println(document.toJson());
                            collection.insertOne(document);
                            System.out.println("Dokumentu sartuta dago MongoDBn.");
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
