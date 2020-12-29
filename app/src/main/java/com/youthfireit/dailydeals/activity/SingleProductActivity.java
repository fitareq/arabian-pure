package com.youthfireit.dailydeals.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reginald.editspinner.EditSpinner;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.ImageAdapter;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;
import com.youthfireit.dailydeals.model.Cart;
import com.youthfireit.dailydeals.model.PImage;
import com.youthfireit.dailydeals.model.Products;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;
import com.youthfireit.dailydeals.viewmodels.SingleProductViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingleProductActivity extends AppCompatActivity implements View.OnClickListener {


private WebView webView;
private ExtendedFloatingActionButton addToCartBtn;
private TextView productTitle, productStock,
        addToWishlist, addedToWishlist, productDescription,
        productSku, productQuantity, productPrice, productOfferPrice, toolbarTitle, ShowMoreDescription;
private AppCompatImageButton productQuantityAdd, productQuantityRemove;
private int PQuantity = 1;
private Products productsList;
private String product_slug, product_id, product_image, product_title;
private ImageView backBtn;
//private Spinner attributeOptionSpinner;
private EditSpinner attributeOptionSpinner;
List<String> list = new ArrayList<>();
private SliderView singleImageSlider;
private ImageAdapter sliderViewAdapter;
private List<PImage> pImageList;
private FirebaseAuth mAuth;
private DatabaseReference reference;
private String product_attribute_option;
private static ProgressDialog progressDialog;


private SingleProductViewModel singleProductViewModel;
private CartModel cart;

private String price, offer_price, sku, title, description, att, p_image;
private int p_id, quantt;



@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_product);

    {
        product_id = getIntent().getStringExtra("id");
        product_slug = getIntent().getStringExtra("slug");
        product_image = getIntent().getStringExtra("image");
        product_title = getIntent().getStringExtra("title");
    }

    initializeViews();
    singleProductViewModel = new ViewModelProvider(this).get(SingleProductViewModel.class);
        /*singleProductViewModel.getProduct(Integer.parseInt(product_id)).observe(this, new Observer<ProductsModel>() {
            @Override
            public void onChanged(ProductsModel productsModel) {
                 price = productsModel.getProductPrice();
                 p_id = productsModel.getProductId();
                 offer_price = productsModel.getProductOfferPrice();
                 sku = productsModel.getProductSku();
                 quantt = productsModel.getProductQuantity();
                 title = productsModel.getProductTitle();
                 description = productsModel.getProductDescription();
                 att = productsModel.getProductAttributeOptions();
                 p_image = productsModel.getProductImage();
                loadProductData();
            }
        });*/

    loadProductData();
       /* progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);*/

        /*toolbar = findViewById(R.id.single_product_toolbar);
        imageView = findViewById(R.id.single_product_image_view);
        singleImageSlider = findViewById(R.id.single_product_image_slider);
        productTitle = findViewById(R.id.single_product_title);
        productStock = findViewById(R.id.single_product_stock);
        addToWishlist = findViewById(R.id.single_product_wishlist);
        addedToWishlist = findViewById(R.id.single_product_wishlisted);
        productDescription = findViewById(R.id.single_product_description);
        productSku = findViewById(R.id.single_product_sku);
        productQuantity = findViewById(R.id.single_product_quantity);
        productQuantityAdd = findViewById(R.id.quantity_add);
        productQuantityRemove = findViewById(R.id.quantity_sub);
        productPrice = findViewById(R.id.single_product_price);
        productOfferPrice = findViewById(R.id.single_product_offer_price);*/

    singleImageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    singleImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
    attributeOptionSpinner.setEditable(false);
    //loadProductData();

    //list.remove(0);
    //String st = list.toString();
    //List<String> arrayList = Arrays.asList(st.split(","));
    //list = Arrays.asList(st.split(","));
    //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
    //attributeOptionSpinner.setEditable(false);
    //attributeOptionSpinner.setAdapter(adapter);


    loadProductImage();

    //progressDialog.dismiss();

    //Picasso.get().load(image).into(imageView);
       /* textView.setText(title);
        productDescription.setText(description);
        String st = "Available: "+quantity;
        productStock.setText(st);
        productSku.setText(sku);
        toolbar.setTitle(title);*/
    //toolbar.setTitle(slug);


    //toolbar.setTitle(slug);
    //textView.setText(productsList.get(0).getTitle());
    addedToWishlist.setOnClickListener(this);
    addToWishlist.setOnClickListener(this);
    addToCartBtn.setOnClickListener(this);
    productQuantityAdd.setOnClickListener(this);
    productQuantityRemove.setOnClickListener(this);
    backBtn.setOnClickListener(this);
    //ShowMoreDescription.setOnClickListener(this);

}



