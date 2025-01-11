package si.fri.rso.gamesession.entities;

import si.fri.rso.dmcreator.models.entities.BoatEntity;

import javax.persistence.*;

@Entity
@Table(name = "session_boat_entity")
@NamedQueries(value =
        {
                @NamedQuery(name = "SessionBoatEntity.getAll",
                        query = "SELECT im FROM SessionBoatEntity im")
        })
public class SessionBoatEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        // Referenca na igro
        @ManyToOne
        @JoinColumn(name = "session_id")
        private GameSessionEntity session;

        // Referenca na lajdo
        @ManyToOne
        @JoinColumn(name = "boat_id")
        private BoatEntity templateBoat;
        @Column(name = "name")
        private String title;
        @Column(name = "hp_hull")
        private Integer hpHull;
        @Column(name = "cHp_hull")
        private Integer cHpHull;
        @Column(name = "hp_sails")
        private Integer hpSails;
        @Column(name = "cHp_sails")
        private Integer cHpSails;
        @Column(name = "hp_steer")
        private Integer hpSteer;
        @Column(name = "cHp_steer")
        private Integer cHpSteer;
        @Column(name = "status")
        private String status;
        @Column(name = "shared")
        private Boolean shared;
        @Column(name = "armorclass")
        private Integer ac;


        @Column(name = "description")
        private String description;
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

        public Integer getId() {
                return id;
        }
        public void setId(Integer id) {
                this.id = id;
        }
        public GameSessionEntity getSession() {
                return session;
        }
        public void setSession(GameSessionEntity session) {
                this.session = session;
        }
        public BoatEntity getTemplateBoat() {
                return templateBoat;
        }
        public void setTemplateBoat(BoatEntity templateBoat) {
                this.templateBoat = templateBoat;
        }
        public String getTitle() {
                return title;
        }
        public void setTitle(String name) {
                this.title = name;
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

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
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
}
