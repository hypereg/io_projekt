package com.example.myapplication.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class AsyncHttpTask {
    private static final String backendConstantURL = "10.0.0.127:7070/api"; // tymczasowy wireguard ip
    public String get(String endpoint) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(backendConstantURL + endpoint)
                .build();

        //todo: dodac serializacje dla danychz mysql
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public boolean post(String endpoint, RequestBody body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(backendConstantURL + endpoint)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }
}