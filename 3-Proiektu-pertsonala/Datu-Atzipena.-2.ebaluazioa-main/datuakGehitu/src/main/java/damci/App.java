package damci;

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

/*Este programa Java carga datos desde un archivo CSV a una base de datos MongoDB. 
Lee el archivo CSV, crea documentos MongoDB a partir de los datos y los inserta en una colección de la base de datos MongoDB. 
El programa utiliza try-with-resources para gestionar la conexión y garantizar la liberación adecuada de recursos.*/
public class App {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBA");
            MongoCollection<Document> collection = database.getCollection("Players");

            Path csvFilePath = Paths.get(
                    "C:\\Users\\galindo.julen\\Desktop\\Datu-atzipena-23-24\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\CollegeBasketballPlayers2009-2021.csv");

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
                            System.out.println("Insertando documento en MongoDB:");
                            System.out.println(document.toJson());
                            collection.insertOne(document);
                            System.out.println("Documento insertado en MongoDB.");
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
