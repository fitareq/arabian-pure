package com.youthfireit.dailydeals.local_room.room_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.youthfireit.dailydeals.utils.Constt;

@Entity(tableName = Constt.TABLE_HOMEPAGE_SLIDER)
public class HomepageSliderModel
{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer sliderId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String sliderTitle;

    @ColumnInfo(name = "title_bd")
    @SerializedName("title_bd")
    private String sliderTitleBd;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String sliderDescription;

    @ColumnInfo(name = "description_bd")
    @SerializedName("description_bd")
    private String sliderDescriptionBd;

    @ColumnInfo(name = "button_text")
    @SerializedName("button_text")
    private String sliderButtonText;

    @ColumnInfo(name = "button_text_bd")
    @SerializedName("button_text_bd")
    private String sliderButtonTextBd;

    @ColumnInfo(name = "button_link")
    @SerializedName("button_link")
    private String sliderButtonLink;

    @ColumnInfo(name = "background_image")
    @SerializedName("background_image")
    private String sliderBackgroundImage;

    @ColumnInfo(name = "slider_image")
    @SerializedName("slider_image")
    private String sliderImage;

    @ColumnInfo(name = "title_color")
    @SerializedName("title_color")
    private String sliderTitleColor;

    @ColumnInfo(name = "description_color")
    @SerializedName("description_color")
    private String sliderDescriptionColor;

    @ColumnInfo(name = "button_color")
    @SerializedName("button_color")
    private String sliderButtonColor;

    @ColumnInfo(name = "image_reverse")
    @SerializedName("image_reverse")
    private Integer sliderImageReverse;

    public HomepageSliderModel(@NonNull Integer sliderId, String sliderTitle, String sliderTitleBd, String sliderDescription, String sliderDescriptionBd, String sliderButtonText, String sliderButtonTextBd, String sliderButtonLink, String sliderBackgroundImage, String sliderImage,
                               String sliderTitleColor, String sliderDescriptionColor, String sliderButtonColor, Integer sliderImageReverse) {
        this.sliderId = sliderId;
        this.sliderTitle = sliderTitle;
        this.sliderTitleBd = sliderTitleBd;
        this.sliderDescription = sliderDescription;
        this.sliderDescriptionBd = sliderDescriptionBd;
        this.sliderButtonText = sliderButtonText;
        this.sliderButtonTextBd = sliderButtonTextBd;
        this.sliderButtonLink = sliderButtonLink;
        this.sliderBackgroundImage = sliderBackgroundImage;
        this.sliderImage = sliderImage;
        this.sliderTitleColor = sliderTitleColor;
        this.sliderDescriptionColor = sliderDescriptionColor;
        this.sliderButtonColor = sliderButtonColor;
        this.sliderImageReverse = sliderImageReverse;
    }

    @NonNull
    public Integer getSliderId() {
        return sliderId;
    }

    public String getSliderTitle() {
        return sliderTitle;
    }

    public String getSliderTitleBd() {
        return sliderTitleBd;
    }

    public String getSliderDescription() {
        return sliderDescription;
    }

    public String getSliderDescriptionBd() {
        return sliderDescriptionBd;
    }

    public String getSliderButtonText() {
        return sliderButtonText;
    }

    public String getSliderButtonTextBd() {
        return sliderButtonTextBd;
    }

    public String getSliderButtonLink() {
        return sliderButtonLink;
    }

    public String getSliderBackgroundImage() {
        return sliderBackgroundImage;
    }

    public String getSliderImage() {
        return sliderImage;
    }

    public String getSliderTitleColor() {
        return sliderTitleColor;
    }

    public String getSliderDescriptionColor() {
        return sliderDescriptionColor;
    }

    public String getSliderButtonColor() {
        return sliderButtonColor;
    }

    public Integer getSliderImageReverse() {
        return sliderImageReverse;
    }

    public void setSliderId(@NonNull Integer sliderId) {
        this.sliderId = sliderId;
    }

    public void setSliderTitle(String sliderTitle) {
        this.sliderTitle = sliderTitle;
    }

    public void setSliderTitleBd(String sliderTitleBd) {
        this.sliderTitleBd = sliderTitleBd;
    }

    public void setSliderDescription(String sliderDescription) {
        this.sliderDescription = sliderDescription;
    }

    public void setSliderDescriptionBd(String sliderDescriptionBd) {
        this.sliderDescriptionBd = sliderDescriptionBd;
    }

    public void setSliderButtonText(String sliderButtonText) {
        this.sliderButtonText = sliderButtonText;
    }

    public void setSliderButtonTextBd(String sliderButtonTextBd) {
        this.sliderButtonTextBd = sliderButtonTextBd;
    }

    public void setSliderButtonLink(String sliderButtonLink) {
        this.sliderButtonLink = sliderButtonLink;
    }

    public void setSliderBackgroundImage(String sliderBackgroundImage) {
        this.sliderBackgroundImage = sliderBackgroundImage;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    public void setSliderTitleColor(String sliderTitleColor) {
        this.sliderTitleColor = sliderTitleColor;
    }

    public void setSliderDescriptionColor(String sliderDescriptionColor) {
        this.sliderDescriptionColor = sliderDescriptionColor;
    }

    public void setSliderButtonColor(String sliderButtonColor) {
        this.sliderButtonColor = sliderButtonColor;
    }

    public void setSliderImageReverse(Integer sliderImageReverse) {
        this.sliderImageReverse = sliderImageReverse;
    }
}
