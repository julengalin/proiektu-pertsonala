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
    private int gamesPlayed;
    private double minutesPerGame;
    private double dunksMade;
    private double stops;
    private double pts;
    private double oreb;
    private double dreb;
    private double ast;
    private double stl;
    private double blk;

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", team=" + team + ", conference=" + conference + ", gamesPlayed="
                + gamesPlayed + ", minutesPerGame=" + minutesPerGame + ", dunksMade=" + dunksMade + ", stops=" + stops
                + ", pts=" + pts + ", oreb=" + oreb + ", dreb=" + dreb + ", ast=" + ast + ", stl=" + stl + ", blk="
                + blk + "]";
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player(String playerName, String team, String conference, int gamesPlayed, double minutesPerGame,
            double dunksMade, double stops, double pts, double oreb, double dreb, double ast, double stl, double blk) {
        this.playerName = playerName;
        this.team = team;
        this.conference = conference;
        this.gamesPlayed = gamesPlayed;
        this.minutesPerGame = minutesPerGame;
        this.dunksMade = dunksMade;
        this.stops = stops;
        this.pts = pts;
        this.oreb = oreb;
        this.dreb = dreb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
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

    public double getDunksMade() {
        return dunksMade;
    }

    public void setDunksMade(double dunksMade) {
        this.dunksMade = dunksMade;
    }

    public double getStops() {
        return stops;
    }

    public void setStops(double stops) {
        this.stops = stops;
    }

    public double getPts() {
        return pts;
    }

    public void setPts(double pts) {
        this.pts = pts;
    }

    public double getOreb() {
        return oreb;
    }

    public void setOreb(double oreb) {
        this.oreb = oreb;
    }

    public double getDreb() {
        return dreb;
    }

    public void setDreb(double dreb) {
        this.dreb = dreb;
    }

    public double getAst() {
        return ast;
    }

    public void setAst(double ast) {
        this.ast = ast;
    }

    public double getStl() {
        return stl;
    }

    public void setStl(double stl) {
        this.stl = stl;
    }

    public double getBlk() {
        return blk;
    }

    public void setBlk(double blk) {
        this.blk = blk;
    }

    public Player() {

    }
}
    