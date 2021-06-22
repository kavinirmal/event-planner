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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    TextView login,name,email,phone,password,confirm_password;
    private String sname,semail,sphone,spassword,sconfirm_password;
    Button btn_sign_up;

    private  String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private  String MobilePattern = "^\\+[0-9]{10,13}$";
    private String PasswordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";

    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;

    ConstraintLayout constraintLayout;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        phone = findViewById(R.id.et_phone);
        password = findViewById(R.id.et_password);
        confirm_password = findViewById(R.id.et_confirm_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        constraintLayout = findViewById(R.id.cv_layout);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(SignUpActivity.this, FragmentActivity.class);
            startActivity(intent);
            finish();
        }

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        login = findViewById(R.id.tv_login);
        String text = "<font>Already have an account?</font> <font color=#187bcd><b>LOGIN</b></font>";
        login.setText(Html.fromHtml(text));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validate() {
        sname = name.getText().toString().trim();
        semail = email.getText().toString().trim();
        sphone = phone.getText().toString().trim();
        spassword = password.getText().toString().trim();
        sconfirm_password = confirm_password.getText().toString().trim();

        if (!(sname.isEmpty())){
            if (!(semail.isEmpty())){
                        if (!(sphone.isEmpty())){
                                if (!(spassword.isEmpty())){
                                    if (!(sconfirm_password.isEmpty())){
                                        if (semail.matches(EmailPattern)){
                                            if (sphone.matches(MobilePattern)){
                                                if (spassword.matches(PasswordPattern)){
                                                    if (spassword.equals(sconfirm_password)){
                                                        SaveUser(sname,semail,sphone,spassword);
                                                    }else {
                                                        confirm_password.setError("Password does not match");
                                                    }
                                                }else {
                                                    password.setError("Password must have 8 digit");
                                                }
                                            }else {
                                                phone.setError("Mobile No format invalid! (+94123456789)");
                                            }
                                        }else {
                                            email.setError("Enter valid Email!");
                                        }

                                    }else {
                                        confirm_password.setError("Confirm password is Empty!");
                                    }
                                }else {
                                    password.setError("Password is Empty!");
                                }
                        }else {
                            phone.setError("Phone no is Empty!");
                        }
            }else {
                email.setError("Email is Empty!");
            }

        }else {
            name.setError("Name is Empty!");
        }
    }

    private void SaveUser(String sname, String semail, String sphone, String spassword) {
        snackbar = Snackbar.make(constraintLayout,"Sign in please wait...",Snackbar.LENGTH_LONG)
                .setAction("Dissmiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.setActionTextColor(Color.CYAN);
        snackbar.show();

        mAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   userID = mAuth.getCurrentUser().getUid();
                   DocumentReference documentReference = fstore.collection("user").document(userID);
                   Map<String, Object> users = new HashMap<>();
                   users.put("userName",sname);
                   users.put("userEmail",semail);
                   users.put("userPhone",sphone);
                   documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(SignUpActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                           startActivity(intent);
                       }
                   });
               }else {
                   Toast.makeText(SignUpActivity.this,"Error occurred !",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}