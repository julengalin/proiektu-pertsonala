package dambi.nbarestapi.models;

public class Player {
    
    private String playerName;
    private String team;
    private String conference;
    private int gamesPlayed;
    private double minutesPerGame;
    private int dunksMade;
    private int stops;
    private int pts;

    public Player() {

    }

    public Player(String playerName, String team, String conference, int gamesPlayed, double minutesPerGame,
            int dunksMade, int stops, int pts) {
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
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
    public double getMinutesPerGame() {
        return minutesPerGame;
    }
    public void setMinutesPerGame(double minutesPerGame) {
        this.minutesPerGame = minutesPerGame;
    }
    public int getDunksMade() {
        return dunksMade;
    }
    public void setDunksMade(int dunksMade) {
        this.dunksMade = dunksMade;
    }
    public int getStops() {
        return stops;
    }
    public void setStops(int stops) {
        this.stops = stops;
    }
    public int getPts() {
        return pts;
    }
    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", team=" + team + ", conference=" + conference + ", gamesPlayed="
                + gamesPlayed + ", minutesPerGame=" + minutesPerGame + ", dunksMade=" + dunksMade + ", stops=" + stops
                + ", pts=" + pts + "]";
    }
    
}
