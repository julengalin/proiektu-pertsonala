package dambi.nbarestapi.models;

/**
 * Klase hau Player objektuaren modeloa izango litzateke. PLayer objektua datu basean 
 * dokumentuetan banatutako objektuak mapeatzeko erabiltzen da. Klaseak zortzi atributu
 * ditu, jokalariaren izena, jokalariaren ekipoa, jokalariaren konferentzia, jokalariaren partido jokatuak
 * jokalariaren minutuak partidetan, jokalariaren mateak eginda, jokalariaren geldiketak eta jokalariaren puntuak.
 * Klase honetan objektuaren eraikitzaileak, getter-ak, setter-ak eta toString funtzioa aurkitu daitezke.
 * 
 */
public class Player {
    
    private String playerName;
    private String team;
    private String conference;
    private String gamesPlayed;
    private String minutesPerGame;
    private String dunksMade;
    private String stops;
    private String pts;

    public Player() {

    }

    public Player(String playerName, String team, String conference, String gamesPlayed, String minutesPerGame,
            String dunksMade, String stops, String pts) {
        this.playerName = playerName;
        this.team = team;
        this.conference = conference;
        this.gamesPlayed = gamesPlayed;
        this.minutesPerGame = minutesPerGame;
        this.dunksMade = dunksMade;
        this.stops = stops;
        this.pts = pts;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getMinutesPerGame() {
        return minutesPerGame;
    }

    public void setMinutesPerGame(String minutesPerGame) {
        this.minutesPerGame = minutesPerGame;
    }

    public String getDunksMade() {
        return dunksMade;
    }

    public void setDunksMade(String dunksMade) {
        this.dunksMade = dunksMade;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", team=" + team + ", conference=" + conference + ", gamesPlayed="
                + gamesPlayed + ", minutesPerGame=" + minutesPerGame + ", dunksMade=" + dunksMade + ", stops=" + stops
                + ", pts=" + pts + "]";
    }

}
