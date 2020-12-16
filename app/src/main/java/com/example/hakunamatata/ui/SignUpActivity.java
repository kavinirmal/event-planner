package com.example.hakunamatata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakunamatata.R;
import com.example.hakunamatata.api.ApiClient;
import com.example.hakunamatata.dto.UserRequestDTO;
import com.example.hakunamatata.dto.UserResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    TextView login,name,email,nic,date,phone,address,password,confirm_password;
    private String sname,semail,snic,sdate,sphone,saddress,spassword,sconfirm_password;
    Button btn_sign_up;

    private  String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private  String MobilePattern = "^\\+[0-9]{10,13}$";
    private String PasswordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        nic = findViewById(R.id.et_nic);
        date = findViewById(R.id.et_birth);
        phone = findViewById(R.id.et_phone);
        address = findViewById(R.id.et_address);
        password = findViewById(R.id.et_password);
        confirm_password = findViewById(R.id.et_confirm_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);

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
        snic = nic.getText().toString().trim();
        sdate = date.getText().toString().trim();
        sphone = phone.getText().toString().trim();
        saddress = address.getText().toString().trim();
        spassword = password.getText().toString().trim();
        sconfirm_password = confirm_password.getText().toString().trim();

        if (!(sname.isEmpty())){
            if (!(semail.isEmpty())){
                if (!(snic.isEmpty())){
                    if (!(sdate.isEmpty())){
                        if (!(sphone.isEmpty())){
                            if (!(saddress.isEmpty())){
                                if (!(spassword.isEmpty())){
                                    if (!(sconfirm_password.isEmpty())){
                                        if (semail.matches(EmailPattern)){
                                            if (sphone.matches(MobilePattern)){
                                                if (spassword.matches(PasswordPattern)){
                                                    if (spassword.equals(sconfirm_password)){
                                                        SaveUser();
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
                                address.setError("Address is Empty!");
                            }
                        }else {
                            phone.setError("Phone no is Empty!");
                        }

                    }else {
                        date.setError("Birthday is Empty!");
                    }
                }else {
                    nic.setError("NIC is Empty!");
                }

            }else {
                email.setError("Email is Empty!");
            }

        }else {
            name.setError("Name is Empty!");
        }
    }

    private void SaveUser() {
//        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
//        startActivity(intent);
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName(name.getText().toString());
        userRequestDTO.setEmail(email.getText().toString());
        userRequestDTO.setNic(nic.getText().toString());
        userRequestDTO.setDob(date.getText().toString());
        userRequestDTO.setPhone(phone.getText().toString());
        userRequestDTO.setAddress(address.getText().toString());
        userRequestDTO.setPassword(password.getText().toString());

        Call<UserResponseDTO> call = ApiClient.getUserService().saveUser(userRequestDTO);
        call.enqueue(new Callback<UserResponseDTO>() {
            @Override
            public void onResponse(Call<UserResponseDTO> call, Response<UserResponseDTO> response) {
                if (response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"saved successfully",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(SignUpActivity.this,"saved not successfully",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<UserResponseDTO> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,"saved not successfully"+t,Toast.LENGTH_LONG).show();
                System.out.println("error==========="+t);

            }
        });

    }
}