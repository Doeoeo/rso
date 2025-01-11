package si.fri.rso.dmcreator;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "DM Creator API",
                version = "v1",
                contact = @Contact(email = "sc3544@student.uni-lj.si"),
                license = @License(name = "dev"),
                description = "API for managing DM-created elements."
        ),
        servers = @Server(url = "http://localhost:8080/dm-api")
)
@ApplicationPath("/dm-api")
public class DMApplication extends Application {
}
