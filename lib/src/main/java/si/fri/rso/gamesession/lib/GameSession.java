package si.fri.rso.gamesession.lib;

import java.util.List;

public class GameSession {
    private Integer id; // Id igre
    private String title;
    private Integer dmId;
    private List<SessionBoat> boats;
    private String joinCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDmId() {
        return dmId;
    }

    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }

    public List<SessionBoat> getBoats() {
        return boats;
    }

    public void setBoats(List<SessionBoat> boats) {
        this.boats = boats;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
