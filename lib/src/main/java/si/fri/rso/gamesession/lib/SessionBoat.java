package si.fri.rso.gamesession.lib;

public class SessionBoat {

    private Integer id;
    private Integer sessionId;
    private Integer templateBoatId;
    private String title;
    private Integer hpHull;
    private Integer cHpHull;
    private Integer hpSails;
    private Integer cHpSails;
    private Integer hpSteer;
    private Integer cHpSteer;
    private String status;
    private Boolean shared;
    private Integer ac;

    // Boat stats
    private Integer crew;
    private Integer str;
    private Integer dex;
    private Integer cha;
    private Integer intelligence;
    private Integer wis;
    private Integer charisma;

    private String description; // Description



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSessionId() {
        return sessionId;
    }
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
    public Integer getTemplateBoatId() {
        return templateBoatId;
    }
    public void setTemplateBoatId(Integer templateBoatId) {
        this.templateBoatId = templateBoatId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getHpHull() {
        return hpHull;
    }
    public void setHpHull(Integer hpHull) {
        this.hpHull = hpHull;
    }
    public Integer getHpSails() {
        return hpSails;
    }
    public void setHpSails(Integer hpSails) {
        this.hpSails = hpSails;
    }
    public Integer getHpSteer() {
        return hpSteer;
    }
    public void setHpSteer(Integer hpSteer) {
        this.hpSteer = hpSteer;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Boolean getShared() {
        return shared;
    }
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Integer getcHpHull() {
        return cHpHull;
    }

    public void setcHpHull(Integer cHpHull) {
        this.cHpHull = cHpHull;
    }

    public Integer getcHpSails() {
        return cHpSails;
    }

    public void setcHpSails(Integer cHpSails) {
        this.cHpSails = cHpSails;
    }

    public Integer getcHpSteer() {
        return cHpSteer;
    }

    public void setcHpSteer(Integer cHpSteer) {
        this.cHpSteer = cHpSteer;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
    }

    public Integer getStr() {
        return str;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    public Integer getDex() {
        return dex;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public Integer getCha() {
        return cha;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWis() {
        return wis;
    }

    public void setWis(Integer wis) {
        this.wis = wis;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
