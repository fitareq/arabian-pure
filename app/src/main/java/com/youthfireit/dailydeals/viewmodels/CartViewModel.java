package com.youthfireit.dailydeals.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.youthfireit.dailydeals.local_room.CartRepository;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;

import java.util.List;


public class CartViewModel extends AndroidViewModel {

    private CartRepository cartRepository;
    private LiveData<List<CartModel>> allCarts;

public CartViewModel(@NonNull Application application) {

    super(application);

    cartRepository = new CartRepository(application);
    allCarts = cartRepository.getAllCarts();
}



public LiveData<List<CartModel>> getAllCarts() {return allCarts; }
public void addProductToCart(CartModel cart){cartRepository.addProductToCart(cart);}
public void deleteORCheckoutSingleCart(CartModel cart){cartRepository.deleteORCheckoutSingleCart(cart);}
public void deleteORCheckoutAllCart(){cartRepository.deleteORCheckoutAllCart();}




}
