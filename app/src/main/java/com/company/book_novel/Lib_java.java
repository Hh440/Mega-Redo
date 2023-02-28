package com.company.book_novel;

public class Lib_java {
    String name,url;
    String description;
    public Lib_java() {
    }

    public Lib_java(String name,String url,String description) {
        this.name = name;
        this.url=url;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
