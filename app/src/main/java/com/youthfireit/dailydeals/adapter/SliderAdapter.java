package com.youthfireit.dailydeals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.room_model.HomepageSliderModel;
import com.youthfireit.dailydeals.model.HomepageSlider;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder>
{
    private List<HomepageSliderModel> sliderList;
    private Context context;

    public SliderAdapter(List<HomepageSliderModel> sliderList)
    {
        this.sliderList = sliderList;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent)
    {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.slider_card_view,parent,false);
        return new SliderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position)
    {
        HomepageSliderModel current = sliderList.get(position);
        String image = Constt.SLIDER_IMAGE_BASE_URL+current.getSliderImage();
        String title = current.getSliderTitle();
        viewHolder.sliderTitle.setText(title);
        if (!image.isEmpty())
        {
            Picasso.get().load(image).into(viewHolder.sliderImage);
        }
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    public void setSliderList(List<HomepageSliderModel> sliders)
    {
        sliderList = sliders;
    }



    public class SliderViewHolder extends SliderViewAdapter.ViewHolder
    {
        public ImageView sliderImage;
        public TextView sliderTitle;
        public SliderViewHolder(View itemView)
        {
            super(itemView);
            sliderImage = itemView.findViewById(R.id.slider_image_view);
            sliderTitle = itemView.findViewById(R.id.slider_title_textView);
        }
    }


}
