package com.dreams.hakunamatata.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dreams.hakunamatata.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView sign_up,email,password,forget_password;
    Button btn_login;
    private String semail,spassword;

    private  String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String PasswordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";
    FirebaseAuth mAuth;

    Snackbar snackbar;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sign_up = findViewById(R.id.tv_sign_up);
        email = findViewById(R.id.tv_email);
        password = findViewById(R.id.tv_password);
        btn_login = findViewById(R.id.button);
        forget_password = findViewById(R.id.textView3);
        constraintLayout = findViewById(R.id.cv_layout);
        mAuth = FirebaseAuth.getInstance();
        String text = "<font>Don't have an account?</font> <font color=#187bcd><b>SIGN UP</b></font>";
        sign_up.setText(Html.fromHtml(text));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validate() {
        semail = email.getText().toString().trim();
        spassword = password.getText().toString().trim();
        if (!(semail.isEmpty())){
            if (!(spassword.isEmpty())){
                if (semail.matches(EmailPattern)){
                    if (spassword.matches(PasswordPattern)){
                        logNormal(semail,spassword);
                    }else {
                        password.setError("Password must have 8 digit");
                    }
                }else {
                    email.setError("Email invalid!");
                }

            }else {
                password.setError("Password id Empty!");
            }
        }else{
            email.setError("Email is Empty!");
        }

    }

    private void logNormal(String semail, String spassword) {
        snackbar = Snackbar.make(constraintLayout,"Login please wait...",Snackbar.LENGTH_LONG)
                .setAction("Dissmiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.setActionTextColor(Color.CYAN);
        snackbar.show();

        mAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Error occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}