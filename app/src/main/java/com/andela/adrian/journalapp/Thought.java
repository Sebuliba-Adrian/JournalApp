package com.andela.adrian.journalapp;

import com.orm.SugarRecord;

/**
 * Created by adrian on 01/07/2018.
 */

public class Thought extends SugarRecord {
    String title, thought;
    long time;

    public Thought() {
    }

    public Thought(String title, String thought, long time) {
        this.title = title;
        this.thought = thought;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
