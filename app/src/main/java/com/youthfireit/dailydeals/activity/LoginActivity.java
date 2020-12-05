package com.youthfireit.dailydeals.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.firebase.SaveUserInfo;
import com.youthfireit.dailydeals.model.Login;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    /*http://arabianpure.com/public/api/products*/
    private TextInputEditText userPhone, userPassword;
    private TextInputLayout phone,password;
    private Button btnLogin;
    private TextView forgotPassword, register;
    private CheckBox rememberMe;
    private Toolbar loginToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("user");

        //initializing view
        initialize();
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
        progressDialog.show();
        String number = userPhone.getText().toString();
        String pass = userPassword.getText().toString();
        if (TextUtils.isEmpty(number))
        {
            userPhone.setError("Enter a Valid Phone");
            progressDialog.dismiss();
        }else if (TextUtils.isEmpty(pass))
        {
            userPassword.setError("Enter a Valid Password");
            progressDialog.dismiss();
        }else if (pass.length()<8)
        {
            userPassword.setError("Password Must be At Least 8 Digits");
            progressDialog.dismiss();
        }else
        {
            Login login = new Login(number,pass);
            ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
            Call<Login> call = arabianPureApi.loginUser(login);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call,  Response<Login> response)
                {
                    if (response.code()==201)
                    {
                        //customToastShow("Login Successful "+response.body().getLoggedinId(),1);
                        mAuth.createUserWithEmailAndPassword(number+"@site.com",pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                mAuth.signInWithEmailAndPassword(number+"@site.com",pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        saveNewUserToFirebaseDatabase(response.body().getLoggedinId());
                                    }
                                });

                            }
                        });

                        //goToHomePage(response.body().getLoggedinId());
                    }else
                        {
                            customToastShow("Phone Number or Password isn't Valid", 0);
                        }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t)
                {
                    customToastShow("Phone Number or Password isn't Valid", 0);
                }
            });
        }
    }

    private void saveNewUserToFirebaseDatabase(String id)
    {
        //customToastShow("saveNewUserToFirebaseDatabase",1);
        int uId = Integer.parseInt(id);
        String userId = mAuth.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        reference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (!snapshot.exists())
                {
                    //customToastShow("true",1);
                    ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
                    Call<SaveUserInfo> call = arabianPureApi.getUserInfo(uId);
                    call.enqueue(new Callback<SaveUserInfo>() {
                        @Override
                        public void onResponse(Call<SaveUserInfo> call, Response<SaveUserInfo> response) {
                            if (response.isSuccessful())
                            {

                                ref.setValue(response.body());
                                goToHomePage();
                                customToastShow("Login Successful",1);
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<SaveUserInfo> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void goToHomePage()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("from","login");
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

    private void initialize()

    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        userPhone = findViewById(R.id.login_phone_number);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.login_forgot_password);
        register = findViewById(R.id.login_register_textview);
        loginToolbar = findViewById(R.id.login_toolbar);
        phone = findViewById(R.id.phone_textfield);
        password = findViewById(R.id.password_textfield);

    }
}