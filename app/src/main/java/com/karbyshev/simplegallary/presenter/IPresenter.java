package com.karbyshev.simplegallary.presenter;

import android.content.Context;

import com.karbyshev.simplegallary.adapter.MyItem;

import java.util.ArrayList;

public interface IPresenter {

    void parseJason(String search);

    void perfomText(String newSearch);
}
