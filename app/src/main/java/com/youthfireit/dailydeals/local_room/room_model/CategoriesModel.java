package com.youthfireit.dailydeals.local_room.room_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.youthfireit.dailydeals.utils.Constt;

@Entity(tableName = Constt.TABLE_CATEGORIES)
public class CategoriesModel
{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer categoryId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String categoryName;

    @ColumnInfo(name = "name_bd")
    @SerializedName("name_bd")
    private String categoryNameBd;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String categoryDescription;

    @ColumnInfo(name = "image")
    @SerializedName("image")
    private String categoryImage;

    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    private String categoryThumbnail;

    @ColumnInfo(name = "parent_id")
    @SerializedName("parent_id")
    private Integer categoryParentId;

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    private String categorySlug;

    public CategoriesModel(@NonNull Integer categoryId, String categoryName, String categoryNameBd, String categoryDescription,
                           String categoryImage, String categoryThumbnail, Integer categoryParentId, String categorySlug) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryNameBd = categoryNameBd;
        this.categoryDescription = categoryDescription;
        this.categoryImage = categoryImage;
        this.categoryThumbnail = categoryThumbnail;
        this.categoryParentId = categoryParentId;
        this.categorySlug = categorySlug;
    }

    @NonNull
    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryNameBd() {
        return categoryNameBd;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public String getCategoryThumbnail() {
        return categoryThumbnail;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategoryId(@NonNull Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryNameBd(String categoryNameBd) {
        this.categoryNameBd = categoryNameBd;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public void setCategoryThumbnail(String categoryThumbnail) {
        this.categoryThumbnail = categoryThumbnail;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }
}
