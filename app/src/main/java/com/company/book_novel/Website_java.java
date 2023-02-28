package com.company.book_novel;

import androidx.recyclerview.widget.RecyclerView;

public class Website_java{
    String name,url;
    Website_java(){

    }
    Website_java(String name,String url){
        this.name=name;
        this.url=url;

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
}
