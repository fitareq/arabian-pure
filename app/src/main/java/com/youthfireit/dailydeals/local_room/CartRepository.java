package com.youthfireit.dailydeals.local_room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.youthfireit.dailydeals.local_room.room_model.CartDao;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;

import java.util.List;


public class CartRepository
{
    private CartDao cartDao;
    private final LiveData<List<CartModel>> allCarts;



public CartRepository(Application application) {

    localDatabase db = localDatabase.getDatabase(application);
    cartDao = db.cartDao();
    allCarts = cartDao.getCarts();
}

public LiveData<List<CartModel>> getAllCarts(){return allCarts;}
public void addProductToCart(CartModel cart)
{
    localDatabase.databaseWriteExecutors.execute(new Runnable() {
        @Override
        public void run() {
            cartDao.addProductToCart(cart);
        }
    });
}
public void deleteORCheckoutSingleCart(CartModel cart)
{
    localDatabase.databaseWriteExecutors.execute(new Runnable() {
        @Override
        public void run() {
            cartDao.deleteORCheckoutSingleCart(cart);
        }
    });
}
public void deleteORCheckoutAllCart()
{
    localDatabase.databaseWriteExecutors.execute(new Runnable() {
        @Override
        public void run() {
            cartDao.deleteORCheckoutAllCart();
        }
    });
}




}
