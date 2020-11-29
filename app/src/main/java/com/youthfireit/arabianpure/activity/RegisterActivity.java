package com.youthfireit.arabianpure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.Register;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText editTextName, editTextEmail, editTextPhone, editTextPassword, editTextConfirmPassword;
    private Toolbar registerToolbar;
    private Button registerButton;
    private TextView textViewLogin;
   /* private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");*/

        initialize();

        setSupportActionBar(registerToolbar);

        registerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToHomePage();
            }
        });


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToLoginPage();
            }
        });



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser()
    {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirm_password = editTextConfirmPassword.getText().toString();
        String phone_number = editTextPhone.getText().toString();
        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "enter a name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "enter a email", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(confirm_password))
        {
            Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(phone_number))
        {
            Toast.makeText(this, "enter a phone number", Toast.LENGTH_SHORT).show();
        }else if (!password.equals(confirm_password))
        {
            Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                    else if (response.code()==201)
                    {
                        /*String n = phone_number+"@site.com";
                        Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_SHORT).show();
                        mAuth.createUserWithEmailAndPassword(n,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                reference.child(mAuth.getUid()).setValue(register);
                            }
                        });*/

                        goToLoginPage();
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
        editTextName = findViewById(R.id.register_name);
        editTextEmail = findViewById(R.id.register_email);
        editTextPhone = findViewById(R.id.register_phone_number);
        editTextPassword = findViewById(R.id.register_password);
        editTextConfirmPassword = findViewById(R.id.register_confirm_password);
        registerToolbar = findViewById(R.id.register_toolbar);
        registerButton = findViewById(R.id.register_button);
        textViewLogin = findViewById(R.id.register_login_textview);
    }
}