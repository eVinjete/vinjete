package si.evinjete.vinjete;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Path("/vinjeta/{vinjetaId}")
    public Response getVinjeta(@PathParam("vinjetaId") String vinjetaId) {
        Vinjeta vinjeta = vinjetaBean.getVinjeta(vinjetaId);
        return vinjeta != null
                ? Response.ok(vinjeta).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/uporabnik/{uporabnikId}")
    public Response getVinjetaFromUporabnikId(@PathParam("uporabnikId") String uporabnikId) {
        Integer id;
        try{
            id = Integer.valueOf(uporabnikId);
        }
        catch (Exception e){
            System.out.println("INFO -- /uporabnik/{id} called but given non INTEGER parameter " + uporabnikId);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<Vinjeta> vinjete = vinjetaBean.getVinjetaFromUporabnik(id);
        final GenericEntity<List<Vinjeta>> entity
                = new GenericEntity<List<Vinjeta>>(vinjete) {};

        return vinjete != null
                ? Response.ok(entity).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/tablica/{tablica}")
    public Response getVinjetaFromTablica(@PathParam("tablica") String tablica) {
        List<Vinjeta> vinjeta = vinjetaBean.getVinjetaFromTablica(tablica);
        return vinjeta != null && !vinjeta.isEmpty()
                ? Response.ok(vinjeta.get(0)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewVinjeta(Vinjeta vinjeta) {

        if(vinjeta.getNumberPlate() == null || vinjeta.getClientId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        vinjeta.setTimestamp(new Date());
        vinjetaBean.addNewVinjeta(vinjeta);

        Optional<String> keySms = ConfigurationUtil.getInstance().get("keysms");
        System.out.println(keySms);

        Client client = ClientBuilder.newClient();
        wb = client.target("https://gw.sinhro.si/api/http?username=sinhro&password="+keySms.get()+"&call-number=38641560927&text=Hvala za nakup vinjete "+vinjeta.getNumberPlate());
        Response response = wb.request().get();

        return Response.ok(vinjeta).build();
    }

    @DELETE
    @Path("{vinjetaId}")
    public Response deleteVinjeta(@PathParam("vinjetaId") String vinjetaId) {
        vinjetaBean.deleteVinjeta(vinjetaId);
        return Response.noContent().build();
    }
}
