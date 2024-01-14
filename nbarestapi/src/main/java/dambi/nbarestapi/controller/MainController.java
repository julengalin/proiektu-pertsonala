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

@RestController
@RequestMapping(path = "/players")
public class MainController {
    
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(path = "/allplayers")
    public @ResponseBody Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping(path = "/findplayer")
    public @ResponseBody Player findPlayer(@RequestParam String playerName) {
        return playerRepository.findByName(playerName);
    }

    @PostMapping(value = "/addplayer")
    public @ResponseBody String addPlayer(@RequestParam String playerName, @RequestParam String team, @RequestParam String conference, @RequestParam int gamesPlayed, 
    @RequestParam double minutesPerGame, @RequestParam int dunksMade, @RequestParam int stops, @RequestParam int pts) {

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

    @PutMapping(value = "/updateplayer/{player_name}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String playerName, @RequestParam String team, @RequestParam String conference, @RequestParam int gamesPlayed, 
    @RequestParam double minutesPerGame, @RequestParam int dunksMade, @RequestParam int stops, @RequestParam int pts) {
        
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
