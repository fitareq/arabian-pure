package com.youthfireit.dailydeals.model;

public class OrderTracking
{
    String phone;
    String trackingid;
    String product_title;
    String shipping_status;
    String city;
    String shipping_address;

    public OrderTracking(String phone, String trackingid, String product_title, String shipping_status, String city, String shipping_address) {
        this.phone = phone;
        this.trackingid = trackingid;
        this.product_title = product_title;
        this.shipping_status = shipping_status;
        this.city = city;
        this.shipping_address = shipping_address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTrackingid() {
        return trackingid;
    }

    public String getProduct_title() {
        return product_title;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public String getCity() {
        return city;
    }

    public String getShipping_address() {
        return shipping_address;
    }
}
