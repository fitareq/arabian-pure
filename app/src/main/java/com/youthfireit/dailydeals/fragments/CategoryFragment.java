package com.youthfireit.dailydeals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.CategoryAdapter;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesModel;
import com.youthfireit.dailydeals.model.Category;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment
{
    List<CategoriesModel> categories;
    private RecyclerView allCategory;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.category_fragment,container,false);
        allCategory = rootView.findViewById(R.id.category_fragment_recyclerview);
        allCategory.setHasFixedSize(true);
        allCategory.setNestedScrollingEnabled(false);
        manager = new GridLayoutManager(getContext(),2);
        allCategory.setLayoutManager(manager);
        loadCategoryData();


        return rootView;
    }

    private void loadCategoryData()
    {
      /*  ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Category>> categoryCall = arabianPureApi.getCategory();
        categoryCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse( Call<List<Category>> call,  Response<List<Category>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                categories = response.body();
                adapter = new CategoryAdapter(categories, categories.size(), (CategoryAdapter.categoryClickListener) getContext());
                allCategory.setAdapter(adapter);
            }

            @Override
            public void onFailure( Call<List<Category>> call,  Throwable t) {

            }
        });*/
    }
}
