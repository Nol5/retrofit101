package com.automate.retrofit101;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import api.getProfileAPI;
import model.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstName;
    private Response<Profile> response1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (TextView) findViewById(R.id.firstName);
        Button btnGetName = (Button) findViewById(R.id.btnGetName);


        btnGetName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Profile profile = new Profile();
        String API = "http://localhost:8882";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getProfileAPI service = retrofit.create(getProfileAPI.class);

        Call<Profile> call = service.getProfile(profile);
        call.toString();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                response1 = response;
                Profile Profile = response.body();
                firstName.setText(Profile.getName());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }
}
