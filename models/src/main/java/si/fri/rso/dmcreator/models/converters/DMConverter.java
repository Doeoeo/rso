package si.fri.rso.dmcreator.models.converters;

import si.fri.rso.dmcreator.lib.DM;
import si.fri.rso.dmcreator.models.entities.DMEntity;
import si.fri.rso.dmcreator.lib.Boat;
import si.fri.rso.dmcreator.models.converters.BoatConverter;

import java.util.stream.Collectors;

public class DMConverter {

    // Convert DMEntity to DM DTO
    public static DM toDto(DMEntity entity) {
        DM dto = new DM();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreated(entity.getCreated());
        dto.setUserId(entity.getUserId());

        // Convert related boats
        if (entity.getBoats() != null) {
            dto.setBoats(entity.getBoats().stream()
                    .map(BoatConverter::toDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    // Convert DM DTO to DMEntity
    public static DMEntity toEntity(DM dto) {
        DMEntity entity = new DMEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCreated(dto.getCreated());
        entity.setUserId(dto.getUserId());

        // Convert related boats
        if (dto.getBoats() != null) {
            entity.setBoats(dto.getBoats().stream()
                    .map(BoatConverter::toEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }
}