private void initializeViews() {
    //progressDialog.show();
    //ShowMoreDescription = findViewById(R.id.show_more);
    //ShowMoreDescription.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_more_24,0);
    //webView = findViewById(R.id.web_view);
    backBtn = findViewById(R.id.single_product_back_btn);
    toolbarTitle = findViewById(R.id.single_product_toolbar_title);
    //imageView = findViewById(R.id.single_product_image_view);
    singleImageSlider = findViewById(R.id.single_product_image_slider);
    productTitle = findViewById(R.id.single_product_title);
    productStock = findViewById(R.id.single_product_stock);
    addToWishlist = findViewById(R.id.single_product_wishlist);
    addedToWishlist = findViewById(R.id.single_product_wishlisted);
    productDescription = findViewById(R.id.single_product_description);
    productSku = findViewById(R.id.single_product_sku);
    productQuantity = findViewById(R.id.single_product_quantity);
    productQuantityAdd = findViewById(R.id.quantity_add);
    productQuantityRemove = findViewById(R.id.quantity_sub);
    productPrice = findViewById(R.id.single_product_price);
    productOfferPrice = findViewById(R.id.single_product_offer_price);
    addToCartBtn = findViewById(R.id.add_to_cart_btn);
    //attributeOptionSpinner = findViewById(R.id.attribute_option_spinner);
    attributeOptionSpinner = findViewById(R.id.edit_spinner);
    //firebase instances
    mAuth = FirebaseAuth.getInstance();
    reference = FirebaseDatabase.getInstance().getReference().child("users");


}



private void loadProductImage() {

    int pid = Integer.parseInt(product_id);
    ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
    Call<List<PImage>> call = arabianPureApi.getProductImages(pid);
    call.enqueue(new Callback<List<PImage>>() {
        @Override
        public void onResponse(Call<List<PImage>> call, Response<List<PImage>> response) {

            if (response.isSuccessful()) {
                pImageList = response.body();
                if (pImageList != null) {
                    sliderViewAdapter = new ImageAdapter(pImageList);
                    singleImageSlider.setSliderAdapter(sliderViewAdapter);
                }
            }
        }



        @Override
        public void onFailure(Call<List<PImage>> call, Throwable t) {

        }
    });


}



