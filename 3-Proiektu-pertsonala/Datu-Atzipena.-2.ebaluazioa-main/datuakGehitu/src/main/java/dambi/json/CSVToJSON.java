package dambi.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dambi.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CSVToJSON {
    /**
     * Convierte datos de un archivo CSV a un archivo JSON .
     * Crea objetos JSON con la información de cada jugador, incluyendo un objeto
     * Player y un array de posiciones.
     * El archivo CSV debe tener las columnas necesarias y la primera fila como
     * encabezado.
     * Los archivos de entrada y salida están especificados en las rutas
     * correspondientes.
     * 
     * @throws CsvException Excepción relacionada con la lectura del archivo CSV.
     */
    public static void main(String[] args) throws CsvException {
        try {
            // Ruta del archivo CSV de entrada
            Path csvFilePath = Paths.get(
                "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\csv\\CollegeBasketballPlayers2009-2021.csv");

            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()));

            List<String[]> records = csvReader.readAll();
            String[] columnNames = records.get(0);

            ObjectMapper objectMapper = new ObjectMapper();

            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                List<String> positions = Arrays.asList(record[14].split(","));

                Player player = new Player(
                        record[0], record[1], record[2], record[3], record[4], record[5]);

                ObjectNode playerNode = objectMapper.createObjectNode();

                playerNode.set("player", objectMapper.valueToTree(player));

                ArrayNode positionsArrayNode = playerNode.putArray("positions");
                for (String position : positions) {
                    positionsArrayNode.add(position);
                }

                for (int j = 7; j < columnNames.length; j++) {
                    playerNode.put(columnNames[j], record[j]);
                }

                jsonArray.add(playerNode);
            }

            String jsonString = jsonArray.toString();

            // Escritura de la cadena JSON en un archivo de salida
            FileWriter jsonWriter = new FileWriter(
                    "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\json\\CollegeBasketballPlayers2009-2021.json");
            jsonWriter.write(jsonString);
            jsonWriter.close();

            csvReader.close();
            System.out.println("Archivo JSON generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
