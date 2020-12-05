package com.youthfireit.dailydeals.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.youthfireit.dailydeals.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton logoutBtn;
    private FirebaseAuth mAuth;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        if (mAuth.getCurrentUser().getUid()==null)
            logoutBtn.setVisibility(View.GONE);
        else logoutBtn.setVisibility(View.VISIBLE);
        logoutBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    private void initializeViews()
    {
        //initialize views
        logoutBtn = findViewById(R.id.logout_btn);
        backBtn = findViewById(R.id.settings_back_btn);
        //initialize firebase instances
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.logout_btn)
        {
            mAuth.signOut();
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            intent.putExtra("from","settings");
            startActivity(intent);
            finish();
        }else if (v.getId()==R.id.settings_back_btn)
        {
            onBackPressed();
        }
    }
}