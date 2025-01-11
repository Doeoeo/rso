package si.fri.rso.gamesession.services;


import si.fri.rso.dmcreator.models.entities.DMEntity;
import si.fri.rso.gamesession.converters.GameSessionConverter;
import si.fri.rso.gamesession.converters.SessionBoatConverter;
import si.fri.rso.gamesession.entities.GameSessionEntity;
import si.fri.rso.gamesession.entities.SessionBoatEntity;
import si.fri.rso.gamesession.lib.GameSession;
import si.fri.rso.gamesession.lib.SessionBoat;
import si.fri.rso.utility.JoinCodeGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class GameSessionBean {
    private Logger log = Logger.getLogger(GameSessionBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private GameSessionBean gameSessionBean;

    private Client httpClient;
    private String baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8081"; // only for demonstration
    }

    // Vrne vse igre, ki pripadajo nekemu dmu
    public List<GameSession> getGameSessions(Integer id){
        TypedQuery<GameSessionEntity> query = em.createNamedQuery(
                "GameSessionEntity.getByDmId", GameSessionEntity.class);
        query.setParameter("dmId", id);

        List<GameSessionEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.stream().map(GameSessionConverter::toDto).collect(Collectors.toList());

    }

    // Vrne specificno igro
    public GameSession getGameSession(Integer id){
        GameSessionEntity existingEntity = em.find(GameSessionEntity.class, id);
        if (existingEntity == null) {
            return null;
        }
        return GameSessionConverter.toDto(existingEntity);
    }

    // Vrne igro glede na geslo
    public GameSession getGameSessionByJoinCode(String joinCode){
        TypedQuery<GameSessionEntity> query = em.createNamedQuery(
                "GameSessionEntity.getByJoinCode", GameSessionEntity.class);
        query.setParameter("joinCode", joinCode);
        List<GameSessionEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.stream().map(GameSessionConverter::toDto).findFirst().get();
    }

    // Zazene igro s tem, da ji doda geslo
    public  GameSession startGameSession(Integer id){
        GameSessionEntity existingEntity = em.find(GameSessionEntity.class, id);
        if (existingEntity == null) {
            return null;
        }

        String joinCode = JoinCodeGenerator.generateCode();
        while (isJoinCodeValid(joinCode)) {
            joinCode = JoinCodeGenerator.generateCode();
        }

        try {
            beginTx();
            existingEntity.setJoinCode(joinCode);
            existingEntity = em.merge(existingEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return GameSessionConverter.toDto(existingEntity);

    }

    private boolean isJoinCodeValid(String joinCode){
        TypedQuery<GameSessionEntity> query = em.createNamedQuery(
                "GameSessionEntity.getByJoinCode", GameSessionEntity.class);
        query.setParameter("joinCode", joinCode);
        return !query.getResultList().isEmpty();
    }

    public SessionBoat updateSessionBoatValue(Integer id, String field, String newValue){
        SessionBoatEntity sessionBoatEntity = em.find(SessionBoatEntity.class, id);
        if (sessionBoatEntity == null) {
            return null;
        }

        try {
            beginTx();
            switch (field) {
                case "cHpHull":
                    sessionBoatEntity.setcHpHull(Integer.parseInt(newValue));
                    break;
                case "cHpSails":
                    sessionBoatEntity.setcHpSails(Integer.parseInt(newValue));
                    break;
                case "cHpSteer":
                    sessionBoatEntity.setcHpSteer(Integer.parseInt(newValue));
                    break;
                default:
                    return null;
            }
            sessionBoatEntity = em.merge(sessionBoatEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }
        return SessionBoatConverter.toDto(sessionBoatEntity);
    }

    public GameSession createGameSession(GameSession gs) {

        GameSessionEntity gameSessionEntity = GameSessionConverter.toEntity(gs, em);

        try {
            beginTx();
            em.persist(gameSessionEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (gameSessionEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return GameSessionConverter.toDto(gameSessionEntity);
    }

    public GameSession updateGameSession(Integer id, GameSession gs) {
        GameSessionEntity existingEntity = em.find(GameSessionEntity.class, id);

        if (existingEntity == null) {
            return null;
        }

        GameSessionEntity updatedEntity = GameSessionConverter.toEntity(gs, em);

        try {
            beginTx();
            updatedEntity.setId(existingEntity.getId());
            updatedEntity = em.merge(updatedEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return GameSessionConverter.toDto(updatedEntity);
    }

    public boolean deleteGameSession(Integer id) {

        GameSessionEntity gameSessionEntity = em.find(GameSessionEntity.class, id);

        if (gameSessionEntity != null) {
            try {
                beginTx();
                em.remove(gameSessionEntity);
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
