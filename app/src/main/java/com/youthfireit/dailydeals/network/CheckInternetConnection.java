package com.youthfireit.dailydeals.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnection
{
    public static boolean isConnectionAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable() && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }
    /*http://arabianpure.com/api/products
    http://arabianpure.com/api/home
    http://arabianpure.com/api/product/{slug}
    http://arabianpure.com/api/otp/activation
    http://arabianpure.com/api/category/newest/{id}
    http://arabianpure.com/api/category/priceasc/{id}
    http://arabianpure.com/api/category/pricedesc/{id}
    http://arabianpure.com/api/category/showamount/{number}/{id}
    http://arabianpure.com/api/category/price/
    http://arabianpure.com/api/category/color/{code}/{id}
    http://arabianpure.com/api/category/color/{sizecode}/{id}
    http://arabianpure.com/api/category/{brand_id}/{category_id}
    http://arabianpure.com/api/page/{slug}
    http://arabianpure.com/api/category/{slug}
    http://arabianpure.com/api/brand/{id}
    http://arabianpure.com/api/offer/{slug}
    http://arabianpure.com/api/search
    http://arabianpure.com/api/review_add
    http://arabianpure.com/api/user/profile/myaccounts(get)
    http://arabianpure.com/api/user/profile/myaccounts(post)
    http://arabianpure.com/api/user/profile/orders
    http://arabianpure.com/api/user/profile/order/action/{id}/status/{action}
    http://arabianpure.com/api/user/profile/orders/search
    http://arabianpure.com/api/user/wishlists
    http://arabianpure.com/api/user/wishlists/delete/{id}
    http://arabianpure.com/api/register
    http://arabianpure.com/api/register
    http://arabianpure.com/api/login
    http://arabianpure.com/api/login
    http://arabianpure.com/api/logout
    http://arabianpure.com/api/categoryAll
    http://arabianpure.com/api/slider
    http://arabianpure.com/api/banner
    http://arabianpure.com/api/category/all/products/{category_id}*/
}
