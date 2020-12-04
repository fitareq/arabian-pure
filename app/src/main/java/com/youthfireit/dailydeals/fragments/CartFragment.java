package com.youthfireit.dailydeals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.firebase.CartAdapter;
import com.youthfireit.dailydeals.model.Cart;

public class CartFragment extends Fragment
{

    private View rootView;
    private RecyclerView cartRecyclerView;
    CartAdapter adapter;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    String uId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.cart_fragment,container,false);

        initializeViews();





        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(reference,Cart.class)
                .build();
        adapter = new CartAdapter(options);
        cartRecyclerView.setAdapter(adapter);



        return rootView;
    }

    private void initializeViews()
    {
        cartRecyclerView = rootView.findViewById(R.id.cart_recyclerview);
        mAuth = FirebaseAuth.getInstance();
        uId = mAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("cart").child(uId);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
