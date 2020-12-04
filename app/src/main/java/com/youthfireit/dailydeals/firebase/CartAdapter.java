package com.youthfireit.dailydeals.firebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.model.Cart;
import com.youthfireit.dailydeals.model.CheckOut;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends FirebaseRecyclerAdapter<Cart,CartAdapter.CartViewHolder>
{
    private FirebaseAuth mAuth;
    private DatabaseReference cartReference, userReference;
    private String uId;
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartAdapter(@NonNull FirebaseRecyclerOptions<Cart> options) {

        super(options);
        mAuth = FirebaseAuth.getInstance();
        uId = mAuth.getCurrentUser().getUid();
        cartReference = FirebaseDatabase.getInstance().getReference().child("cart");
        userReference = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position, @NonNull Cart model)
    {
        String pid = model.getProduct_id();
        String image = model.getProduct_image();
        String title = model.getProduct_title();
        String price = model.getPrice();
        int quantity = Integer.parseInt(model.getQtybutton());
        int user_id = Integer.parseInt(model.getUser_id());
        String att_option = model.getAttributes_option();
        Picasso.get().load(image).into(holder.cartImageView);
        holder.cartTitle.setText(title);
        String p = "৳"+price;
        holder.cartPrice.setText(p);
        holder.cartQuantity.setText(String.valueOf(quantity));

        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cartReference.child(uId).child(pid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.cartQuantityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartReference.child(uId).child(pid).child("qtybutton").setValue(String.valueOf(quantity+1));
            }
        });
        holder.cartQuantitySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>1)
                {
                    cartReference.child(uId).child(pid).child("qtybutton").setValue(String.valueOf(quantity-1));
                }
            }
        });
        holder.cartCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Checkout");
                View ve = LayoutInflater.from(context).inflate(R.layout.check_out_card_view,null);
                /*final EditText name = new EditText(context);
                final EditText email = new EditText(context);
                name.setInputType(InputType.TYPE_CLASS_TEXT);
                email.setInputType(InputType.TYPE_CLASS_TEXT);
                name.setHint("Name");
                email.setHint("Email");
                builder.setView(name);*/
                builder.setView(LayoutInflater.from(context).inflate(R.layout.check_out_card_view,null));
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //name.setText("ok");

                        TextInputEditText nameEditText = ve.findViewById(R.id.check_name);
                        TextInputEditText emailEditText = ve.findViewById(R.id.check_email);
                        TextInputEditText phoneEditText = ve.findViewById(R.id.check_phone);
                        TextInputEditText cityEditText = ve.findViewById(R.id.check_delivery_city);
                        TextInputEditText deliveryAddressEditText = ve.findViewById(R.id.check_delivery_address);
                        TextInputEditText messageEditText = ve.findViewById(R.id.check_delivery_message);
                        String name = nameEditText.getText().toString();
                        String email = emailEditText.getText().toString();
                        String phone = phoneEditText.getText().toString();
                        String city = cityEditText.getText().toString();
                        String address = deliveryAddressEditText.getText().toString();
                        String message = messageEditText.getText().toString();
                        double amunt = quantity*Integer.parseInt(price);
                        int shipping_cost;
                        if (city.equalsIgnoreCase("dhaka"))
                            shipping_cost = 60;
                        else shipping_cost = 80;
                        String tracking_id = user_id+pid+user_id+phone;
                        String img = image.replace("http://arabianpure.com/public/images/","");
                        int poid = Integer.parseInt(pid);
                        String qntt = String.valueOf(quantity);
                        String scost = String.valueOf(shipping_cost);
                        String amnt = String.valueOf(amunt);

                        CheckOut checkOut = new CheckOut(0, user_id,poid,title,img,att_option,qntt, phone
                                ,name,email,city,address,message,amnt,price,scost,tracking_id);
                        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
                        Call<CheckOut> call = arabianPureApi.checkoutProduct(checkOut);
                        call.enqueue(new Callback<CheckOut>() {
                            @Override
                            public void onResponse(Call<CheckOut> call, Response<CheckOut> response) {
                                if (response.code()==200)
                                {
                                    Toast.makeText(context, "Order placed Successfully", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<CheckOut> call, Throwable t) {

                            }
                        });


                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                /*userReference.child(uId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if (snapshot.exists())
                        {
                            String email = snapshot.child("email").getValue().toString();
                            String uid = snapshot.child("id").getValue().toString();
                            String name = snapshot.child("name").getValue().toString();
                            String phone_number = snapshot.child("phone_number").getValue().toString();
                            int amount = quantity*Integer.parseInt(price);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });

    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_view,parent,false);
        context = parent.getContext();
        return new CartAdapter.CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
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
