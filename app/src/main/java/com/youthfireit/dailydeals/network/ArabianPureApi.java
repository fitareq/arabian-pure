package com.youthfireit.dailydeals.network;

import com.youthfireit.dailydeals.firebase.SaveUserInfo;
import com.youthfireit.dailydeals.model.Category;
import com.youthfireit.dailydeals.model.CheckOut;
import com.youthfireit.dailydeals.model.HomepageBanner;
import com.youthfireit.dailydeals.model.HomepageSlider;
import com.youthfireit.dailydeals.model.Login;
import com.youthfireit.dailydeals.model.PImage;
import com.youthfireit.dailydeals.model.Products;
import com.youthfireit.dailydeals.model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArabianPureApi
{
    @GET("products")
    Call<List<Products>> getProducts();
    @GET("product/{slug}")
    Call<Products> getSingleProduct(@Path("slug") String slug);
    @GET("categoryAll")
    Call<List<Category>> getCategory();
    @GET("slider")
    Call<List<HomepageSlider>> getSlider();
    @GET("banner")
    Call<List<HomepageBanner>> getBanner();
    @GET("category/all/products/{category_id}")
    Call<List<Products>> getSpecificCategoryProducts(@Path("category_id") String category_id);
    @GET("productsimage/{product_id}")
    Call<List<PImage>> getProductImages(@Path("product_id") int product_id);
    @GET("userinfo/{id}")
    Call<SaveUserInfo> getUserInfo(@Path("id") int id);
    @POST("register")
    Call<Register> registerUser(@Body Register register);
    @POST("login")
    Call<Login> loginUser(@Body Login login);
    @POST("checkout")
    Call<CheckOut> checkoutProduct(@Body CheckOut checkOut);

}
