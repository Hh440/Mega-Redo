package com.company.book_novel;

public class Book_chapter {
    String cname,curl;
    Book_chapter(){

    }
    Book_chapter(String cname,String curl){
        this.cname=cname;
        this.curl=curl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }
}
