package dambi.nbarestapi.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import jakarta.annotation.PostConstruct;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBPlayerRepository implements PlayerRepository {
    
    @Autowired
    private MongoClient client;
    private MongoCollection<Player> playerCollection;

    /**
     * Funtzio honek Mongo datu basea zein den eta datu basean zein kolekzio aukeratu behar duen zehazten du.
     */
    @PostConstruct
    void init() {
        playerCollection = client.getDatabase("NBA").getCollection("Players", Player.class);
    }

    /**
     * Funtzio honek jokalarien kolekzioan aurkitzen diren jokalari guztiak bilatzen ditu eta ArrayList batera 
     * pasatzen ditu.
     * 
     * @return List<Player> Jokalari guztien List bat
     */
    @Override
    public List<Player> findAll() {
        return playerCollection.find().into(new ArrayList<>());
    }

     /**
     * Funtzio honek jokalarien bilaketa bat egiten du, horretarako topatu nahi den jokalariaren
     * identifikadorea pasatzen da. Identifikadorea edukita jokalarien pasatako parametroarekin 
     * bat egiten duen jokalaria topatu eta bueltatzen du. Eq importazio estatikoa erabilita find metodoari
     * filtroa pasatzen zaio, identifikadorea zehazki Mongo-k sariei ematen dio ObjectId-a da beraz 
     * filtroan zehaztu egiten da zein eremuri erreferentzia egiten dion.
     * 
     * @return Player Bilatu den jokalaria
     */
    @Override
    public Player findByName(String playerName) {
        return playerCollection.find(eq("player_name", playerName)).first();
    }

    /**
     * Funtzio honek jokalari berri bat kolekziora gehitzen du. Alde batetik gehitu nahi den jokalaria pasatzen da
     * eta jokalari hori insertOne metodoaren bitartez kolekziora gehitzen da. Azkenik gehitu berri den jokalaria
     * bueltatzen da.
     * 
     * @param player Player klaseko objektu bat
     * @return Player Gehitu berri den Player klaseko objektua
     */
    @Override
    public Player save(Player player) {
        playerCollection.insertOne(player);
        return player;
    }

     /**
     * Funtzio honek parametro bidez pasatzen den identifikadoreakin bat egiten duen jokalaria ezabatzen
     * du. Identifikadore hori Mongo datu basean sari bakoitzari ematen zaion ObjectId-a izango litzateke.
     * Filtro bidez identifikadorea pasatzen da eta bat egiuten duen jokalaria ezabatzen da. GetDeletedCount
     * funtzioaren bitartez ezabatu den kopurua bueltatzen da.
     * 
     * @param playerName Ezabatu nahi den jokalariaren identifikadorea
     * @return long Ezabatu den jokalaria kopurua long formatuan
     */
    @Override 
    public long delete(String playerName) {
        return playerCollection.deleteMany(eq("player_name", playerName)).getDeletedCount();
    }
}
