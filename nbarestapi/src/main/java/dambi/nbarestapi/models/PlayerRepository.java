package dambi.nbarestapi.models;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository {
    
    List<Player> findAll();
    Player findByName(String playerName);
    Player save(Player player);
    long delete(String playerName);

}
