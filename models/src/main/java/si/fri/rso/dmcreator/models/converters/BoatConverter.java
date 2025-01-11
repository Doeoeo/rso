package si.fri.rso.dmcreator.models.converters;

import si.fri.rso.dmcreator.lib.Boat;
import si.fri.rso.dmcreator.models.entities.BoatEntity;

public class BoatConverter {

    public static Boat toDto(BoatEntity entity) {
        Boat dto = new Boat();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setWidth(entity.getWidth());
        dto.setHeight(entity.getHeight());
        dto.setCreated(entity.getCreated());
        dto.setCrew(entity.getCrew());
        dto.setStr(entity.getStr());
        dto.setDex(entity.getDex());
        dto.setCha(entity.getCha());
        dto.setIntelligence(entity.getIntelligence());
        dto.setWis(entity.getWis());
        dto.setCharisma(entity.getCharisma());
        dto.setArmorClass(entity.getAc());
        dto.setHpHull(entity.getHpHull());
        dto.setHpSails(entity.getHpSails());
        dto.setHpSteer(entity.getHpSteer());
        return dto;
    }

    public static BoatEntity toEntity(Boat dto) {
        BoatEntity entity = new BoatEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setWidth(dto.getWidth());
        entity.setHeight(dto.getHeight());
        entity.setCreated(dto.getCreated());
        entity.setCrew(dto.getCrew());
        entity.setStr(dto.getStr());
        entity.setDex(dto.getDex());
        entity.setCha(dto.getCha());
        entity.setIntelligence(dto.getIntelligence());
        entity.setWis(dto.getWis());
        entity.setCharisma(dto.getCharisma());
        entity.setAc(dto.getArmorClass());
        entity.setHpHull(dto.getHpHull());
        entity.setHpSails(dto.getHpSails());
        entity.setHpSteer(dto.getHpSteer());
        return entity;
    }

}
