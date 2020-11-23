package com.youthfireit.arabianpure.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.activity.SingleProductActivity;
import com.youthfireit.arabianpure.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>
{
    private final List<Products> values;
    public Context context;
    productClickListener productListener;
    public ProductsAdapter( List<Products> values, Context context,productClickListener productListener) {
        this.context = context;
        this.values = values;
        this.productListener = productListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_card_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String image = "http://arabianpure.com/public/images/"+values.get(position).getImage();
        String title = values.get(position).getTitle();
        String price = values.get(position).getPrice();
        String offerprice = values.get(position).getOffer_price();
        String att = values.get(position).getAttribute_options();
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("(\".+?\")").matcher(att);
        while (m.find())
            list.add(m.group(1).replace("\"","").replace(" ",""));
        att = list.get(0)+list.get(1);
        list.remove(0);

        Picasso.get().load(image).fit().into(holder.imageView);
        holder.title.setText(att);
        if (offerprice==null)
        {
            if (price==null)
            {
                price = "৳0";
            }
            else price="৳"+price;
            holder.price.setText(price);
            holder.previousPrice.setVisibility(View.INVISIBLE);
        }
        else
        {
            price="৳"+price;
            offerprice = "৳"+offerprice;
            holder.previousPrice.setVisibility(View.VISIBLE);
            holder.price.setText(offerprice);
            holder.previousPrice.setText(price);
            holder.previousPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }




    }



    @Override
    public int getItemCount()
    {
        return values.size();
    }















    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title,price,previousPrice;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            previousPrice = itemView.findViewById(R.id.product_previous_price);
            imageView = itemView.findViewById(R.id.product_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String image = "http://arabianpure.com/public/images/"+values.get(position).getImage();
                    String slug = values.get(position).getSlug();
                    String title = values.get(position).getTitle();
                    String description = values.get(position).getDescription();
                    String quantity = values.get(position).getQuantity();
                    String sku = values.get(position).getSku();
                    productListener.onProductClickListener(image,slug,title,description,quantity,sku);
                    /*Intent intent = new Intent(context,SingleProductActivity.class);
                    intent.putExtra("image",image);
                    intent.putExtra("slug",slug);
                    context.startActivity(intent);*/
                }
            });
        }
    }




    public interface productClickListener
    {
        void onProductClickListener(String image, String slug, String title, String description, String quantity, String sku);
    }
}
