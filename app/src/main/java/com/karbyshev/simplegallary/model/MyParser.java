package com.karbyshev.simplegallary.model;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.karbyshev.simplegallary.adapter.MyItem;
import com.karbyshev.simplegallary.presenter.IPresenter;
import com.karbyshev.simplegallary.utils.AppController;
import com.karbyshev.simplegallary.view.IMainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyParser implements IPresenter {

    private int mPage = 2;
    private String FEED_URL;
    private String creatorName;
    private String imageUrl;


    private IMainView mainView;

    public MyParser(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void parseJason(Context context, final ArrayList<MyItem> myItemArrayList, String search) {
        FEED_URL = "https://pixabay.com/api/?key=8334968-4779a336d920b0785293ef347&q=" + search + "&image_type=photo&page=" + mPage + "&per_page=51&pretty=true";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, FEED_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseMyJSON(response, myItemArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void perfomText(String newSearch) {
        if (TextUtils.isEmpty(newSearch)){
            mainView.editTextIsEmpty();
        } else {
            mainView.isOk();
        }
    }

    private void parseMyJSON(JSONObject response, ArrayList<MyItem> myItemArrayList){
        try {
            JSONArray jsonArray = response.getJSONArray("hits");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                creatorName = object.getString("user");
                imageUrl = object.getString("webformatURL");

                myItemArrayList.add(new MyItem(imageUrl, creatorName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
