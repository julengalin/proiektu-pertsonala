package dambi.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

/**
 * Este programa convierte datos de un archivo CSV que contiene información
 * sobre jugadores de fútbol
 * a un archivo XML. Lee el archivo CSV, procesa la información de cada jugador,
 * y crea un archivo XML
 * organizado con detalles sobre el jugador, incluyendo sus posiciones y otros
 * atributos.
 * El resultado es un archivo XML estructurado que representa la información de
 * los jugadores de manera legible y organizada.
 */
public class CSVToXML {
    public static void main(String[] args) throws CsvException {
        try {
            Path csvFilePath = Paths.get(
                "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\mongoToJSON.json");

            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()));

            List<String[]> records = csvReader.readAll();
            String[] columnNames = records.get(0);

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                List<String> positions = Arrays.asList(record[6].split(","));

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

            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.set("players", jsonArray);

            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = xmlMapper.writeValueAsString(rootNode);

            FileWriter xmlWriter = new FileWriter(
                "C:\\Users\\Julen\\OneDrive\\Escritorio\\proiektu-pertsonala\\3-Proiektu-pertsonala\\Datu-Atzipena.-2.ebaluazioa-main\\data\\mongoToJSON.json");
                xmlWriter.write(xmlString);
            xmlWriter.close();

            csvReader.close();
            System.out.println("Archivo XML generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
