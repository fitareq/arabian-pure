package com.youthfireit.dailydeals.local_room.room_model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

@Dao
public interface SliderDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSliderList(List<HomepageSliderModel> sliders);
    @Query("DELETE FROM "+ Constt.TABLE_HOMEPAGE_SLIDER)
    void deleteAllSlider();
    @Query("SELECT * FROM "+Constt.TABLE_HOMEPAGE_SLIDER)
    List<HomepageSliderModel> getAllSlider();
}
