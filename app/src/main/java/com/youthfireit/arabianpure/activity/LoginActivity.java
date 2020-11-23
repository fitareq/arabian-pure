package com.youthfireit.arabianpure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.Login;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import org.jetbrains.annotations.NotNull;

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
            //Toast.makeText(this, "enter a phone number", Toast.LENGTH_SHORT).show();
            //customToastShow("Enter a Valid Phone",0);
            userPhone.setError("Enter a Valid Phone");
        }else if (TextUtils.isEmpty(pass))
        {
            //Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
            //customToastShow("Enter a Valid Password",0);
            userPassword.setError("Enter a Valid Password");
        }else if (pass.length()<8)
        {
            userPassword.setError("Password Must be At Least 8 Digits");
            //customToastShow("Password Must be At Least 8 Digits",0);
        }else
        {
            Login login = new Login(number,pass);
            ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
            Call<Login> call = arabianPureApi.loginUser(login);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(@NotNull Call<Login> call, @NotNull Response<Login> response)
                {
                    if (!response.isSuccessful())
                    {
                        customToastShow("Phone Number or Password isn't Valid",0);
                        //Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                    }else
                    /*if (response.body().getStatus_code().equals("201"))
                    {*/ {
                        //Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        customToastShow("Login Successful",1);
                        goToHomePage();
                    }

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        }
    }

    private void customToastShow(String message, int a)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
        TextView textView = layout.findViewById(R.id.custom_toast_text_view);
        ImageView imageView = layout.findViewById(R.id.custom_toast_image_view);
        if (a==0)
        {
            imageView.setImageResource(R.drawable.ic_baseline_clear_24);
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_baseline_done_24);
        }
        textView.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
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