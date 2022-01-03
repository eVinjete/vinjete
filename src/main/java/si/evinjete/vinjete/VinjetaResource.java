package si.evinjete.vinjete;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("vinjete")
public class VinjetaResource {

    private WebTarget wb;

    @Inject
    private VinjetaService vinjetaBean;

    @PersistenceContext
    private EntityManager em;

    @GET
    public Response getAllVinjete() {
        List<Vinjeta> vinjete = vinjetaBean.getVinjete();
        return Response.ok(vinjete).build();
    }

    @GET
    @Path("{vinjetaId}")
    public Response getVinjeta(@PathParam("vinjetaId") String vinjetaId) {
        Vinjeta vinjeta = vinjetaBean.getVinjeta(vinjetaId);
        return vinjeta != null
                ? Response.ok(vinjeta).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewVinjeta(Vinjeta vinjeta) {

        if(vinjeta.getNumberPlate() == null || vinjeta.getClientId() == null || vinjeta.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        vinjeta.setTimestamp(new Date());
        vinjetaBean.addNewVinjeta(vinjeta);

        return Response.ok(vinjeta).build();
    }

    @DELETE
    @Path("{vinjetaId}")
    public Response deleteVinjeta(@PathParam("vinjetaId") String vinjetaId) {
        vinjetaBean.deleteVinjeta(vinjetaId);
        return Response.noContent().build();
    }
}
