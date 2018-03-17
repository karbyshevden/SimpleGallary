package com.karbyshev.simplegallary.view;

import com.karbyshev.simplegallary.adapter.MyItem;

import java.util.ArrayList;
import java.util.List;

public interface IMainView {

    void onItemClick(int position);

    void editTextIsEmpty();

    void showData(List<MyItem> list);
}
