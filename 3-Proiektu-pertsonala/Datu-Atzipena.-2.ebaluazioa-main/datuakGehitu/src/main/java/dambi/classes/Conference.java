package dambi.classes;

import java.util.List;

public class Conference {
    
    private String conferenceName;
    private int teamCount; // Número de equipos en la conferencia
    private List<String> teams; // Lista de equipos en orden alfabético

    // Constructor, getters y setters

    @Override
    public String toString() {
        return "Conference [conferenceName=" + conferenceName + ", teamCount=" + teamCount + ", teams=" + teams + "]";
    }
}
