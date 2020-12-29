package com.youthfireit.dailydeals.local_room.room_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.youthfireit.dailydeals.utils.Constt;

@Entity(tableName = Constt.TABLE_ALL_PRODUCT)
public class ProductsModel
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private final Integer productId;

    @ColumnInfo(name = "category_id")
    @SerializedName("category_id")
    private final Integer productCategoryId;

    @ColumnInfo(name = "brand_id")
    @SerializedName("brand_id")
    private final Integer productBrandId;

    @ColumnInfo(name = "admin_id")
    @SerializedName("admin_id")
    private final Integer productAdminId;

    @ColumnInfo(name = "vendor_id")
    @SerializedName("vendor_id")
    private final String productVendorId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private final String productTitle;

    @ColumnInfo(name = "title_bd")
    @SerializedName("title_bd")
    private final String productTitleBd;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private final String productDescription;

    @ColumnInfo(name = "description_bd")
    @SerializedName("description_bd")
    private final String productDescriptionBd;

    @ColumnInfo(name = "specifications")
    @SerializedName("specifications")
    private final String productSpecifications;

    @ColumnInfo(name = "specifications_bd")
    @SerializedName("specifications_bd")
    private final String productSpecificationBd;

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    private final Integer productQuantity;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private final String productPrice;

    @ColumnInfo(name = "offer_price")
    @SerializedName("offer_price")
    private final String productOfferPrice;

    @ColumnInfo(name = "status")
    @SerializedName("status")
    private final Integer productStatus;

    @ColumnInfo(name = "featured")
    @SerializedName("featured")
    private final Integer productFeatured;

    @ColumnInfo(name = "week_deals")
    @SerializedName("week_deals")
    private final Integer productWeekDeals;

    @ColumnInfo(name = "onsale")
    @SerializedName("onsale")
    private final Integer productOnsale;

    @ColumnInfo(name = "toprated")
    @SerializedName("toprated")
    private final Integer productToprated;

    @ColumnInfo(name = "sku")
    @SerializedName("sku")
    private final String productSku;

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    private final String productSlug;

    @ColumnInfo(name = "attribute_set_id")
    @SerializedName("attribute_set_id")
    private final String productAttributeSetId;

    @ColumnInfo(name = "attributes_id")
    @SerializedName("attributes_id")
    private final String productAttributesId;

    @ColumnInfo(name = "image")
    @SerializedName("image")
    private final String productImage;

    @ColumnInfo(name = "attribute_options")
    @SerializedName("attribute_options")
    private final String productAttributeOptions;



    public ProductsModel(@NonNull Integer productId, Integer productCategoryId, Integer productBrandId, Integer productAdminId,
                         String productVendorId, String productTitle, String productTitleBd, String productDescription, String productDescriptionBd,
                         String productSpecifications, String productSpecificationBd, Integer productQuantity, String productPrice,
                         String productOfferPrice, Integer productStatus, Integer productFeatured, Integer productWeekDeals, Integer productOnsale,
                         Integer productToprated, String productSku, String productSlug, String productAttributeSetId,
                         String productAttributesId, String productImage, String productAttributeOptions) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.productBrandId = productBrandId;
        this.productAdminId = productAdminId;
        this.productVendorId = productVendorId;
        this.productTitle = productTitle;
        this.productTitleBd = productTitleBd;
        this.productDescription = productDescription;
        this.productDescriptionBd = productDescriptionBd;
        this.productSpecifications = productSpecifications;
        this.productSpecificationBd = productSpecificationBd;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productOfferPrice = productOfferPrice;
        this.productStatus = productStatus;
        this.productFeatured = productFeatured;
        this.productWeekDeals = productWeekDeals;
        this.productOnsale = productOnsale;
        this.productToprated = productToprated;
        this.productSku = productSku;
        this.productSlug = productSlug;
        this.productAttributeSetId = productAttributeSetId;
        this.productAttributesId = productAttributesId;
        this.productImage = productImage;
        this.productAttributeOptions = productAttributeOptions;
    }

@NonNull public Integer getProductId() {
        return productId;
    }



public Integer getProductCategoryId() {
        return productCategoryId;
    }



public Integer getProductBrandId() {
        return productBrandId;
    }



public Integer getProductAdminId() {
        return productAdminId;
    }



public String getProductVendorId() {
        return productVendorId;
    }



public String getProductTitle() {
        return productTitle;
    }



public String getProductTitleBd() {
        return productTitleBd;
    }



public String getProductDescription() {
        return productDescription;
    }



public String getProductDescriptionBd() {
        return productDescriptionBd;
    }



public String getProductSpecifications() {
        return productSpecifications;
    }



public String getProductSpecificationBd() {
        return productSpecificationBd;
    }



public Integer getProductQuantity() {
        return productQuantity;
    }



public String getProductPrice() {
        return productPrice;
    }



public String getProductOfferPrice() {
        return productOfferPrice;
    }



public Integer getProductStatus() {
        return productStatus;
    }



public Integer getProductFeatured() {
        return productFeatured;
    }



public Integer getProductWeekDeals() {
        return productWeekDeals;
    }



public Integer getProductOnsale() {
        return productOnsale;
    }



public Integer getProductToprated() {
        return productToprated;
    }



public String getProductSku() {
        return productSku;
    }



public String getProductSlug() {
        return productSlug;
    }



public String getProductAttributeSetId() {
        return productAttributeSetId;
    }



public String getProductAttributesId() {
        return productAttributesId;
    }



public String getProductImage() {
        return productImage;
    }



public String getProductAttributeOptions() {
        return productAttributeOptions;
    }




}
