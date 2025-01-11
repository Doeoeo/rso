package si.fri.rso.gamesession.converters;

import si.fri.rso.dmcreator.models.entities.DMEntity;
import si.fri.rso.gamesession.entities.GameSessionEntity;
import si.fri.rso.gamesession.entities.SessionBoatEntity;
import si.fri.rso.gamesession.lib.GameSession;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class GameSessionConverter {
    public static GameSession toDto(GameSessionEntity entity) {
        GameSession dto = new GameSession();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDmId(entity.getDmEntity().getId()); // Simplified DM reference

        if (entity.getBoats() != null) {
            dto.setBoats(entity.getBoats().stream().map(SessionBoatConverter::toDto).collect(Collectors.toList()));
        }
        dto.setJoinCode(entity.getJoinCode());
        return dto;
    }

    public static GameSessionEntity toEntity(GameSession dto, EntityManager em) {
        GameSessionEntity entity = new GameSessionEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setJoinCode(dto.getJoinCode());
        // Poiscemo referenco na DMEntity namesto da dobimo cel entity objekt
        DMEntity dmEntity = em.getReference(DMEntity.class, dto.getDmId());
        entity.setDmEntity(dmEntity);

        List<SessionBoatEntity> boats = dto.getBoats().stream()
                .map(boat -> SessionBoatConverter.toEntity(boat, em, entity)) // Pass session reference
                .collect(Collectors.toList());

        entity.setBoats(boats);

        return entity;
    }
}
