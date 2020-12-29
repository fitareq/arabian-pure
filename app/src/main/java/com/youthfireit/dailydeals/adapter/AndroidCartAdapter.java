package com.youthfireit.dailydeals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.utils.Constt;

import java.util.List;


public class AndroidCartAdapter extends RecyclerView.Adapter<AndroidCartAdapter.CartViewHolder> {

    private List<CartModel> carts;
    private Context context;

@NonNull
@Override
public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    context = parent.getContext();
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_view,parent,false);
    return new CartViewHolder(v);
}



@Override
public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
    CartModel current = carts.get(position);
    String title = current.getProductTitle();
    String price = String.valueOf(current.getProductPrice());
    int quantity = current.getProductQuantity();
    String image = current.getProductImage();

    if (!image.isEmpty())
    {
        image = Constt.PRODUCT_IMAGE_BASE_URL+image;
        Picasso.get().load(image).into(holder.cartImageView);
    }
    holder.cartTitle.setText(title);
    holder.cartPrice.setText(price);
    holder.cartQuantity.setText(quantity);
    holder.cartCheckout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(context, "Checkout", Toast.LENGTH_SHORT).show();
        }
    });

}
@Override
public int getItemCount()
{

    return carts.size();
}

public void setCategories(List<CartModel> carts){this.carts = carts;}

public static class CartViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView cartImageView;
        public TextView cartTitle, cartPrice, cartQuantity;
        public Button cartDelete, cartCheckout;
        public AppCompatImageButton cartQuantityAdd, cartQuantitySub;

        public CartViewHolder(@NonNull View itemView) {

            super(itemView);

            cartImageView = itemView.findViewById(R.id.cart_image_view);
            cartTitle = itemView.findViewById(R.id.cart_title);
            cartPrice = itemView.findViewById(R.id.cart_price);
            cartQuantity = itemView.findViewById(R.id.cart_product_quantity);
            cartDelete = itemView.findViewById(R.id.cart_delete_btn);
            cartCheckout = itemView.findViewById(R.id.cart_checkout_btn);
            cartQuantityAdd = itemView.findViewById(R.id.cart_quantity_add);
            cartQuantitySub = itemView.findViewById(R.id.cart_quantity_sub);
        }




    }
}
