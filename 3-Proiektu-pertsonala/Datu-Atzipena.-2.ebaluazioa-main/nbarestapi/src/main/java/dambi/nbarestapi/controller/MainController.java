package dambi.nbarestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dambi.nbarestapi.models.Player;
import dambi.nbarestapi.models.PlayerRepository;

/**
 * Klase hau Rest Api-aren kontroladorea da. Klase honetan Rest Api-aren
 * Endpoint-ak eta beraien portaerak
 * zehazten dira. Aplikazio honek hainbat Endpoint ezberdin ditu web eskaera beraz
 * gain. Aplikazioaren funtzionalitatea
 * CRUD baten moduan funtzionatzen du rest zerbitzuak datu basean datuak
 * gehituz, ezabatuz, aktualizatuz eta
 * bistaratuz. Endpoint bakoitza eskaera ezberdin bat egiteaz arduratzen da.
 */
@RestController
@RequestMapping(path = "/players")
public class MainController {
    
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Funtzio honek GET request-ak egiteko mapping-a ezartzen du. Endpoint-a
     * zehazten du eta endpoint-aren
     * funtzionamendua ezartzen du. Gure jokalarien errepositorioko sari guztiak
     * bilatzen ditu eta JSON formatuan
     * bueltatzen ditu automatikoki (XML formatuan ere bueltatu dezake findAll()
     * funtzioak). Kasu honetan
     * Player klaseko Iterable-ak bueltatzen ditu.
     * 
     * @return Iterable<Player> Player motako Iterable bat, honek jokalari guztiak bere gain hartuz
     */
    @GetMapping(path = "/allplayers")
    public @ResponseBody Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Funtzio honek GET request-ak egiteko Endpoint zehatza ezartzen du. Funtzioak
     * String motako
     * parametro bat jasotzen du kasu honetan jokalariaren identifikadorea izanik.
     * Parametro horrekin
     * Jokalarien errepositorioan identifikadore hori duen Jokalaria topatzen du eta
     * bueltatzen du JSON formatuan.
     * Eskaera hau interesgarria da soilik Jokalari bat topatzea nahi baduzu, hala ere
     * kontutan izan behar da
     * bere identifikadorea ezagutzea beharrezkoa dela.
     * 
     * @param String playerName Jokalari zehatzaren identifikadorea String formatuan
     * @return Player Pasatako identifikadorearekin bat etortzen duen jokalaria
     */
    @GetMapping(path = "/findplayer")
    public @ResponseBody Player findPlayer(@RequestParam String playerName) {
        return playerRepository.findByName(playerName);
    }

    /**
     * Funtzio honek POST motako request-ak egiteko Endpoint-a mapeatzen du. Funtzio
     * honen bitartez datu basean
     * jokalari berri bat gehitu daiteke. Horretarako Player motako jokalari bat sortzen da
     * konstruktorean zehaztutako
     * parametroekin, behin jokalaria sortuta jokalarien errepositoriaren gordetzen da.
     * @param playerName Jokalariaren izena String formatuan
     * @param team Jokalariaren ekipoa String formatuan
     * @param conference Jokalariaren konferentzia String formatuan
     * @param gamesPlayed Jokalariaren jolastutako partidak String formatuan
     * @param minutesPerGame Jokalariaren jokatutako minutuak String formatuan
     * @param dunksMade Jokalariaren mateak (ondo eginak) String formatuan
     * @param stops Jokalariak zenbat aldiz ondo defendatu duen kantitatea String formatuan
     * @param pts Jokalariaren puntuak String formatuan
     * @return String The player has been added mezu bat String formatuan
     */
    @PostMapping(value = "/addplayer")
    public @ResponseBody String addPlayer(@RequestParam String playerName, @RequestParam String team, @RequestParam String conference, @RequestParam String gamesPlayed, 
    @RequestParam String minutesPerGame, @RequestParam String dunksMade, @RequestParam String stops, @RequestParam String pts) {

        Player p = new Player();
        p.setPlayerName(playerName);
        p.setTeam(team);
        p.setConference(conference);
        p.setGamesPlayed(gamesPlayed);
        p.setMinutesPerGame(minutesPerGame);
        p.setDunksMade(dunksMade);
        p.setStops(stops);
        p.setPts(pts);
        playerRepository.save(p);
        return "The player has been added";
    }

    /**
     * Funtzio honek DELETE motatako request-ak egiteko Endpoint bat mapeatzen du.
     * Funtzio honek String
     * motako parametro bakarra jasotzen du. Delete motatako request-ek mongo
     * errepositorioan aurkitzen diren
     * delete funtzioei deitzen die eta hauek ezabatu diren jokalari kopurua bueltatzen
     * dute behin hura ezabatzerakoan.
     * Beti bat ezabatuko dute eta ez bada bat jasotzen errore bat gertatu dela
     * jakin daiteke.
     * 
     * @param playerName Jokalariaren izena String formatuan
     * @return String Jokalaria era egokian edo desegokian gorde delaren mezu bat String formatuan
     * 
     */
    @DeleteMapping(path = "/deleteplayer")
    public @ResponseBody String deletePlayer(@RequestParam String playerName) {

        try {
            long deleted = playerRepository.delete(playerName);

            if (deleted > 0) {
                return ("The Player has been deleted");
            } else {
                return ("Error deleting the player, try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting the player, try again";
        }
    }

    /**
     * Funtzio honek PUT motatako request-ak egiteko Endpoint-a mapeatzen du.
     * Funtzio honen bitartez sari
     * zehatz bat eguneratu daiteke. Hau lortzeko POST-ean egiten den moduan
     * jokalariaren parametroak bidaltzen
     * dira request-aren bitartez. Hala ere, kasu honetan jokalariaren identifikadorea
     * ere eskatzen da. Lehenik
     * aldatu nahi den jokalaria topatzen da identifikadorearen bitartez eta geroago
     * pasatu diren parametroak
     * 
     * @return ResponseEntity<Player> 
     */
    @PutMapping(value = "/updateplayer/{player_name}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String playerName, @RequestParam String team, @RequestParam String conference, @RequestParam String gamesPlayed, 
    @RequestParam String minutesPerGame, @RequestParam String dunksMade, @RequestParam String stops, @RequestParam String pts) {
        
        try {

            Player p = playerRepository.findByName(playerName);
            p.setTeam(team);
            p.setConference(conference);
            p.setGamesPlayed(gamesPlayed);
            p.setMinutesPerGame(minutesPerGame);
            p.setDunksMade(dunksMade);
            p.setStops(stops);
            p.setPts(pts);
            playerRepository.save(p);

            return ResponseEntity.ok().build();
        
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
