package model;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
