package si.evinjete.vinjete;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.io.IOException;


@Path("upload")
public class VinjeteResource {


    @POST
    @Path("/image")
    @Consumes("image/jpeg")
    public Response uploadImage(InputStream uploadedInputStream) throws IOException {
        String pwd = System.getProperty("user.dir");
        File file = File.createTempFile("tablica", ".jpg");
        copyInputStreamToFile(uploadedInputStream, file);
        String absolutePath = file.toString();
        String numberPlate = "";

        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Absolute path: " + absolutePath);
        System.out.println("Number plate: " + numberPlate);

        return Response.status(200).entity(numberPlate).build();
    }

    @POST
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
    }
}
