package si.fri.rso.dmcreator.lib;

import java.time.Instant;

public class Boat {
    private Integer id;  // Matches entity ID
    private String title; // Name of the boat
    private String description; // Description
    private Integer width; // Width of the boat
    private Integer height; // Height of the boat
    private Instant created; // Timestamp of creation

    // Boat stats
    private Integer crew;
    private Integer str;
    private Integer dex;
    private Integer cha;
    private Integer intelligence;
    private Integer wis;
    private Integer charisma;

    private Integer armorClass; // AC
    private Integer hpHull;
    private Integer hpSails;
    private Integer hpSteer;

    // Getters and Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
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

    public Integer getArmorClass() { return armorClass; }
    public void setArmorClass(Integer armorClass) { this.armorClass = armorClass; }

    public Integer getHpHull() { return hpHull; }
    public void setHpHull(Integer hpHull) { this.hpHull = hpHull; }

    public Integer getHpSails() { return hpSails; }
    public void setHpSails(Integer hpSails) { this.hpSails = hpSails; }

    public Integer getHpSteer() { return hpSteer; }
    public void setHpSteer(Integer hpSteer) { this.hpSteer = hpSteer; }
}
