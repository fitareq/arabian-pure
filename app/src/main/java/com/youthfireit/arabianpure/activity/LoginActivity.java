package com.youthfireit.arabianpure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.Login;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    /*http://arabianpure.com/public/api/products*/
    private TextInputEditText userPhone, userPassword;
    private Button btnLogin;
    private TextView forgotPassword, register;
    private CheckBox rememberMe;
    private Toolbar loginToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //initializing view
        initilize();
        setSupportActionBar(loginToolbar);

        loginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });


        register.setOnClickListener(v -> goToRegisterPage());


        forgotPassword.setOnClickListener(v -> goToResetPasswordPage());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin()
    {
        String number = userPhone.getText().toString();
        String pass = userPassword.getText().toString();
        if (TextUtils.isEmpty(number))
        {
            Toast.makeText(this, "enter a phone number", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
        }else
        {
            Login login = new Login(number,pass);
            ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
            Call<Login> call = arabianPureApi.loginUser(login);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response)
                {
                    if (!response.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                    /*if (response.body().getStatus_code().equals("201"))
                    {*/
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        goToHomePage();

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        }
    }

    private void goToHomePage()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void goToResetPasswordPage()
    {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
        finish();
    }


    private void goToRegisterPage()
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void initilize()

    {
        userPhone = findViewById(R.id.login_phone_number);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.login_forgot_password);
        register = findViewById(R.id.login_register_textview);
        loginToolbar = findViewById(R.id.login_toolbar);

    }
}