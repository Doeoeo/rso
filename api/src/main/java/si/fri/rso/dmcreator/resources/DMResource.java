package si.fri.rso.dmcreator.resources;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import si.fri.rso.dmcreator.lib.Boat;
import si.fri.rso.dmcreator.lib.DM;
import si.fri.rso.dmcreator.models.entities.BoatEntity;
import si.fri.rso.dmcreator.models.entities.DMEntity;
import si.fri.rso.dmcreator.services.beans.BoatBean;
import si.fri.rso.dmcreator.services.beans.DMBean;
import si.fri.rso.samples.imagecatalog.lib.ImageMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dm")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DMResource {

    @Inject
    private DMBean dmBean;
    @Inject
    private BoatBean boatBean;

    @GET
    public Response getAllDMs() {
        return Response.status(Response.Status.OK).entity(dmBean.getDMs()).build();
    }

    @GET
    @Path("/{id}")
    public Response getDM(@PathParam("id") Integer id) {
        DM dm = dmBean.getDM(id);
        if (dm == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dm).build();
    }

    @GET
    @Path("/{id}/boats")
    public Response getBoatsByDM(@PathParam("id") Integer dmId) {
        List<Boat> boats = dmBean.getDM(dmId).getBoats(); // Fetch boats from DM
        if (boats == null || boats.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(boats).build();
    }

    @POST
    public Response createDM(DM dm) {
        DM createdDM = dmBean.createDM(dm);
        return Response.status(Response.Status.CREATED).entity(createdDM).build();
    }

    @PUT
    @Path("boats/{boatId}")
    public Response putImageMetadata(@Parameter(description = "Boat ID.", required = true)
                                     @PathParam("boatId") Integer boatId,
                                     @RequestBody(
                                             description = "DTO object with Boat data.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Boat.class)))
                                     Boat boat){

        boat = boatBean.updateBoat(boatId, boat);

        if (boat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(boat).build();

    }

    @POST
    @Path("/boats")
    public Response createBoat(@RequestBody(
            description = "DTO object with Boat data.",
            required = true, content = @Content(
            schema = @Schema(implementation = Boat.class))) Boat boat) {

        Boat savedBoat = boatBean.createBoat(boat);
        if (savedBoat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(savedBoat).build();
    }
}
