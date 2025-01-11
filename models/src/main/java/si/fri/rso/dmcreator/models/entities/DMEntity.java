package si.fri.rso.dmcreator.models.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "dms")
@NamedQueries({
        @NamedQuery(name = "DMEntity.getAll", query = "SELECT d FROM DMEntity d")
})
public class DMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Instant created;

    @Column(name = "user_id")
    private String userId;

    // Relationships
    @OneToMany(mappedBy = "dmEntity", cascade = CascadeType.ALL)
    private List<BoatEntity> boats; // DM defined boats

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

    public List<BoatEntity> getBoats() { return boats; }
    public void setBoats(List<BoatEntity> boats) { this.boats = boats; }
}
