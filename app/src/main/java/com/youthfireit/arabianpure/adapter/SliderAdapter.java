package com.youthfireit.arabianpure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.HomepageSlider;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder>
{
    private List<HomepageSlider> productsList;
    private Context context;

    public SliderAdapter(List<HomepageSlider> productsList)
    {
        this.productsList = productsList;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent)
    {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.slider_card_view,null);
        return new SliderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position)
    {
        String image = "http://arabianpure.com/public/images/sliders/"+productsList.get(position).getSlider_image();
        viewHolder.sliderTitle.setText(productsList.get(position).getTitle());
        Picasso.get().load(image).into(viewHolder.sliderImage);
    }

    @Override
    public int getCount() {
        return productsList.size();
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
