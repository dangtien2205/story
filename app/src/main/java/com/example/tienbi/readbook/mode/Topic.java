package com.example.tienbi.readbook.mode;

import java.util.ArrayList;

/**
 * Created by TienBi on 18/09/2016.
 */
public class Topic {
    private String idtopic;
    private String topic;
    private String imageId;
    private ArrayList<Story> stories;

    public String getTopic() {
        return topic;
    }

    public String getImageId() {
        return imageId;
    }

    public String getIdtopic() {
        return idtopic;
    }

    public Topic(String idtopic, String topic, String imageId) {
        this.idtopic = idtopic;
        this.topic = topic;
        this.imageId = imageId;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }
}
