package com.example.ecf2;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeolocationService extends Thread {
    private JSONObject json;
    private String strURL = "https://api.airvisual.com/v2/city?city=%s&state=%s&country=%s&key=%s";
    private Double latitude;
    private Double longitude;
    private String apiKey = "KevBjAL4MwxGG9kxq";

    public GeolocationService() {
    }

    public static GeolocationService builder() {
        return new GeolocationService();
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public void run() {
        HttpURLConnection cnt = null;
        try {
//            URL url = new URL(String.format(strURL, city, state, country, apiKey));

//            cnt = (HttpURLConnection) url.openConnection();
            cnt.setRequestMethod("GET");
            cnt.setDoInput(true);


            InputStream in = cnt.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String response = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            this.json = new JSONObject(response);
            reader.close();
            in.close();

        } catch (Exception ex) {
        } finally {
            cnt.disconnect();
        }
    }
}
