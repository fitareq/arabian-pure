package com.youthfireit.dailydeals.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.youthfireit.dailydeals.local_room.SingleProductRepository;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;


public class SingleProductViewModel extends AndroidViewModel {

    private final SingleProductRepository repository;
    //private LiveData<ProductsModel> product;


public SingleProductViewModel(@NonNull Application application) {

    super(application);
    repository = new SingleProductRepository(application);

}

public ProductsModel getProduct(int productId)
{

    return repository.getProduct(productId);
}
public void addProductToCart(CartModel cart){repository.addProductToCart(cart);}


}
