package com.youthfireit.arabianpure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.HomepageBanner;

import java.util.List;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerViewHolder>
{

    private List<HomepageBanner> homepageBanners;
    private Context context;

    public BannerAdapter(List<HomepageBanner> homepageBanners) {
        this.homepageBanners = homepageBanners;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.banner_card_view,null);
        return new BannerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder viewHolder, int position)
    {

        String image = "http://arabianpure.com/public/images/"+homepageBanners.get(position).getImage();
        Picasso.get().load(image).into(viewHolder.bannerImage);
    }

    @Override
    public int getCount() {
        return homepageBanners.size();
    }







    public class BannerViewHolder extends SliderViewAdapter.ViewHolder {
        public ImageView bannerImage;
        public BannerViewHolder(View itemView)
        {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.banner_image_view);
        }
    }
}
