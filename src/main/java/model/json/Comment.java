package model.json;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    int id;
    @JsonIgnore
    Date dateDate;
    String user;
    String subject;
    String content;

    public int getId() {
        return id;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment() {

    }
}
