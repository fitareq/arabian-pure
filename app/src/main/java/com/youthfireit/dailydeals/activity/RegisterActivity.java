package com.youthfireit.dailydeals.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.model.Register;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText editTextName, editTextEmail, editTextPhone, editTextPassword, editTextConfirmPassword;
    private Toolbar registerToolbar;
    private Button registerButton;
    private TextView textViewLogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private static ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        initialize();

        setSupportActionBar(registerToolbar);
        registerToolbar.setNavigationOnClickListener(this);
        textViewLogin.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private void registerUser()
    {
        progressDialog.show();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirm_password = editTextConfirmPassword.getText().toString();
        String phone_number = editTextPhone.getText().toString();
        if (TextUtils.isEmpty(name))
        {
            editTextName.setError("Enter name");
        }else if (TextUtils.isEmpty(email))
        {
            editTextEmail.setError("Enter email");
        }else if (TextUtils.isEmpty(password))
        {
            editTextPassword.setError("Enter password");
        }else if (TextUtils.isEmpty(confirm_password))
        {
            editTextConfirmPassword.setError("Confirm password");
        }else if (TextUtils.isEmpty(phone_number))
        {
            editTextPhone.setError("Enter phone");
        }else if (!password.equals(confirm_password))
        {
            editTextPassword.setError("Password didn't match");
        }
        else
        {
            Register register = new Register(name, email, password, phone_number);
            ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
            Call<Register> call = arabianPureApi.registerUser(register);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse( Call<Register> call,  Response<Register> response) {
                    if (!response.isSuccessful()) {
                        customToastShow(response.message(), 0);
                        progressDialog.dismiss();
                    }
                    else if (response.code()==201)
                    {
                        String n = phone_number+"@site.com";

                        mAuth.createUserWithEmailAndPassword(n,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    customToastShow("Registration Successful", 1);
                                    goToLoginPage();
                                    progressDialog.dismiss();
                                }
                                else {
                                    customToastShow("Internal Server Error!!", 0);
                                    progressDialog.dismiss();
                                }
                            }
                        });


                    }
                }

                @Override
                public void onFailure( Call<Register> call, Throwable t)
                {

                }
            });
        }
    }


    private void goToHomePage()
    {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.putExtra("from","register");
        startActivity(intent);
        finish();
    }


    private void goToLoginPage()
    {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void initialize()
    {
        //views
        editTextName = findViewById(R.id.register_name);
        editTextEmail = findViewById(R.id.register_email);
        editTextPhone = findViewById(R.id.register_phone_number);
        editTextPassword = findViewById(R.id.register_password);
        editTextConfirmPassword = findViewById(R.id.register_confirm_password);
        registerToolbar = findViewById(R.id.register_toolbar);
        registerButton = findViewById(R.id.register_button);
        textViewLogin = findViewById(R.id.register_login_textview);

        //progress bar
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");
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
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onClick(View v)
    {

        if (v.getId()==R.id.register_toolbar)
        {
            onBackPressed();
        }else if (v.getId()==R.id.register_login_textview)
        {
            goToLoginPage();
        }else if (v.getId()==R.id.register_login_textview)
        {
            registerUser();
        }

    }
}