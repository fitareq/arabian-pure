package com.youthfireit.dailydeals.local_room.room_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.youthfireit.dailydeals.utils.Constt;

@Entity(tableName = Constt.TABLE_HOMEPAGE_BANNER)
public class HomepageBannerModel
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer bannerId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String bannerTitle;

    @ColumnInfo(name = "title_bd")
    @SerializedName("title_bd")
    private String bannerTitleBd;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String bannerUrl;

    @ColumnInfo(name = "image")
    @SerializedName("image")
    private String bannerImage;

    @ColumnInfo(name = "short_code")
    @SerializedName("short_code")
    private String bannerShortCode;


    public HomepageBannerModel(@NonNull Integer bannerId, String bannerTitle, String bannerTitleBd,
                               String bannerUrl, String bannerImage, String bannerShortCode) {
        this.bannerId = bannerId;
        this.bannerTitle = bannerTitle;
        this.bannerTitleBd = bannerTitleBd;
        this.bannerUrl = bannerUrl;
        this.bannerImage = bannerImage;
        this.bannerShortCode = bannerShortCode;
    }

    @NonNull
    public Integer getBannerId() {
        return bannerId;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public String getBannerTitleBd() {
        return bannerTitleBd;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public String getBannerShortCode() {
        return bannerShortCode;
    }

    public void setBannerId(@NonNull Integer bannerId) {
        this.bannerId = bannerId;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public void setBannerTitleBd(String bannerTitleBd) {
        this.bannerTitleBd = bannerTitleBd;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public void setBannerShortCode(String bannerShortCode) {
        this.bannerShortCode = bannerShortCode;
    }
}
