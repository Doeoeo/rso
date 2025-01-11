package si.fri.rso.dmcreator.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.dmcreator.lib.Boat;
import si.fri.rso.dmcreator.models.converters.BoatConverter;
import si.fri.rso.dmcreator.models.entities.BoatEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class BoatBean {

    private Logger log = Logger.getLogger(BoatBean.class.getName());

    @Inject
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("BoatBean initialized.");
    }

    // Fetch all boats
    public List<Boat> getBoats() {
        TypedQuery<BoatEntity> query = em.createNamedQuery("BoatEntity.getAll", BoatEntity.class);
        List<BoatEntity> resultList = query.getResultList();
        return resultList.stream().map(BoatConverter::toDto).collect(Collectors.toList());
    }

    // Fetch boats with filters (pagination, etc.)
    public List<Boat> getBoatsFiltered(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, BoatEntity.class, queryParameters).stream()
                .map(BoatConverter::toDto)
                .collect(Collectors.toList());
    }

    // Fetch a single boat by ID
    public Boat getBoat(Integer id) {
        BoatEntity boatEntity = em.find(BoatEntity.class, id);
        if (boatEntity == null) {
            throw new NotFoundException("Boat not found!");
        }
        return BoatConverter.toDto(boatEntity);
    }

    // Create a new boat
    public Boat createBoat(Boat boat) {
        BoatEntity boatEntity = BoatConverter.toEntity(boat);

        try {
            beginTx();
            em.persist(boatEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            throw new InternalServerErrorException("Error creating boat: " + e.getMessage());
        }

        return BoatConverter.toDto(boatEntity);
    }

    // Update an existing boat
    public Boat updateBoat(Integer id, Boat boat) {
        BoatEntity existingEntity = em.find(BoatEntity.class, id);
        if (existingEntity == null) {
            throw new NotFoundException("Boat not found!");
        }

        BoatEntity updatedEntity = BoatConverter.toEntity(boat);

        try {
            beginTx();
            updatedEntity.setId(existingEntity.getId());
            updatedEntity = em.merge(updatedEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            throw new InternalServerErrorException("Error updating boat: " + e.getMessage());
        }

        return BoatConverter.toDto(updatedEntity);
    }

    // Delete a boat by ID
    public boolean deleteBoat(Integer id) {
        BoatEntity boatEntity = em.find(BoatEntity.class, id);
        if (boatEntity != null) {
            try {
                beginTx();
                em.remove(boatEntity);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
                throw new InternalServerErrorException("Error deleting boat: " + e.getMessage());
            }
            return true;
        } else {
            throw new NotFoundException("Boat not found!");
        }
    }

    // Transaction helpers
    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
