package com.youthfireit.dailydeals.model;

public class Cart
{
    //  order_id ip_address product_quantity price shipping_cost
    private String user_id;
    private String product_id;
    private String qtybutton;
    private String price;
    private String attributes_option;
    private String product_image;
    private String product_title;

    public Cart() {
    }

    public Cart(String user_id, String product_id, String qtybutton, String price, String attributes_option, String product_image, String product_title) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.qtybutton = qtybutton;
        this.price = price;
        this.attributes_option = attributes_option;
        this.product_image = product_image;
        this.product_title = product_title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQtybutton() {
        return qtybutton;
    }

    public void setQtybutton(String qtybutton) {
        this.qtybutton = qtybutton;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAttributes_option() {
        return attributes_option;
    }

    public void setAttributes_option(String attributes_option) {
        this.attributes_option = attributes_option;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }
}
