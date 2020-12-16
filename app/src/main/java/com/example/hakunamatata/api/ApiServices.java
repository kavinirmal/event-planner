package com.example.hakunamatata.api;

import com.example.hakunamatata.dto.LoginRequestDTO;
import com.example.hakunamatata.dto.LoginResponseDTO;
import com.example.hakunamatata.dto.UserRequestDTO;
import com.example.hakunamatata.dto.UserResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("app-user/login")
    Call<LoginResponseDTO> logUser(@Body LoginRequestDTO loginRequestDTO);

    @POST("new")
    Call<UserResponseDTO> saveUser(@Body UserRequestDTO userRequestDTO);
}
