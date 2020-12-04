package com.youthfireit.dailydeals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.model.PImage;

import java.util.List;

public class ImageAdapter extends SliderViewAdapter<ImageAdapter.imageViewHolder>
{
    List<PImage> pImageList;
    Context context;

    public ImageAdapter(List<PImage> pImageList) {
        this.pImageList = pImageList;
    }

    @Override
    public imageViewHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.banner_card_view,parent,false);
        return new imageViewHolder(v);
    }



    @Override
    public void onBindViewHolder(imageViewHolder viewHolder, int position)
    {
        String image = "http://arabianpure.com/public/images/"+pImageList.get(position).getImage();
        Picasso.get().load(image).into(viewHolder.bannerImage);

    }

    @Override
    public int getCount() {
        return pImageList.size();
    }



    public class imageViewHolder extends SliderViewAdapter.ViewHolder
    {
        public ImageView bannerImage;

        public imageViewHolder(View itemView)
        {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.banner_image_view);
        }
    }
}
