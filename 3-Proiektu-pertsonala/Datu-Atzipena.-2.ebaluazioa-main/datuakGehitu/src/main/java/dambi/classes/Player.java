package dambi.classes;

public class Player {
    
    private int playerCount; // Número total de jugadores
    private String playerName; // Nombre del jugador
    private String lastName; // Apellido del jugador
    private double minutesPerGame;
    private double dunksMade;
    private double stops;
    private double pts;
    private double oreb;
    private double dreb;
    private double ast;
    private double stl;
    private double blk;

    // Constructor, getters y setters

    @Override
    public String toString() {
        return "Player [playerCount=" + playerCount + ", playerName=" + playerName + ", lastName=" + lastName +
                ", minutesPerGame=" + minutesPerGame + ", dunksMade=" + dunksMade + ", stops=" + stops +
                ", pts=" + pts + ", oreb=" + oreb + ", dreb=" + dreb + ", ast=" + ast + ", stl=" + stl +
                ", blk=" + blk + "]";
    }
}
