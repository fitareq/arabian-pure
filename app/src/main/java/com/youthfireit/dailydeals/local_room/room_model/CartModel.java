package com.youthfireit.dailydeals.local_room.room_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.youthfireit.dailydeals.utils.Constt;

@Entity(tableName = Constt.TABLE_CART_USER)
public class CartModel
{


    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    private Integer userId;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    @SerializedName("product_id")
    private Integer productId;

    @ColumnInfo(name = "qtybutton")
    @SerializedName("qtybutton")
    private Integer productQuantity;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private String productPrice;
    //attributes_option
    @ColumnInfo(name = "attributes_option")
    @SerializedName("attributes_option")
    private String attributesOption;
    //product_image
    @ColumnInfo(name = "product_image")
    @SerializedName("product_image")
    private String productImage;
    //product_title
    @ColumnInfo(name = "product_title")
    @SerializedName("product_title")
    private String productTitle;

    public CartModel(Integer userId, @NonNull Integer productId, Integer productQuantity, String productPrice,
                     String attributesOption, String productImage, String productTitle) {
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.attributesOption = attributesOption;
        this.productImage = productImage;
        this.productTitle = productTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    @NonNull
    public Integer getProductId() {
        return productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getAttributesOption() {
        return attributesOption;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }



public void setUserId(Integer userId) {this.userId = userId;}

public void setProductId(@NonNull Integer productId) {this.productId = productId;}

public void setProductQuantity(Integer productQuantity) {this.productQuantity = productQuantity;}

public void setProductPrice(String productPrice) {this.productPrice = productPrice;}

public void setAttributesOption(String attributesOption) {this.attributesOption = attributesOption;}

public void setProductImage(String productImage) {this.productImage = productImage;}

public void setProductTitle(String productTitle) {this.productTitle = productTitle;}

}
