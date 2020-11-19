package com.youthfireit.arabianpure.model;

public class Category
{
    private int id;
    private String name;
    private String name_bd;
    private String description;
    private String image;
    private String thumbnail;
    private String parent_id;
    private String slug;
    private String created_at;
    private String updated_at;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getName_bd() {
        return name_bd;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getSlug() {
        return slug;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
