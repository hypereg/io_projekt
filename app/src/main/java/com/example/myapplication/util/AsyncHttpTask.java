package com.example.myapplication.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class AsyncHttpTask {
    private static final String BASE_URL = "http://10.0.0.127:7070/api";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public <T> T get(String endpoint, Type type) throws IOException {
        Request req = new Request.Builder()
                .url(BASE_URL + endpoint)
                .build();
        try (Response res = client.newCall(req).execute()) {
            if (!res.isSuccessful()) throw new IOException("HTTP " + res.code());
            return gson.fromJson(res.body().string(), type);
        }
    }

    public boolean post(String endpoint, Object data) throws IOException {
        String json = gson.toJson(data);
        RequestBody body = RequestBody.create(json, JSON);
        Request req = new Request.Builder()
                .url(BASE_URL + endpoint)
                .post(body)
                .build();
        try (Response res = client.newCall(req).execute()) {
            return res.isSuccessful();
        }
    }

    public boolean delete(String endpoint) throws IOException {
        Request req = new Request.Builder()
                .url(BASE_URL + endpoint)
                .delete()
                .build();
        try (Response res = client.newCall(req).execute()) {
            return res.isSuccessful();
        }
    }
}