private void loadProductData() {


    ProductsModel productsModel = singleProductViewModel.getProduct(Integer.parseInt(product_id));

    price = productsModel.getProductPrice();
    p_id = productsModel.getProductId();
    offer_price = productsModel.getProductOfferPrice();
    sku = productsModel.getProductSku();
    quantt = productsModel.getProductQuantity();
    title = productsModel.getProductTitle();
    description = productsModel.getProductDescription();
    att = productsModel.getProductAttributeOptions();
    p_image = productsModel.getProductImage();

    if (title != null) {
        toolbarTitle.setText(title);
        productTitle.setText(title);
    }

    String st;
    if (quantt != 0) {
        st = "Available: " + quantt;
    } else {
        st = "Out-of-stock";
    }
    productStock.setText(st);


    if (sku != null) {
        sku = "SKU: " + sku;
        productSku.setText(sku);
    } else productSku.setVisibility(View.GONE);

    if (att != null) {
        Matcher m = Pattern.compile("(\".+?\")").matcher(att);
        while (m.find())
            list.add(Objects.requireNonNull(m.group(1)).replace("\"", "").replace(" ", ""));
        list.remove(0);
        att = String.valueOf(list);
        spinnerSelect(att);
    } else attributeOptionSpinner.setVisibility(View.GONE);


    if (description != null) {

        if (description.indexOf('/') > 2)
            description = description.substring(0, (description.indexOf('/') - 1));
        description = description.replaceAll("<.*?>", "");
        productDescription.setText(description);
    } else {
        productDescription.setText(R.string.no_description);
    }

    if (price == null) {
        productPrice.setVisibility(View.GONE);
        productOfferPrice.setVisibility(View.GONE);



            /*price = "৳"+price;
            offer_price = "৳"+offer_price;
            productOfferPrice.setVisibility(View.VISIBLE);
            productPrice.setText(price);
            productOfferPrice.setText(offer_price);
            productPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);*/
    } else {
        if (offer_price == null) {
            productOfferPrice.setVisibility(View.GONE);
            productPrice.setVisibility(View.VISIBLE);
            cart.setProductPrice(price);
            String p = "৳" + price;
            productPrice.setText(p);
        } else {
            String p = "৳" + offer_price;
            cart.setProductPrice(offer_price);
            productPrice.setText(p);
            p = "৳" + price;
            productOfferPrice.setText(p);
            productOfferPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
            /*String p;
            if (price==null)
                p="0";
            else p=price;
            price = "৳"+p;
            productPrice.setText(price);*/
    }

        /*ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<Products> call = arabianPureApi.getSingleProduct(product_slug);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(SingleProductActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }else
                {


                    productsList = response.body();
                    //textView.setText(productsList.get(productsList.size()-1).getTitle());
                    //getSupportActionBar().setTitle(productsList.size());
                    //toolbar.setTitle(productsList.size());

                    //int id = Integer.parseInt(response.body().getId());

                    String price = productsList.getPrice();

                    String offer_price = productsList.getOffer_price();
                    String sku = "SKU: "+productsList.getSku();
                    String st = "Available: "+productsList.getQuantity();
                    String title = productsList.getTitle();
                    String description = productsList.getDescription();
                    String ShortDescription;
                    if (description!=null) {
                        ShortDescription = description.substring(0, (description.indexOf('/') - 1));
                        ShortDescription = ShortDescription.replaceAll("<.*?>", "");
                        productDescription.setText(ShortDescription);
                        webView.loadDataWithBaseURL(null, description, "text/html", "utf-8", null);
                    }else {
                        productDescription.setText(R.string.no_description);
                        ShowMoreDescription.setVisibility(View.GONE);
                    }
                    //description = description.replaceAll("<\\br>","\n").replaceAll("<.*?>", "").replaceAll("\\*[&nbsp;]\\*", "\t");
                    String att = productsList.getAttribute_options();

                    if (att!=null) {
                        Matcher m = Pattern.compile("(\".+?\")").matcher(att);
                        while (m.find())
                            list.add(m.group(1).replace("\"", "").replace(" ", ""));
                        list.remove(0);
                        att = String.valueOf(list);
                        spinnerSelect(att);
                    }
                    //list =  Arrays.asList(att.split(","));





                    if (offer_price!=null)
                    {
                        price = "৳"+price;
                        offer_price = "৳"+offer_price;
                        productOfferPrice.setVisibility(View.VISIBLE);
                        productPrice.setText(price);
                        productOfferPrice.setText(offer_price);
                        productPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }else
                    {
                        String p;
                        if (price==null)
                            p="0";
                        else p=price;
                        price = "৳"+p;
                        productPrice.setText(price);
                    }

                    product_attribute_option = productsList.getAttribute_options();
                    //productPrice.setText(productsList.getPrice());
                    toolbarTitle.setText(title);
                    productTitle.setText(title);
                    productStock.setText(st);
                    productSku.setText(sku);

                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });*/
}



private void spinnerSelect(String att) {

    List<String> arrayList = Arrays.asList(att.split(","));
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
    attributeOptionSpinner.setEditable(false);
    attributeOptionSpinner.setAdapter(adapter);
}



private void goToHome() {

    Intent intent = new Intent(SingleProductActivity.this, MainActivity.class);
    intent.putExtra("from", "singleproduct");
    startActivity(intent);
    finish();
}



@Override
public void onClick(View v) {

    if (v.getId() == R.id.single_product_wishlisted) {
        addToWishlist.setVisibility(View.VISIBLE);
        addedToWishlist.setVisibility(View.GONE);
    } else if (v.getId() == R.id.single_product_wishlist) {
        addToWishlist.setVisibility(View.GONE);
        addedToWishlist.setVisibility(View.VISIBLE);
    } else if (v.getId() == R.id.quantity_add) {
        ++PQuantity;
        productQuantity.setText(String.valueOf(PQuantity));
    } else if (v.getId() == R.id.quantity_sub) {
        if (PQuantity > 1) {
            --PQuantity;
            productQuantity.setText(String.valueOf(PQuantity));
        }
    } else if (v.getId() == R.id.add_to_cart_btn) {
        CartModel cart = new CartModel(0, p_id, Integer.valueOf(productQuantity.getText().toString()),

                price, att, p_image, product_title);
        //cart.setProductPrice();
        //cart.setProductQuantity();
        String att_option = attributeOptionSpinner.getText().toString()
                                    .replace("[", "").replace("]", "");
        cart.setAttributesOption(att_option);

        // addToCart();
        singleProductViewModel.addProductToCart(cart);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    } else if (v.getId() == R.id.single_product_back_btn) {
        onBackPressed();
    }
        /*else if (v.getId()==R.id.show_more)
        {
            String s = ShowMoreDescription.getText().toString();
            if (s.equals("Show More")) {
                webView.setVisibility(View.VISIBLE);
                productDescription.setVisibility(View.GONE);
                ShowMoreDescription.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_less_24,0);
                ShowMoreDescription.setText(R.string.show_less);
            }else
            {
                webView.setVisibility(View.GONE);
                productDescription.setVisibility(View.VISIBLE);
                ShowMoreDescription.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_more_24,0);
                ShowMoreDescription.setText(R.string.show_more);
            }
        }*/
}



private void addToCart() {


    singleProductViewModel.addProductToCart(cart);

    if (mAuth.getCurrentUser() != null) {
        String uId = mAuth.getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cart").child(uId).child(product_id);
        reference.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    //Toast.makeText(SingleProductActivity.this, "in snapshot", Toast.LENGTH_SHORT).show();
                    String id = snapshot.child("id").getValue().toString();
                    String quantity = productQuantity.getText().toString();
                    String price = productPrice.getText().toString();
                    price = price.replace("৳", "");
                    String att_option = attributeOptionSpinner.getText().toString().replace("[", "").replace("]", "");
                    att_option = att_option.replace("[", "").replace("]", "");

                    Cart cart = new Cart(id, product_id, quantity, price, att_option, product_image, product_title);

                    ref.setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                                Toast.makeText(SingleProductActivity.this, "product added to cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                        /*ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
                        Call<Cart> call = arabianPureApi.addToCartProduct(cart);
                        call.enqueue(new Callback<Cart>() {
                            @Override
                            public void onResponse(Call<Cart> call, Response<Cart> response)
                            {
                                if (!response.isSuccessful())
                                {
                                    Toast.makeText(SingleProductActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                }
                                else if(response.code()==201)
                                    Toast.makeText(SingleProductActivity.this, "response successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Cart> call, Throwable t) {

                            }
                        });*/
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } else Toast.makeText(this, "user not exist", Toast.LENGTH_SHORT).show();

}




}