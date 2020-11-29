package com.youthfireit.arabianpure.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private final List<Category> categories;
    private final int listSize;
    categoryClickListener categoryListener;

    public CategoryAdapter(List<Category> categories, int lSize, categoryClickListener categoryListener)
    {
        this.categories = categories;
        this.listSize = lSize;
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
        String image = "http://arabianpure.com/public/images/categories/"+categories.get(position).getThumbnail();
        String title = categories.get(position).getName();
        Picasso.get().load(image).into(holder.categoryImage);
        holder.categoryTitle.setText(title);
    }

    @Override
    public int getItemCount() {

        return listSize;
    }







    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryTitle;
        public ImageView categoryImage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            categoryImage = itemView.findViewById(R.id.category_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    String id = categories.get(getAdapterPosition()).getId();
                    String title = categories.get(getAdapterPosition()).getName();
                    categoryListener.onCategoryClickListener(id,title);
                }
            });



        }




    }


public interface categoryClickListener
{
    void onCategoryClickListener(String id, String title);
}


}
