package com.automate.manager;

import com.automate.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileAPI {

    @GET("profile")
    Call<Profile> getProfile();
}
