package si.fri.rso.gamesession.converters;

import si.fri.rso.dmcreator.models.entities.BoatEntity;
import si.fri.rso.gamesession.entities.GameSessionEntity;
import si.fri.rso.gamesession.entities.SessionBoatEntity;
import si.fri.rso.gamesession.lib.SessionBoat;

import javax.persistence.EntityManager;

public class SessionBoatConverter {
    public static SessionBoat toDto(SessionBoatEntity entity) {
        SessionBoat dto = new SessionBoat();
        dto.setId(entity.getId());
        dto.setSessionId(entity.getSession().getId());
        dto.setTemplateBoatId(entity.getTemplateBoat().getId());
        dto.setTitle(entity.getTitle());
        dto.setHpHull(entity.getHpHull());
        dto.setHpSails(entity.getHpSails());
        dto.setHpSteer(entity.getHpSteer());
        dto.setcHpHull(entity.getcHpHull());
        dto.setcHpSails(entity.getcHpSails());
        dto.setcHpSteer(entity.getcHpSteer());
        dto.setStatus(entity.getStatus());
        dto.setShared(entity.getShared());
        dto.setAc(entity.getAc());
        dto.setCha(entity.getCha());
        dto.setCharisma(entity.getCharisma());
        dto.setDex(entity.getDex());
        dto.setIntelligence(entity.getIntelligence());
        dto.setStr(entity.getStr());
        dto.setWis(entity.getWis());
        dto.setDescription(entity.getDescription());
        dto.setCrew(entity.getCrew());

        return dto;
    }
    public static SessionBoatEntity toEntity(SessionBoat dto, EntityManager em, GameSessionEntity session) {
        SessionBoatEntity entity = new SessionBoatEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setHpHull(dto.getHpHull());
        entity.setHpSails(dto.getHpSails());
        entity.setHpSteer(dto.getHpSteer());
        entity.setStatus(dto.getStatus());
        entity.setShared(dto.getShared());
        entity.setcHpHull(dto.getcHpHull());
        entity.setcHpSails(dto.getcHpSails());
        entity.setcHpSteer(dto.getcHpSteer());
        entity.setSession(session);
        entity.setAc(dto.getAc());
        entity.setCha(dto.getCha());
        entity.setCharisma(dto.getCharisma());
        entity.setDex(dto.getDex());
        entity.setIntelligence(dto.getIntelligence());
        entity.setStr(dto.getStr());
        entity.setWis(dto.getWis());
        entity.setDescription(dto.getDescription());
        entity.setCrew(dto.getCrew());


        // Reference for template boat
        BoatEntity templateBoat = em.getReference(BoatEntity.class, dto.getTemplateBoatId());
        entity.setTemplateBoat(templateBoat);

        return entity;
    }

}
