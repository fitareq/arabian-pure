package com.youthfireit.dailydeals.model;

public class CheckOut
{
    /*$user_id, $product_id, $product_title, $product_image, $attribute_options, $product_quantity,
    phone, name , email, city_name, shipping_address, message, amount, price, shipping_cost*/

    private int is_completed;
    private int user_id;
    private int product_id;
    private String product_title;
    private String product_image;
    private String attribute_options;
    private String product_quantity;
    private String phone;
    private String name;
    private String email;
    private String city_name;
    private String shipping_address;
    private String message;
    private String amount;
    private String price;
    private String shipping_cost;
    private String tracking_id;

    public CheckOut(int is_completed, int user_id, int product_id, String product_title, String product_image,
                    String attribute_options, String product_quantity, String phone, String name,
                    String email, String city_name, String shipping_address,
                    String message, String amount, String price, String shipping_cost, String tracking_id) {
        this.is_completed = is_completed;
        this.user_id = user_id;
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_image = product_image;
        this.attribute_options = attribute_options;
        this.product_quantity = product_quantity;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.city_name = city_name;
        this.shipping_address = shipping_address;
        this.message = message;
        this.amount = amount;
        this.price = price;
        this.shipping_cost = shipping_cost;
        this.tracking_id = tracking_id;
    }

    public int getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(int is_completed) {
        this.is_completed = is_completed;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getAttribute_options() {
        return attribute_options;
    }

    public void setAttribute_options(String attribute_options) {
        this.attribute_options = attribute_options;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(String shipping_cost) {
        this.shipping_cost = shipping_cost;
    }
}
