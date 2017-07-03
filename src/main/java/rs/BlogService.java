package rs;

import model.json.Blog;
import model.json.Comment;
import model.json.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("blog/entry")
@ApplicationScoped
public class BlogService implements Serializable {

    @Inject
    @Singleton
    private Blog blog;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postEntry(Entry entry) {
        entry.setId (blog.getEntrySequenceNextVal ());
        blog.getEntries ().put (entry.getId (), entry);
        return Response.ok ().build ();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // – zwraca wszystkie wpisy
    public Response getEntries() {
        List<Entry> entries = new ArrayList<> ();
        for (Map.Entry<Integer, Entry> mapEntry : blog.getEntries ().entrySet ()) {
            entries.add (mapEntry.getValue ());
        }
        if (entries != null && entries.size () > 0) return Response.ok (entries).build ();
        return Response.noContent ().build ();
    }

    @GET
    @Path("/{entryId}")
    @Produces(MediaType.APPLICATION_JSON)
    ///{id} – zwraca wpis o podanym id
    public Response getEntry(@PathParam("entryId") int entryId) {
        Entry entry = blog.getEntries ().get (entryId);
        if (entry != null) return Response.ok (entry).build ();
        return Response.noContent ().build ();
    }

    @PUT
    @Path("/{entryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    ///{id} – modyfikuje wpis o podanym id
    public Response putEntry(@PathParam("entryId") int entryId, Entry entry) {
        entry.setId (entryId);
        blog.getEntries ().put (entryId, entry);
        return Response.ok ().build ();
    }

    @DELETE
    @Path("/{entryId}")
    ///{id} – usuwa wpis o podanym id
    public Response deleteEntry(@PathParam("entryId") int entryId) {
        if (blog.getEntries ().remove (entryId) != null) return Response.ok ().build ();
        return Response.noContent ().build ();

    }

    @DELETE
    // – usuwa wszystkie wpisy
    public Response deleteEntries() {
        blog.getEntries ().clear ();
        return Response.ok ().build ();
    }

    @PUT
    @Path("/{entryId}/comment")
    @Consumes(MediaType.APPLICATION_JSON)
    ///{id}/comment – dodaje komentarz do wpisu
    public Response putEntryComment(@PathParam("entryId") int entryId, Comment comment) {
        Entry entry = blog.getEntries ().get (entryId);
        int nextVal = entry.getComments ().size ();
        comment.setId (nextVal);
        blog.getEntries ().get (entryId).getComments ().add (comment);
        return Response.ok ().build ();
    }

    @GET
    @Path("/{entryId}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    ///{id}/comment – wyświetla komentarze do wpisu
    public Response getEntryComments(@PathParam("entryId") int entryId) {
        Entry entry = blog.getEntries ().get (entryId);
        if (entry != null) return Response.ok (entry.getComments ()).build ();
        return Response.noContent ().build ();

    }


    @DELETE
    @Path("/{entryId}/comment/{commentId}")
    ///{id}/comment/{id} – usuwa komentarz o podanym id dla wskazanego wpisu
    public Response deleteEntryComment(@PathParam("entryId") int entryId, @PathParam("commentId") int commentId) {
        Entry entry = blog.getEntries ().get (entryId);
        for (int i = 0; i < entry.getComments ().size (); i++) {
            Comment comment = entry.getComments ().get (i);
            if (comment.getId () == commentId) {
                entry.getComments ().remove (i);
                return Response.ok ().build ();
            }
        }
        return Response.noContent ().build ();
    }
}