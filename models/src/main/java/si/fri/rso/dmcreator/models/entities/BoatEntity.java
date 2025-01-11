package si.fri.rso.dmcreator.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "boats")
@NamedQueries(value =
        {
                @NamedQuery(name = "BoatEntity.getAll",
                        query = "SELECT im FROM BoatEntity im")
        })
public class BoatEntity {

    // id ladje
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // ime ladje
    @Column(name = "title")
    private String title;
    // opis ladje
    @Column(name = "description")
    private String description;

    // mere ladje
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;
    @Column(name = "created")
    private Instant created;

    // podatki ladje
    @Column(name = "crew")
    private Integer crew;
    @Column(name = "str")
    private Integer str;
    @Column(name = "dex")
    private Integer dex;
    @Column(name = "cha")
    private Integer cha;
    @Column(name = "intelligence")
    private Integer intelligence;
    @Column(name = "wis")
    private Integer wis;
    @Column(name = "charisma")
    private Integer charisma;
    @Column(name = "armorclass")
    private Integer ac;
    @Column(name = "hpHull")
    private Integer hpHull;
    @Column(name = "hpSails")
    private Integer hpSails;
    @Column(name = "hpSteer")
    private Integer hpSteer;

    @ManyToOne
    @JoinColumn(name = "dm_id") // Foreign key column in DB
    private DMEntity dmEntity;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public Integer getWidth() { return width; }

    public void setWidth(Integer width) { this.width = width; }

    public Integer getHeight() { return height; }

    public void setHeight(Integer height) { this.height = height; }

    public Instant getCreated() { return created; }

    public void setCreated(Instant created) { this.created = created; }

    public Integer getCrew() { return crew; }

    public void setCrew(Integer crew) { this.crew = crew; }

    public Integer getStr() { return str; }

    public void setStr(Integer str) { this.str = str; }

    public Integer getDex() { return dex; }

    public void setDex(Integer dex) { this.dex = dex; }

    public Integer getCha() { return cha; }

    public void setCha(Integer cha) { this.cha = cha; }

    public Integer getIntelligence() { return intelligence; }

    public void setIntelligence(Integer intelligence) { this.intelligence = intelligence; }

    public Integer getWis() { return wis; }

    public void setWis(Integer wis) { this.wis = wis; }

    public Integer getCharisma() { return charisma; }

    public void setCharisma(Integer charisma) { this.charisma = charisma; }

    public Integer getAc() { return ac; }

    public void setAc(Integer ac) { this.ac = ac; }

    public Integer getHpHull() { return hpHull; }

    public void setHpHull(Integer hpHull) { this.hpHull = hpHull; }

    public Integer getHpSails() { return hpSails; }

    public void setHpSails(Integer hpSails) { this.hpSails = hpSails; }

    public Integer getHpSteer() { return hpSteer; }

    public void setHpSteer(Integer hpSteer) { this.hpSteer = hpSteer; }

    public DMEntity getDmEntity() { return dmEntity ; }

    public void setDmEntity(DMEntity dmEntity ) { this.dmEntity  = dmEntity ; }

}