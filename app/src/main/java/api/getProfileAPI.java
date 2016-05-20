package api;

import model.Profile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface getProfileAPI {

    @POST("/hello-world")
    Call<Profile> getProfile(@Body Profile profile);
}

