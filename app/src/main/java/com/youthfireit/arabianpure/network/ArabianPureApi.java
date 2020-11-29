package com.youthfireit.arabianpure.network;

import android.media.Image;

import com.youthfireit.arabianpure.model.Category;
import com.youthfireit.arabianpure.model.HomepageBanner;
import com.youthfireit.arabianpure.model.HomepageSlider;
import com.youthfireit.arabianpure.model.Login;
import com.youthfireit.arabianpure.model.PImage;
import com.youthfireit.arabianpure.model.Products;
import com.youthfireit.arabianpure.model.Register;

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
    Call<Register> getUserInfo(@Path("id") int id);
    @POST("register")
    Call<Register> registerUser(@Body Register register);
    @POST("login")
    Call<Login> loginUser(@Body Login login);

}
