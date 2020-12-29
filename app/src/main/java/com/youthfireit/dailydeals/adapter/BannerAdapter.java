package com.youthfireit.dailydeals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.room_model.HomepageBannerModel;
import com.youthfireit.dailydeals.model.HomepageBanner;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerViewHolder>
{

    private List<HomepageBannerModel> homepageBanners;
    private Context context;

    public BannerAdapter(List<HomepageBannerModel> homepageBanners) {
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

        HomepageBannerModel current = homepageBanners.get(position);
        String image =current.getBannerImage();
        if (!image.isEmpty())
        {
            image = Constt.BANNER_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(viewHolder.bannerImage);
        }
    }

    @Override
    public int getCount() {
        return homepageBanners.size();
    }


    public void setHomepageBanners(List<HomepageBannerModel> banners)
    {
        homepageBanners = banners;
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
