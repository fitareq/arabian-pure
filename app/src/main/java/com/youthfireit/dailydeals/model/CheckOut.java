package com.youthfireit.dailydeals.model;

public class CheckOut
{
    /*$user_id, $product_id, $product_title, $product_image, $attribute_options, $product_quantity,
    phone, name , email, city_name, shipping_address, message, amount, price, shipping_cost*/

   /* {
        "":"45",
            "":"52",
            "":"null",
            "":"weekly deals product",
            "":"15921329091.jpg",
            "":"null",
            "":"2",
            "":"172.168.0.1",
            "":"2",
            "":"tareq",
            "":"01647161559",
            "":"fitareq@gmail.com",
            "":"Dhaka",
            "":"mirpur",
            "":"hello",
            "":"500",
            "":"250",
            "":"60",
            "":"Processing",
            "":"null",
            "":"0",
            "":"Cash On Deliver",
            "":"Processing",
            "":"null",
            "":"11223655987",
            "":"5ef827295f0ec"
    }*/
    private final String user_id;
    private final String product_id;
    private final String vendor_id;
    private final String product_title;
    private final String product_image;
    private final String attribute_options;
    private final String product_quantity;
    private final String ip_address;
    private final String carts_id;
    private final String name;
    private final String phone;
    private final String email;
    private final String city_name;
    private final String shipping_address;
    private final String message;
    private final String amount;
    private final String price;
    private final String shipping_cost;
    private final String status;
    private final String currency;
    private final String is_completed;
    private final String payment_method;
    private final String delivery_status;
    private final String courier_info;
    private final String tracking_id;
    private final String transaction_id;

    public CheckOut(String user_id, String product_id, String vendor_id,
                    String product_title, String product_image, String attribute_options,
                    String product_quantity, String ip_address, String carts_id, String name,
                    String phone, String email, String city_name, String shipping_address,
                    String message, String amount, String price, String shipping_cost,
                    String status, String currency, String is_completed, String payment_method,
                    String delivery_status, String courier_info, String tracking_id, String transaction_id) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.vendor_id = vendor_id;
        this.product_title = product_title;
        this.product_image = product_image;
        this.attribute_options = attribute_options;
        this.product_quantity = product_quantity;
        this.ip_address = ip_address;
        this.carts_id = carts_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city_name = city_name;
        this.shipping_address = shipping_address;
        this.message = message;
        this.amount = amount;
        this.price = price;
        this.shipping_cost = shipping_cost;
        this.status = status;
        this.currency = currency;
        this.is_completed = is_completed;
        this.payment_method = payment_method;
        this.delivery_status = delivery_status;
        this.courier_info = courier_info;
        this.tracking_id = tracking_id;
        this.transaction_id = transaction_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getAttribute_options() {
        return attribute_options;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getCarts_id() {
        return carts_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public String getMessage() {
        return message;
    }

    public String getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }

    public String getShipping_cost() {
        return shipping_cost;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIs_completed() {
        return is_completed;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public String getCourier_info() {
        return courier_info;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }
}
