package dambi.nbarestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dambi.nbarestapi.models.Conference;
import dambi.nbarestapi.models.Player;
import dambi.nbarestapi.models.MongoDBPlayerRepository;
import dambi.nbarestapi.models.MongoDBConferenceRepository;

@RestController
@RequestMapping("/api")
public class NBAController {

    @Autowired
    private MongoDBPlayerRepository playerRepository;
    @Autowired
    private MongoDBConferenceRepository conferenceRepository;

    @GetMapping("/getconferences")
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAllConferences();
    }

    @GetMapping("/players/top5players")
    public List<Player> getTop5PlayersWithBestStats() {
        return playerRepository.findTop5Players(); // Implementa este método en tu repositorio
    }

    @GetMapping("/getplayers")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/players/{team}/{conference}")
    public List<Player> getAllPlayersByTeamAndConference(@PathVariable String team, @PathVariable String conference) {
        return playerRepository.findAllPlayersByTeamAndConference(team, conference); // Implementa este método en tu repositorio
    }

    @PostMapping("/players")
    public Player addNewPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/players/{playerName}")
    public Player updatePlayerStats(@PathVariable String playerName, @RequestBody Player player) {
        // Implementa la lógica para actualizar las estadísticas del jugador en tu repositorio
        Player existingPlayer = playerRepository.findByName(playerName);
        if (existingPlayer != null) {
            // Actualizar las estadísticas del jugador
            existingPlayer.setPts(player.getPts());
            existingPlayer.setAst(player.getAst());
            existingPlayer.setOreb(player.getOreb());
            existingPlayer.setDreb(player.getDreb());
            // Guardar los cambios en el repositorio
            return playerRepository.save(existingPlayer);
        } else {
            // Si no se encuentra el jugador, devolver null o lanzar una excepción, según la lógica de tu aplicación
            return null;
        }
    }

    @DeleteMapping("/conferences/{conferenceName}")
    public void deleteConference(@PathVariable String conferenceName) {
        conferenceRepository.deleteConference(conferenceName);
    }

    @DeleteMapping("/teams/{teamName}")
    public void deleteTeam(@PathVariable String teamName) {
        playerRepository.delete(teamName);
    }

    @DeleteMapping("/players/{playerName}")
    public void deletePlayer(@PathVariable String playerName) {
        playerRepository.delete(playerName);
    }
}
