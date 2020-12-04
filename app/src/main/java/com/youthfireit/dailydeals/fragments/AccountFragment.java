package com.youthfireit.dailydeals.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youthfireit.dailydeals.activity.LoginActivity;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.activity.SettingsActivity;
import com.youthfireit.dailydeals.adapter.ProductsAdapter;
import com.youthfireit.dailydeals.model.Products;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment implements View.OnClickListener
{
    private TextView myOrder, myWishList, userName;
    private Button loginOrRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private View rootView;
    private ImageView settingsBtn;
    private RecyclerView accountProductsRecyclerView;
    private RecyclerView.Adapter accountProductsAdapter;
    private RecyclerView.LayoutManager accountProductsManager;
    private List<Products> accountProducts;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.account_fragment, container, false);
        initializeViews();
        checkIfUserLoggedIn();
        loadProductsData();
        loginOrRegister.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        //userSignOut.setOnClickListener(this);
        return rootView;
    }

    private void loadProductsData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse( Call<List<Products>> call,  Response<List<Products>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                accountProducts = response.body();
                if (accountProducts!=null)
                {
                    accountProductsAdapter = new ProductsAdapter(accountProducts, getContext(), (ProductsAdapter.productClickListener) getContext()
                    );
                    accountProductsRecyclerView.setAdapter(accountProductsAdapter);
                }

            }

            @Override
            public void onFailure( Call<List<Products>> call,  Throwable t)
            {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkIfUserLoggedIn()
    {
        if (mAuth.getCurrentUser()!=null)
        {
            reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if (snapshot.exists())
                    {
                        userName.setText(snapshot.child("name").getValue().toString());
                        loginOrRegister.setVisibility(View.GONE);
                        //userSignOut.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else {

            userName.setText(R.string.wellcome_msg);
            //userSignOut.setVisibility(View.GONE);
            loginOrRegister.setVisibility(View.VISIBLE);
        }
    }

    private void initializeViews()
    {
        //initialize views
        loginOrRegister = rootView.findViewById(R.id.accountfragment_login_button);
        myOrder = rootView.findViewById(R.id.user_order);
        myWishList = rootView.findViewById(R.id.user_wishlist);
        accountProductsRecyclerView = rootView.findViewById(R.id.account_product_recyclerview);

        //userSignOut = rootView.findViewById(R.id.user_sign_out);
        userName = rootView.findViewById(R.id.user_name);
        settingsBtn = rootView.findViewById(R.id.settings_btn);

        //initialize firebase objects
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("users");

        //initialize products recycler view
        accountProductsRecyclerView.setHasFixedSize(true);
        accountProductsRecyclerView.setNestedScrollingEnabled(false);
        accountProductsManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        accountProductsRecyclerView.setLayoutManager(accountProductsManager);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.accountfragment_login_button)
        {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        }else if (v.getId()==R.id.settings_btn)
        {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        }
        /*else if (v.getId()==R.id.user_sign_out)
        {
            mAuth.signOut();
            checkIfUserLoggedIn();
        }*/
    }
}
