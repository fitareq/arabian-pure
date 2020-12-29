package com.youthfireit.dailydeals.local_room.room_model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

@Dao
public interface ProductsDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductList(List<ProductsModel> products);

    @Query("SELECT * FROM "+ Constt.TABLE_ALL_PRODUCT+" WHERE id=:id")
    ProductsModel getSingleProduct(int id);

    @Query("SELECT * FROM "+Constt.TABLE_ALL_PRODUCT)
    LiveData<List<ProductsModel>> getAllProducts();

    @Query("DELETE FROM "+Constt.TABLE_ALL_PRODUCT)
    void deleteAllProducts();

    @Query("SELECT * FROM "+Constt.TABLE_ALL_PRODUCT+" WHERE toprated=1")
    List<ProductsModel> getAllTopRatedProducts();

    @Query("SELECT * FROM "+Constt.TABLE_ALL_PRODUCT+" WHERE week_deals=1")
    List<ProductsModel> getAllWeekDealsProducts();
}
