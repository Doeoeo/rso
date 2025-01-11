package si.fri.rso.gamesession.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import si.fri.rso.gamesession.entities.GameSessionEntity;
import si.fri.rso.gamesession.lib.GameSession;
import si.fri.rso.gamesession.lib.SessionBoat;
import si.fri.rso.gamesession.lib.UpdateRequest;
import si.fri.rso.gamesession.services.GameSessionBean;
import si.fri.rso.samples.imagecatalog.lib.ImageMetadata;
import si.fri.rso.utility.JoinCodeGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
@ApplicationScoped
public class GameSessionResource {

    @Inject
    private GameSessionBean gsBean;

    @GET
    @Path("gm/{dmId}")
    public Response getAllGameSessions(@Parameter(description = "DM ID.", required = true)
                                       @PathParam("dmId") Integer dmId) {
        return Response.status(Response.Status.OK).entity(gsBean.getGameSessions(dmId)).build();
    }

    @POST
    public Response createGameSession(@RequestBody(
                                              description = "DTO object with GameSession data.",
                                              required = true, content = @Content(
                                              schema = @Schema(implementation = GameSession.class)))
                                              GameSession gameSession) {

        gameSession = gsBean.createGameSession(gameSession);

        if (gameSession == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(gameSession).build();
    }

    @PUT
    @Path("{gsId}")
    public Response updateGameSession(@Parameter(description = "GameSession ID.", required = true)
                                      @PathParam("gsId") Integer gsId,
                                      @RequestBody(
                                                    description = "DTO object with GameSession data.",
                                                    required = true,
                                                    content = @Content(schema = @Schema(implementation = GameSession.class)))
                                                    GameSession gameSession) {
        gameSession = gsBean.updateGameSession(gsId, gameSession);

        if (gameSession == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(gameSession).build();
    }

    @GET
    @Path("{gsId}")
    public Response getGameSession(@Parameter(description = "GameSession ID.", required = true)
                                   @PathParam("gsId") Integer gsId){
        return Response.status(Response.Status.OK).entity(gsBean.getGameSession(gsId)).build();
    }

    @DELETE
    @Path("{gsId}")
    public Response deleteGameSession(@Parameter(description = "GameSession ID.", required = true)
                                      @PathParam("gsId") Integer gsId){
        return Response.status(Response.Status.OK).entity(gsBean.deleteGameSession(gsId)).build();
    }

    @PUT
    @Path("{gsId}/start")
    public Response startGameSession(@Parameter(description = "GameSession ID.", required = true)
                                     @PathParam("gsId") Integer gsId){
        GameSession gameSession = gsBean.startGameSession(gsId);
        if (gameSession == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(gameSession).build();


    }
    @PUT
    @Path("updateBoat")
    public Response startGameSession(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UpdateRequest updateRequest = objectMapper.readValue(message, UpdateRequest.class);
            SessionBoat updatedSessionBoat = gsBean.updateSessionBoatValue(updateRequest.getBoatId(), updateRequest.getField(), updateRequest.getNewValue());
            if (updatedSessionBoat != null) {
                return Response.ok().entity(updatedSessionBoat).build();
            }

        } catch (JsonProcessingException e) {
            System.out.println("4");

            throw new RuntimeException(e);
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("joinGame/{code}")
    public Response getGameSession(@Parameter(description = "Game Session code.", required = true)
                                   @PathParam("code") String code){
        return Response.status(Response.Status.OK).entity(gsBean.getGameSessionByJoinCode(code)).build();
    }

}
