package com.nghiatbp_toanum.musicapp.Service;

public class APIService {
    private static String base_url = "https://musicapp16722.000webhostapp.com/Server/";

    public static Dataservice getService() {
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
