package rs;

import model.json.Blog;
import model.json.Comment;
import model.json.Entry;
import model.json.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public void postEntry(Entry entry) {
        entry.setId (blog.getEntrySequenceNextVal ());
        blog.getEntries ().put (entry.getId (), entry);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // – zwraca wszystkie wpisy
    public List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<> ();
        for (Map.Entry<Integer, Entry> mapEntry : blog.getEntries ().entrySet ()) {
            entries.add (mapEntry.getValue ());
        }
        return entries;
    }

    @GET
    @Path("/{entryId}")
    @Produces(MediaType.APPLICATION_JSON)
    ///{id} – zwraca wpis o podanym id
    public Entry getEntry(@PathParam("entryId") int entryId) {
        return blog.getEntries ().get (entryId);
    }

    @PUT
    @Path("/{entryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    ///{id} – modyfikuje wpis o podanym id
    public void putEntry(@PathParam("entryId") int entryId, Entry entry) {
        entry.setId (entryId);
        blog.getEntries ().put (entryId, entry);
    }

    @DELETE
    @Path("/{entryId}")
    ///{id} – usuwa wpis o podanym id
    public boolean deleteEntry(@PathParam("entryId") int entryId) {
        return blog.getEntries ().remove (entryId) != null;

    }

    @DELETE
    // – usuwa wszystkie wpisy
    public boolean deleteEntries() {
        blog.getEntries ().clear ();
        return true;
    }

    @PUT
    @Path("/{entryId}/comment")
    @Consumes(MediaType.APPLICATION_JSON)
    ///{id}/comment – dodaje komentarz do wpisu
    public void putEntryComment(@PathParam("entryId") int entryId, Comment comment) {
        Entry entry = blog.getEntries ().get (entryId);
        int nextVal = entry.getComments ().size ();
        comment.setId (nextVal);
        blog.getEntries ().get (entryId).getComments ().add (comment);
    }

    @GET
    @Path("/{entryId}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    ///{id}/comment – wyświetla komentarze do wpisu
    public List<Comment> getEntryComments(@PathParam("entryId") int entryId) {
        Entry entry = blog.getEntries ().get (entryId);
        return entry == null ? null : entry.getComments ();
    }


    @DELETE
    @Path("/{entryId}/comment/{commentId}")
    ///{id}/comment/{id} – usuwa komentarz o podanym id dla wskazanego wpisu
    public boolean deleteEntryComment(@PathParam("entryId") int entryId, @PathParam("commentId") int commentId) {
        Entry entry = blog.getEntries ().get (entryId);
        for (int i = 0; i < entry.getComments ().size (); i++) {
            Comment comment = entry.getComments ().get (i);
            if (comment.getId () == commentId) {
                entry.getComments ().remove (i);
                return true;
            }
        }
        return false;
    }
}