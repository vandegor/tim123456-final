package rs;

import model.json.Comment;
import model.json.Entry;

import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("search")
public class BlogSearchService {

    private static final String END_POINT = "http://localhost:8080";
    private static final String RESOURCE = "/lab/rest/blog/entry";


    private List<Entry> getEntries() {
        return ClientBuilder.newClient ()
                .target (END_POINT + RESOURCE)
                .request (MediaType.APPLICATION_JSON)
                .get (new GenericType<List<Entry>> () {
                });
    }

    @GET
    @Path("/entry/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    // – odszukuje i zwraca wpisy zawierające podany ciąg znaków w tytule.
    //Wykorzystuje usługę
    //@GET: /blog/entry aby uzyskać dostęp do wszystkich wpisów.
    public List<Entry> getEntriesByTitle(@PathParam("title") String title) {
        List<Entry> entries = getEntries ();
        for (int i = 0; i < entries.size (); ) {
            if (entries.get (i).getSubject ().contains (title)) i++;
            else {
                entries.remove (i);
            }
        }
        return entries;
    }

    @DELETE
    @Path("/entry/{title}")
    //entry/{title} - odszukuje i usuwa wpisy zawierające podany ciąg znaków w tytule.
    //Wykorzystuje usługę
    //@GET: /blog/entry aby uzyskać dostęp do wszystkich wpisów oraz usługę
    //@DELETE: /blog/entry/{id} aby usunąć wpis.
    public Response deleteEntriesByTitle(@PathParam("title") String title) {
        List<Entry> entries = getEntriesByTitle (title);
        for (Entry entry : entries) {
            ClientBuilder.newClient ()
                    .target (END_POINT + RESOURCE)
                    .path ("" + entry.getId ())
                    .request (MediaType.APPLICATION_JSON)
                    .delete ();
        }
        return Response.ok ().build ();
    }

    @DELETE
    @Path("/comment/{user}")
    //comment/{user} - odszukuje i usuwa komentarze dodane przez wskazanego użytkownika.
    //Wykorzystuje usługę
    //GET: /blog/entry aby uzyskać dostęp do wszystkich wpisów oraz usługę
    //@DELETE: /blog/entry/{id}/comment/{id} aby usunąć komentarz.
    public Response deleteCommentsByUser(@PathParam("user") String user) {
        List<Entry> entries = getEntries ();
        for (Entry entry : entries) {
            for (Comment comment : entry.getComments ()) {
                if (comment.getUser ().equals (user))
                    ClientBuilder.newClient ()
                            .target (END_POINT + RESOURCE)
                            .path ("" + entry.getId ())
                            .path ("comment")
                            .path ("" + comment.getId ())
                            .request (MediaType.APPLICATION_JSON)
                            .delete ();
            }
        }
        return Response.ok ().build ();
    }
}
