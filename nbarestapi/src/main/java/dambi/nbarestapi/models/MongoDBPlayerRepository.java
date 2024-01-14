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

    @PostConstruct
    void init() {
        playerCollection = client.getDatabase("NBA").getCollection("Players", Player.class);
    }

    @Override
    public List<Player> findAll() {
        return playerCollection.find().into(new ArrayList<>());
    }

    @Override
    public Player findByName(String playerName) {
        return playerCollection.find(eq("player_name", playerName)).first();
    }

    @Override
    public Player save(Player player) {
        playerCollection.insertOne(player);
        return player;
    }

    @Override 
    public long delete(String playerName) {
        return playerCollection.deleteMany(eq("player_name", playerName)).getDeletedCount();
    }
}
