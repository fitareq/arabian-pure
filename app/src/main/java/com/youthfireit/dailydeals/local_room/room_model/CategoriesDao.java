package com.youthfireit.dailydeals.local_room.room_model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

@Dao
public interface CategoriesDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategoryList(List<CategoriesModel> categories);

    @Query("SELECT * FROM "+ Constt.TABLE_CATEGORIES+" WHERE id LIKE :cId")
    CategoriesModel getSingleCategory(int cId);

    @Query("SELECT * FROM "+Constt.TABLE_CATEGORIES)
    List<CategoriesModel> getAllCategory();

    @Query("DELETE FROM "+Constt.TABLE_CATEGORIES)
    void deleteAllCategory();
}
