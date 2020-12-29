package com.youthfireit.dailydeals.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesModel;
import com.youthfireit.dailydeals.model.Category;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private  List<CategoriesModel> categories;
    private final categoryClickListener categoryListener;

    public CategoryAdapter(List<CategoriesModel> categories, categoryClickListener categoryListener)
    {
        this.categories = categories;
        this.categoryListener = categoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        CategoriesModel current = categories.get(position);

        String image = current.getCategoryThumbnail();
        String title = current.getCategoryName();

        if (!image.isEmpty())
        {
            image = Constt.CATEGORY_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(holder.categoryImage);
        }
        holder.categoryTitle.setText(title);
    }

    @Override
    public int getItemCount() {

        return categories.size();
    }

    public void setCategories(List<CategoriesModel> categories)
    {
        this.categories = categories;
    }






    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryTitle;
        public ImageView categoryImage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            categoryImage = itemView.findViewById(R.id.category_image);


            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                int id = categories.get(position).getCategoryId();
                String title = categories.get(position).getCategoryName();
                categoryListener.onCategoryClickListener(id,title);
            });



        }




    }


public interface categoryClickListener
{
    void onCategoryClickListener(int id, String title);
}


}
