package com.automate.retrofit101;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.automate.manager.ProfileAPI;
import com.automate.manager.ProfileManager;
import com.automate.model.Player;
import com.automate.model.Profile;
import com.automate.model.Profile2;
import com.automate.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstName;
    private ProfileAPI service;

    private TextView name;
    private TextView age;
    private TextView citizen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (TextView) findViewById(R.id.firstName);
        Button btnGetPlayer = (Button) findViewById(R.id.btnGetPlayer);
        Button btnGetProfile = (Button) findViewById(R.id.btnGetProfile);

        name =(TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        citizen = (TextView) findViewById(R.id.citizen);

        btnGetPlayer.setOnClickListener(this);
        btnGetProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetPlayer) {
            Call<Player> call = ProfileManager.getProfileApi().getPlayer();
            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    Player player = response.body();
                    String userStr = "";
                    if(player != null) {
                        for(int i = 0 ; i < player.getUsers().size(); i++) {
                            userStr += player.getUsers().get(i).getName() + "\n";
                        }
                        firstName.setText(userStr);
                    }
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {

                }
            });
        }


        if (v.getId() == R.id.btnGetProfile) {
            User user = new User();
            user.setId("1");
            user.setName("ying");
            Call<Object> call = ProfileManager.getProfileApi().getProfile(user);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Gson gson = new Gson();
                    JsonObject jb = gson.toJsonTree(response.body()).getAsJsonObject();

                    String nameStr = "";
                    String ageStr = "";
                    String citizenIdStr = "";

                    if (response.code() == 200) {
                        Profile r = gson.fromJson(jb, Profile.class);
                        nameStr = r.getName();
                        ageStr = r.getAge();
                        citizenIdStr = r.getCitizenId();
                    } else if (response.code() == 241) {
                        Profile2 profile2 = gson.fromJson(jb, Profile2.class);
                        nameStr = profile2.getName2();

                    }
                    name.setText(nameStr);
                    age.setText(ageStr);
                    citizen.setText(citizenIdStr);

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    firstName.setText("You fail");
                }
            });
        }
    }
}
