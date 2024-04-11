package dambi.CSV;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;

public class PlayersToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Conexión a la base de datos "NBA"
            MongoDatabase database = mongoClient.getDatabase("NBA");

            // Colección para los jugadores
            MongoCollection<Document> playerCollection = database.getCollection("Players");

            Path csvFilePath = Paths.get("C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\csv\\mongoToCSV.csv");

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                // Leer la primera línea para obtener los nombres de las columnas
                String[] columnNames = csvReader.readNext(); // Primer registro para los nombres de las columnas

                // Conjunto para almacenar jugadores únicos por nombre completo
                Set<String> uniquePlayers = new HashSet<>();

                // Leer y ordenar los registros por apellido, evitando duplicados
                csvReader.readNext(); // Saltar el primer registro (encabezado)
                csvReader.readAll().stream()
                        .map(Arrays::asList)
                        .sorted(Comparator.comparing(record -> {
                            String[] nameParts = record.get(1).split("\\s+", 2);
                            return nameParts[nameParts.length - 1]; // Ordenar por apellido
                        }))
                        .forEach(record -> {
                            String playerName = record.get(1);

                            // Verificar si el jugador ya ha sido agregado
                            if (!uniquePlayers.contains(playerName)) {
                                Document playerDocument = new Document();

                                // Separar el nombre y el apellido
                                if (playerName.contains(".")) {
                                    // Caso especial como "C.J. Collins"
                                    playerDocument.append("name", playerName);
                                } else {
                                    String[] nameParts = playerName.split("\\s+", 2);
                                    playerDocument.append("first_name", nameParts[0]);
                                    if (nameParts.length > 1) {
                                        playerDocument.append("last_name", nameParts[1]);
                                    }
                                }

                                // Agregar los demás campos al documento del jugador
                                for (int j = 0; j < columnNames.length; j++) {
                                    if (j != 1 && j != 2 && j != 3) { // Evitar agregar la conferencia y el equipo
                                        String columnName = columnNames[j];
                                        String value = record.get(j);
                                        playerDocument.append(columnName, value);
                                    }
                                }

                                // Insertar jugador en la colección de jugadores
                                playerCollection.insertOne(playerDocument);
                                System.out.println("Jugador insertado en MongoDB: " + playerName);

                                // Agregar el jugador al conjunto de jugadores únicos
                                uniquePlayers.add(playerName);
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
