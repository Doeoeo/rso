package si.fri.rso.gamesession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import si.fri.rso.gamesession.lib.UpdateRequest;
import si.fri.rso.gamesession.services.GameSessionBean;
import si.fri.rso.gamesession.lib.SessionBoat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ServerEndpoint("/session")
public class GameSessionWebSocket {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Connection opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(@RequestBody(
                            description = "String json of a part of the SessionBoat object.",
                            required = true, content = @Content(
                                schema = @Schema(implementation = String.class))) String message,
                          @RequestBody(
                                  description = "The Session object of the websocket.",
                                  required = true, content = @Content(
                                  schema = @Schema(implementation = Session.class))) Session session) {

        broadcast(message);

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error in session " + session.getId() + ": " + throwable.getMessage());
    }

    // Broadcast method to send messages to all connected clients
    private void broadcast(String message) {
        for (Session clientSession : sessions) { // `sessions` should be a collection of all connected sessions
            if (clientSession.isOpen()) {
                clientSession.getAsyncRemote().sendText(message);
            }
        }
    }

}
