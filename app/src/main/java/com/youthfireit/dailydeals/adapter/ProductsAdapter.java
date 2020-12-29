package com.youthfireit.dailydeals.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.myViewHolder> {


private List<ProductsModel> values;
private Context context;
private final productClickListener productListener;



public ProductsAdapter(List<ProductsModel> values, Context context, productClickListener productListener) {

    this.context = context;
    this.values = values;
    this.productListener = productListener;
}



@NonNull
@Override
public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    context = parent.getContext();
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_card_view, parent, false);
    return new myViewHolder(v);
}



@Override
public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

    ProductsModel current = values.get(position);
    String image = current.getProductImage();
    String title = current.getProductTitle();
    String price = current.getProductPrice();
    String offerprice = current.getProductOfferPrice();
    String att = current.getProductAttributeOptions();
    String attr_name;
    List<String> list = new ArrayList<>();
    if (att != null) {
        Matcher m = Pattern.compile("(\".+?\")").matcher(att);
        while (m.find())
            list.add(m.group(1).replace("\"", "").replace(" ", ""));
        att = list.get(1);
        attr_name = list.get(0);
        list = Arrays.asList(att.split(","));
    }

    if (!image.isEmpty()) {
        image = Constt.PRODUCT_IMAGE_BASE_URL + image;
        Picasso.get().load(image).fit().into(holder.imageView);
    }
    holder.title.setText(title);

    if (offerprice != null) {
        price = "৳" + price;
        offerprice = "৳" + offerprice;
        holder.previousPrice.setVisibility(View.VISIBLE);
        holder.price.setText(offerprice);
        holder.previousPrice.setText(price);
        holder.previousPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    } else {
        String p;
        if (price == null)
            p = "0";
        else p = price;
        price = "৳" + p;
        holder.price.setText(price);
        holder.previousPrice.setVisibility(View.INVISIBLE);
    }


}



public void setValues(List<ProductsModel> products) {

    this.values = products;
}



@Override
public int getItemCount() {

    return values.size();
}



public class myViewHolder extends RecyclerView.ViewHolder {


    public TextView title, price, previousPrice;
    public ImageView imageView;
    public RatingBar productRatingBar;



    public myViewHolder(@NonNull View itemView) {

        super(itemView);
        title = itemView.findViewById(R.id.product_title);
        price = itemView.findViewById(R.id.product_price);
        previousPrice = itemView.findViewById(R.id.product_previous_price);
        imageView = itemView.findViewById(R.id.product_image);
        productRatingBar = itemView.findViewById(R.id.product_rating);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();
                String image = "http://arabianpure.com/public/images/" + values.get(position).getProductImage();
                String slug = values.get(position).getProductSlug();
                int id = values.get(position).getProductId();
                String title = values.get(position).getProductTitle();
                String description = values.get(position).getProductDescription();
                int quantity = values.get(position).getProductQuantity();
                String sku = values.get(position).getProductSku();
                productListener.onProductClickListener(String.valueOf(id), slug, image, title);
                    /*Intent intent = new Intent(context,SingleProductActivity.class);
                    intent.putExtra("image",image);
                    intent.putExtra("slug",slug);
                    context.startActivity(intent);*/
            }
        });
    }




}


public interface productClickListener {


    void onProductClickListener(String id, String slug, String image, String title);




}




}
