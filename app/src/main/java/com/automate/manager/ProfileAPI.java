package com.automate.manager;

import com.automate.model.Player;
import com.automate.model.Profile;
import com.automate.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProfileAPI {

    @GET("player")
    Call<Player> getPlayer();

    @POST("profile")
    Call<Object> getProfile(@Body User user);
}
