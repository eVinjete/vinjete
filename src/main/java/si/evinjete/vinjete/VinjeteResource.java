package si.evinjete.vinjete;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.io.IOException;

@Path("upload")
public class VinjeteResource {


    @POST
    @Path("/vinjeta/vnesi")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vnesiVinjeto(Vinjeta v) {
        System.out.println(v.getNumberPlate());
        return Response.status(200).entity(123).build();
        /*String numberPlate = v.getNumberPlate();
        System.out.println("Recieved a new vinjeta entry: " + numberPlate);

        return Response.status(200).entity(numberPlate).build();*/
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRequest() {
        System.out.println("Recieved GET request.");
        return Response.status(200).build();
    }

    /*@POST
    @Path("/slika")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response zaznajSlika(int slika) throws IOException {



        return Response.status(200).entity(123).build();
    }

    private static void copyInputStreamToFile(InputStream input, File file) throws IOException {
        // append = false
        try (OutputStream output = new FileOutputStream(file, false)) {
            input.transferTo(output);
        }
    }*/
}
