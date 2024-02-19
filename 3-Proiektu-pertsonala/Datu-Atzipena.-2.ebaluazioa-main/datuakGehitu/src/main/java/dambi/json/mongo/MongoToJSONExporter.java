package dambi.json.mongo;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoToJSONExporter {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("NBA");
            MongoCollection<Document> collection = database.getCollection("Players");

            Path jsonFilePath = Paths.get(
                "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\mongoToJSON.json");

            try (FileWriter jsonWriter = new FileWriter(jsonFilePath.toFile())) {
                FindIterable<Document> documents = collection.find();

                // Iniciar el archivo JSON
                jsonWriter.write("[\n");

                // Iterar sobre los documentos
                int documentCount = 0;
                for (Document document : documents) {
                    // Escribir el documento como cadena JSON
                    jsonWriter.write(document.toJson());

                    // Verificar si hay más documentos para evitar la coma al final del último
                    if (++documentCount < collection.countDocuments()) {
                        jsonWriter.write(",\n");
                    }
                }

                // Finalizar el archivo JSON
                jsonWriter.write("\n]");

                System.out.println("Exportación a JSON completada con éxito.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
