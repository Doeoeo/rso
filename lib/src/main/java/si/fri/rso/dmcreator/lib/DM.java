package si.fri.rso.dmcreator.lib;

import java.time.Instant;
import java.util.List;

public class DM {

    private Integer id;
    private String name;
    private String description;
    private Instant created;
    private String userId;
    private List<Boat> boats;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Instant getCreated() { return created; }
    public void setCreated(Instant created) { this.created = created; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<Boat> getBoats() { return boats; }
    public void setBoats(List<Boat> boats) { this.boats = boats; }
}
