package com.youthfireit.dailydeals.local_room.room_model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.youthfireit.dailydeals.model.HomepageBanner;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

@Dao
public interface BannerDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBannerList(List<HomepageBannerModel> banners);
    @Query("DELETE FROM "+ Constt.TABLE_HOMEPAGE_BANNER)
    void deleteAllBanner();
    @Query("SELECT * FROM "+Constt.TABLE_HOMEPAGE_BANNER)
    List<HomepageBannerModel> getAllBanner();
}
