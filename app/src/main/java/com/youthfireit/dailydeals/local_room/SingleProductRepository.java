package com.youthfireit.dailydeals.local_room;

import android.app.Application;

import com.youthfireit.dailydeals.local_room.room_model.CartDao;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsDao;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;


public class SingleProductRepository {

    private final ProductsDao productsDao;
    private final CartDao cartDao;



public SingleProductRepository(Application application)
    {
        localDatabase db = localDatabase.getDatabase(application);
        productsDao = db.productsDao();
        cartDao = db.cartDao();

    }



public ProductsModel getProduct(int productId) {

    return productsDao.getSingleProduct(productId);}
public void addProductToCart(CartModel cart)
{

    localDatabase.databaseWriteExecutors.execute(() -> cartDao.addProductToCart(cart));
}




}
