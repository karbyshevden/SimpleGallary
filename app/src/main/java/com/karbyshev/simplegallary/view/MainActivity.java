package com.karbyshev.simplegallary.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karbyshev.simplegallary.R;
import com.karbyshev.simplegallary.adapter.MyAdapter;
import com.karbyshev.simplegallary.adapter.MyItem;
import com.karbyshev.simplegallary.model.MyParser;
import com.karbyshev.simplegallary.presenter.IPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView, SwipeRefreshLayout.OnRefreshListener {
    public static final String MY_CREARTOR = "creator";
    public static final String MY_IMAGE_URL = "imageUrl";
    private EditText mEditText;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MyAdapter myAdapter;
    private IPresenter iPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private String newSearch = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.my_editText);
        mButton = (Button) findViewById(R.id.my_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newSearch = mEditText.getText().toString();
                iPresenter.perfomText(newSearch);
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.my_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        iPresenter = new MyParser(MainActivity.this);
        iPresenter.parseJason(newSearch);

        myAdapter = new MyAdapter(MainActivity.this);
        myAdapter.setOnItemClickListener(MainActivity.this);
        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(int position, ArrayList<MyItem> list) {
        Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
        MyItem myItem = list.get(position);

        intent.putExtra(MY_IMAGE_URL, myItem.getmImageUrl());
        intent.putExtra(MY_CREARTOR, myItem.getmCreatorName());
        startActivity(intent);
    }

    @Override
    public void editTextIsEmpty() {
        Toast.makeText(MainActivity.this, "Please, enter some word!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<MyItem> list) {
        myAdapter.setNewData(list);
        mEditText.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        myAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
