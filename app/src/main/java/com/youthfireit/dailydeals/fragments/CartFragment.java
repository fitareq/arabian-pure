package com.youthfireit.dailydeals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.AndroidCartAdapter;
import com.youthfireit.dailydeals.firebase.CartAdapter;
import com.youthfireit.dailydeals.local_room.room_model.CartModel;
import com.youthfireit.dailydeals.model.Cart;
import com.youthfireit.dailydeals.viewmodels.CartViewModel;

import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment
{

    private View rootView;
    private RecyclerView cartRecyclerView;
    //private CartAdapter adapter;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private String uId;
    private final Context context;
    private final CartFragmentListener cartFragmentListener;
    private AndroidCartAdapter adapter;
    private CartViewModel viewModel;
    private RecyclerView.LayoutManager cartManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.cart_fragment,container,false);
        initializeViews();
        viewModel.getAllCarts().observe(getViewLifecycleOwner(), new Observer<List<CartModel>>() {
            @Override
            public void onChanged(List<CartModel> cartModels) {
                adapter.setCategories(cartModels);
                cartRecyclerView.setAdapter(adapter);
            }
        });

        /*mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null) {

            cartRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                    .setQuery(reference, Cart.class)
                    .build();
            adapter = new CartAdapter(options);
            cartRecyclerView.setAdapter(adapter);
        }*/



        return rootView;
    }

    private void initializeViews()
    {
        cartRecyclerView = rootView.findViewById(R.id.cart_recyclerview);
        cartManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(cartManager);
        adapter = new AndroidCartAdapter();
        viewModel = new ViewModelProvider(getActivity()).get(CartViewModel.class);

        //uId = mAuth.getUid();
        //reference = FirebaseDatabase.getInstance().getReference().child("cart").child(uId);
    }

    public CartFragment(Context context, CartFragmentListener cartFragmentListener)
    {
        this.context = context;
        this.cartFragmentListener = cartFragmentListener;
    }

    /*@Override
    public void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser()!=null) {
            adapter.startListening();
        }else {
            cartFragmentListener.goToAccount();
        }
    }*/

    /*@Override
    public void onStop() {
        super.onStop();
        if (mAuth.getCurrentUser()!=null)
            adapter.stopListening();
    }*/

    public interface CartFragmentListener
    {
        void goToAccount();
    }
}
