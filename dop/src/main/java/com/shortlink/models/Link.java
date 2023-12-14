package com.shortlink.models;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mainLink;
    private String shortLink;
    private String desс;
    private Integer useCount = 0;
    public Link(){}
    public Link(long id, String mainLink, String shortLink, String desс, Integer useCount) {
        this.id = id;
        this.mainLink = mainLink;
        this.shortLink = shortLink;
        this.desс = desс;
        this.useCount = useCount;
    }
    public Link(long id, String mainLink, String shortLink, String desс) {
        this.id = id;
        this.mainLink = mainLink;
        this.shortLink = shortLink;
        this.desс = desс;
    }
    public Link(String mainLink, String shortLink, String desс) {
        this.id = -1;
        this.mainLink = mainLink;
        this.shortLink = shortLink;
        this.desс = desс;
    }

    public String getMainLink() {
        return mainLink;
    }

    public long getId() {
        return id;
    }

    public String getShortLink() {
        return shortLink;
    }
    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount() {
        this.useCount++;
    }

    public String getDesс() {
        return desс;
    }

    public void setDesс(String desс) {
        this.desс = desс;
    }
}
