package com.automate.retrofit101;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.automate.manager.ProfileAPI;
import com.automate.manager.ProfileManager;
import com.automate.model.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstName;
    private ProfileAPI service;

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

        Call<Profile> call = ProfileManager.getProfileApi().getProfile();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

//                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                    firstName.setText(jsonObject.get("message").toString());

                firstName.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                firstName.setText("You fail");
            }
        });


    }
}
