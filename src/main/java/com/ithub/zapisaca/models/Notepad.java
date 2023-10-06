package com.ithub.zapisaca.models;

import javax.persistence.*;

@Entity
public class Notepad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String tag;
    private String text;

    public Notepad(){
    }

    public Notepad(String name, String tag, String text) {
        this.name = name;
        this.tag = tag;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
