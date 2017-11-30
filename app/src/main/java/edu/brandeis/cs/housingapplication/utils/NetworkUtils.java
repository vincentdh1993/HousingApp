package edu.brandeis.cs.housingapplication.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by dvili on 11/29/2017.
 */

public class NetworkUtils {
    private static final String BASE_URL = "http://10.0.2.2:8080";

    //Generic http post to any url. Generic in the sense that it works with any POST to the backend
    //that this app is using.
    public static String doHttpPost(URL url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(body).addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        Log.d("REQUEST", request.toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().string();
    }

    //Since all the HTTP GETs involve query params and no request body,
    //this method is short and sweet. I
    public static String doHttpGet(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Log.d("REQUEST", request.toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().string();
    }


    public static URL createUrl(String... path) {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        for (int i = 0; i < path.length; i++) {
            builder.appendPath(path[i]);
        }
        return returnURL(builder.build());
    }

    public static URL createUrl(String path, Map<String, String> params) {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon().appendPath(path);
        for (String key : params.keySet()) {
            builder.appendQueryParameter(key, params.get(key));
        }
        return returnURL(builder.build());
    }

    //There's a metric asston of code duplication here. Does anyone give a shit?
    public static URL createUrl(Map<String, String> params, String... path) {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        for (int i = 0; i < path.length; i++) {
            builder.appendPath(path[i]);
        }
        for (String key : params.keySet()) {
            builder.appendQueryParameter(key, params.get(key));
        }
        return returnURL(builder.build());
    }

    private static URL returnURL(Uri uri) {
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null; //you're fucked
    }

    //Helper for debugging
    private void printBody(Request request) {
        try {
            Request copy = request.newBuilder().build();
            Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            Log.d("REQUEST BODY", buffer.readUtf8());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
