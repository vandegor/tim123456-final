package model.json;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entry {
    private int id;
    @JsonIgnore
    private Date dateDate;
    private String subject;
    private String content;
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public Entry(String title) {
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public String getDate() {
        return new SimpleDateFormat ("yyyy-MM-dd").format (dateDate);

    }

    public void setDate(String date) {
        try {
            this.dateDate = new SimpleDateFormat ("yyyy-MM-dd").parse (date);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
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
