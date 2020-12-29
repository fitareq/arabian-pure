package com.youthfireit.dailydeals.local_room.room_model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

@Dao
public interface CartDao
{
    @Insert
    void addProductToCart(CartModel usercart);
    @Delete
    void deleteORCheckoutSingleCart(CartModel cart);
    @Query("DELETE FROM "+ Constt.TABLE_CART_USER)
    void deleteORCheckoutAllCart();
    @Query("SELECT * FROM "+ Constt.TABLE_CART_USER)
    LiveData<List<CartModel>> getCarts();
}
