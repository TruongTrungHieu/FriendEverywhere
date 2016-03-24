package com.fithou.friendeverywhere.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Locale;

public class CountryObject implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("dial_code")
    private String dial_code;

    public CountryObject(String name, String dial_code) {
        this.name = name;
        this.dial_code = dial_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDial_code() {
        return dial_code;
    }

    public void setDial_code(String dial_code) {
        this.dial_code = dial_code;
    }

    public String toString() {
        return  dial_code + "  " + name;
    }

    public static CountryObject parseJsonObject(JSONObject json) {
        Gson gson = new GsonBuilder().create();
        CountryObject countryObject = gson.fromJson(json.toString(), CountryObject.class);
        return countryObject;
    }

}
