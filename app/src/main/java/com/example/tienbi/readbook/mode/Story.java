package com.example.tienbi.readbook.mode;

import java.io.Serializable;

/**
 * Created by TienBi on 18/09/2016.
 */
public class Story implements Serializable {
    private String idstory;
    private String title;
    private String content;
    private int isLike;
    private String idtopic;

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public Story(String idstory, String title, String content, int isLike, String idtopic) {
        this.idstory=idstory;
        this.title = title;
        this.content = content;
        this.isLike = isLike;
        this.idtopic=idtopic;

    }

    public String getIdstory() {
        return idstory;
    }

    public int getIsLike() {
        return isLike;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int isLike() {
        return isLike;
    }

    public String getIdtopic() {
        return idtopic;
    }
}
