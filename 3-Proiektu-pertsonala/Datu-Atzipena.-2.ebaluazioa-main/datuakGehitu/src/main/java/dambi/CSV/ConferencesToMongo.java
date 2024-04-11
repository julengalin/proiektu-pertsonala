package dambi.CSV;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;

public class ConferencesToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Conexión a la base de datos "NBA"
            MongoDatabase database = mongoClient.getDatabase("NBA");

            // Colección para las conferencias
            MongoCollection<Document> conferenceCollection = database.getCollection("Conferences");

            Path csvFilePath = Paths.get("C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\csv\\mongoToCSV.csv");

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                // Leer la primera línea para obtener los nombres de las columnas
                String[] columnNames = csvReader.readNext();

                // Inicializar mapa para contar equipos por conferencia (usamos TreeMap para ordenar alfabéticamente)
                Map<String, Integer> conferenceTeamCount = new TreeMap<>();
                // Inicializar mapa para contar jugadores por conferencia (usamos TreeMap para ordenar alfabéticamente)
                Map<String, Integer> conferencePlayersCount = new TreeMap<>();

                // Leer cada línea del archivo CSV y procesarla
                String[] record;
                while ((record = csvReader.readNext()) != null) {
                    String conferenceName = record[3]; // Suponiendo que el nombre de la conferencia está en la cuarta columna
                    conferenceTeamCount.put(conferenceName, conferenceTeamCount.getOrDefault(conferenceName, 0) + 1);
                    conferencePlayersCount.put(conferenceName, conferencePlayersCount.getOrDefault(conferenceName, 0) + Integer.parseInt(record[4])); // Suma el número de jugadores de cada equipo
                }

                // Insertar conferencias en la colección de conferencias
                for (Map.Entry<String, Integer> entry : conferenceTeamCount.entrySet()) {
                    String conferenceName = entry.getKey();
                    int teamCount = entry.getValue();
                    int playersCount = conferencePlayersCount.getOrDefault(conferenceName, 0);

                    Document conferenceDocument = new Document("conferenceName", conferenceName)
                            .append("teamCount", teamCount)
                            .append("playersCount", playersCount);
                    conferenceCollection.insertOne(conferenceDocument);
                    System.out.println("Conferencia insertada en MongoDB.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
