package com.youthfireit.arabianpure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.adapter.CategoryAdapter;
import com.youthfireit.arabianpure.model.Category;

import java.util.List;

public class CategoryFragment extends Fragment
{
    List<Category> categories;
    private RecyclerView allCategory;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    public CategoryFragment() { }
    public CategoryFragment(List<Category> categories)
    {
        this.categories = categories;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.category_fragment,container,false);
        allCategory = rootView.findViewById(R.id.category_fragment_recyclerview);
        allCategory.setHasFixedSize(true);
        allCategory.setNestedScrollingEnabled(false);
        manager = new GridLayoutManager(getContext(),2);
        allCategory.setLayoutManager(manager);
        adapter = new CategoryAdapter(categories,categories.size());
        allCategory.setAdapter(adapter);

        return rootView;
    }
}
