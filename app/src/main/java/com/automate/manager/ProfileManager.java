package com.automate.manager;

public class ProfileManager {

    public static ProfileAPI getProfileApi(){
        return HTTPManager.getInstance().getRetrofit().create(ProfileAPI.class);
    }
}
