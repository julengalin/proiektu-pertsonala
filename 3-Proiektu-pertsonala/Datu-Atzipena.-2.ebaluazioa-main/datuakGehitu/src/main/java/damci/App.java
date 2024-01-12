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
            MongoDatabase database = mongoClient.getDatabase("futbolariakDataBase");
            MongoCollection<Document> collection = database.getCollection("futbolariak");

            Path csvFilePath = Paths.get(
                    "C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_players.csv");

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                List<String[]> records = csvReader.readAll();

                String[] columnNames = records.get(0);

                for (int i = 1; i < records.size(); i++) {
                    String[] record = records.get(i);
                    Document document = new Document();
                    for (int j = 0; j < columnNames.length; j++) {
                        document.append(columnNames[j], record[j]);
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
