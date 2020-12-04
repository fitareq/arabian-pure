package com.youthfireit.dailydeals.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.youthfireit.dailydeals.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton logoutBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        logoutBtn.setOnClickListener(this);
    }

    private void initializeViews()
    {
        //initialize views
        logoutBtn = findViewById(R.id.logout_btn);
        //initialize firebase instances
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.logout_btn)
        {
            mAuth.signOut();
        }
    }
}