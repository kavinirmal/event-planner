package com.example.hakunamatata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.hakunamatata.R;
import com.example.hakunamatata.api.ApiClient;
import com.example.hakunamatata.dto.LoginRequestDTO;
import com.example.hakunamatata.dto.LoginResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView sign_up,email,password,forget_password;
    Button btn_login;
    private String semail,spassword;

    private  String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String PasswordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";


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
                        LogInToHome();
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

    private void LogInToHome() {
        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(intent);
//        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
//        loginRequestDTO.setEmail(email.getText().toString());
//        loginRequestDTO.setPassword(password.getText().toString());
//
//        Call<LoginResponseDTO> loginResponseDTOCall = ApiClient.getUserService().logUser(loginRequestDTO);
//        loginResponseDTOCall.enqueue(new Callback<LoginResponseDTO>() {
//            @Override
//            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
//
//            }
//        });
    }
}