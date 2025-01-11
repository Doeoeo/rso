package si.fri.rso.gamesession.entities;

import si.fri.rso.dmcreator.models.entities.DMEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game_sessions")
@NamedQueries(value =
        {
                @NamedQuery(name = "GameSessionEntity.getAll",
                        query = "SELECT im FROM GameSessionEntity im"),
                @NamedQuery(name = "GameSessionEntity.getByDmId",
                        query = "SELECT gs FROM GameSessionEntity gs WHERE gs.dmEntity.id = :dmId"),
                @NamedQuery(name = "GameSessionEntity.getByJoinCode",
                        query = "SELECT gs FROM GameSessionEntity gs WHERE gs.joinCode = :joinCode")
        })
public class GameSessionEntity {
    // Id igre
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // ime igre
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "dm_id") // Foreign key column in DB
    private DMEntity dmEntity;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<SessionBoatEntity> boats;
    @Column(name = "join_code", unique = true)
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
    public DMEntity getDmEntity() {
        return dmEntity;
    }
    public void setDmEntity(DMEntity dmEntity) {
        this.dmEntity = dmEntity;
    }
    public List<SessionBoatEntity> getBoats() {
        return boats;
    }
    public void setBoats(List<SessionBoatEntity> boats) {
        this.boats = boats;
    }
    public String getJoinCode() {
        return joinCode;
    }
    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
