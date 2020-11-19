package com.youthfireit.arabianpure.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.youthfireit.arabianpure.activity.LoginActivity;
import com.youthfireit.arabianpure.R;

public class AccountFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.account_fragment, container, false);
        Button login = rootView.findViewById(R.id.accountfragment_login_button);
        login.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.accountfragment_login_button)
        {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
