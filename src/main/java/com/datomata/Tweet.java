package com.datomata;

public class Tweet {

    private final long id;
    private final String content;
    private final String name;
    private final String image;
    private final String url;

    public Tweet(long id, String content, String name, String image, String url) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.image = image;
        this.url = url;
    }

    public String getUrl () {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
