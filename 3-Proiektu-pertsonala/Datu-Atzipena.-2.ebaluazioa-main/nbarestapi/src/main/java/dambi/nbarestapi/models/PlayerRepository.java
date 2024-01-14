package dambi.nbarestapi.models;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Klase honetan jokalariaren errepositorioaren funtzioak zehazten dira. Funtzio hauek
 * beharrezkoak diren eragiketak izango lirateke (CRUD ereduaren eragiketak). 
 * Funtzio hauek geroago Mongo errepositorioan implementatuko dira eta kontroladoretik
 * ere deituko dira. Azkenik, klase hau interfaze moduan eraikitzen da geroago 
 * beste klaseetan implementatu ahal izateko.
 */
@Repository
public interface PlayerRepository {
    
    List<Player> findAll();
    Player findByName(String playerName);
    Player save(Player player);
    long delete(String playerName);

}
