package com.agiantagravic.cms.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String subtitle;
    private String aboutTitle;

    @Column(columnDefinition = "Text")
    private String about;
    private String avatar;
    private char ab = 'a';
    private boolean anon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAboutTitle() {
        return aboutTitle;
    }

    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

    public char getAb() {
        return ab;
    }

    public void setAb(char ab) {
        this.ab = ab;
    }
}
