package dambi.nbarestapi.models;

/**
 * Klase hau Player objektuaren modeloa izango litzateke. PLayer objektua datu basean 
 * dokumentuetan banatutako objektuak mapeatzeko erabiltzen da. Klaseak zortzi atributu
 * ditu, jokalariaren izena, jokalariaren ekipoa, jokalariaren konferentzia, jokalariaren partido jokatuak
 * jokalariaren minutuak partidetan, jokalariaren mateak eginda, jokalariaren geldiketak eta jokalariaren puntuak.
 * Klase honetan objektuaren eraikitzaileak, getter-ak, setter-ak eta toString funtzioa aurkitu daitezke.
 * 
 */
public class Stats {
    
    private String playerName;
    private double minutesPerGame;
    public String getPlayerName() {
        return playerName;
    }



    public Stats(String playerName, double minutesPerGame, double dunksMade, double stops, double offensiveRebounds,
            double defensiveRebounds, double totalRebounds, double assists, double steals, double blocks,
            double points) {
        this.playerName = playerName;
        this.minutesPerGame = minutesPerGame;
        this.dunksMade = dunksMade;
        this.stops = stops;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.totalRebounds = totalRebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.points = points;
    }



    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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



    public double getOffensiveRebounds() {
        return offensiveRebounds;
    }



    public void setOffensiveRebounds(double offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }



    public double getDefensiveRebounds() {
        return defensiveRebounds;
    }



    public void setDefensiveRebounds(double defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }



    public double getTotalRebounds() {
        return totalRebounds;
    }



    public void setTotalRebounds(double totalRebounds) {
        this.totalRebounds = totalRebounds;
    }



    public double getAssists() {
        return assists;
    }



    public void setAssists(double assists) {
        this.assists = assists;
    }



    public double getSteals() {
        return steals;
    }



    public void setSteals(double steals) {
        this.steals = steals;
    }



    public double getBlocks() {
        return blocks;
    }



    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }



    public double getPoints() {
        return points;
    }



    public void setPoints(double points) {
        this.points = points;
    }



    private double dunksMade;
    private double stops;
    private double offensiveRebounds;
    private double defensiveRebounds;
    private double totalRebounds;
    private double assists;
    private double steals;
    private double blocks;
    private double points;

    public Stats() {

    }



    @Override
    public String toString() {
        return "Stats [playerName=" + playerName + ", minutesPerGame=" + minutesPerGame + ", dunksMade=" + dunksMade
                + ", stops=" + stops + ", offensiveRebounds=" + offensiveRebounds + ", defensiveRebounds="
                + defensiveRebounds + ", totalRebounds=" + totalRebounds + ", assists=" + assists + ", steals=" + steals
                + ", blocks=" + blocks + ", points=" + points + "]";
    }


}
