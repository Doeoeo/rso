package si.fri.rso.dmcreator.services.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import si.fri.rso.dmcreator.lib.DM;
import si.fri.rso.dmcreator.models.converters.DMConverter;
import si.fri.rso.dmcreator.models.entities.DMEntity;

@RequestScoped
public class DMBean {

    private Logger log = Logger.getLogger(DMBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private DMBean dmBeanProxy;

    private Client httpClient;
    private String baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8081"; // only for demonstration
    }

    public List<DM> getDMs() {

        TypedQuery<DMEntity> query = em.createNamedQuery(
                "DMEntity.getAll", DMEntity.class);

        List<DMEntity> resultList = query.getResultList();

        return resultList.stream().map(DMConverter::toDto).collect(Collectors.toList());

    }

    public DM getDM(Integer id) {

        DMEntity dmEntity = em.find(DMEntity.class, id);

        if (dmEntity == null) {
            throw new NotFoundException();
        }

        return DMConverter.toDto(dmEntity);
    }

    public DM createDM(DM dm) {

        DMEntity dmEntity = DMConverter.toEntity(dm);

        try {
            beginTx();
            em.persist(dmEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (dmEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return DMConverter.toDto(dmEntity);
    }

    public DM updateDM(Integer id, DM dm) {

        DMEntity existingEntity = em.find(DMEntity.class, id);

        if (existingEntity == null) {
            return null;
        }

        DMEntity updatedDMEntity = DMConverter.toEntity(dm);

        try {
            beginTx();
            updatedDMEntity.setId(existingEntity.getId());
            updatedDMEntity = em.merge(updatedDMEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return DMConverter.toDto(updatedDMEntity);
    }

    public boolean deleteDM(Integer id) {

        DMEntity dmEntity = em.find(DMEntity.class, id);

        if (dmEntity != null) {
            try {
                beginTx();
                em.remove(dmEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

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
