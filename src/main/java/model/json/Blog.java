package model.json;

import javax.inject.Singleton;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class Blog implements Serializable {
    private int id;
    private int entrySequence;
    private Map<Integer, Entry> entries;

    public int getEntrySequence() {
        return entrySequence;
    }

    public int getEntrySequenceNextVal() {
        return ++entrySequence;
    }

    public void setEntrySequence(int entrySequence) {
        this.entrySequence = entrySequence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Entry> getEntries() {
        return entries;
    }

    public void setEntries(Map<Integer, Entry> entries) {
        this.entries = entries;
    }

    public Blog() {
        entries = new HashMap<> ();
    }

}
