package com.youthfireit.dailydeals.firebase;

public class SaveUserInfo
{
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private String delivery_phone;
    private String delivery_address;
    private String city_id;
    private String gender;

    public SaveUserInfo(String id, String name, String email, String password, String phone_number, String address, String delivery_phone, String delivery_address, String city_id, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.address = address;
        this.delivery_phone = delivery_phone;
        this.delivery_address = delivery_address;
        this.city_id = city_id;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_phone() {
        return delivery_phone;
    }

    public void setDelivery_phone(String delivery_phone) {
        this.delivery_phone = delivery_phone;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
