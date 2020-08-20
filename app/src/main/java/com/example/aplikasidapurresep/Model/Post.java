package com.example.aplikasidapurresep.Model;

public class Post {
    private String postid;
    private String postimage;
    private String name_receipt;
    private String description_receipt;
    private String waktu_memasak;
    private String ingredients;
    private String way_of_making;
    private String publisher;

    public Post() {
    }

    public Post(String postid, String postimage, String name_receipt, String description_receipt, String waktu_memasak, String ingredients, String way_of_making, String publisher) {
        this.postid = postid;
        this.postimage = postimage;
        this.name_receipt = name_receipt;
        this.description_receipt = description_receipt;
        this.waktu_memasak = waktu_memasak;
        this.ingredients = ingredients;
        this.way_of_making = way_of_making;
        this.publisher = publisher;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getName_receipt() {
        return name_receipt;
    }

    public void setName_receipt(String name_receipt) {
        this.name_receipt = name_receipt;
    }

    public String getDescription_receipt() {
        return description_receipt;
    }

    public void setDescription_receipt(String description_receipt) {
        this.description_receipt = description_receipt;
    }

    public String getWaktu_memasak() {
        return waktu_memasak;
    }

    public void setWaktu_memasak(String waktu_memasak) {
        this.waktu_memasak = waktu_memasak;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getWay_of_making() {
        return way_of_making;
    }

    public void setWay_of_making(String way_of_making) {
        this.way_of_making = way_of_making;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}