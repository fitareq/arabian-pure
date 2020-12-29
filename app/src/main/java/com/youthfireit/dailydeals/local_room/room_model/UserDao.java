package com.youthfireit.dailydeals.local_room.room_model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.youthfireit.dailydeals.utils.Constt;


@Dao
public interface UserDao
{
    @Insert
    void logInUser(UserModel userModel);
    @Query("DELETE FROM "+ Constt.TABLE_LOGGED_IN_USER)
    void logOutUser();
    @Query("SELECT * FROM "+ Constt.TABLE_LOGGED_IN_USER)
    LiveData<UserModel> getCurrentUser();
}
