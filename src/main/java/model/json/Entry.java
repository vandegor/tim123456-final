package model.json;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entry {
    private int id;
    private Date date;
    private String subject;
    private String content;
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public Entry(String title) {
    }

    public Date getDate() {
        return date;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments = comments == null ? new ArrayList<Comment> () : comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Entry() {

    }
}
