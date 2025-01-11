package si.fri.rso.gamesession;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Game session API",
                version = "v1",
                contact = @Contact(email = "sc3544@student.uni-lj.si"),
                license = @License(name = "dev"),
                description = "API the game session."
        ),
        servers = @Server(url = "http://localhost:8080/game")
)
@ApplicationPath("/game")
public class GameSessionApplication extends Application{



}
