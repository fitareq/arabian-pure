package com.youthfireit.dailydeals.local_room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.youthfireit.dailydeals.local_room.room_model.BannerDao;
import com.youthfireit.dailydeals.local_room.room_model.CartDao;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesDao;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageBannerModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageSliderModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsDao;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;
import com.youthfireit.dailydeals.local_room.room_model.SliderDao;
import com.youthfireit.dailydeals.local_room.room_model.UserModel;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.local_room.room_model.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserModel.class, CartModel.class, ProductsModel.class, HomepageBannerModel.class, HomepageSliderModel.class, CategoriesModel.class},version = 1, exportSchema = false)
public abstract class localDatabase extends RoomDatabase
{
    public abstract UserDao userDao();
    public abstract CartDao cartDao();
    public abstract BannerDao bannerDao();
    public abstract SliderDao sliderDao();
    public abstract ProductsDao productsDao();
    public abstract CategoriesDao categoriesDao();
    private static volatile localDatabase INSTANCE;
    public static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutors = Executors.newFixedThreadPool(NUMBER_OF_THREAD);



    public static localDatabase getDatabase(final Context context)
    {
        if (INSTANCE==null)
        {
            synchronized (localDatabase.class)
            {
                if (INSTANCE==null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),localDatabase.class,"database_dailydeal")
                                       .addCallback(sRoomDatabaseCallback)
                                       .build();
                }
            }
        }
        return INSTANCE;
    }




    private static  RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
         /*databaseWriteExecutors.execute(() -> {
             //INSTANCE.cartDao().deleteORCheckoutAllCart();
                INSTANCE.productsDao().deleteAllProducts();
             //INSTANCE.sliderDao().deleteAllSlider();
             //INSTANCE.bannerDao().deleteAllBanner();
             //INSTANCE.userDao().logOutUser();
             //INSTANCE.categoriesDao().deleteAllCategory();
         });*/
        }
    };

}
